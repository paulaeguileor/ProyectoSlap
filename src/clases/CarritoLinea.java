package clases;

public class CarritoLinea {
    private int id;
    private String usuario;
    private String tipoArticulo;
    private int codigoArticulo;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;

    public CarritoLinea(int id, String usuario, String tipoArticulo, int codigoArticulo,
                        String descripcion, int cantidad, double precioUnitario) {
        this.id = id;
        this.usuario = usuario;
        this.tipoArticulo = tipoArticulo;
        this.codigoArticulo = codigoArticulo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getTipoArticulo() { return tipoArticulo; }
    public int getCodigoArticulo() { return codigoArticulo; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
}