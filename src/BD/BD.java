package BD;
//al principio de BD.java (imports necesarios)
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import clases.Calzado;
import clases.Color;

import java.sql.*;


import clases.*;  // Articulo, Camisa, Pantalon, Jersey, Abrigo, Vestido, Bolso, Calzado, Color

public class BD {

    private Connection con;

    public void initBD(String nombreBD) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
            System.out.println("Conexión abierta a " + nombreBD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBD() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearTablas() {
        try (Statement st = con.createStatement()) {

            // ---------- USUARIOS ----------
            String sqlUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT UNIQUE,"
                    + "contrasenia TEXT,"
                    + "direccion TEXT,"
                    + "email TEXT,"
                    + "telefono TEXT,"
                    + "rol TEXT DEFAULT 'CLIENTE'"
                    + ")";
            st.execute(sqlUsuarios);

            // ---------- ARTÍCULOS ----------
            String sqlCamisas = "CREATE TABLE IF NOT EXISTS Camisas ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlCamisas);

            String sqlPantalones = "CREATE TABLE IF NOT EXISTS Pantalones ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlPantalones);

            String sqlJerseys = "CREATE TABLE IF NOT EXISTS Jerseys ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlJerseys);

            String sqlAbrigos = "CREATE TABLE IF NOT EXISTS Abrigos ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlAbrigos);

            String sqlVestidos = "CREATE TABLE IF NOT EXISTS Vestidos ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlVestidos);

            String sqlBolsos = "CREATE TABLE IF NOT EXISTS Bolsos ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlBolsos);

            String sqlCalzados = "CREATE TABLE IF NOT EXISTS Calzados ("
                    + "codigo INTEGER PRIMARY KEY,"
                    + "descripcion TEXT,"
                    + "color TEXT,"
                    + "precio REAL)";
            st.execute(sqlCalzados);

            // ---------- PEDIDOS ----------
            String sqlPedidos = "CREATE TABLE IF NOT EXISTS Pedidos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "usuario TEXT,"
                    + "fecha TEXT,"        // YYYY-MM-DD
                    + "estado TEXT,"
                    + "importe REAL"
                    + ")";
            st.execute(sqlPedidos);

            // ---------- LÍNEAS DE PEDIDO ----------
            String sqlLineas = "CREATE TABLE IF NOT EXISTS LineasPedido ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "idPedido INTEGER,"
                    + "tipoArticulo TEXT,"
                    + "codigoArticulo INTEGER,"
                    + "descripcion TEXT,"
                    + "cantidad INTEGER,"
                    + "precioUnitario REAL,"
                    + "subtotal REAL,"
                    + "FOREIGN KEY(idPedido) REFERENCES Pedidos(id)"
                    + ")";
            st.execute(sqlLineas);

            // ---------- CARRITO ----------
            String sqlCarrito = "CREATE TABLE IF NOT EXISTS Carrito ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "usuario TEXT,"
                    + "tipoArticulo TEXT,"
                    + "codigoArticulo INTEGER,"
                    + "descripcion TEXT,"
                    + "cantidad INTEGER,"
                    + "precioUnitario REAL"
                    + ")";
            st.execute(sqlCarrito);

            // ---------- SOLICITUDES CAMBIO/DEVOLUCIÓN ----------
            String sqlSolicitudes = "CREATE TABLE IF NOT EXISTS Solicitudes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "idPedido INTEGER,"
                    + "usuario TEXT,"
                    + "fecha TEXT,"        // YYYY-MM-DD
                    + "tipo TEXT,"         // CAMBIO / DEVOLUCION
                    + "estado TEXT,"       // PENDIENTE / ACEPTADO / RECHAZADO
                    + "FOREIGN KEY(idPedido) REFERENCES Pedidos(id)"
                    + ")";
            st.execute(sqlSolicitudes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --------- MÉTODOS INSERTAR ---------
    
    public boolean insertarUsuario(String nombre, String contrasenia) {
        String sql = "INSERT OR IGNORE INTO Usuarios (nombre, contrasenia) VALUES (?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, contrasenia);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Para comprobar que existe el usuario:
    public boolean comprobarUsuario(String nombre, String contrasenia) {
        String sql = "SELECT * FROM Usuarios WHERE nombre = ? AND contrasenia = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, contrasenia);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean esAdmin(String nombre) {
        String sql = "SELECT rol FROM Usuarios WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String rol = rs.getString("rol");
                    return "ADMIN".equalsIgnoreCase(rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Para que nosotras podeamos ser admin
    public void hacerAdmin(String nombre) {
        String sql = "UPDATE Usuarios SET rol = 'ADMIN' WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public UsuariosInfo cargarUsuario(String nombre) {
        String sql = "SELECT * FROM Usuarios WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nombre");
                    String contrasenia = rs.getString("contrasenia");
                    String direccion = rs.getString("direccion");
                    String email = rs.getString("email");
                    String telefono = rs.getString("telefono");
                    return new UsuariosInfo(id, nom, contrasenia, direccion, email, telefono);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void actualizarUsuarioContacto(String nombre, String direccion, String email, String telefono) {
        String sql = "UPDATE Usuarios SET direccion = ?, email = ?, telefono = ? WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, direccion);
            ps.setString(2, email);
            ps.setString(3, telefono);
            ps.setString(4, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarCamisa(Camisa c) {
        String sql = "INSERT OR IGNORE INTO Camisas (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getCodigo());
            ps.setString(2, c.getDesc());
            ps.setString(3, c.getColor().name());
            ps.setDouble(4, c.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarPantalon(Pantalon p) {
        String sql = "INSERT OR IGNORE INTO Pantalones (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getDesc());
            ps.setString(3, p.getColor().name());
            ps.setDouble(4, p.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarJersey(Jersey j) {
        String sql = "INSERT OR IGNORE INTO Jerseys (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, j.getCodigo());
            ps.setString(2, j.getDesc());
            ps.setString(3, j.getColor().name());
            ps.setDouble(4, j.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarAbrigo(Abrigo a) {
        String sql = "INSERT OR IGNORE INTO Abrigos (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getCodigo());
            ps.setString(2, a.getDesc());
            ps.setString(3, a.getColor().name());
            ps.setDouble(4, a.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarVestido(Vestido v) {
        String sql = "INSERT OR IGNORE INTO Vestidos (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, v.getCodigo());
            ps.setString(2, v.getDesc());
            ps.setString(3, v.getColor().name());
            ps.setDouble(4, v.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarBolso(Bolso b) {
        String sql = "INSERT OR IGNORE INTO Bolsos (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getCodigo());
            ps.setString(2, b.getDesc());
            ps.setString(3, b.getColor().name());
            ps.setDouble(4, b.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarCalzado(Calzado c) {
        String sql = "INSERT OR IGNORE INTO Calzados (codigo, descripcion, color, precio) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getCodigo());
            ps.setString(2, c.getDesc());
            ps.setString(3, c.getColor().name());
            ps.setDouble(4, c.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Añadir línea al carrito
    public void agregarAlCarrito(String usuario, String tipoArticulo, int codigo, 
            String descripcion, int cantidad, double precioUnitario) {
		String sql = "INSERT INTO Carrito (usuario, tipoArticulo, codigoArticulo, descripcion, cantidad, precioUnitario) "
		+ "VALUES (?,?,?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuario);
			ps.setString(2, tipoArticulo);
			ps.setInt(3, codigo);
			ps.setString(4, descripcion);
			ps.setInt(5, cantidad);
			ps.setDouble(6, precioUnitario);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    // --------- MÉTODOS CARGAR ---------

    // Cargar carrito de un usuario
    public List<CarritoLinea> cargarCarrito(String usuario) {
        List<CarritoLinea> lista = new ArrayList<>();

        String sql = "SELECT * FROM Carrito WHERE usuario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tipo = rs.getString("tipoArticulo");
                    int codigo = rs.getInt("codigoArticulo");
                    String descripcion = rs.getString("descripcion");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precioUnitario");

                    lista.add(new CarritoLinea(id, usuario, tipo, codigo, descripcion, cantidad, precio));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Vaciar carrito de un usuario
    public void vaciarCarrito(String usuario) {
        String sql = "DELETE FROM Carrito WHERE usuario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bolso> cargarBolsos() {
        List<Bolso> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bolsos";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorStr = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color color = Color.valueOf(colorStr);
                Bolso b = new Bolso(codigo, descripcion, color, precio);
                lista.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Camisa> cargarCamisas() {
        List<Camisa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Camisas";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorStr = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color color = Color.valueOf(colorStr);
                Camisa c = new Camisa(codigo, descripcion, color, precio);
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Pantalon> cargarPantalones() {
        List<Pantalon> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pantalones";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorStr = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color color = Color.valueOf(colorStr);
                Pantalon p = new Pantalon(codigo, descripcion, color, precio);
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Jersey> cargarJerseys() {
        List<Jersey> lista = new ArrayList<>();
        String sql = "SELECT * FROM Jerseys";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorStr = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color color = Color.valueOf(colorStr);
                Jersey j = new Jersey(codigo, descripcion, color, precio);
                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Abrigo> cargarAbrigos() {
        List<Abrigo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Abrigos";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorStr = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color color = Color.valueOf(colorStr);
                Abrigo a = new Abrigo(codigo, descripcion, color, precio);
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public List<Vestido> cargarVestidos() {
        List<Vestido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vestidos";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorStr = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color color = Color.valueOf(colorStr);
                Vestido v = new Vestido(codigo, descripcion, color, precio);
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Calzado> cargarCalzado() {
        List<Calzado> lista = new ArrayList<>();

        String sql = "SELECT codigo, descripcion, color, precio FROM Calzados";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                String colorTxt = rs.getString("color");
                double precio = rs.getDouble("precio");

                Color colorEnum = Color.valueOf(colorTxt);

                Calzado c = new Calzado(codigo, descripcion, colorEnum, precio);
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    // Crea un pedido con las líneas del carrito de ese usuario
    public int crearPedidoDesdeCarrito(String usuario) {

        List<CarritoLinea> carrito = cargarCarrito(usuario);
        if (carrito.isEmpty()) return -1;

        double total = 0;
        for (CarritoLinea c : carrito) {
            total += c.getCantidad() * c.getPrecioUnitario();
        }

        String fecha = java.time.LocalDate.now().toString();
        String estado = "PENDIENTE";

        try {
            con.setAutoCommit(false); // Iniciamos transacción

            // --- Insertamos el pedido ---
            int idPedido = -1;
            String sqlPedido = "INSERT INTO Pedidos (usuario, fecha, estado, importe) VALUES (?,?,?,?)";

            try (PreparedStatement ps = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, usuario);
                ps.setString(2, fecha);
                ps.setString(3, estado);
                ps.setDouble(4, total);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) idPedido = rs.getInt(1);
                }
            }

            // --- Insertamos las líneas del pedido ---
            String sqlLineas = "INSERT INTO LineasPedido "
                             + "(idPedido, tipoArticulo, codigoArticulo, descripcion, cantidad, precioUnitario, subtotal) "
                             + "VALUES (?,?,?,?,?,?,?)";

            try (PreparedStatement ps = con.prepareStatement(sqlLineas)) {
                for (CarritoLinea c : carrito) {
                    ps.setInt(1, idPedido);
                    ps.setString(2, c.getTipoArticulo());
                    ps.setInt(3, c.getCodigoArticulo());
                    ps.setString(4, c.getDescripcion());
                    ps.setInt(5, c.getCantidad());
                    ps.setDouble(6, c.getPrecioUnitario());
                    ps.setDouble(7, c.getCantidad() * c.getPrecioUnitario());
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            // --- Vaciar carrito ---
            vaciarCarrito(usuario);

            con.setAutoCommit(true);
            return idPedido;

        } catch (SQLException e) {
            try { con.setAutoCommit(true); } catch (SQLException ex) {}
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<PedidoInfo> cargarPedidosUsuario(String usuario) {
        List<PedidoInfo> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedidos WHERE usuario = ? ORDER BY fecha DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new PedidoInfo(
                            rs.getInt("id"),
                            usuario,
                            rs.getString("fecha"),
                            rs.getString("estado"),
                            rs.getDouble("importe")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<LineaPedidoInfo> cargarLineasPedido(int idPedido) {
        List<LineaPedidoInfo> lista = new ArrayList<>();

        String sql = "SELECT * FROM LineasPedido WHERE idPedido = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new LineaPedidoInfo(
                            rs.getInt("id"),
                            idPedido,
                            rs.getString("tipoArticulo"),
                            rs.getInt("codigoArticulo"),
                            rs.getString("descripcion"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precioUnitario"),
                            rs.getDouble("subtotal")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public void crearSolicitud(String usuario, int idPedido, String tipo) {

        String fecha = java.time.LocalDate.now().toString();
        String estado = "PENDIENTE";

        String sql = "INSERT INTO Solicitudes (idPedido, usuario, fecha, tipo, estado) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setString(2, usuario);
            ps.setString(3, fecha);
            ps.setString(4, tipo);
            ps.setString(5, estado);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<SolicitudInfo> cargarSolicitudesUsuario(String usuario) {
        List<SolicitudInfo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicitudes WHERE usuario = ? ORDER BY fecha DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idPedido = rs.getInt("idPedido");
                    String fecha = rs.getString("fecha");
                    String tipo = rs.getString("tipo");
                    String estado = rs.getString("estado");
                    lista.add(new SolicitudInfo(id, idPedido, usuario, fecha, tipo, estado));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}



