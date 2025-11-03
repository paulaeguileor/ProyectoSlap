package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import clases.Articulo;

public class ModeloVentanaCarrito extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<String> titulos = Arrays.asList( "Código", "Descripción","Precio");
	private List<Articulo> lArticulos;
	
	public ModeloVentanaCarrito(List<Articulo> lA) {
		super();
		lArticulos = lA;
	
	}

	@Override
	public int getRowCount() {
		if (lArticulos!=null) {
			return lArticulos.size();
		}else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return titulos.size();
	}

	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Articulo a = lArticulos.get(row);
		switch(column) {
			case 0: return a.getCodigo();
			case 1: return a.getDesc();
			case 2: return a.getPrecio();
			default: return null; 
				
		}
	}

	@Override
	public void removeRow(int row) {
		if (lArticulos!=null) {
			if (row != -1 ) {;
				lArticulos.remove(row);
			}else {
				JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Su lista está vacía");
		}
	}
	
	

}
