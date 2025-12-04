package clases;

public class SolicitudInfo {
    private int id;
    private int idPedido;
    private String usuario;
    private String fecha;
    private String tipo;
    private String estado;

    public SolicitudInfo(int id, int idPedido, String usuario,
                         String fecha, String tipo, String estado) {
        this.id = id;
        this.idPedido = idPedido;
        this.usuario = usuario;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public String getUsuario() { return usuario; }
    public String getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
}