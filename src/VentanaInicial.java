import javax.swing.*;

import ropa.Abrigos;
import ropa.Bolsos;
import ropa.Calzado;
import ropa.Camisas;
import ropa.Jerseis;
import ropa.Pantalones;
import ropa.Vestidos;

import java.awt.*;

public class VentanaInicial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel pNorte, pCentro, pSur, pBotones, pMenu;
    private JTextField buscar; 
    private JLabel logo, footer; 
    private JButton btnUsuario, btnCarrito, btnCamisas, btnJerseis, btnAbrigos, btnVestidos, btnPantalones, btnCalzado, btnBolsos;
    
    private JFrame ventanaActual; 

    public VentanaInicial() {
    	ventanaActual = this;
        setSize(1500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pNorte = new JPanel(new BorderLayout());
        pNorte.setBackground(Color.WHITE);
        pCentro = new JPanel();
        pCentro.setBackground(Color.WHITE);
        pSur = new JPanel();
        pSur.setBackground(Color.LIGHT_GRAY);
        pBotones = new JPanel(new GridLayout(1,3,1,1));
        pBotones.setBackground(Color.WHITE);
        pMenu = new JPanel(new GridLayout(1,7,1,1));
        pMenu.setBackground(Color.WHITE);
        
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
        
        //LISTENERS 
        btnUsuario.addActionListener((e)->{
        	ventanaActual.dispose();
        	new MiCuenta(ventanaActual);
        });
        
        btnCarrito.addActionListener((e)->{
        	ventanaActual.dispose();
        	new VentanaCarrito(ventanaActual);
        });
        
        btnCamisas.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Camisas(ventanaActual);
        });
        
        btnJerseis.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Jerseis(ventanaActual);
        });
        btnAbrigos.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Abrigos(ventanaActual);
        });
        btnVestidos.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Vestidos(ventanaActual);
        });
        btnPantalones.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Pantalones(ventanaActual);
        });
        btnCalzado.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Calzado(ventanaActual);
        });
        btnBolsos.addActionListener((e)->{
        	ventanaActual.setVisible(false);
        	new Bolsos(ventanaActual);
        });
   
       
        

        setVisible(true);
    }

    

    public static void main(String[] args) {
    	new VentanaInicial();
    }
}

