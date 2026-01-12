package clases;

import java.util.ArrayList;
import java.util.List;



public final class RecursividadSLAP {

    private RecursividadSLAP() {}

    // =========================================================
    // ===================== AUTOTEST (USADO) ===================
    // =========================================================

    /**
     * Se llama desde Main. No toca BD ni UI, solo calcula cosas recursivas
     * para demostrar que está en uso.
     */
    public static void autoTest(MainArticulos ma) {
        if (ma == null) return;

        // 1) Crear un texto de catálogo recursivo y contar líneas (recursivo)
        String texto = ma.catalogoComoTextoRecursivo();
        int numLineas = contarLineasRecursivo(texto);

        // 2) Hash recursivo del catálogo (para demostrar procesamiento)
        long hash = hashTextoRecursivo(texto);

        // 3) Contar caracteres no blancos (recursivo)
        int noBlancos = contarNoBlancosRecursivo(texto);

        // 4) Solo imprimir para evidenciar ejecución (sin afectar a UI)
        System.out.println("[RecursividadSLAP] Catálogo -> lineas=" + numLineas
                + " | hash=" + hash + " | noBlancos=" + noBlancos);

        // 5) Pequeña demo de búsqueda recursiva (si existe código 201, devuelve algo)
        Articulo a = ma.buscarPorCodigoRecursivo(201);
        if (a != null) {
            String normalizada = normalizarEspaciosRecursivo(a.getDesc());
            System.out.println("[RecursividadSLAP] Ejemplo normalizado: " + normalizada);
        }
    }

    // =========================================================
    // ===================== STRINGS RECURSIVOS =================
    // =========================================================

    public static int contarLineasRecursivo(String s) {
        if (s == null || s.isEmpty()) return 0;
        // Si no hay saltos de línea, es 1 línea
        return 1 + contarLineasDesdeRecursivo(s, 0);
    }

    private static int contarLineasDesdeRecursivo(String s, int i) {
        if (s == null) return 0;
        if (i >= s.length()) return 0;

        char c = s.charAt(i);
        if (c == '\n') {
            return 1 + contarLineasDesdeRecursivo(s, i + 1);
        }
        return contarLineasDesdeRecursivo(s, i + 1);
    }

    public static int contarNoBlancosRecursivo(String s) {
        if (s == null) return 0;
        return contarNoBlancosDesdeRecursivo(s, 0);
    }

    private static int contarNoBlancosDesdeRecursivo(String s, int i) {
        if (i >= s.length()) return 0;

        char c = s.charAt(i);
        int suma = (Character.isWhitespace(c) ? 0 : 1);
        return suma + contarNoBlancosDesdeRecursivo(s, i + 1);
    }

    public static long hashTextoRecursivo(String s) {
        if (s == null) return 0L;
        // Hash simple: (hash * 131) + char
        return hashTextoDesdeRecursivo(s, 0, 0L);
    }

    private static long hashTextoDesdeRecursivo(String s, int i, long acc) {
        if (i >= s.length()) return acc;
        long nuevo = (acc * 131L) + (long) s.charAt(i);
        return hashTextoDesdeRecursivo(s, i + 1, nuevo);
    }

    public static String normalizarEspaciosRecursivo(String s) {
        if (s == null) return null;
        // 1) recortar espacios a los lados (recursivo)
        String trimmed = trimIzqRecursivo(trimDerRecursivo(s));
        // 2) colapsar espacios múltiples (recursivo)
        return colapsarEspaciosRecursivo(trimmed);
    }

    private static String trimIzqRecursivo(String s) {
        if (s == null || s.isEmpty()) return s;
        if (!Character.isWhitespace(s.charAt(0))) return s;
        return trimIzqRecursivo(s.substring(1));
    }

    private static String trimDerRecursivo(String s) {
        if (s == null || s.isEmpty()) return s;
        int last = s.length() - 1;
        if (!Character.isWhitespace(s.charAt(last))) return s;
        return trimDerRecursivo(s.substring(0, last));
    }

    private static String colapsarEspaciosRecursivo(String s) {
        if (s == null || s.length() <= 1) return s;
        return colapsarEspaciosDesdeRecursivo(s, 0, new StringBuilder()).toString();
    }

    private static StringBuilder colapsarEspaciosDesdeRecursivo(String s, int i, StringBuilder sb) {
        if (i >= s.length()) return sb;

        char c = s.charAt(i);

        // Si hay espacio, añadir solo si el anterior no es espacio
        if (Character.isWhitespace(c)) {
            if (sb.length() == 0 || !Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.append(' ');
            }
            return colapsarEspaciosDesdeRecursivo(s, i + 1, sb);
        }

        sb.append(c);
        return colapsarEspaciosDesdeRecursivo(s, i + 1, sb);
    }

    public static boolean contieneSubcadenaRecursivo(String texto, String patron) {
        if (texto == null || patron == null) return false;
        if (patron.isEmpty()) return true;
        if (texto.length() < patron.length()) return false;
        return contieneDesdeRecursivo(texto, patron, 0);
    }

    private static boolean contieneDesdeRecursivo(String texto, String patron, int i) {
        if (i > texto.length() - patron.length()) return false;
        if (matchEnRecursivo(texto, patron, i, 0)) return true;
        return contieneDesdeRecursivo(texto, patron, i + 1);
    }

    private static boolean matchEnRecursivo(String texto, String patron, int iTexto, int iPat) {
        if (iPat >= patron.length()) return true;
        if (iTexto + iPat >= texto.length()) return false;
        if (texto.charAt(iTexto + iPat) != patron.charAt(iPat)) return false;
        return matchEnRecursivo(texto, patron, iTexto, iPat + 1);
    }

    // =========================================================
    // ===================== NÚMEROS RECURSIVOS =================
    // =========================================================

    public static long factorialRecursivo(int n) {
        if (n < 0) return 0;
        if (n <= 1) return 1;
        return n * factorialRecursivo(n - 1);
    }

    public static long potenciaRecursiva(long base, int exp) {
        if (exp < 0) return 0;
        if (exp == 0) return 1;
        return base * potenciaRecursiva(base, exp - 1);
    }

    public static int sumaHastaRecursivo(int n) {
        if (n <= 0) return 0;
        return n + sumaHastaRecursivo(n - 1);
    }

    public static int maximoArrayRecursivo(int[] a) {
        if (a == null || a.length == 0) return Integer.MIN_VALUE;
        return maximoArrayDesdeRecursivo(a, 0, a[0]);
    }

    private static int maximoArrayDesdeRecursivo(int[] a, int i, int max) {
        if (i >= a.length) return max;
        int nuevoMax = (a[i] > max ? a[i] : max);
        return maximoArrayDesdeRecursivo(a, i + 1, nuevoMax);
    }

    // =========================================================
    // ===================== LISTAS RECURSIVAS ==================
    // =========================================================

    public static <T> int sizeRecursivo(List<T> lista) {
        if (lista == null) return 0;
        return sizeDesdeRecursivo(lista, 0);
    }

    private static <T> int sizeDesdeRecursivo(List<T> lista, int i) {
        if (i >= lista.size()) return 0;
        return 1 + sizeDesdeRecursivo(lista, i + 1);
    }

    public static <T> T getRecursivo(List<T> lista, int index) {
        if (lista == null) return null;
        if (index < 0 || index >= lista.size()) return null;
        return getDesdeRecursivo(lista, 0, index);
    }

    private static <T> T getDesdeRecursivo(List<T> lista, int i, int objetivo) {
        if (i >= lista.size()) return null;
        if (i == objetivo) return lista.get(i);
        return getDesdeRecursivo(lista, i + 1, objetivo);
    }

    public static <T> List<T> copiarRecursivo(List<T> lista) {
        List<T> out = new ArrayList<>();
        if (lista == null) return out;
        copiarDesdeRecursivo(lista, 0, out);
        return out;
    }

    private static <T> void copiarDesdeRecursivo(List<T> lista, int i, List<T> out) {
        if (i >= lista.size()) return;
        out.add(lista.get(i));
        copiarDesdeRecursivo(lista, i + 1, out);
    }

    public static List<Articulo> filtrarPorPrecioMinRecursivo(List<Articulo> lista, double min) {
        List<Articulo> out = new ArrayList<>();
        if (lista == null) return out;
        filtrarPorPrecioMinDesdeRecursivo(lista, 0, min, out);
        return out;
    }

    private static void filtrarPorPrecioMinDesdeRecursivo(List<Articulo> lista, int i, double min, List<Articulo> out) {
        if (i >= lista.size()) return;

        Articulo a = lista.get(i);
        if (a != null && a.getPrecio() >= min) {
            out.add(a);
        }

        filtrarPorPrecioMinDesdeRecursivo(lista, i + 1, min, out);
    }

    public static double sumaPreciosRecursivo(List<Articulo> lista) {
        if (lista == null) return 0.0;
        return sumaPreciosDesdeRecursivo(lista, 0);
    }

    private static double sumaPreciosDesdeRecursivo(List<Articulo> lista, int i) {
        if (i >= lista.size()) return 0.0;
        Articulo a = lista.get(i);
        double p = (a == null ? 0.0 : a.getPrecio());
        return p + sumaPreciosDesdeRecursivo(lista, i + 1);
    }

    public static Articulo buscarCodigoRecursivo(List<Articulo> lista, int codigo) {
        if (lista == null) return null;
        return buscarCodigoDesdeRecursivo(lista, 0, codigo);
    }

    private static Articulo buscarCodigoDesdeRecursivo(List<Articulo> lista, int i, int codigo) {
        if (i >= lista.size()) return null;
        Articulo a = lista.get(i);
        if (a != null && a.getCodigo() == codigo) return a;
        return buscarCodigoDesdeRecursivo(lista, i + 1, codigo);
    }

    // =========================================================
    // ===================== ORDENACIÓN RECURSIVA ===============
    // =========================================================

    /**
     * Ordenación recursiva por precio (tipo merge sort, pero muy explícita).
     * Devuelve una lista nueva ordenada de menor a mayor.
     */
    public static List<Articulo> ordenarPorPrecioRecursivo(List<Articulo> lista) {
        if (lista == null) return new ArrayList<>();
        if (lista.size() <= 1) return copiarRecursivo(lista);

        int mid = lista.size() / 2;
        List<Articulo> izq = subListaRecursiva(lista, 0, mid);
        List<Articulo> der = subListaRecursiva(lista, mid, lista.size());

        List<Articulo> o1 = ordenarPorPrecioRecursivo(izq);
        List<Articulo> o2 = ordenarPorPrecioRecursivo(der);

        return mergePorPrecioRecursivo(o1, o2);
    }

    /**
     * Crea una sublista [from, to) recursivamente.
     */
    public static List<Articulo> subListaRecursiva(List<Articulo> lista, int from, int to) {
        List<Articulo> out = new ArrayList<>();
        if (lista == null) return out;
        if (from < 0) from = 0;
        if (to > lista.size()) to = lista.size();
        if (from >= to) return out;

        subListaDesdeRecursivo(lista, from, to, out);
        return out;
    }

    private static void subListaDesdeRecursivo(List<Articulo> lista, int i, int to, List<Articulo> out) {
        if (i >= to) return;
        out.add(lista.get(i));
        subListaDesdeRecursivo(lista, i + 1, to, out);
    }

    /**
     * Merge recursivo de dos listas ya ordenadas.
     */
    public static List<Articulo> mergePorPrecioRecursivo(List<Articulo> a, List<Articulo> b) {
        List<Articulo> out = new ArrayList<>();
        mergeDesdeRecursivo(a, 0, b, 0, out);
        return out;
    }

    private static void mergeDesdeRecursivo(List<Articulo> a, int ia, List<Articulo> b, int ib, List<Articulo> out) {
        boolean finA = (a == null || ia >= a.size());
        boolean finB = (b == null || ib >= b.size());

        if (finA && finB) return;

        if (finA) {
            out.add(b.get(ib));
            mergeDesdeRecursivo(a, ia, b, ib + 1, out);
            return;
        }

        if (finB) {
            out.add(a.get(ia));
            mergeDesdeRecursivo(a, ia + 1, b, ib, out);
            return;
        }

        Articulo A = a.get(ia);
        Articulo B = b.get(ib);

        double pA = (A == null ? Double.MAX_VALUE : A.getPrecio());
        double pB = (B == null ? Double.MAX_VALUE : B.getPrecio());

        if (pA <= pB) {
            out.add(A);
            mergeDesdeRecursivo(a, ia + 1, b, ib, out);
        } else {
            out.add(B);
            mergeDesdeRecursivo(a, ia, b, ib + 1, out);
        }
    }

    // =========================================================
    // ===================== "ÁRBOL" RECURSIVO ==================
    // =========================================================
    // Para sumar líneas y tener un ejemplo de estructura recursiva real.

    public static final class NodoArticulo {
        private Articulo dato;
        private NodoArticulo izq;
        private NodoArticulo der;

        public NodoArticulo(Articulo dato) {
            this.dato = dato;
        }

        public Articulo getDato() { return dato; }
        public NodoArticulo getIzq() { return izq; }
        public NodoArticulo getDer() { return der; }

        private void setIzq(NodoArticulo n) { this.izq = n; }
        private void setDer(NodoArticulo n) { this.der = n; }
    }

    /**
     * Inserta por código en un árbol binario (recursivo).
     */
    public static NodoArticulo insertarEnArbolRecursivo(NodoArticulo raiz, Articulo a) {
        if (a == null) return raiz;
        if (raiz == null) return new NodoArticulo(a);

        int cRaiz = (raiz.getDato() == null ? Integer.MIN_VALUE : raiz.getDato().getCodigo());
        int c = a.getCodigo();

        if (c <= cRaiz) {
            raiz.setIzq(insertarEnArbolRecursivo(raiz.getIzq(), a));
        } else {
            raiz.setDer(insertarEnArbolRecursivo(raiz.getDer(), a));
        }
        return raiz;
    }

    public static NodoArticulo construirArbolPorCodigoRecursivo(List<Articulo> lista) {
        if (lista == null || lista.isEmpty()) return null;
        return construirArbolDesdeRecursivo(lista, 0, null);
    }

    private static NodoArticulo construirArbolDesdeRecursivo(List<Articulo> lista, int i, NodoArticulo raiz) {
        if (i >= lista.size()) return raiz;
        raiz = insertarEnArbolRecursivo(raiz, lista.get(i));
        return construirArbolDesdeRecursivo(lista, i + 1, raiz);
    }

    /**
     * Recorrido in-order del árbol para devolver una lista ordenada por código.
     */
    public static List<Articulo> inorderRecursivo(NodoArticulo raiz) {
        List<Articulo> out = new ArrayList<>();
        inorderDesdeRecursivo(raiz, out);
        return out;
    }

    private static void inorderDesdeRecursivo(NodoArticulo n, List<Articulo> out) {
        if (n == null) return;
        inorderDesdeRecursivo(n.getIzq(), out);
        out.add(n.getDato());
        inorderDesdeRecursivo(n.getDer(), out);
    }
}
