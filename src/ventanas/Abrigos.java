package ventanas;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import BD.BD;
import clases.Abrigo;
import clases.CarritoGlobal;

public class Abrigos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame vActual, vAnterior;
    private JPanel pSur, pCentro, pNorte;
    private JButton btnVolver;
    private JScrollPane scrollPane;
    private BD bd;

    public Abrigos(JFrame va, BD bd) {
        super("Abrigos");
        vActual = this;
        vAnterior = va;
        this.bd = bd;

        // --- Configuración básica de la ventana ---
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        ToolTipManager.sharedInstance().setInitialDelay(200);
        ToolTipManager.sharedInstance().setDismissDelay(8000);

        // --- Panel superior (título) ---
        pNorte = new JPanel();
        pNorte.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("ABRIGOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(30, 30, 30));

        pNorte.add(lblTitulo);

        // --- Panel inferior (botón Volver) ---
        pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        btnVolver = new JButton("VOLVER");

        btnVolver.setToolTipText(
                "Vuelve al menú principal de la tienda");

        pSur.add(btnVolver);

        // --- Panel central con GridLayout ---
        pCentro = new JPanel(new GridLayout(4, 3, 10, 10));
        pCentro.setBackground(Color.WHITE);
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        List<Abrigo> listaAbrigos = this.bd.cargarAbrigos();

        // --- Crear panel para cada abrigo ---
        for (Abrigo abrigo : listaAbrigos) {
            JPanel pArticulo = new JPanel(new BorderLayout());
            pArticulo.setBackground(Color.WHITE);

            ImageIcon icon = new ImageIcon("img/chaquetas/abrigo" + abrigo.getCodigo() + ".png");

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

            // Tooltip en la imagen con la descripción
            lbl.setToolTipText(
                    abrigo.getDesc());

            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(Color.WHITE);
            lbl.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(235, 235, 235)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            lbl.setPreferredSize(new Dimension(maxWidth + 20, maxHeight + 20));

            JLabel lblNombre = new JLabel(abrigo.getDesc());
            lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblPrecio = new JLabel(String.format("%.2f €", abrigo.getPrecio()));
            lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton btnAniadirCarrito = new JButton("AÑADIR AL CARRITO");
            btnAniadirCarrito.setAlignmentX(Component.CENTER_ALIGNMENT);

            btnAniadirCarrito.setToolTipText(
                    "Añade este producto al carrito");

            btnAniadirCarrito.addActionListener(e -> {
                CarritoGlobal.addArticulo(abrigo);
                JOptionPane.showMessageDialog(null, abrigo.getDesc() + " añadido al carrito.");
            });

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

        // --- Botón volver ---
        btnVolver.addActionListener(e -> {
            vActual.dispose();
            vAnterior.setVisible(true);
        });

        setVisible(true);
    }
}
