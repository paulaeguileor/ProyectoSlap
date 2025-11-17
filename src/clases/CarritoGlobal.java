package clases;

import java.util.ArrayList;
import java.util.List;

public class CarritoGlobal {
    private static List<Articulo> articulos = new ArrayList<>();

    public static void addArticulo(Articulo a) {
        articulos.add(a);
    }

    public static void removeArticulo(int index) {
        if(index >= 0 && index < articulos.size()) {
            articulos.remove(index);
        }
    }

    public static void clear() {
        articulos.clear();
    }

    public static List<Articulo> getArticulos() {
        return articulos;
    }
}
