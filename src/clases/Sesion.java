package clases;

public class Sesion {
	public static String usuarioActual = null;
    public static boolean esAdmin = false;

    public static boolean haySesion() {
        return usuarioActual != null && !usuarioActual.isBlank();
    }

    public static void iniciarSesion(String usuario, boolean admin) {
        usuarioActual = usuario;
        esAdmin = admin;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        esAdmin = false;
    }
}
