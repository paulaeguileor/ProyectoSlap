package clases;

import java.util.ArrayList;
import java.util.List;

public class CarritoGlobal {

    private static List<Articulo> articulos = new ArrayList<>();
    private static double descuento = 0.0;  
    
    // =====================
    // Gestión del carrito
    // =====================

    public static void addArticulo(Articulo a) {
        articulos.add(a);
    }

    public static void removeArticulo(int index) {
        if (index >= 0 && index < articulos.size()) {
            articulos.remove(index);
        }
    }

    public static void clear() {
        articulos.clear();
        descuento = 0;   // se reinicia el descuento al vaciar
    }

    public static List<Articulo> getArticulos() {
        return articulos;
    }

    // =====================
    // Cálculos de precios
    // =====================

    // Total sin descuento
    public static double getTotal() {
        double total = 0;
        for (Articulo a : articulos) {
            total += a.getPrecio();
        }
        return total;
    }

    // =====================
    // Descuentos (ThreadDescuentos)
    // =====================

    public static void setDescuento(double d) {
        descuento = d;   
    }

    public static double getDescuento() {
        return descuento;
    }

    public static double getTotalConDescuento() {
        double total = getTotal();
        return total * (1 - descuento);
    }
}
