package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	private Connection con;
	
	public void initBD (String nombreBD) {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeBD() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void crearTablas() {
	    String sqlUsuarios = 
	        "CREATE TABLE IF NOT EXISTS Usuarios (" +
	        "nombre VARCHAR(15) PRIMARY KEY," +
	        "email VARCHAR(30)," +
	        "telefono INTEGER," +
	        "direccion VARCHAR(40)" +
	        ")";

	    String sqlArticulos = 
	        "CREATE TABLE IF NOT EXISTS Articulos (" +
	        "id CHAR(5) PRIMARY KEY," +
	        "nombre VARCHAR(15)," +
	        "numCantidad INTEGER," +
	        "talla CHAR(1)" +
	        ")";

	    try {
	        Statement st = con.createStatement();
	        st.execute(sqlUsuarios);
	        st.execute(sqlArticulos);
	        st.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void main(String[] args) {
		BD db = new BD();
		db.initBD("slap.db");
		db.crearTablas();
		db.closeBD();
	}
}
