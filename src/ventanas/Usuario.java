package ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Usuario extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame vAnterior;

	//Contenido Central
	private JPanel pCentro;
	
	//Haremos un panel superior (navegador) con botones para que sea más cómodo acceder a cada apartado
	private JPanel pNav;
	private JButton btnPerfil, btnCDs, btnCompras;
	
	//Mantenemos el botón de volver a la página anterior
	private JPanel pSur;
	private JButton btnVolver;
	
	private JTable tablaDirecciones;
	private DefaultTableModel modeloTablaDirecciones;
	//private JButton btnAniadirDir, btnEditarDir, btnDir; (IDEA)
	
	private JTable tablaPedidos;
	private DefaultTableModel modeloTablaPedidos;
	//private JButton btnDetallePedido; (IDEA)
	
	public Usuario(JFrame va) {
		super();
		vAnterior = va;
		
		setTitle("Mi cuenta");
		setSize(1500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        //Navegador
        pNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pNav.setBorder(new EmptyBorder(8, 12, 8, 12));
        btnPerfil = new JButton("Perfil");
        btnCDs = new JButton("Cambios y Devoluciones");
        btnCompras = new JButton("Compras"); //Pdríamos hace dentro de este filtrarlo por: tienda / online (IDEA)
        pNav.add(btnPerfil);
        pNav.add(btnCDs);
        pNav.add(btnCompras);
        getContentPane().add(pNav, BorderLayout.NORTH);
        
        //Contenido central
        pCentro = new JPanel(new BorderLayout());
        pCentro.setBorder(new EmptyBorder(10, 12, 10, 12));
        getContentPane().add(pCentro, BorderLayout.CENTER);
        
        //Contenido de la parte sur
        pSur = new JPanel();
        btnVolver = new JButton("Volver");
        pSur.add(btnVolver);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        
        /* LISTENERS DE LOS BOTONES: PERFIL, CAMBIOS Y DEVOLUCIONES Y PEDIDOS. Y EL DE CERRAR SESIÓN */
        btnPerfil.addActionListener(e -> mostrarPerfilCentrado("NOMBRE Y APELLIDOS",
                "DIRECCIÓN",
                "EMAIL",
                "TELÉFONO"));
        btnCompras.addActionListener(e -> mostrarCompras());
        btnCDs.addActionListener(e -> mostrarCambiosDevoluciones());
        btnVolver.addActionListener(e -> {
        	if (vAnterior != null) vAnterior.setVisible(true);
            dispose();
        });
        
        mostrarPerfilCentrado(
                "NOMBRE Y APELLIDOS",
                "DIRECCIÓN",
                "EMAIL",
                "TELÉFONO"
        );
        setVisible(true);
	}
	
	//PERFIL
    private void mostrarPerfilCentrado(String nombreCompleto, String direccion, String email, String telefono) {
        JPanel panelPerfilBis = new JPanel(new BorderLayout());
        panelPerfilBis.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Contenedor centrado y estrecho
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel columna = new JPanel();
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columna.setMaximumSize(new Dimension(520, Integer.MAX_VALUE));
        columna.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = etiquetaSeccion(nombreCompleto);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        columna.add(lblNombre);
        columna.add(Box.createVerticalStrut(20));

        // Bloques de info (etiqueta pequeña + valor)
        columna.add(bloqueInfo("DIRECCIÓN", direccion));
        columna.add(Box.createVerticalStrut(12));
        columna.add(bloqueInfo("E-MAIL", email));
        columna.add(Box.createVerticalStrut(12));
        columna.add(bloqueInfo("TELÉFONO", telefono));
        columna.add(Box.createVerticalStrut(24));

        // Botón Cerrar sesión en el centro
        JButton btnCerrarSesion = new JButton("CERRAR SESIÓN");
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.addActionListener(e -> {
            if (vAnterior != null) vAnterior.setVisible(true);
            dispose();
        });
        columna.add(btnCerrarSesion);

        // Centrado vertical/horizontal
        centro.add(Box.createVerticalGlue());
        centro.add(columna);
        centro.add(Box.createVerticalGlue());

        //Márgenes laterales
        panelPerfilBis.add(new JPanel(), BorderLayout.WEST);
        panelPerfilBis.add(new JPanel(), BorderLayout.EAST);
        panelPerfilBis.add(centro, BorderLayout.CENTER);

        setCentro(panelPerfilBis);
    }
    
    private JPanel bloqueInfo(String titulo, String valor) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.BLACK);

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(Color.BLACK);

        p.add(lblTitulo);
        p.add(Box.createVerticalStrut(4));
        p.add(lblValor);
        return p;
    }

    private JLabel etiquetaSeccion(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 18f));
        l.setForeground(Color.BLACK);
        return l;
    }

    private void setCentro(JComponent comp) {
        pCentro.removeAll();
        pCentro.add(comp, BorderLayout.CENTER);
        pCentro.revalidate();
        pCentro.repaint();
    }
    
    //COMPRAS
    private void mostrarCompras() {
        JPanel pC = new JPanel(new BorderLayout());
        JLabel titulo = crearTitulo("Compras");
        pC.add(titulo, BorderLayout.NORTH);

        // Modelo vacío
        modeloTablaPedidos = new DefaultTableModel(new Object[]{"Nº Pedido", "Fecha", "Estado", "Importe"}, 0);
        tablaPedidos = new JTable(modeloTablaPedidos);
        tablaPedidos.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tablaPedidos);
        pC.add(scroll, BorderLayout.CENTER);

        setCentro(pC);
    }
    
    //CAMBIOS Y DEVOLUCIONES
    private void mostrarCambiosDevoluciones() {
        JPanel pCD = new JPanel(new BorderLayout());
        JLabel titulo = crearTitulo("Cambios y Devoluciones");
        pCD.add(titulo, BorderLayout.NORTH);

        // Modelo vacío
        modeloTablaDirecciones = new DefaultTableModel(new Object[]{"Nº Solicitud", "Fecha", "Tipo", "Estado"}, 0);
        tablaDirecciones = new JTable(modeloTablaDirecciones);
        tablaDirecciones.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tablaDirecciones);
        pCD.add(scroll, BorderLayout.CENTER);

        setCentro(pCD);
    }
    
    private JLabel crearTitulo(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 20f));
        l.setBorder(new EmptyBorder(0, 0, 10, 0));
        return l;
    }
}
