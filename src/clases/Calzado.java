package clases;

public class Calzado extends Articulo{
	private int talla;

	public Calzado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Calzado(int codigo, String desc, Color color, double precio) {
		super(codigo, desc, color, precio);
		// TODO Auto-generated constructor stub
	}

	public Calzado(int talla) {
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
		return "Calzado [talla=" + talla + ", codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio="
				+ precio + ", getTalla()=" + getTalla() + ", getCodigo()=" + getCodigo() + ", getDesc()=" + getDesc()
				+ ", getColor()=" + getColor() + ", getPrecio()=" + getPrecio() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
