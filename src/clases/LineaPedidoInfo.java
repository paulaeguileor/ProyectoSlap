package clases;

public class LineaPedidoInfo {
    private int id;
    private int idPedido;
    private String tipoArticulo;
    private int codigoArticulo;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public LineaPedidoInfo(int id, int idPedido, String tipoArticulo, int codigoArticulo,
                           String descripcion, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idPedido = idPedido;
        this.tipoArticulo = tipoArticulo;
        this.codigoArticulo = codigoArticulo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public String getTipoArticulo() { return tipoArticulo; }
    public int getCodigoArticulo() { return codigoArticulo; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }
}
