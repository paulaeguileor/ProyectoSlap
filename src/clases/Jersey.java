package clases;

public class Jersey extends Articulo{
	private TallaLetra talla;

	public Jersey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jersey(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Jersey(TallaLetra talla) {
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
		return "Jersey [codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio=" + precio + ", talla=" + talla + "]";

	}

	
}
