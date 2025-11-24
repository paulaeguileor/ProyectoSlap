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
		String sqlUsuario = "CREATE TABLE IF NOT EXISTS Usuario("
				+"dni VARCHAR(9) PRIMARY KEY,"
				+"nom VARCHAR(20),"
				+"edad INTEGER)";
		try {
			Statement st = con.createStatement();
			st.execute(sqlUsuario);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
