package clases;

public class Abrigo extends Articulo{
	private TallaLetra talla;

	public Abrigo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Abrigo(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Abrigo(TallaLetra talla) {
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
		return "Abrigo [talla=" + talla + ", getTalla()=" + getTalla() + ", getCodigo()=" + getCodigo() + ", getDesc()="
				+ getDesc() + ", getColor()=" + getColor() + ", getPrecio()=" + getPrecio() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
	
}
