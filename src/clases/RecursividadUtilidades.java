package clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilidades recursivas:
 * - Contar, listar, buscar, filtrar
 * - Crear "ticket" y resúmenes en texto
 * - Normalizar textos (espacios, mayúsculas)
 * - Validaciones sencillas (códigos repetidos, precios)
 *

 */
public final class RecursividadUtilidades {

    private RecursividadUtilidades() {}

    /**
     * Crea una lista con todos los artículos del catálogo (recursivo).
     * Así luego puedes hacer resúmenes y búsquedas.
     */
    public static List<Articulo> recogerTodos(MainArticulos ma) {
        List<Articulo> out = new ArrayList<>();
        if (ma == null) return out;

        copiarRec(ma.getListaCamisas(), 0, out);
        copiarRec(ma.getListaPantalones(), 0, out);
        copiarRec(ma.getListaJerseis(), 0, out);
        copiarRec(ma.getListaAbrigos(), 0, out);
        copiarRec(ma.getListaVestidos(), 0, out);
        copiarRec(ma.getListaBolsos(), 0, out);
        copiarRec(ma.getListaCalzado(), 0, out);

        return out;
    }

    private static void copiarRec(List<? extends Articulo> origen, int i, List<Articulo> out) {
        if (origen == null || out == null) return;
        if (i >= origen.size()) return;
        out.add(origen.get(i));
        copiarRec(origen, i + 1, out);
    }

    // =========================================================
    // ===================== CONTAR / SUMAR =====================
    // =========================================================

    public static int contarArticulosRec(List<?> lista) {
        return contarArticulosDesdeRec(lista, 0);
    }

    private static int contarArticulosDesdeRec(List<?> lista, int i) {
        if (lista == null) return 0;
        if (i >= lista.size()) return 0;
        return 1 + contarArticulosDesdeRec(lista, i + 1);
    }

    public static double sumarPreciosRec(List<Articulo> lista) {
        return sumarPreciosDesdeRec(lista, 0);
    }

    private static double sumarPreciosDesdeRec(List<Articulo> lista, int i) {
        if (lista == null) return 0.0;
        if (i >= lista.size()) return 0.0;
        Articulo a = lista.get(i);
        double p = (a == null ? 0.0 : a.getPrecio());
        return p + sumarPreciosDesdeRec(lista, i + 1);
    }

    public static double precioMedioRec(List<Articulo> lista) {
        int n = contarArticulosRec(lista);
        if (n <= 0) return 0.0;
        return sumarPreciosRec(lista) / n;
    }

    // =========================================================
    // ===================== MÍN / MÁX SIMPLE ===================
    // =========================================================

    public static Articulo masCaroRec(List<Articulo> lista) {
        return masCaroDesdeRec(lista, 0, null);
    }

    private static Articulo masCaroDesdeRec(List<Articulo> lista, int i, Articulo mejor) {
        if (lista == null || i >= lista.size()) return mejor;

        Articulo a = lista.get(i);
        if (a != null) {
            if (mejor == null || a.getPrecio() > mejor.getPrecio()) {
                mejor = a;
            }
        }
        return masCaroDesdeRec(lista, i + 1, mejor);
    }

    public static Articulo masBaratoRec(List<Articulo> lista) {
        return masBaratoDesdeRec(lista, 0, null);
    }

    private static Articulo masBaratoDesdeRec(List<Articulo> lista, int i, Articulo mejor) {
        if (lista == null || i >= lista.size()) return mejor;

        Articulo a = lista.get(i);
        if (a != null) {
            if (mejor == null || a.getPrecio() < mejor.getPrecio()) {
                mejor = a;
            }
        }
        return masBaratoDesdeRec(lista, i + 1, mejor);
    }

    // =========================================================
    // ===================== BÚSQUEDAS FÁCILES ==================
    // =========================================================

    public static Articulo buscarPorCodigoRec(List<Articulo> lista, int codigo) {
        return buscarPorCodigoDesdeRec(lista, 0, codigo);
    }

    private static Articulo buscarPorCodigoDesdeRec(List<Articulo> lista, int i, int codigo) {
        if (lista == null || i >= lista.size()) return null;

        Articulo a = lista.get(i);
        if (a != null && a.getCodigo() == codigo) return a;

        return buscarPorCodigoDesdeRec(lista, i + 1, codigo);
    }

    public static List<Articulo> buscarPorTextoRec(List<Articulo> lista, String texto) {
        List<Articulo> out = new ArrayList<>();
        buscarPorTextoDesdeRec(lista, 0, texto, out);
        return out;
    }

    private static void buscarPorTextoDesdeRec(List<Articulo> lista, int i, String texto, List<Articulo> out) {
        if (lista == null || out == null) return;
        if (i >= lista.size()) return;

        Articulo a = lista.get(i);
        if (a != null) {
            String desc = a.getDesc();
            if (contieneTextoRec(normalizarBasico(desc), normalizarBasico(texto))) {
                out.add(a);
            }
        }

        buscarPorTextoDesdeRec(lista, i + 1, texto, out);
    }

    // =========================================================
    // ===================== FILTROS SENCILLOS ==================
    // =========================================================

    public static List<Articulo> filtrarPrecioMinRec(List<Articulo> lista, double min) {
        List<Articulo> out = new ArrayList<>();
        filtrarPrecioMinDesdeRec(lista, 0, min, out);
        return out;
    }

    private static void filtrarPrecioMinDesdeRec(List<Articulo> lista, int i, double min, List<Articulo> out) {
        if (lista == null || out == null) return;
        if (i >= lista.size()) return;

        Articulo a = lista.get(i);
        if (a != null && a.getPrecio() >= min) out.add(a);

        filtrarPrecioMinDesdeRec(lista, i + 1, min, out);
    }

    public static List<Articulo> filtrarPrecioMaxRec(List<Articulo> lista, double max) {
        List<Articulo> out = new ArrayList<>();
        filtrarPrecioMaxDesdeRec(lista, 0, max, out);
        return out;
    }

    private static void filtrarPrecioMaxDesdeRec(List<Articulo> lista, int i, double max, List<Articulo> out) {
        if (lista == null || out == null) return;
        if (i >= lista.size()) return;

        Articulo a = lista.get(i);
        if (a != null && a.getPrecio() <= max) out.add(a);

        filtrarPrecioMaxDesdeRec(lista, i + 1, max, out);
    }

    public static List<Articulo> filtrarPorColorRec(List<Articulo> lista, Color color) {
        List<Articulo> out = new ArrayList<>();
        filtrarPorColorDesdeRec(lista, 0, color, out);
        return out;
    }

    private static void filtrarPorColorDesdeRec(List<Articulo> lista, int i, Color color, List<Articulo> out) {
        if (lista == null || out == null) return;
        if (i >= lista.size()) return;

        Articulo a = lista.get(i);
        if (a != null && color != null && a.getColor() == color) out.add(a);

        filtrarPorColorDesdeRec(lista, i + 1, color, out);
    }

    // =========================================================
    // ===================== TICKET (TEXTO) =====================
    // =========================================================

    /**
     * Crea un "ticket" en texto a partir de una lista de artículos.
     * Queda muy defendible: "lo uso para imprimir un resumen".
     */
    public static String generarTicketRec(List<Articulo> carrito) {
        StringBuilder sb = new StringBuilder();
        sb.append("=========== TICKET ===========\n");
        construirLineasTicketRec(carrito, 0, sb);
        sb.append("------------------------------\n");
        sb.append("TOTAL: ").append(formatoPrecio(sumarPreciosRec(carrito))).append("\n");
        sb.append("==============================\n");
        return sb.toString();
    }

    private static void construirLineasTicketRec(List<Articulo> carrito, int i, StringBuilder sb) {
        if (sb == null) return;
        if (carrito == null || i >= carrito.size()) return;

        Articulo a = carrito.get(i);
        if (a != null) {
            sb.append(i + 1).append(") ")
              .append(a.getCodigo()).append(" - ")
              .append(normalizarEspaciosRec(a.getDesc())).append(" | ")
              .append(formatoPrecio(a.getPrecio()))
              .append("\n");
        }

        construirLineasTicketRec(carrito, i + 1, sb);
    }

    private static String formatoPrecio(double p) {
        // Lo dejo simple para no depender de Locale
        return String.format("%.2f€", p);
    }

    // =========================================================
    // ===================== VALIDACIONES =======================
    // =========================================================

    /**
     * Comprueba si hay códigos repetidos. (recursivo)
     */
    public static boolean hayCodigosRepetidosRec(List<Articulo> lista) {
        if (lista == null) return false;
        return hayRepetidosDesdeRec(lista, 0);
    }

    private static boolean hayRepetidosDesdeRec(List<Articulo> lista, int i) {
        if (i >= lista.size()) return false;
        Articulo a = lista.get(i);
        int codigo = (a == null ? Integer.MIN_VALUE : a.getCodigo());

        // buscar si aparece más adelante
        if (apareceCodigoDespuesRec(lista, i + 1, codigo)) return true;

        return hayRepetidosDesdeRec(lista, i + 1);
    }

    private static boolean apareceCodigoDespuesRec(List<Articulo> lista, int j, int codigo) {
        if (lista == null) return false;
        if (j >= lista.size()) return false;

        Articulo b = lista.get(j);
        if (b != null && b.getCodigo() == codigo) return true;

        return apareceCodigoDespuesRec(lista, j + 1, codigo);
    }

    /**
     * Comprueba si hay precios inválidos (<=0). (recursivo)
     */
    public static boolean hayPreciosInvalidosRec(List<Articulo> lista) {
        return hayPreciosInvalidosDesdeRec(lista, 0);
    }

    private static boolean hayPreciosInvalidosDesdeRec(List<Articulo> lista, int i) {
        if (lista == null) return false;
        if (i >= lista.size()) return false;

        Articulo a = lista.get(i);
        if (a != null && a.getPrecio() <= 0.0) return true;

        return hayPreciosInvalidosDesdeRec(lista, i + 1);
    }

    // =========================================================
    // ===================== NORMALIZACIÓN TEXTO ================
    // =========================================================

    /**
     * Normaliza básico:
     * - null safe
     * - trim
     * - colapsa espacios
     * - pasa a minúsculas
     */
    public static String normalizarBasico(String s) {
        if (s == null) return "";
        String t = normalizarEspaciosRec(s);
        return toLowerRec(t, 0, new StringBuilder()).toString();
    }

    public static String normalizarEspaciosRec(String s) {
        if (s == null) return "";
        // trim izquierda y derecha (recursivo)
        String t = trimIzqRec(trimDerRec(s));
        // colapsar espacios internos (recursivo)
        return colapsarEspaciosRec(t, 0, new StringBuilder()).toString();
    }

    private static String trimIzqRec(String s) {
        if (s == null || s.isEmpty()) return "";
        if (!Character.isWhitespace(s.charAt(0))) return s;
        return trimIzqRec(s.substring(1));
    }

    private static String trimDerRec(String s) {
        if (s == null || s.isEmpty()) return "";
        int last = s.length() - 1;
        if (!Character.isWhitespace(s.charAt(last))) return s;
        return trimDerRec(s.substring(0, last));
    }

    private static StringBuilder colapsarEspaciosRec(String s, int i, StringBuilder sb) {
        if (s == null) return sb;
        if (i >= s.length()) return sb;

        char c = s.charAt(i);
        if (Character.isWhitespace(c)) {
            if (sb.length() == 0 || !Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.append(' ');
            }
            return colapsarEspaciosRec(s, i + 1, sb);
        }

        sb.append(c);
        return colapsarEspaciosRec(s, i + 1, sb);
    }

    private static StringBuilder toLowerRec(String s, int i, StringBuilder sb) {
        if (s == null) return sb;
        if (i >= s.length()) return sb;
        sb.append(Character.toLowerCase(s.charAt(i)));
        return toLowerRec(s, i + 1, sb);
    }

    // =========================================================
    // ===================== CONTIENE TEXTO =====================
    // =========================================================

    /**
     * Contiene recursivo (búsqueda de subcadena).
     */
    public static boolean contieneTextoRec(String texto, String patron) {
        if (texto == null || patron == null) return false;
        if (patron.isEmpty()) return true;
        if (texto.length() < patron.length()) return false;
        return contieneDesdeRec(texto, patron, 0);
    }

    private static boolean contieneDesdeRec(String texto, String patron, int i) {
        if (i > texto.length() - patron.length()) return false;
        if (matchRec(texto, patron, i, 0)) return true;
        return contieneDesdeRec(texto, patron, i + 1);
    }

    private static boolean matchRec(String texto, String patron, int it, int ip) {
        if (ip >= patron.length()) return true;
        if (it + ip >= texto.length()) return false;
        if (texto.charAt(it + ip) != patron.charAt(ip)) return false;
        return matchRec(texto, patron, it, ip + 1);
    }

    // =========================================================
    // ===================== RESÚMENES BONITOS ==================
    // =========================================================

    public static String resumenCatalogoRec(MainArticulos ma) {
        List<Articulo> todos = recogerTodos(ma);

        StringBuilder sb = new StringBuilder();
        sb.append("===== RESUMEN CATÁLOGO =====\n");
        sb.append("Total artículos: ").append(contarArticulosRec(todos)).append("\n");
        sb.append("Precio medio: ").append(formatoPrecio(precioMedioRec(todos))).append("\n");

        Articulo caro = masCaroRec(todos);
        Articulo barato = masBaratoRec(todos);

        sb.append("Más caro: ").append(caro != null ? normalizarEspaciosRec(caro.getDesc()) : "null").append("\n");
        sb.append("Más barato: ").append(barato != null ? normalizarEspaciosRec(barato.getDesc()) : "null").append("\n");

        sb.append("Códigos repetidos: ").append(hayCodigosRepetidosRec(todos)).append("\n");
        sb.append("Precios inválidos: ").append(hayPreciosInvalidosRec(todos)).append("\n");

        sb.append("============================\n");
        return sb.toString();
    }


    public static String listarRec(List<Articulo> lista) {
        StringBuilder sb = new StringBuilder();
        listarDesdeRec(lista, 0, sb);
        return sb.toString();
    }

    private static void listarDesdeRec(List<Articulo> lista, int i, StringBuilder sb) {
        if (sb == null) return;
        if (lista == null || i >= lista.size()) return;

        Articulo a = lista.get(i);
        if (a != null) {
            sb.append("• ")
              .append(a.getCodigo())
              .append(" | ")
              .append(normalizarEspaciosRec(a.getDesc()))
              .append(" | ")
              .append(a.getColor())
              .append(" | ")
              .append(formatoPrecio(a.getPrecio()))
              .append("\n");
        }

        listarDesdeRec(lista, i + 1, sb);
    }
}

