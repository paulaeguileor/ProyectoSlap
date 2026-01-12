package clases;

import java.util.ArrayList;
import java.util.List;


public final class RecursividadAvanzada {

    private RecursividadAvanzada() {}

    // =========================================================
    // ========================= AUTO TEST ======================
    // =========================================================

    public static void autoTest(MainArticulos ma) {
        if (ma == null) return;

        List<Articulo> todos = new ArrayList<>();

        copiarListaRec(ma.getListaCamisas(), 0, todos);
        copiarListaRec(ma.getListaPantalones(), 0, todos);
        copiarListaRec(ma.getListaJerseis(), 0, todos);
        copiarListaRec(ma.getListaAbrigos(), 0, todos);
        copiarListaRec(ma.getListaVestidos(), 0, todos);
        copiarListaRec(ma.getListaBolsos(), 0, todos);
        copiarListaRec(ma.getListaCalzado(), 0, todos);

        double suma = sumaPreciosRecursiva(todos, 0);
        Articulo masCaro = maxPrecioRecursivo(todos, 0, null);
        Articulo masBarato = minPrecioRecursivo(todos, 0, null);

        System.out.println("[RecursividadAvanzada]");
        System.out.println("Articulos: " + contarRecursivo(todos, 0));
        System.out.println("Suma precios: " + suma);
        System.out.println("Mas caro: " + (masCaro != null ? masCaro.getDesc() : "null"));
        System.out.println("Mas barato: " + (masBarato != null ? masBarato.getDesc() : "null"));

        NodoArticulo raiz = construirArbolRecursivo(todos, 0, null);
        List<Articulo> ordenados = new ArrayList<>();
        inorderRecursivo(raiz, ordenados);

        System.out.println("Arbol construido y recorrido: " + ordenados.size());
    }

    // =========================================================
    // ===================== LISTAS RECURSIVAS ==================
    // =========================================================

    private static void copiarListaRec(List<? extends Articulo> origen, int i, List<Articulo> destino) {
        if (origen == null) return;
        if (i >= origen.size()) return;
        destino.add(origen.get(i));
        copiarListaRec(origen, i + 1, destino);
    }

    public static int contarRecursivo(List<?> lista, int i) {
        if (lista == null) return 0;
        if (i >= lista.size()) return 0;
        return 1 + contarRecursivo(lista, i + 1);
    }

    public static double sumaPreciosRecursiva(List<Articulo> lista, int i) {
        if (lista == null) return 0.0;
        if (i >= lista.size()) return 0.0;
        Articulo a = lista.get(i);
        double precio = (a == null ? 0.0 : a.getPrecio());
        return precio + sumaPreciosRecursiva(lista, i + 1);
    }

    public static Articulo maxPrecioRecursivo(List<Articulo> lista, int i, Articulo actualMax) {
        if (lista == null || i >= lista.size()) return actualMax;

        Articulo actual = lista.get(i);
        if (actual != null) {
            if (actualMax == null || actual.getPrecio() > actualMax.getPrecio()) {
                actualMax = actual;
            }
        }
        return maxPrecioRecursivo(lista, i + 1, actualMax);
    }

    public static Articulo minPrecioRecursivo(List<Articulo> lista, int i, Articulo actualMin) {
        if (lista == null || i >= lista.size()) return actualMin;

        Articulo actual = lista.get(i);
        if (actual != null) {
            if (actualMin == null || actual.getPrecio() < actualMin.getPrecio()) {
                actualMin = actual;
            }
        }
        return minPrecioRecursivo(lista, i + 1, actualMin);
    }

    public static Articulo buscarCodigoRecursivo(List<Articulo> lista, int i, int codigo) {
        if (lista == null || i >= lista.size()) return null;
        Articulo a = lista.get(i);
        if (a != null && a.getCodigo() == codigo) return a;
        return buscarCodigoRecursivo(lista, i + 1, codigo);
    }

    // =========================================================
    // ===================== STRINGS RECURSIVOS =================
    // =========================================================

    public static int contarLetrasRecursivo(String s, int i) {
        if (s == null) return 0;
        if (i >= s.length()) return 0;
        char c = s.charAt(i);
        int suma = Character.isWhitespace(c) ? 0 : 1;
        return suma + contarLetrasRecursivo(s, i + 1);
    }

    public static String invertirStringRecursivo(String s) {
        if (s == null || s.length() <= 1) return s;
        return invertirStringRecursivo(s.substring(1)) + s.charAt(0);
    }

    public static boolean contieneRecursivo(String texto, String patron) {
        if (texto == null || patron == null) return false;
        if (patron.isEmpty()) return true;
        if (texto.length() < patron.length()) return false;
        return contieneDesdeRec(texto, patron, 0);
    }

    private static boolean contieneDesdeRec(String t, String p, int i) {
        if (i > t.length() - p.length()) return false;
        if (matchRec(t, p, i, 0)) return true;
        return contieneDesdeRec(t, p, i + 1);
    }

    private static boolean matchRec(String t, String p, int it, int ip) {
        if (ip >= p.length()) return true;
        if (it + ip >= t.length()) return false;
        if (t.charAt(it + ip) != p.charAt(ip)) return false;
        return matchRec(t, p, it, ip + 1);
    }

    // =========================================================
    // ===================== NUMEROS RECURSIVOS =================
    // =========================================================

    public static int factorialRecursivo(int n) {
        if (n < 0) return 0;
        if (n <= 1) return 1;
        return n * factorialRecursivo(n - 1);
    }

    public static int sumaHastaRecursiva(int n) {
        if (n <= 0) return 0;
        return n + sumaHastaRecursiva(n - 1);
    }

    public static int potenciaRecursiva(int base, int exp) {
        if (exp < 0) return 0;
        if (exp == 0) return 1;
        return base * potenciaRecursiva(base, exp - 1);
    }


    public static class NodoArticulo {
        Articulo dato;
        NodoArticulo izq;
        NodoArticulo der;

        NodoArticulo(Articulo a) {
            this.dato = a;
        }
    }

    public static NodoArticulo construirArbolRecursivo(List<Articulo> lista, int i, NodoArticulo raiz) {
        if (lista == null || i >= lista.size()) return raiz;
        raiz = insertarEnArbolRecursivo(raiz, lista.get(i));
        return construirArbolRecursivo(lista, i + 1, raiz);
    }

    private static NodoArticulo insertarEnArbolRecursivo(NodoArticulo nodo, Articulo a) {
        if (a == null) return nodo;
        if (nodo == null) return new NodoArticulo(a);

        if (a.getCodigo() <= nodo.dato.getCodigo()) {
            nodo.izq = insertarEnArbolRecursivo(nodo.izq, a);
        } else {
            nodo.der = insertarEnArbolRecursivo(nodo.der, a);
        }
        return nodo;
    }

    public static void inorderRecursivo(NodoArticulo n, List<Articulo> out) {
        if (n == null) return;
        inorderRecursivo(n.izq, out);
        out.add(n.dato);
        inorderRecursivo(n.der, out);
    }

 
    public static void combinacionesPorPrecioRec(
            List<Articulo> base,
            int i,
            double max,
            List<Articulo> actual,
            List<List<Articulo>> resultado
    ) {
        if (base == null) return;

        double suma = sumaPreciosRecursiva(actual, 0);
        if (suma > max) return;

        if (i >= base.size()) {
            resultado.add(new ArrayList<>(actual));
            return;
        }

        // no coger
        combinacionesPorPrecioRec(base, i + 1, max, actual, resultado);

        // coger
        actual.add(base.get(i));
        combinacionesPorPrecioRec(base, i + 1, max, actual, resultado);
        actual.remove(actual.size() - 1);
    }
}

