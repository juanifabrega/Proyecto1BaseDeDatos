package parquimetros;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import quick.dbtable.DBTable;

public class VentanaInspector extends JInternalFrame {

	

    private BDD bdd;
	private DBTable tabla;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInspector frame = new VentanaInspector();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaInspector() {
		 super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		 setVisible(false);
		 bdd = new BDD();

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

}
