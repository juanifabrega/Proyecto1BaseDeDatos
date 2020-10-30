package parquimetros;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import quick.dbtable.DBTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaInspector extends JInternalFrame {

	
	private static final long serialVersionUID = 1L;
	private BDD bdd;
	private DBTable tabla;
	private LinkedList<String> listaPatentes;
	
	
	public VentanaInspector() {
		 super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		 setVisible(false);
		 bdd = new BDD();
		 listaPatentes = new LinkedList<String>();
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
        
        JButton btnAgregarPatente = new JButton("Agregar patente");
        btnAgregarPatente.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		listaPatentes.add(JOptionPane.showInputDialog("Agregar patente:"));
        	    System.out.println("Patente agregada: " + listaPatentes.getLast());        		
        	}
        });
        getContentPane().add(btnAgregarPatente);
        
	}
	
	

	public boolean loguear(int legajo, String clave) {
		boolean exito = false;			
		
		//////////////////////////////////////////////////////////////////
		// 																//
		//	Acá hay que encontrar los datos del inspector en la BDD.	//
		//  Si los datos son correctos: exito = true					//
		// 			   caso contrario: exito=false					    //
		//																//
		//////////////////////////////////////////////////////////////////
		
//		try {
//			ResultSet resultado = bdd.ejecutarSentencia("SELECT legajo, password FROM inspectores;");
//			while(resultado.next())
//				if(legajo == resultado.getInt(1)) {
//					bdd.ejecutarSentencia("SELECT password FROM inspectores WHERE legajo = "+ legajo +";").next()==clave
//					if(clave == resu)
//				}
//			
//			
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		
		
		
					
		
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

