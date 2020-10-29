package parquimetros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class VentanaInspector extends JInternalFrame {

	
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
		setBounds(100, 100, 450, 300);

	}

}
