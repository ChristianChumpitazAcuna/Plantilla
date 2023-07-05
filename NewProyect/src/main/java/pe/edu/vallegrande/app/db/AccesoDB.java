package pe.edu.vallegrande.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
	private AccesoDB() {
		// TODO Auto-generated constructor stub
	}

	public static Connection getConnection() throws SQLException {
		Connection cn = null;

		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String urlDB = "jdbc:sqlserver://localhost:1433;databaseName=dbPizzaHut;encrypt=true;TrustServerCertificate=True;";
		String user = "sa";
		String pass = "root";

		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			cn = DriverManager.getConnection(urlDB, user, pass);
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw new SQLException("No se econtro el driver de la DB");
		} catch (Exception e) {
			throw new SQLException("No se puede establecer conexion con la DB");
		}
		return cn;

	}
}
