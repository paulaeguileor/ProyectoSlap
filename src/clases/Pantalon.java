package clases;

public class Pantalon extends Articulo{
	private int talla;

	public Pantalon() {
		super();
		
	}

	public Pantalon(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Pantalon(int talla) {
		super();
		this.talla = talla;
	}

	public int getTalla() {
		return talla;
	}

	public void setTalla(int talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "Pantalon [codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio=" + precio + ", talla=" + talla + "]";
	}

	
}
