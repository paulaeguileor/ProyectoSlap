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

        setSize(1200, 600);
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
        btnVolver = new JButton("Volver");
        btnEliminar = new JButton("Eliminar artículo");
        btnVaciar = new JButton("Vaciar cesta");
        btnFinalizar = new JButton("Finalizar compra");

        // Labels
        lblTotal = new JLabel("Total calculado:");
        lblTotalCalculado = new JLabel("0 €");
        lblNumArt = new JLabel("Número de artículos:");
        lblContadorArticulos = new JLabel("0");
        lblSeparador = new JLabel("|");

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

        // Botón finalizar COMPRA (con BD)
        btnFinalizar.addActionListener(e -> {
            // 1) Comprobar login
            if (Sesion.usuarioActual == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debes iniciar sesión para finalizar la compra",
                        "Atención",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String usuario = Sesion.usuarioActual;

            // 2) Obtener artículos del carrito global
            List<Articulo> listaArticulos = CarritoGlobal.getArticulos();
            if (listaArticulos.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "El carrito está vacío",
                        "Atención",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            // 3) Volcar carrito global a la tabla Carrito de la BD
            bd.vaciarCarrito(usuario);  // limpiamos lo anterior por si acaso

            for (Articulo a : listaArticulos) {
                String tipo = a.getClass().getSimpleName();
                int codigo = a.getCodigo();
                String desc = a.getDesc();
                int cantidad = 1;  // ahora mismo siempre 1
                double precio = a.getPrecio();

                bd.agregarAlCarrito(usuario, tipo, codigo, desc, cantidad, precio);
            }

            // 4) Crear pedido desde el carrito BD
            int idPedido = bd.crearPedidoDesdeCarrito(usuario);

            if (idPedido != -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "¡Tu compra se ha realizado con éxito!\nNº de pedido: " + idPedido,
                        "Compra realizada",
                        JOptionPane.INFORMATION_MESSAGE
                );
                CarritoGlobal.clear();
                modelo.fireTableDataChanged();
                actualizarTotales();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se ha podido realizar la compra",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Tabla render
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

        // Hover sobre fila
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

    // Actualiza total y contador
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
}
