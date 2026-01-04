package ventanas;

import javax.swing.*;
import javax.swing.Timer;

import BD.BD;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VentanaInicial extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel pNorte, pCentro, pSur, pBotones, pMenu;
    private JTextField buscar;
    private JLabel logo, footer, lblImagen;
    private JButton btnUsuario, btnCarrito, btnCamisas, btnJerseis, btnAbrigos, btnVestidos, btnPantalones, btnCalzado, btnBolsos;

    private JFrame ventanaActual;

    private Timer timer;
    private int indiceImagen = 1;
    private final int TOTAL_IMAGENES = 4;
    private final int INTERVALO_MS = 1150;

    public VentanaInicial(BD bd) {
        ventanaActual = this;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ----- PANELES -----
        pNorte = new JPanel(new BorderLayout());
        pNorte.setBackground(Color.WHITE);

        pCentro = new JPanel(new GridBagLayout());
        pCentro.setBackground(Color.WHITE);

        pSur = new JPanel();
        pSur.setBackground(Color.LIGHT_GRAY);

        pBotones = new JPanel(new GridLayout(1, 3, 1, 1));
        pBotones.setBackground(Color.WHITE);

        pMenu = new JPanel(new GridLayout(1, 7, 1, 1));
        pMenu.setBackground(Color.WHITE);

        // ----- COMPONENTES -----
        buscar = new JTextField(10);

        btnCamisas = new JButton("Camisas");
        btnCamisas.setBorder(BorderFactory.createEmptyBorder());

        btnJerseis = new JButton("Jerséis");
        btnJerseis.setBorder(BorderFactory.createEmptyBorder());

        btnAbrigos = new JButton("Abrigos");
        btnAbrigos.setBorder(BorderFactory.createEmptyBorder());

        btnVestidos = new JButton("Vestidos");
        btnVestidos.setBorder(BorderFactory.createEmptyBorder());

        btnPantalones = new JButton("Pantalones");
        btnPantalones.setBorder(BorderFactory.createEmptyBorder());

        btnCalzado = new JButton("Calzado");
        btnCalzado.setBorder(BorderFactory.createEmptyBorder());

        btnBolsos = new JButton("Bolsos");
        btnBolsos.setBorder(BorderFactory.createEmptyBorder());

        btnUsuario = new JButton("Mi cuenta");
        btnUsuario.setBorder(BorderFactory.createEmptyBorder());

        btnCarrito = new JButton("Carrito");
        btnCarrito.setBorder(BorderFactory.createEmptyBorder());

        logo = new JLabel("S L A P");
        logo.setFont(new Font("Serif", Font.BOLD, 36));
        logo.setForeground(Color.BLACK);

        footer = new JLabel("© 2025 - Tienda SLAP");

        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        lblImagen.setVerticalAlignment(JLabel.CENTER);

        pCentro.add(lblImagen);

        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pCentro, BorderLayout.CENTER);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        pNorte.add(logo, BorderLayout.WEST);

        pMenu.add(btnCamisas);
        pMenu.add(btnJerseis);
        pMenu.add(btnAbrigos);
        pMenu.add(btnVestidos);
        pMenu.add(btnPantalones);
        pMenu.add(btnCalzado);
        pMenu.add(btnBolsos);
        pNorte.add(pMenu, BorderLayout.CENTER);

        pBotones.add(buscar);
        pBotones.add(btnUsuario);
        pBotones.add(btnCarrito);
        pNorte.add(pBotones, BorderLayout.EAST);

        pSur.add(footer);

        // ----- LISTENERS -----
        btnUsuario.addActionListener(e -> {
            setVisible(false);

            if (!clases.Sesion.haySesion()) {
                new InicioSesion(ventanaActual, bd);
            } else {
                new Usuario(ventanaActual, bd, clases.Sesion.usuarioActual);
            }
        });

        btnCarrito.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new VentanaCarrito(ventanaActual, bd);
        });

        btnCamisas.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Camisas(ventanaActual, bd);
        });

        btnJerseis.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Jerseis(ventanaActual, bd);
        });

        btnAbrigos.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Abrigos(ventanaActual, bd);
        });

        btnVestidos.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Vestidos(ventanaActual, bd);
        });

        btnPantalones.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Pantalones(ventanaActual, bd);
        });

        btnCalzado.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Calzado(ventanaActual, bd);
        });

        btnBolsos.addActionListener(e -> {
            ventanaActual.setVisible(false);
            new Bolsos(ventanaActual, bd);
        });

        iniciarCarrusel();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                refrescarImagenEscalada();
            }
        });

        setVisible(true);
    }

    private void iniciarCarrusel() {
        refrescarImagenEscalada();

        timer = new Timer(INTERVALO_MS, e -> {
            indiceImagen++;
            if (indiceImagen > TOTAL_IMAGENES) indiceImagen = 1;
            refrescarImagenEscalada();
        });

        timer.start();
    }

    private void refrescarImagenEscalada() {
        String ruta = "img/Inicio/VentanaInicial" + indiceImagen + ".png";
        ImageIcon iconOriginal = new ImageIcon(ruta);

        int w = pCentro.getWidth();
        int h = pCentro.getHeight();

        if (w <= 0 || h <= 0) {
            lblImagen.setIcon(iconOriginal);
            return;
        }

        Image imgEscalada = iconOriginal.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(imgEscalada));
    }

}