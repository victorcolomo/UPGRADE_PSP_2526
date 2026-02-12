package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase que gestion la conexion a la BBDD
 */
public class DBConnection {

	// jdbc:mysql://[host]:[puerto]/[nombre_base_de_datos]
	private static final String URL = "jdbc:mysql://localhost:3306/empresa";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,USER,PASS);
	}
}
