package clases;

public class Camisa extends Articulo{
	private TallaLetra talla;

	public Camisa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Camisa(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Camisa(TallaLetra talla) {
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
		return "Camisa [talla=" + talla + ", codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio="
				+ precio + ", getTalla()=" + getTalla() + ", getCodigo()=" + getCodigo() + ", getDesc()=" + getDesc()
				+ ", getColor()=" + getColor() + ", getPrecio()=" + getPrecio() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	} 
	
}
