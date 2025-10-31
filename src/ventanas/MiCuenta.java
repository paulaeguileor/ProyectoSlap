package ventanas;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiCuenta extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame vActual, vAnterior;
	private JPanel pSur; 
	private JButton btnVolver;
	
	public MiCuenta(JFrame va) {
		super();
		vActual = this; 
		vAnterior = va;
		
		setTitle("Mi cuenta");
		setSize(1500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        pSur = new JPanel();
        
        btnVolver = new JButton("Volver");
        
        getContentPane().add(pSur, BorderLayout.SOUTH);
        
        pSur.add(btnVolver);
        
        btnVolver.addActionListener((e)->{
        	vAnterior.setVisible(true);
        	vActual.dispose();
        });
        
        setVisible(true);
	}

}
