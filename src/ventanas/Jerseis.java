package ventanas;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import BD.BD;
import clases.CarritoGlobal;
import clases.Jersey;
import clases.MainArticulos;

public class Jerseis extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior; 
    private JPanel pSur, pCentro, pNorte;
    private JButton btnVolver;
    private JScrollPane scrollPane;
    private BD bd;

    public Jerseis(JFrame va, BD bd) {
        super("Jerseis");
        vActual = this;
        vAnterior = va;
        this.bd = bd;

        // --- Configuración básica de la ventana ---
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

     // --- Panel superior (título) ---
        pNorte = new JPanel();
        pNorte.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("JERSÉIS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(30, 30, 30));

        pNorte.add(lblTitulo);
        
        // --- Panel inferior (botón Volver) ---
        pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        btnVolver = new JButton("VOLVER");
        
        btnVolver.setToolTipText(
        		"Volver a la pantalla anterior");
        
        pSur.add(btnVolver);

        // --- Panel central con GridLayout ---
        pCentro = new JPanel(new GridLayout(4, 3, 10, 10));
        pCentro.setBackground(Color.WHITE);
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // margen lateral

        List<Jersey> listaJerseis = this.bd.cargarJerseys();

        // --- Cargar imágenes manteniendo proporción ---
        for (Jersey jersey: listaJerseis) {
        	JPanel pArticulo = new JPanel(new BorderLayout());
            ImageIcon icon = new ImageIcon("img/jerseis/jersey" + jersey.getCodigo() + ".png");

            // Obtener dimensiones originales
            int originalWidth = icon.getIconWidth();
            int originalHeight = icon.getIconHeight();

            // Escalado proporcional solo si son demasiado grandes
            int maxWidth = 350;   // ancho máximo permitido
            int maxHeight = 525;  // alto máximo permitido (2:3)
            double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
            if (scale > 1) scale = 1; // no ampliar imágenes pequeñas

            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            Image img = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel lbl = new JLabel(new ImageIcon(img));

            // Centrado y estilo de “tarjeta”
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(Color.WHITE);
            lbl.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(235, 235, 235)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));


            // Tamaño uniforme del marco de cada imagen
            lbl.setPreferredSize(new Dimension(maxWidth + 20, maxHeight + 20));
            
            lbl.setToolTipText(
            		jersey.getDesc());
            
            // --- etiqueta nombre del jersey ---
            JLabel lblNombre = new JLabel(jersey.getDesc());
            lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

            // --- etiqueta precio del jersey ---
            JLabel lblPrecio = new JLabel(String.format("%.2f €", jersey.getPrecio()));
            lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            
            JButton btnAniadirCarrito = new JButton("AÑADIR AL CARRITO");
            btnAniadirCarrito.addActionListener(e -> {
                CarritoGlobal.addArticulo(jersey);
                JOptionPane.showMessageDialog(null, jersey.getDesc() + " añadido al carrito.");
            });
            
            
            // --- panel con nombre, precio y botón ---
            JPanel pInfo = new JPanel();
            pInfo.setLayout(new BoxLayout(pInfo, BoxLayout.Y_AXIS));
            pInfo.setBackground(Color.WHITE);

            btnAniadirCarrito.setToolTipText(
            		"Añadir " + jersey.getDesc() + " al carrito");
            
            btnAniadirCarrito.setAlignmentX(Component.CENTER_ALIGNMENT);

            pInfo.add(lblNombre);
            pInfo.add(Box.createVerticalStrut(5));
            pInfo.add(lblPrecio);
            pInfo.add(Box.createVerticalStrut(10));
            pInfo.add(btnAniadirCarrito);

            pArticulo.add(lbl, BorderLayout.CENTER);
            pArticulo.add(pInfo, BorderLayout.SOUTH);
            
            pCentro.add(pArticulo);
           
        }

        // --- Scroll con panel central ---
        scrollPane = new JScrollPane(pCentro);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // desplazamiento suave
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.WHITE);

        // --- Añadir a la ventana ---
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        // --- Listener botón Volver ---
        btnVolver.addActionListener(e -> {
            vActual.dispose();
            vAnterior.setVisible(true);
        });

        setVisible(true);
    }
}
