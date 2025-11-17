 package ventanas;

import java.awt.*;
import javax.swing.*;

public class Abrigos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior; 
    private JPanel pSur, pCentro;
    private JButton btnVolver;
    private JScrollPane scrollPane;

    public Abrigos(JFrame va) {
        super("Abrigos");
        vActual = this;
        vAnterior = va;

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
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // margen lateral

        // --- Cargar imágenes manteniendo proporción ---
        for (int i = 1; i <= 12; i++) {
            ImageIcon icon = new ImageIcon("img/chaquetas/chaqueta" + i + ".png");

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

            pCentro.add(lbl);
        }

        // --- Scroll con panel central ---
        scrollPane = new JScrollPane(pCentro);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // desplazamiento suave
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.WHITE);

        // --- Añadir a la ventana ---
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
