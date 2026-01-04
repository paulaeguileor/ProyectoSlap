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
            new VentanaInicial(bd);
        });
    }

}
