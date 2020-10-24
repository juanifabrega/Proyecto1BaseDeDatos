package parquimetros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;

public class VentanaConsultas extends JInternalFrame {

/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConsultas frame = new VentanaConsultas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	
	public VentanaConsultas() {
		 super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		 
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	
	
	
	
}
