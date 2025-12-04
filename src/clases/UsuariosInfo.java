package clases;

public class UsuariosInfo {
    private int id;
    private String nombre;
    private String contrasenia;
    private String direccion;
    private String email;
    private String telefono;

    public UsuariosInfo(int id, String nombre, String contrasenia,
                       String direccion, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}