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
	

	public boolean loguear(int legajo, String clave) throws SQLException, ClassNotFoundException {
		boolean exito;			
		bdd.conectar("inspector", "inspector");	
		
		String consultaSql = "SELECT legajo, password " +
				"FROM inspectores " +
				"WHERE legajo="+legajo+" AND " +
				"password=md5('"+clave+"');";
		
		ResultSet rs = bdd.ejecutarSentencia(consultaSql);
		exito = rs.next();
		
		try { // ésto es para mostrar la ventana cuando los datos son correctos
	    	if(exito) {
	    		System.out.println("aparece ventana");
	    		setVisible(true);
	    		setMaximum(true);
	    	}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}		
		return exito;
	}
	
	
	

}

