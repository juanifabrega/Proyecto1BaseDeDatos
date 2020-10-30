package parquimetros;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import quick.dbtable.DBTable;

public class VentanaInspector extends JInternalFrame {

	
	private static final long serialVersionUID = 1L;
	private BDD bdd;
	private DBTable tabla;
	
	
	public VentanaInspector() {
		 super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		 setVisible(false);
		 bdd = new BDD();
		 try {
			bdd.conectar("inspector", "inspector");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
                     "Error", JOptionPane.ERROR_MESSAGE);
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
		}
		 
		 addInternalFrameListener(new InternalFrameAdapter() {
		 	@Override
		 	public void internalFrameClosing(InternalFrameEvent e) {
		 		dispose();
		 		try {
		 			bdd.desconectar();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,
		                    "No se pudo desconectar de la base de datos.\n" + ex.getMessage(),
		                     "Error", JOptionPane.ERROR_MESSAGE);
			        System.out.println("SQLException: " + ex.getMessage());
			        System.out.println("SQLState: " + ex.getSQLState());
			        System.out.println("VendorError: " + ex.getErrorCode());
				}
		 	}
		 });		 

		setBounds(100, 100, 853, 521);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocation(0, -12);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
	}
	
	

	public boolean loguear(String legajo, String clave) {
		boolean exito = true;
				
		
		//////////////////////////////////////////////////////////////////
		// 																//
		//	Acá hay que encontrar los datos del inspector en la BDD.	//
		//  Si los datos son correctos: exito = true					//
		// 			   caso contrario: exito=false					    //
		//																//
		//////////////////////////////////////////////////////////////////
		
					
		
		try { // ésto es para mostrar la ventana cuando los datos son correctos
	    	if(exito) {
	    		setVisible(true);
	    		setMaximum(true);
	    	}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}		
		return exito;
	}
	
	
	

}

