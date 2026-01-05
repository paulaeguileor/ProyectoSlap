package clases;

import javax.swing.SwingUtilities;

import BD.BD;
import ventanas.VentanaInicial;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BD bd = new BD();
            bd.initBD("tienda.db");
            bd.crearTablas();

            if (bd.cargarAbrigos().isEmpty()) {
                MainArticulos ma = new MainArticulos();
                ma.rellenarBD(bd);
            }
            
            bd.insertarUsuario("admin", "admin");
            bd.hacerAdmin("admin");

            new VentanaInicial(bd);
        });
    }
}
