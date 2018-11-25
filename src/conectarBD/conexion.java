package conectarBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	public static Connection getConnetion() {// java.sql

		Connection con = null; // Connectionjava.sql https://youtu.be/V1IeAjvWdE4

		try {
			Class.forName("com.mysql.jdbc.Driver");// cargar el driver de SQL
			String url = "jdbc:mysql://localhost/calzaditos";
			String user = "root";
			String pass = "12345678";
			// String bd = "user";

			con = DriverManager.getConnection(url, user, pass);// quitar el error con clic en la segunda opc de la "x"
			System.out.println("conectado");
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println("Error conexion Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error conexion BD");
			e.printStackTrace();
		}

		return null;
	}

}
