package ventanas;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import BD.BD;
import clases.Bolso;
import clases.CarritoGlobal;

public class Bolsos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior; 
    private JPanel pSur, pCentro, pNorte;
    private JButton btnVolver;
    private JScrollPane scrollPane;
    private BD bd;

    public Bolsos(JFrame va, BD bd) {
        super("Bolsos");
        vActual = this;
        vAnterior = va;
        this.bd = bd;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // ===== NORTE (título) =====
        pNorte = new JPanel();
        pNorte.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("BOLSOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(30, 30, 30));
        pNorte.add(lblTitulo);

        // ===== SUR (volver) =====
        pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        btnVolver = new JButton("VOLVER");
        
        btnVolver.setToolTipText(
        		"Volver a la pantalla anterior");
        
        pSur.add(btnVolver);

        // ===== CENTRO =====
        pCentro = new JPanel(new GridLayout(4, 3, 10, 10));
        pCentro.setBackground(Color.WHITE);
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        List<Bolso> listaBolsos = this.bd.cargarBolsos();

        for (Bolso bolso : listaBolsos) {
            JPanel pArticulo = new JPanel(new BorderLayout());
            pArticulo.setBackground(Color.WHITE);

            ImageIcon icon = new ImageIcon("img/bolsos/bolso" + bolso.getCodigo() + ".png");

            int originalWidth = icon.getIconWidth();
            int originalHeight = icon.getIconHeight();

            int maxWidth = 350;
            int maxHeight = 525;
            double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
            if (scale > 1) scale = 1;

            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            Image img = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel lblImagen = new JLabel(new ImageIcon(img));

            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
            lblImagen.setVerticalAlignment(SwingConstants.CENTER);
            lblImagen.setOpaque(true);
            lblImagen.setBackground(Color.WHITE);
            lblImagen.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(235, 235, 235)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            lblImagen.setPreferredSize(new Dimension(maxWidth + 20, maxHeight + 20));

            
            lblImagen.setToolTipText(
            		bolso.getDesc());

            // ===== Nombre =====
            JLabel lblNombre = new JLabel(bolso.getDesc());
            lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

            // ===== Precio =====
            JLabel lblPrecio = new JLabel(String.format("%.2f €", bolso.getPrecio()));
            lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);

            // ===== Botón =====
            JButton btnAniadirCarrito = new JButton("AÑADIR AL CARRITO");
            btnAniadirCarrito.setToolTipText("Añadir " + bolso.getDesc() + " al carrito");
            btnAniadirCarrito.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAniadirCarrito.addActionListener(e -> {
                CarritoGlobal.addArticulo(bolso);
                JOptionPane.showMessageDialog(null, bolso.getDesc() + " añadido al carrito.");
            });
            
            btnAniadirCarrito.setToolTipText(
                    "Añadir " + bolso.getDesc() + " al carrito");


            // ===== Panel info =====
            JPanel pInfo = new JPanel();
            pInfo.setLayout(new BoxLayout(pInfo, BoxLayout.Y_AXIS));
            pInfo.setBackground(Color.WHITE);
            pInfo.add(lblNombre);
            pInfo.add(Box.createVerticalStrut(5));
            pInfo.add(lblPrecio);
            pInfo.add(Box.createVerticalStrut(10));
            pInfo.add(btnAniadirCarrito);

            pArticulo.add(lblImagen, BorderLayout.CENTER);
            pArticulo.add(pInfo, BorderLayout.SOUTH);

            pCentro.add(pArticulo);
        }

        // ===== Scroll =====
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
