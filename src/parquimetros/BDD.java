package parquimetros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BDD {

	
	/*
	 * Puse todas las variables estáticas, así no hay que estar instanciando a ésta clase.
	 * Y además aseguramos que no pueda haber más de 1 conexión en simultaneo.
	 * 
	 * No se si estará bien hecho así
	 */
	
	private static final String servidor = "localhost:3306";
	private static final String baseDeDatos = "parquimetros";
	private static final String url = "jdbc:mysql://"+ servidor + "/" + baseDeDatos;
							 //  "?serverTimezone=America/Argentina/Buenos_Aires";
	private static Connection conexion = null;
	
	
	
	public static Connection conectar(String usuario, String clave) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(url, usuario, clave);
		System.out.println("Conexión exitosa a la BDD Parquímetros con usuario "+usuario);			
		
		return conexion;
	}
		

	public static void desconectar() throws SQLException {		
		if (conexion != null)
			conexion.close();
			conexion = null;
	}
	
	
	public static Connection getConexion() {		
		return conexion;		
	}
	
}
