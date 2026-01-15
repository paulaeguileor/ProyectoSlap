package ventanas;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import clases.CarritoGlobal;

public class ThreadDescuentos extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblSlot1, lblSlot2, lblSlot3;
    private JLabel lblInfo;
    private JButton btnAccion;

    private boolean girando;
    private boolean terminado;

    private Random random;

    // Rutas reales de imágenes
    private String[] imagenes = {
            "img/chaquetas/abrigo501.png", "img/chaquetas/abrigo502.png", "img/chaquetas/abrigo503.png",
            "img/camisas/camisa201.png", "img/camisas/camisa202.png", "img/camisas/camisa203.png",
            "img/bolsos/bolso701.png", "img/bolsos/bolso702.png", "img/bolsos/bolso703.png",
            "img/pantalones/pantalon301.png", "img/pantalones/pantalon302.png", "img/pantalones/pantalon303.png",
            "img/vestidos/vestido601.png", "img/vestidos/vestido602.png", "img/vestidos/vestido603.png",
            "img/jerseis/jersey401.png", "img/jerseis/jersey402.png", "img/jerseis/jersey403.png"
    };

    private String resultado1, resultado2, resultado3;

    public ThreadDescuentos(Runnable alTerminar) {
        random = new Random();
        girando = false;
        terminado = false;

        configurarVentana();
        crearComponentes();
        colocarComponentes();
        configurarEventos(alTerminar);

        setVisible(true);
    }

    // ================= CONFIGURACIÓN UI =================

    private void configurarVentana() {
        setTitle("Ruleta de Descuento");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void crearComponentes() {
        lblSlot1 = new JLabel();
        lblSlot2 = new JLabel();
        lblSlot3 = new JLabel();

        configurarSlot(lblSlot1);
        configurarSlot(lblSlot2);
        configurarSlot(lblSlot3);

        lblInfo = new JLabel("Pulsa GIRAR para intentar ganar un 10% de descuento", JLabel.CENTER);
        lblInfo.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnAccion = new JButton("GIRAR");
    }

    private void configurarSlot(JLabel lbl) {
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setVerticalAlignment(JLabel.CENTER);
        lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    private void colocarComponentes() {
        JPanel pCentro = new JPanel(new GridLayout(1, 3, 15, 15));
        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pCentro.add(lblSlot1);
        pCentro.add(lblSlot2);
        pCentro.add(lblSlot3);

        add(lblInfo, BorderLayout.NORTH);
        add(pCentro, BorderLayout.CENTER);
        add(btnAccion, BorderLayout.SOUTH);
    }

    private void configurarEventos(Runnable alTerminar) {
        btnAccion.addActionListener(e -> {
            if (!girando && !terminado) {
                iniciarGiro();
            } else if (terminado) {
                dispose();
                alTerminar.run();
            }
        });
    }

    // ================= LÓGICA DE GIRO =================

    private void iniciarGiro() {
        girando = true;
        btnAccion.setEnabled(false);
        lblInfo.setText("Girando... ");

        Thread hilo = new Thread(() -> ejecutarGiro());
        hilo.start();
    }

    private void ejecutarGiro() {
        long tiempoFinal = System.currentTimeMillis() + 3000;

        while (System.currentTimeMillis() < tiempoFinal) {
            resultado1 = obtenerImagenAleatoria();
            resultado2 = obtenerImagenAleatoria();
            resultado3 = obtenerImagenAleatoria();

            actualizarSlots(resultado1, resultado2, resultado3);

            dormir(100);
        }

        comprobarResultado();
    }

    private String obtenerImagenAleatoria() {
        return imagenes[random.nextInt(imagenes.length)];
    }

    private void actualizarSlots(String r1, String r2, String r3) {
        SwingUtilities.invokeLater(() -> {
            lblSlot1.setIcon(cargarIcono(r1));
            lblSlot2.setIcon(cargarIcono(r2));
            lblSlot3.setIcon(cargarIcono(r3));
        });
    }

    private void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {}
    }

    // ================= RESULTADO =================

    private void comprobarResultado() {
        boolean premio = resultado1.equals(resultado2) && resultado2.equals(resultado3);

        if (premio) {
            CarritoGlobal.setDescuento(0.10);
        } else {
            CarritoGlobal.setDescuento(0);
        }

        mostrarResultado(premio);
    }

    private void mostrarResultado(boolean premio) {
        SwingUtilities.invokeLater(() -> {
            girando = false;
            terminado = true;
            btnAccion.setEnabled(true);
            btnAccion.setText("ACEPTAR");

            if (premio) {
                lblInfo.setText("¡Has ganado un 10% de descuento!");
            } else {
                lblInfo.setText("¡Vaya! No ha habido premio");
            }
        });
    }

 
    private ImageIcon cargarIcono(String ruta) {
        try {
            ImageIcon icon = new ImageIcon(ruta);   // carga directa desde disco
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + ruta);
            return new ImageIcon();
        }
    }


}
