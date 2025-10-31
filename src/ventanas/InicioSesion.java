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

public class InicioSesion extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame vAnterior;
	
	//Declaramos los paneles
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	
	//Inicio Sesión
	private JButton btnInicioSesion, btnCierreSesion;
	//Declaramos los componentes etiqueta
	private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
	//Declaramos los componente cuadro de texto
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseniaUsuario;
	
	public InicioSesion(JFrame va) {
		super();
		this.vAnterior = va;
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
			String usuario = txtNombreUsuario.getText();
			@SuppressWarnings("deprecation")
			String contrasenia = txtContraseniaUsuario.getText();
			if(usuario.equals("") && contrasenia.equals("")) {
				JOptionPane.showMessageDialog(null, "Has iniciado sesión correctamente");
				//Vaciamos los cuadros de texto
				txtNombreUsuario.setText("");
				txtContraseniaUsuario.setText("");
				new Usuario(vAnterior);
				dispose();
				
			}else {
				JOptionPane.showMessageDialog(null,"Nombre de usuario y/o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
				txtNombreUsuario.setText("");
				txtContraseniaUsuario.setText("");
			}
		});
		
		btnCierreSesion.addActionListener((e)->{
			new VentanaInicial(); //Para que vuelva a la ventana Inicial / SINO se podría hace que se cerrase la aplicación entera; dispose();
		});
        
        setVisible(true);
	}
}
