package ventanas;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import BD.BD;
import clases.CarritoGlobal;
import clases.Pantalon;

public class Pantalones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior; 
    private JPanel pSur, pCentro, pNorte;
    private JButton btnVolver;
    private JScrollPane scrollPane;
    private BD bd;

    public Pantalones(JFrame va, BD bd) {
        super("Pantalones");
        vActual = this;
        vAnterior = va;
        this.bd = bd;

        // --- Configuración básica ---
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

     // --- Panel superior (título) ---
        pNorte = new JPanel();
        pNorte.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("PANTALONES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(30, 30, 30));

        pNorte.add(lblTitulo);
        
        // --- Panel inferior ---
        pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        btnVolver = new JButton("VOLVER");
        
        btnVolver.setToolTipText(
        		"Volver a la pantalla anterior");
        
        pSur.add(btnVolver);

        // --- Panel central ---
        pCentro = new JPanel(new GridLayout(4, 3, 10, 10));
        pCentro.setBackground(Color.WHITE);
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        List<Pantalon> listaPantalones = this.bd.cargarPantalones();

        for (Pantalon pantalon : listaPantalones) {
            JPanel pArticulo = new JPanel(new BorderLayout());
            pArticulo.setBackground(Color.WHITE);

            ImageIcon icon = new ImageIcon("img/pantalones/pantalon" + pantalon.getCodigo() + ".png");

            int originalWidth = icon.getIconWidth();
            int originalHeight = icon.getIconHeight();

            int maxWidth = 350;
            int maxHeight = 525;
            double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
            if (scale > 1) scale = 1;

            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            Image img = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel lbl = new JLabel(new ImageIcon(img));

            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(Color.WHITE);
            lbl.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(235, 235, 235)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            lbl.setPreferredSize(new Dimension(maxWidth + 20, maxHeight + 20));

            lbl.setToolTipText(
            		pantalon.getDesc());
            
            // --- Nombre y precio ---
            JLabel lblNombre = new JLabel(pantalon.getDesc());
            lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblPrecio = new JLabel(String.format("%.2f €", pantalon.getPrecio()));
            lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);

            // --- Botón ---
            JButton btnAniadirCarrito = new JButton("AÑADIR AL CARRITO");
            btnAniadirCarrito.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAniadirCarrito.addActionListener(e -> {
                CarritoGlobal.addArticulo(pantalon);
                JOptionPane.showMessageDialog(null, pantalon.getDesc() + " añadido al carrito.");
            });
            
            btnAniadirCarrito.setToolTipText(
            		"Añadir " + pantalon.getDesc() + " al carrito");

            // --- Panel inferior del artículo ---
            JPanel pInfo = new JPanel();
            pInfo.setLayout(new BoxLayout(pInfo, BoxLayout.Y_AXIS));
            pInfo.setBackground(Color.WHITE);
            pInfo.add(lblNombre);
            pInfo.add(Box.createVerticalStrut(5));
            pInfo.add(lblPrecio);
            pInfo.add(Box.createVerticalStrut(10));
            pInfo.add(btnAniadirCarrito);

            pArticulo.add(lbl, BorderLayout.CENTER);
            pArticulo.add(pInfo, BorderLayout.SOUTH);

            pCentro.add(pArticulo);
        }

        // --- Scroll ---
        scrollPane = new JScrollPane(pCentro);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.WHITE);

        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> {
            vActual.dispose();
            vAnterior.setVisible(true);
        });

        setVisible(true);
    }
}
