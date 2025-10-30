package ropa;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jerseis extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame vActual, vAnterior; 
	private JPanel pSur; 
	private JButton btnVolver;
	
	public Jerseis(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;
		
		setTitle("Jerséis y cardigans");
		setSize(1500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        pSur = new JPanel();
        
        btnVolver = new JButton("Volver");
        
        getContentPane().add(pSur, BorderLayout.SOUTH);
        
        pSur.add(btnVolver);
        
        btnVolver.addActionListener((e)->{
        	vActual.dispose();
        	vAnterior.setVisible(true);       
        });
		
        setVisible(true);
	}

}
