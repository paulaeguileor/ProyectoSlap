package clases;

public class Articulo {
	protected int codigo;
	protected String desc;
	protected Color color;
	protected double precio;
	public Articulo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Articulo(int codigo, String desc, Color color, double precio) {
		super();
		this.codigo = codigo;
		this.desc = desc;
		this.color = color;
		this.precio = precio;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", desc=" + desc + ", color=" + color + ", precio=" + precio
				+ ", getCodigo()=" + getCodigo() + ", getDesc()=" + getDesc() + ", getColor()=" + getColor()
				+ ", getPrecio()=" + getPrecio() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}