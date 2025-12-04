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
        	
        	String sqlUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT UNIQUE,"
                    + "contrasenia TEXT"
                    + ")";
            st.execute(sqlUsuarios);

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

            System.out.println("Tablas creadas (si no existían)");

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

    // --------- MÉTODOS CARGAR ---------

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

}



