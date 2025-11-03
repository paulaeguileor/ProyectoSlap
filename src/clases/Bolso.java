package clases;

public class Bolso extends Articulo{
	private TallaLetra talla;

	public Bolso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bolso(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Bolso(TallaLetra talla) {
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
		return "Bolso [talla=" + talla + ", getTalla()=" + getTalla() + ", getCodigo()=" + getCodigo() + ", getDesc()="
				+ getDesc() + ", getColor()=" + getColor() + ", getPrecio()=" + getPrecio() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
}
