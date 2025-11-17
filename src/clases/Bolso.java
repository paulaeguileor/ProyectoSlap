package clases;

public class Bolso extends Articulo{
	
	public Bolso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bolso(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Bolso [codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio=" + precio +"]";
	}

	

	

	
}
