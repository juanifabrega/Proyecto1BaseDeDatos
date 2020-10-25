package parquimetros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class BDD {

	
	private final String servidor = "localhost:3306";
	private final String baseDeDatos = "parquimetros";
	private final String url = "jdbc:mysql://"+ servidor + "/" + baseDeDatos +
							   "?serverTimezone=America/Argentina/Buenos_Aires";
	private Connection conexion = null;
	
	
	
	public Connection conectar(String usuario, String clave) {
		
		try {
			
			conexion = DriverManager.getConnection(url, usuario, clave);
			System.out.println("Conexión exitosa a la BDD Parquímetros con usuario "+usuario);
			
		}
		
		catch(SQLException ex) {
			
			JOptionPane.showMessageDialog(null,
                    "Se produjo un error al intentar conectarse a la base de datos.\n" + ex.getMessage(),
                     "Error", JOptionPane.ERROR_MESSAGE);
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	        
		} 
		
		return conexion;
	}
	
	
	
	

	public void desconectar() {
		
		if (conexion != null) 			
			try {
				
				conexion.close();
				conexion = null;
				
	         }		
	         catch(SQLException ex){
	        	 
	             JOptionPane.showMessageDialog(null,
	                     "Se produjo un error al intentar desconectarse de la base de datos.\n" + ex.getMessage(),
	                      "Error", JOptionPane.ERROR_MESSAGE);
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            
	         }		
	}
	
}
