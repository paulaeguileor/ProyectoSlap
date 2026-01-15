package ventanas;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import BD.BD;
import clases.Articulo;
import clases.CarritoGlobal;
import clases.Sesion;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class VentanaCarrito extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior;
    private JButton btnVolver, btnEliminar, btnVaciar, btnFinalizar;
    private JPanel pNorte, pCentro, pSur, pTotal;
    private JLabel lblTotal, lblTotalCalculado, lblNumArt, lblContadorArticulos, lblSeparador;
    private JTable tabla;
    private ModeloVentanaCarrito modelo;

    private int fila = -1;

    private BD bd;

    public VentanaCarrito(JFrame vAnterior, BD bd) {
        super("Carrito");
        this.vActual = this;
        this.vAnterior = vAnterior;
        this.bd = bd;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Modelo y tabla apuntando al carrito global
        List<Articulo> articulos = CarritoGlobal.getArticulos();
        modelo = new ModeloVentanaCarrito(articulos);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // Paneles
        pNorte = new JPanel(new BorderLayout());
        pCentro = new JPanel(new BorderLayout());
        pSur = new JPanel(new GridLayout(1, 5, 10, 10));
        pTotal = new JPanel(new GridLayout(1, 3));

        // Botones
        btnVolver = new JButton("VOLVER");
        btnEliminar = new JButton("ELIMINAR ARTÍCULO");
        btnVaciar = new JButton("VACIAR CESTA");
        btnFinalizar = new JButton("FINALIZAR COMPRA");

        // Labels
        lblTotal = new JLabel("Total calculado:");
        lblTotalCalculado = new JLabel("0 €");
        lblNumArt = new JLabel("Número de artículos:");
        lblContadorArticulos = new JLabel("0");
        lblSeparador = new JLabel("|");
        
        //tooltips
        btnVolver.setToolTipText(
        		"Vuelve a la tienda");
        btnEliminar.setToolTipText(
        		"Elimina el artículo seleccionado del carrito");
        btnVaciar.setToolTipText(
        		"Vacía completamente la cesta");
        btnFinalizar.setToolTipText(
        		"Finaliza la compra y gira la ruleta de descuentos");

        lblTotalCalculado.setToolTipText(
        		"Precio total del carrito");
        lblContadorArticulos.setToolTipText(
        		"Número de artículos que hay en el carrito");
        tabla.setToolTipText(
        		"Lista de productos que tienes en el carrito");

        // Añadir paneles
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pCentro, BorderLayout.CENTER);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        pCentro.add(scroll, BorderLayout.CENTER);
        pTotal.add(lblTotal);
        pTotal.add(lblTotalCalculado);
        pTotal.add(btnFinalizar);
        pCentro.add(pTotal, BorderLayout.SOUTH);

        pSur.add(btnEliminar);
        pSur.add(lblSeparador);
        pSur.add(lblNumArt);
        pSur.add(lblContadorArticulos);
        pSur.add(btnVaciar);
        pSur.add(btnVolver);

        actualizarTotales();

        // Botón volver
        btnVolver.addActionListener(e -> {
            vAnterior.setVisible(true);
            vActual.dispose();
        });

        // Botón eliminar
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                CarritoGlobal.removeArticulo(filaSeleccionada);
                modelo.fireTableDataChanged();
                actualizarTotales();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un artículo para eliminar");
            }
        });

        // Botón vaciar
        btnVaciar.addActionListener(e -> {
            CarritoGlobal.clear();
            modelo.fireTableDataChanged();
            actualizarTotales();

            if (Sesion.usuarioActual != null) {
                bd.vaciarCarrito(Sesion.usuarioActual);
            }
        });

        // Botón finalizar COMPRA
        btnFinalizar.addActionListener(e -> {
            if (Sesion.usuarioActual == null) {
                JOptionPane.showMessageDialog(this, "Debes iniciar sesión para finalizar la compra");
                return;
            }

            if (CarritoGlobal.getArticulos().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío");
                return;
            }

            new ThreadDescuentos(() -> finalizarCompra());
        });

        // Render de tabla
        tabla.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel lbl = new JLabel(value.toString());
                lbl.setOpaque(true);
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setBackground(fila == row ? Color.LIGHT_GRAY : Color.WHITE);
                return lbl;
            }
        });

        // Hover sobre filas
        tabla.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                fila = tabla.rowAtPoint(e.getPoint());
                tabla.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {}
        });

        setVisible(true);
    }

    private void actualizarTotales() {
        double total = 0;
        List<Articulo> articulos = CarritoGlobal.getArticulos();
        int contador = articulos.size();
        for (Articulo a : articulos) {
            total += a.getPrecio();
        }
        lblTotalCalculado.setText(String.format("%.2f €", total));
        lblContadorArticulos.setText(String.valueOf(contador));
    }

    private void finalizarCompra() {
        String usuario = Sesion.usuarioActual;
        List<Articulo> listaArticulos = CarritoGlobal.getArticulos();

        bd.vaciarCarrito(usuario);

        for (Articulo a : listaArticulos) {
            bd.agregarAlCarrito(
                    usuario,
                    a.getClass().getSimpleName(),
                    a.getCodigo(),
                    a.getDesc(),
                    1,
                    a.getPrecio()
            );
        }

        int idPedido = bd.crearPedidoDesdeCarrito(usuario);

        if (idPedido != -1) {
            double totalFinal = CarritoGlobal.getTotalConDescuento();

            JOptionPane.showMessageDialog(
                    this,
                    "¡Compra realizada!\n" +
                    "Nº de pedido: " + idPedido + "\n" +
                    "Total final: " + String.format("%.2f €", totalFinal)
            );

            CarritoGlobal.clear();
            modelo.fireTableDataChanged();
            actualizarTotales();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido realizar la compra");
        }
    }
}
