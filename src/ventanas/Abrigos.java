package ventanas;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import BD.BD;
import clases.Abrigo;
import clases.MainArticulos;
import clases.CarritoGlobal;

public class Abrigos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior;
    private JPanel pSur, pCentro;
    private JButton btnVolver;
    private JScrollPane scrollPane;
    private BD bd;

    public Abrigos(JFrame va, BD bd) {
        super("Abrigos");
        vActual = this;
        vAnterior = va;
        this.bd = bd;

        // --- Configuración básica de la ventana ---
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // --- Panel inferior (botón Volver) ---
        pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        btnVolver = new JButton("Volver");
        btnVolver.setFocusPainted(false);
        btnVolver.setBackground(new Color(230, 230, 230));
        btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pSur.add(btnVolver);

        // --- Panel central con GridLayout ---
        pCentro = new JPanel(new GridLayout(4, 3, 10, 10));
        pCentro.setBackground(Color.WHITE);
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        List<Abrigo> listaAbrigos = this.bd.cargarAbrigos();

        // --- Crear panel para cada abrigo ---
        for (Abrigo abrigo : listaAbrigos) {
            JPanel pArticulo = new JPanel(new BorderLayout());
            ImageIcon icon = new ImageIcon("img/chaquetas/abrigo" + abrigo.getCodigo() + ".png");

            // Obtener dimensiones originales
            int originalWidth = icon.getIconWidth();
            int originalHeight = icon.getIconHeight();
            
            // Escalado proporcional solo si son demasiado grandes
            int maxWidth = 350;
            int maxHeight = 525;
            double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
            if (scale > 1) scale = 1;

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

            JButton btnAniadirCarrito = new JButton("AÑADIR AL CARRITO");
            btnAniadirCarrito.addActionListener(e -> {
                CarritoGlobal.addArticulo(abrigo);
                JOptionPane.showMessageDialog(null, abrigo.getDesc() + " añadido al carrito.");
            });


            pArticulo.add(lbl, BorderLayout.CENTER);
            pArticulo.add(btnAniadirCarrito, BorderLayout.SOUTH);

            pCentro.add(pArticulo);
        }

        // --- Scroll con panel central ---
        scrollPane = new JScrollPane(pCentro);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.WHITE);

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
