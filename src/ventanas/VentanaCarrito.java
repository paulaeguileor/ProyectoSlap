package ventanas;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import clases.Articulo;
import clases.CarritoGlobal;

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

    public VentanaCarrito(JFrame vAnterior) {
        super("Carrito");
        this.vActual = this;
        this.vAnterior = vAnterior;

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
                JOptionPane.showMessageDialog(null, "Selecciona un artículo para eliminar");
            }
        });

        // Botón vaciar
        btnVaciar.addActionListener(e -> {
            CarritoGlobal.clear();
            modelo.fireTableDataChanged();
            actualizarTotales();
        });

        // Botón finalizar
        btnFinalizar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "¡Tu compra se ha realizado con éxito!");
            CarritoGlobal.clear();
            modelo.fireTableDataChanged();
            actualizarTotales();
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
