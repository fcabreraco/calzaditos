package Gestor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conectarBD.conexion;
import usuario.Usuario;

public class GestUsuario {

	private Connection con = this.ConexionGeneral(); // Instanciamos la conexión general

	// Metodo usado para la conexion en general con la DB
	public Connection ConexionGeneral() {
		try {
			// Se obtiene y retorna la conexión creada a la base de datos
			return conexion.getConnetion();
		} catch (Exception e) {
			System.out.println("Error de conexión: " + e);
		}
		return null;
	}

	/**
	 * 
	 * @param usu Usuario con los datos necesrios para el login (nombre de usuario y contraseña)
	 * @return El usuario (null o con datos) encontrado en la base de datos
	 */
	public Usuario obtenerUsuario(Usuario usu) {
		Usuario usuario = null;
		try {
			String sql = "SELECT * FROM user_login WHERE usuario = ? AND clave = ?;";
			String[] parametros = { usu.getUsuario(), usu.getclave() };
			ResultSet rs = this.Consultas(sql, parametros);

			while (rs.next()) {
				usuario = new Usuario(Long.parseLong(rs.getString(1)), rs.getString(2),
						Integer.parseInt(rs.getString(3)), Long.parseLong(rs.getString(4)), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
			}
			return usuario;
		} catch (Exception e) {
			System.out.println("Error opteniendo usuario: " + e);
		}
		return null;
	}

	// Se define el presente método con el objetivo de centralizar el consultar datos (Select function, Select View)
	/**
	 * 
	 * @param sql Sentencia SQL (String)
	 * @param parametros Array (String) con los parámetros a inclustar en la sentencia SQL
	 * @return Resultado de la consulta (ResultSet)
	 */
	public ResultSet Consultas(String sql, String[] parametros) {
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			for (int i = 0; i < parametros.length; i++) {
				pst.setString((i + 1), parametros[i]);
			}
			return pst.executeQuery();
		} catch (Exception e) {
			System.out.println("Error opteniendo usuario: " + e);
		}
		return null;
	}
	
	// Obtener usuario por identificación
	/**
	 * 
	 * @param Identificacion Identificación del usuario (String)
	 * @return ArrayList<Usuario> con el usuario que coincide
	 */
	public ArrayList<Usuario> getUsuarioPorIdentificacion(String Identificacion) {
		ArrayList<Usuario> ALU = new ArrayList<Usuario>(); // Array List Usuario (ALU)
		String parametros[] = { Identificacion };
		ResultSet rs = Consultas("SELECT * FROM user_login WHERE id = ?;", parametros);
		try {
			while (rs.next()) {
				ALU.add(new Usuario(Long.parseLong(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)),
						Long.parseLong(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8)));
			}
			return ALU;
		} catch (Exception e) {
		}
		return null;
	}
	
	// Se declara el actual método o función con el objetivo de ejecutar cualquier otra sentencia SQL (Insert, Delete, Update, Call Procedure)
	/**
	 * 
	 * @param sql Setntencia SQL (String)
	 * @param parametros a inclustar en la sentencia SQL (String[])
	 */
	public void EjecutaQuery(String sql, String[] parametros) {
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			for (int i = 0; i < parametros.length; i++) {
				pst.setString((i + 1), parametros[i]);
			}
			System.out.println(pst.execute());
		} catch (Exception e) {
			System.out.println("Error opteniendo usuario: " + e);
		}
	}
}