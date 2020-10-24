package parquimetros;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class VentanaLoginAdmin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private boolean logueoExitoso;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			VentanaLoginAdmin dialog = new VentanaLoginAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public VentanaLoginAdmin() {
		logueoExitoso = false;
		setTitle("Admin");
		setBounds(100, 100, 285, 94);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblContrasenia = new JLabel("Contraseña  ");
			contentPanel.add(lblContrasenia, BorderLayout.WEST);
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						char [] clave = {'a','d','m','i','n'};
						if(Arrays.equals(passwordField.getPassword(), clave)) {	
							dispose();
							JOptionPane.showMessageDialog(
								    null, 
								    "Se ha logueado exitosamente", 
								    "Bienvenido",
								    JOptionPane.INFORMATION_MESSAGE);
		                    dispose();
		                    VentanaPrincipal.setVentanaConsulta(true);
						}
						else {
							JOptionPane.showMessageDialog(
								    null, 
								    "La contraseña es incorrecta.", 
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
							passwordField.setText("");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean fueLogueado() {
		return logueoExitoso;
	}

}
