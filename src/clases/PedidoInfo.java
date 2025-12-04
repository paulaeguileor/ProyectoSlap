package clases;

public class PedidoInfo {
    private int id;
    private String usuario;
    private String fecha;
    private String estado;
    private double importe;

    public PedidoInfo(int id, String usuario, String fecha, String estado, double importe) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.importe = importe;
    }

    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public double getImporte() { return importe; }
}