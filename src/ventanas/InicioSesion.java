package ventanas;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import BD.BD;

public class InicioSesion extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame vAnterior;
	private BD bd;
	
	//Declaramos los paneles
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	
	//Inicio Sesión
	private JButton btnInicioSesion, btnCierreSesion;
	//Declaramos los componentes etiqueta
	private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
	//Declaramos los componente cuadro de texto
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseniaUsuario;
	
	public InicioSesion(JFrame va, BD bd) {
		super();
		this.vAnterior = va;
		this.bd = bd;
		
		setTitle("Mi cuenta");
		setSize(1500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        //Instanciamos los paneles
		pCentro = new JPanel();
		//Modificamos el Layout del panel centro
		pCentro.setLayout(new GridLayout(2, 2)); //Formato de matriz de 2x2
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
        
        //Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
        
        //Inicio Sesión
        //Instanciamos los componentes botón
      	btnInicioSesion = new JButton("INICIO SESIÓN");
      	btnCierreSesion = new JButton("CIERRE SESIÓN");
      	
      	lblTitulo = new JLabel("BIENVENIDO!");
		lblNombreUsuario = new JLabel("Introduce tu nombre de usuario: ");
		lblContraseniaUsuario = new JLabel("Introduce tu contraseña: ");
		
		txtNombreUsuario = new JTextField(20);
		txtContraseniaUsuario = new JPasswordField(20);
		
		//Añadimos el botón al panel principal de la ventana
		pSur.add(btnInicioSesion);
		pSur.add(btnCierreSesion);
		
		pNorte.add(lblTitulo);
		pCentro.add(lblNombreUsuario);
		pCentro.add(txtNombreUsuario);
		pCentro.add(lblContraseniaUsuario);
		pCentro.add(txtContraseniaUsuario);
		
		//Añadimos los listeners
		btnInicioSesion.addActionListener((e)->{
		    String usuario = txtNombreUsuario.getText().trim();
		    String contrasenia = new String(txtContraseniaUsuario.getPassword());

		    if(usuario.isEmpty() || contrasenia.isEmpty()) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Debes introducir nombre de usuario y contraseña",
		                "ERROR",
		                JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    if (bd.comprobarUsuario(usuario, contrasenia)) {
		        // Usuario existente con contraseña correcta
		        JOptionPane.showMessageDialog(this, "Has iniciado sesión correctamente");
		    } else {
		        // Intentamos registrarlo
		        boolean registrado = bd.insertarUsuario(usuario, contrasenia);
		        if (registrado) {
		            JOptionPane.showMessageDialog(this, "Te has registrado e iniciado sesión correctamente");
		        } else {
		            // Nombre ya existe pero con otra contraseña
		            JOptionPane.showMessageDialog(
		                    this,
		                    "Nombre de usuario ya existente con otra contraseña",
		                    "ERROR",
		                    JOptionPane.ERROR_MESSAGE);
		            txtNombreUsuario.setText("");
		            txtContraseniaUsuario.setText("");
		            return;
		        }
		    }

		    txtNombreUsuario.setText("");
		    txtContraseniaUsuario.setText("");
		    
		    clases.Sesion.usuarioActual = usuario;
		    clases.Sesion.esAdmin = bd.esAdmin(usuario);
		    
		    new Usuario(vAnterior, bd, usuario);
		    dispose();
		});
		
		btnCierreSesion.addActionListener((e)->{
			new VentanaInicial(bd);
			dispose();
		});
        
        setVisible(true);
	}
}
