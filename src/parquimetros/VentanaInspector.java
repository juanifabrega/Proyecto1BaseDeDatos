package parquimetros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class VentanaInspector extends JInternalFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInspector window = new VentanaInspector();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInspector() {
		initialize();
		frame.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setEnabled(false);
		frame.setBounds(100, 100, 853, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
