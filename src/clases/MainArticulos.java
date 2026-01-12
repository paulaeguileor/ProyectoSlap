package clases;

import java.util.ArrayList;
import java.util.List;

import BD.BD;

public class MainArticulos {

    private List<Abrigo> listaAbrigos;
    private List<Bolso> listaBolsos;
    private List<Calzado> listaCalzado;
    private List<Camisa> listaCamisas;
    private List<Jersey> listaJerseis;
    private List<Pantalon> listaPantalones;
    private List<Vestido> listaVestidos;

    public MainArticulos() {
        listaAbrigos = new ArrayList<>();
        listaBolsos = new ArrayList<>();
        listaCalzado = new ArrayList<>();
        listaCamisas = new ArrayList<>();
        listaJerseis = new ArrayList<>();
        listaPantalones = new ArrayList<>();
        listaVestidos = new ArrayList<>();

        // ---------- CAMISAS ----------
        listaCamisas.add(new Camisa(201, "Camisa beige con pechera estampada", Color.BEIGE, 89.90));
        listaCamisas.add(new Camisa(202, "Camisa estampado boho tonos cálidos", Color.MULTICOLOR, 92.50));
        listaCamisas.add(new Camisa(203, "Camisa estampado boho en tonos tierra", Color.MULTICOLOR, 95.00));
        listaCamisas.add(new Camisa(204, "Camisa blanca manga larga de popelín", Color.BLANCO, 84.99));
        listaCamisas.add(new Camisa(205, "Camisa vaquera con manga abullonada y lazo en la cintura", Color.AZUL, 89.00));
        listaCamisas.add(new Camisa(206, "Camisa color rojo medio escote en V", Color.ROJO, 80.00));
        listaCamisas.add(new Camisa(207, "Camisa estampado leopardo en tonos khaki", Color.KHAKI, 93.40));
        listaCamisas.add(new Camisa(208, "Camisa estampada con volantes y hombro descubierto", Color.MULTICOLOR, 87.75));
        listaCamisas.add(new Camisa(209, "Camisa verde caqui de manga francesa", Color.KHAKI, 82.60));
        listaCamisas.add(new Camisa(210, "Camisa estampado boho vibrante", Color.MULTICOLOR, 90.80));
        listaCamisas.add(new Camisa(211, "Camisa azul claro de popelín", Color.AZUL, 81.00));
        listaCamisas.add(new Camisa(212, "Camisa color marino escote en V", Color.AZUL, 88.00));

        // ---------- PANTALONES ----------
        listaPantalones.add(new Pantalon(301, "Jean blanco roto", Color.BLANCO, 100.00));
        listaPantalones.add(new Pantalon(302, "Pantalón de traje negro acampanado", Color.NEGRO, 129.90));
        listaPantalones.add(new Pantalon(303, "Pantalón de traje beige", Color.BEIGE, 119.00));
        listaPantalones.add(new Pantalon(304, "Pantalón jean negro", Color.NEGRO, 105.00));
        listaPantalones.add(new Pantalon(305, "Jean azul clásico", Color.MULTICOLOR, 112.50));
        listaPantalones.add(new Pantalon(306, "Jean azul lavado", Color.MULTICOLOR, 109.90));
        listaPantalones.add(new Pantalon(307, "Pantalón fluido estampado", Color.MULTICOLOR, 123.00));
        listaPantalones.add(new Pantalon(308, "Jean azul con rayas", Color.MULTICOLOR, 118.75));
        listaPantalones.add(new Pantalon(309, "Jean negro con botones", Color.NEGRO, 107.80));
        listaPantalones.add(new Pantalon(310, "Jean negro ancho", Color.NEGRO, 115.00));
        listaPantalones.add(new Pantalon(311, "Jean acampanado negro", Color.NEGRO, 130.00));
        listaPantalones.add(new Pantalon(312, "Pantalón fluido estampado estilo boho", Color.MULTICOLOR, 125.50));

        // ---------- JERSEIS ----------
        listaJerseis.add(new Jersey(401, "Jersey rayas asimétrico", Color.MULTICOLOR, 89.90));
        listaJerseis.add(new Jersey(402, "Jersey beige clásico", Color.BEIGE, 85.00));
        listaJerseis.add(new Jersey(403, "Jersey marrón punto angora", Color.MARRÓN, 95.50));
        listaJerseis.add(new Jersey(404, "Jersey caqui cuello alto", Color.KHAKI, 90.00));
        listaJerseis.add(new Jersey(405, "Jersey negro cuello alto", Color.NEGRO, 92.75));
        listaJerseis.add(new Jersey(406, "Cárdigan punto calado beige", Color.BEIGE, 88.00));
        listaJerseis.add(new Jersey(407, "Jersey punto rosa estilo alpaca", Color.ROSA, 94.50));
        listaJerseis.add(new Jersey(408, "Jersey morado básico", Color.MORADO, 80.00));
        listaJerseis.add(new Jersey(409, "Jersey gris básico", Color.GRIS, 82.50));
        listaJerseis.add(new Jersey(410, "Jersey gris vuelo", Color.GRIS, 87.00));
        listaJerseis.add(new Jersey(411, "Jersey cashmere beige", Color.BEIGE, 105.00));
        listaJerseis.add(new Jersey(412, "Jersey alpaca gris", Color.GRIS, 99.90));

        // ---------- ABRIGOS ----------
        listaAbrigos.add(new Abrigo(501, "Blazer ante marrón", Color.MARRÓN, 400.00));
        listaAbrigos.add(new Abrigo(502, "Chaleco vaquero gris", Color.GRIS, 185.00));
        listaAbrigos.add(new Abrigo(503, "Chaqueta manga abullonada marrón", Color.MARRÓN, 220.00));
        listaAbrigos.add(new Abrigo(504, "Abrigo tipo trench corto azul marino", Color.AZUL, 410.00));
        listaAbrigos.add(new Abrigo(505, "Chaqueta vinilo morada", Color.MORADO, 400.00));
        listaAbrigos.add(new Abrigo(506, "Chaqueta estampada geométrica", Color.MULTICOLOR, 250.00));
        listaAbrigos.add(new Abrigo(507, "Abrigo largo gris jaspeado", Color.GRIS, 420.00));
        listaAbrigos.add(new Abrigo(508, "Abrigo marrón tipo batín", Color.MARRÓN, 400.00));
        listaAbrigos.add(new Abrigo(509, "Chaqueta beige de vestir", Color.BEIGE, 230.00));
        listaAbrigos.add(new Abrigo(510, "Abrigo marrón jaspeado con cinturón", Color.MARRÓN, 400.00));
        listaAbrigos.add(new Abrigo(511, "Chaqueta azul marino tipo chanel", Color.AZUL, 250.00));
        listaAbrigos.add(new Abrigo(512, "Abrigo marinero marrón", Color.MARRÓN, 400.00));

        // ---------- VESTIDOS ----------
        listaVestidos.add(new Vestido(601, "Vestido manga larga fluido estampado", Color.MULTICOLOR, 200.00));
        listaVestidos.add(new Vestido(602, "Vestido corto estampado", Color.MULTICOLOR, 190.00));
        listaVestidos.add(new Vestido(603, "Vestido palabra de honor estampado con volantes", Color.MULTICOLOR, 210.00));
        listaVestidos.add(new Vestido(604, "Vestido estampado largo", Color.MULTICOLOR, 220.00));
        listaVestidos.add(new Vestido(605, "Vestido rojo con aberturas", Color.ROJO, 230.00));
        listaVestidos.add(new Vestido(606, "Vestido negro con aberturas", Color.NEGRO, 230.00));
        listaVestidos.add(new Vestido(607, "Vestido lencero estampado leopardo", Color.KHAKI, 250.00));
        listaVestidos.add(new Vestido(608, "Vestido largo estampado boho", Color.MULTICOLOR, 270.00));
        listaVestidos.add(new Vestido(609, "Vestido largo khaki punto", Color.KHAKI, 200.00));
        listaVestidos.add(new Vestido(610, "Vestido vaquero con lazada", Color.MARRÓN, 210.00));
        listaVestidos.add(new Vestido(611, "Vestido corto punto cuello alto beige", Color.BEIGE, 185.00));
        listaVestidos.add(new Vestido(612, "Vestido corto punto cuello alto marrón", Color.MARRÓN, 185.00));

        // ---------- BOLSOS ----------
        listaBolsos.add(new Bolso(701, "Bolso flecos ante marrón", Color.MARRÓN, 310.00));
        listaBolsos.add(new Bolso(702, "Bolso flecos ante marrón oscuro", Color.MARRÓN, 320.00));
        listaBolsos.add(new Bolso(703, "Bolso flecos piel negro", Color.NEGRO, 350.00));
        listaBolsos.add(new Bolso(704, "Bolso ante marrón alargado", Color.MARRÓN, 330.00));
        listaBolsos.add(new Bolso(705, "Bolso marrón oscuro alargado ante", Color.MARRÓN, 340.00));
        listaBolsos.add(new Bolso(706, "Bolso marrón piel alargado", Color.MARRÓN, 355.00));
        listaBolsos.add(new Bolso(707, "Bolso marrón piel tipo saco", Color.MARRÓN, 360.00));
        listaBolsos.add(new Bolso(708, "Bolso marrón piel tipo saco", Color.MARRÓN, 360.00));
        listaBolsos.add(new Bolso(709, "Bolso beige rafia rayas", Color.BEIGE, 295.00));
        listaBolsos.add(new Bolso(710, "Bolso marrón ante mini tipo saco", Color.MARRÓN, 310.00));
        listaBolsos.add(new Bolso(711, "Bolso piel negro entrelazado", Color.NEGRO, 375.00));
        listaBolsos.add(new Bolso(712, "Bolso de mano 'pajarita' vinilo granate", Color.ROJO, 395.00));

        // ---------- CALZADO ----------
        listaCalzado.add(new Calzado(801, "Sandalia romana ante marrón", Color.MARRÓN, 220.00));
        listaCalzado.add(new Calzado(802, "Bota flecos ante marrón", Color.MARRÓN, 240.00));
        listaCalzado.add(new Calzado(803, "Bota campera marrón ante", Color.MARRÓN, 250.00));
        listaCalzado.add(new Calzado(804, "Botín ante marrón claro", Color.MARRÓN, 230.00));
        listaCalzado.add(new Calzado(805, "Bota ante marrón plana", Color.MARRÓN, 220.00));
        listaCalzado.add(new Calzado(806, "Bota estilo india flecos ante marrón", Color.MARRÓN, 260.00));
        listaCalzado.add(new Calzado(807, "Bota alta ante marrón", Color.MARRÓN, 280.00));
        listaCalzado.add(new Calzado(808, "Bota piel militar negra", Color.NEGRO, 300.00));
        listaCalzado.add(new Calzado(809, "Deportivas flecos marrón", Color.MARRÓN, 210.00));
        listaCalzado.add(new Calzado(810, "Cuña camel", Color.MARRÓN, 220.00));
        listaCalzado.add(new Calzado(811, "Bailarinas ganchillo rayas", Color.MULTICOLOR, 200.00));
        listaCalzado.add(new Calzado(812, "Sandalia tacón negra", Color.NEGRO, 250.00));
    }

    // =========================================================
    // ===================== RECURSIVIDAD =======================
    // =========================================================

    /**
     * Antes era un montón de for() seguidos.
     * Ahora, la inserción en BD se hace con recursividad:
     *  - Recorre categorías (lista de listas) de forma recursiva
     *  - Dentro de cada categoría recorre sus artículos también recursivo
     */
    public void rellenarBD(BD bd) {
        List<List<? extends Articulo>> categorias = getCategoriasComoListas();
        insertarCategoriasRecursivo(bd, categorias, 0);
    }

    /** Devuelve todas las categorías como una lista de listas (para poder recorrerlas recursivamente). */
    private List<List<? extends Articulo>> getCategoriasComoListas() {
        List<List<? extends Articulo>> categorias = new ArrayList<>();
        categorias.add(listaCamisas);
        categorias.add(listaPantalones);
        categorias.add(listaJerseis);
        categorias.add(listaAbrigos);
        categorias.add(listaVestidos);
        categorias.add(listaBolsos);
        categorias.add(listaCalzado);
        return categorias;
    }

    /** Recorre categorías de forma recursiva (una categoría por llamada). */
    private void insertarCategoriasRecursivo(BD bd, List<List<? extends Articulo>> categorias, int indiceCategoria) {
        if (bd == null) return;
        if (categorias == null) return;

        // Caso base: no quedan categorías
        if (indiceCategoria >= categorias.size()) {
            return;
        }

        // Insertar todos los artículos de esta categoría (también recursivo)
        List<? extends Articulo> categoriaActual = categorias.get(indiceCategoria);
        insertarArticulosDeUnaCategoriaRecursivo(bd, categoriaActual, 0);

        // Llamada recursiva: siguiente categoría
        insertarCategoriasRecursivo(bd, categorias, indiceCategoria + 1);
    }

    /** Recorre los artículos de una categoría (lista) de forma recursiva. */
    private void insertarArticulosDeUnaCategoriaRecursivo(BD bd, List<? extends Articulo> categoria, int indiceArticulo) {
        if (categoria == null) return;

        // Caso base: fin de la lista
        if (indiceArticulo >= categoria.size()) {
            return;
        }

        Articulo a = categoria.get(indiceArticulo);
        insertarArticuloEnBD(bd, a);

        // Llamada recursiva: siguiente artículo
        insertarArticulosDeUnaCategoriaRecursivo(bd, categoria, indiceArticulo + 1);
    }

    /**
     * Inserta un artículo en su tabla correcta. (No es recursivo, pero lo usamos dentro de la recursión)
     * Deja el código muy largo (a propósito) y clarito para el profe.
     */
    private void insertarArticuloEnBD(BD bd, Articulo a) {
        if (bd == null || a == null) return;

        if (a instanceof Camisa) {
            bd.insertarCamisa((Camisa) a);
            return;
        }

        if (a instanceof Pantalon) {
            bd.insertarPantalon((Pantalon) a);
            return;
        }

        if (a instanceof Jersey) {
            bd.insertarJersey((Jersey) a);
            return;
        }

        if (a instanceof Abrigo) {
            bd.insertarAbrigo((Abrigo) a);
            return;
        }

        if (a instanceof Vestido) {
            bd.insertarVestido((Vestido) a);
            return;
        }

        if (a instanceof Bolso) {
            bd.insertarBolso((Bolso) a);
            return;
        }

        if (a instanceof Calzado) {
            bd.insertarCalzado((Calzado) a);
            return;
        }
    }

    // -------------------- BÚSQUEDA RECURSIVA --------------------

    /** Busca un artículo por código recorriendo TODAS las categorías de forma recursiva. */
    public Articulo buscarPorCodigoRecursivo(int codigo) {
        List<List<? extends Articulo>> categorias = getCategoriasComoListas();
        return buscarEnCategoriasRecursivo(categorias, 0, codigo);
    }

    private Articulo buscarEnCategoriasRecursivo(List<List<? extends Articulo>> categorias, int indiceCategoria, int codigo) {
        if (categorias == null) return null;

        // Caso base: no quedan categorías
        if (indiceCategoria >= categorias.size()) {
            return null;
        }

        // Buscar en la categoría actual
        Articulo encontrado = buscarEnListaRecursivo(categorias.get(indiceCategoria), 0, codigo);
        if (encontrado != null) {
            return encontrado;
        }

        // Si no está, buscar en la siguiente categoría
        return buscarEnCategoriasRecursivo(categorias, indiceCategoria + 1, codigo);
    }

    private Articulo buscarEnListaRecursivo(List<? extends Articulo> lista, int indice, int codigo) {
        if (lista == null) return null;

        // Caso base
        if (indice >= lista.size()) {
            return null;
        }

        Articulo actual = lista.get(indice);
        if (actual != null && actual.getCodigo() == codigo) {
            return actual;
        }

        return buscarEnListaRecursivo(lista, indice + 1, codigo);
    }

    // -------------------- LISTADO RECURSIVO (STRING) --------------------

    /** Genera un listado “bonito” del catálogo usando recursividad (para demostrarla aún más). */
    public String catalogoComoTextoRecursivo() {
        List<List<? extends Articulo>> categorias = getCategoriasComoListas();
        StringBuilder sb = new StringBuilder();
        construirCatalogoTextoRecursivo(categorias, 0, sb);
        return sb.toString();
    }

    private void construirCatalogoTextoRecursivo(List<List<? extends Articulo>> categorias, int indiceCategoria, StringBuilder sb) {
        if (categorias == null || sb == null) return;

        if (indiceCategoria >= categorias.size()) {
            return;
        }

        // Título de categoría según índice (manual, largo y explícito)
        sb.append("=== CATEGORÍA ").append(indiceCategoria + 1).append(" ===").append("\n");
        if (indiceCategoria == 0) sb.append("Camisas\n");
        if (indiceCategoria == 1) sb.append("Pantalones\n");
        if (indiceCategoria == 2) sb.append("Jerséis\n");
        if (indiceCategoria == 3) sb.append("Abrigos\n");
        if (indiceCategoria == 4) sb.append("Vestidos\n");
        if (indiceCategoria == 5) sb.append("Bolsos\n");
        if (indiceCategoria == 6) sb.append("Calzado\n");

        // Listar artículos de esa categoría (recursivo)
        construirCategoriaTextoRecursivo(categorias.get(indiceCategoria), 0, sb);

        sb.append("\n");
        construirCatalogoTextoRecursivo(categorias, indiceCategoria + 1, sb);
    }

    private void construirCategoriaTextoRecursivo(List<? extends Articulo> lista, int indice, StringBuilder sb) {
        if (lista == null || sb == null) return;

        if (indice >= lista.size()) {
            return;
        }

        Articulo a = lista.get(indice);
        if (a != null) {
            sb.append(" - ")
              .append(a.getCodigo())
              .append(" | ")
              .append(a.getDesc())
              .append(" | ")
              .append(a.getColor())
              .append(" | ")
              .append(a.getPrecio())
              .append("\n");
        }

        construirCategoriaTextoRecursivo(lista, indice + 1, sb);
    }

    // =========================================================
    // ======================== GETTERS =========================
    // =========================================================
    public List<Abrigo> getListaAbrigos() { return listaAbrigos; }
    public List<Bolso> getListaBolsos() { return listaBolsos; }
    public List<Calzado> getListaCalzado() { return listaCalzado; }
    public List<Camisa> getListaCamisas() { return listaCamisas; }
    public List<Jersey> getListaJerseis() { return listaJerseis; }
    public List<Pantalon> getListaPantalones() { return listaPantalones; }
    public List<Vestido> getListaVestidos() { return listaVestidos; }
}

