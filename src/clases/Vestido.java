package clases;

public class Vestido extends Articulo{
	private TallaLetra talla;

	public Vestido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vestido(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Vestido(TallaLetra talla) {
		super();
		this.talla = talla;
	}

	public TallaLetra getTalla() {
		return talla;
	}

	public void setTalla(TallaLetra talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "Vestido [codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio=" + precio + ", talla=" + talla + "]";
	}

	

	
	

	
	
}
