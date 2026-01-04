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
	private JButton btnInicioSesion, btnRegistro, btnVolver;
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		btnInicioSesion = new JButton("INICIAR SESIÓN");
		btnRegistro = new JButton("REGISTRARSE");
		btnVolver = new JButton("VOLVER");
      	
      	lblTitulo = new JLabel("BIENVENIDO!");
		lblNombreUsuario = new JLabel("Introduce tu nombre de usuario: ");
		lblContraseniaUsuario = new JLabel("Introduce tu contraseña: ");
		
		txtNombreUsuario = new JTextField(20);
		txtContraseniaUsuario = new JPasswordField(20);
		
		//Añadimos el botón al panel principal de la ventana
		pSur.add(btnInicioSesion);
		pSur.add(btnRegistro);
		pSur.add(btnVolver);
		
		pNorte.add(lblTitulo);
		pCentro.add(lblNombreUsuario);
		pCentro.add(txtNombreUsuario);
		pCentro.add(lblContraseniaUsuario);
		pCentro.add(txtContraseniaUsuario);
		
		//Añadimos los listeners
		btnInicioSesion.addActionListener(e -> {
		    String usuario = txtNombreUsuario.getText().trim();
		    String contrasenia = new String(txtContraseniaUsuario.getPassword());

		    if (usuario.isEmpty() || contrasenia.isEmpty()) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Debes introducir nombre de usuario y contraseña.",
		                "Datos incompletos",
		                JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    if (!bd.comprobarUsuario(usuario, contrasenia)) {
		        JOptionPane.showMessageDialog(
		                this,
		                "Usuario o contraseña incorrectos.\nSi no tienes cuenta, pulsa REGISTRARSE.",
		                "Inicio de sesión fallido",
		                JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    clases.Sesion.iniciarSesion(usuario, bd.esAdmin(usuario));

		    new Usuario(vAnterior, bd, usuario);
		    dispose();
		});
		
		btnVolver.addActionListener(e -> {
		    if (vAnterior != null) vAnterior.setVisible(true);
		    dispose();
		});
		
		btnRegistro.addActionListener(e -> abrirRegistro());
		
		getRootPane().setDefaultButton(btnInicioSesion);
		txtNombreUsuario.requestFocusInWindow();
		
        setVisible(true);
	}
	
	private void abrirRegistro() {
	    JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

	    JTextField txtUser = new JTextField(20);
	    JPasswordField txtPass = new JPasswordField(20);
	    JTextField txtDir = new JTextField(20);
	    JTextField txtEmail = new JTextField(20);
	    JTextField txtTel = new JTextField(20);

	    panel.add(new JLabel("Usuario:"));
	    panel.add(txtUser);
	    panel.add(new JLabel("Contraseña:"));
	    panel.add(txtPass);
	    panel.add(new JLabel("Dirección:"));
	    panel.add(txtDir);
	    panel.add(new JLabel("Email:"));
	    panel.add(txtEmail);
	    panel.add(new JLabel("Teléfono:"));
	    panel.add(txtTel);

	    int res = JOptionPane.showConfirmDialog(
	            this,
	            panel,
	            "Registro",
	            JOptionPane.OK_CANCEL_OPTION,
	            JOptionPane.PLAIN_MESSAGE
	    );

	    if (res != JOptionPane.OK_OPTION) return;

	    String usuario = txtUser.getText().trim();
	    String pass = new String(txtPass.getPassword());
	    String direccion = txtDir.getText().trim();
	    String email = txtEmail.getText().trim();
	    String tel = txtTel.getText().trim();

	    if (usuario.isEmpty() || pass.isEmpty()) {
	        JOptionPane.showMessageDialog(this,
	                "Usuario y contraseña son obligatorios.",
	                "Registro incompleto",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!email.isEmpty() && (!email.contains("@") || !email.contains("."))) {
	        JOptionPane.showMessageDialog(this,
	                "Email no válido.",
	                "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!tel.isEmpty() && !tel.matches("\\d+")) {
	        JOptionPane.showMessageDialog(this,
	                "El teléfono solo puede contener números.",
	                "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    boolean ok = bd.insertarUsuarioCompleto(usuario, pass, direccion, email, tel);

	    if (!ok) {
	        JOptionPane.showMessageDialog(this,
	                "No se ha podido registrar. Ese usuario ya existe.",
	                "Registro fallido",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    JOptionPane.showMessageDialog(this,
	            "Registro completado. Ya puedes iniciar sesión.",
	            "Registro OK",
	            JOptionPane.INFORMATION_MESSAGE);

	    txtNombreUsuario.setText(usuario);
	    txtContraseniaUsuario.setText(pass);
	    getRootPane().setDefaultButton(btnInicioSesion);
	}
}
