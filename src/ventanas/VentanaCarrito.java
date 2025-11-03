package ventanas;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import clases.Articulo;
import clases.Color;

public class VentanaCarrito extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame vActual, vAnterior; 
	private JButton btnVolver;
	
	private ModeloVentanaCarrito modelo;
	private JTable tabla;
	private JScrollPane scroll;
	private List<Articulo> lAr; 
	
	private JPanel pNorte, pCentro, pTotal, pSur;
	private JLabel lblTotalm, lblTotalCalulado, lblBarraVertical, lblNumArt, lblContadorArticulos;
	private JButton btnE, btnContinuarCompra, btnVaciar;
	
	public VentanaCarrito(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;
		
		setTitle("CARRITO");
		setSize(1500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Articulo a1 = new Articulo(1, "88E0", Color.BEIGE, 120.5);
		Articulo a2 = new Articulo(2, "77E3", Color.BLANCO, 225.5);
		Articulo a3 = new Articulo(3, "68E9", Color.KHAKI, 166.5);
		Articulo a4 = new Articulo(4, "54E2",Color.MARRÓN, 305.5);
		lAr = new ArrayList<Articulo>();
		lAr.add(a1);
		lAr.add(a2);
		lAr.add(a3);
		lAr.add(a4);
		modelo = new ModeloVentanaCarrito(lAr);
		tabla = new JTable(modelo);
		scroll = new JScrollPane(tabla);
		
		pNorte = new JPanel(new BorderLayout());
		btnVaciar = new JButton("Vaciar cesta");
		pCentro = new JPanel(new GridLayout(2,1));
		pTotal = new JPanel(new GridLayout(1,3));
		lblTotalm = new JLabel("Total calculado:");
		lblTotalm.setHorizontalAlignment(JLabel.CENTER);
		lblTotalCalulado = new JLabel();
		btnContinuarCompra = new JButton("Finalizar compra");
		btnContinuarCompra.setHorizontalAlignment(JLabel.CENTER);
		btnContinuarCompra.setBorder(BorderFactory.createEmptyBorder());
		pSur = new JPanel(new GridLayout(1,5,10,10));
		pSur.setBackground(tabla.getBackground());
		btnE = new JButton("Eliminar articulo");
		btnE.setBackground(pSur.getBackground());
		btnE.setBorder(BorderFactory.createEmptyBorder());
		btnE.setHorizontalAlignment(JButton.CENTER);
		lblBarraVertical = new JLabel("|");
		lblBarraVertical.setHorizontalAlignment(JLabel.CENTER);
		lblNumArt = new JLabel("Número de artículos: ");
		lblNumArt.setHorizontalAlignment(JLabel.CENTER);
		lblContadorArticulos = new JLabel();
		lblContadorArticulos.setHorizontalAlignment(JLabel.CENTER);
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		pNorte.add(btnVaciar, BorderLayout.WEST);
		pCentro.add(scroll);
		pTotal.add(lblTotalm);
		pTotal.add(lblTotalCalulado);
		pTotal.add(btnContinuarCompra);
		pCentro.add(pTotal);
		pSur.add(btnE);
		pSur.add(lblBarraVertical);
		pSur.add(lblNumArt);
		pSur.add(lblContadorArticulos);
		
		
		
		//Al seleccionar la fila de una lista y darle al boton se elimina el articulo
		/*btnE.addActionListener((e)->{
			if (tabla!=null) {
				int fila = tabla.getSelectedRow();
				if (fila != -1) {;
					lAr.remove(fila);
					tabla.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar");
				}
			}
		});*/
		
		btnE.addActionListener((e)->{
			double precio = 0;
			int contador = 0;
			if (lAr.isEmpty()) {
				lblTotalCalulado.setText("0");
				lblContadorArticulos.setText("0");
			}
			else {
				modelo.removeRow(tabla.getSelectedRow());
				tabla.repaint();
				for (int i = 0; i<lAr.size(); i++) {
					Articulo a = lAr.get(i);
					precio = precio + a.getPrecio();
					String t = String.valueOf(precio);
					lblTotalCalulado.setText(t + " €");
					
				}
				
				for (int i=0; i<lAr.size(); i++) {
					contador = contador +1;
					String t = String.valueOf(contador);
					lblContadorArticulos.setText(t);
				}
			
			}
			
			
		});
		
		btnContinuarCompra.addActionListener((e)->{
			JOptionPane.showMessageDialog(null, "¡Tu compra se ha realizado con éxito!");
		});
		
		btnVaciar.addActionListener((e)->{
			while (!lAr.isEmpty()) {
				for (int i=0;i<lAr.size(); i++) {
					modelo.removeRow(i);
					tabla.repaint();
				}
			}
			lblTotalCalulado.setText("0");
			lblContadorArticulos.setText("0");
		});
		
		int contador = 0;
		for (int i=0; i<lAr.size(); i++) {
			contador = contador +1;
			String t = String.valueOf(contador);
			lblContadorArticulos.setText(t);
		}
		
		double precio = 0;
		for (int i = 0; i<lAr.size(); i++) {
			Articulo a = lAr.get(i);
			precio = precio + a.getPrecio();
			String t = String.valueOf(precio);
			lblTotalCalulado.setText(t + " €");
			
		}
        
        btnVolver = new JButton("Volver");
        pSur.add(btnVolver);
        
        btnVolver.addActionListener((e)->{
        	vAnterior.setVisible(true);
        	vActual.dispose();
        });
        

        
        
        setVisible(true);
	}
}
