package parquimetros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import quick.dbtable.DBTable;


public class BDD {

	private String driver;
	private String servidor;
	private String baseDeDatos;
	private String url;
	private String usuario;
	private String clave;
	private Connection conexion;
	private Statement stmt;
	private ResultSet rs;
	
	
	public BDD() {
		driver = "com.mysql.cj.jdbc.Driver";
		servidor = "localhost:3306";
		baseDeDatos = "parquimetros";
		url = "jdbc:mysql://"+ servidor + "/" + baseDeDatos + "?serverTimezone=America/Argentina/Buenos_Aires";
		conexion = null;
	}
	
	
	public Connection conectar(String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		usuario = user;
		clave = password;
		conexion = DriverManager.getConnection(url, usuario, clave);
		System.out.println("BDD conectada con usuario \""+usuario+"\".");
		return conexion;
	}
	
	
	public void vincularTabla(DBTable tabla) throws ClassNotFoundException, SQLException {
		tabla.connectDatabase(driver, url, usuario, clave);
	}
		

	public void desconectar() throws SQLException {		
		if (conexion != null) {
			conexion.close();
			conexion = null;
			System.out.println("BDD desconectada.");
		}
	}
	
	
	public ResultSet ejecutarSentencia(String sql) throws SQLException {
		stmt = conexion.createStatement();
		rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public void limpiarSentencia() throws SQLException {
		stmt.close();
		rs.close();
	}
	
	public void ejecutarModificacion(String sql) throws SQLException {
		stmt = conexion.createStatement();
		stmt.executeUpdate(sql);		
	}
	
	public void limpiarModificacion() throws SQLException{
		stmt.close();
	}
	
	public Connection getConexion() {		
		return conexion;		
	}
	
	public boolean estaConectado() {
		return conexion != null;
	}
	
}
