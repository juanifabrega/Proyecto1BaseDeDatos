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
import java.sql.SQLException;

public class VentanaLoginAdmin extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;


	public VentanaLoginAdmin(VentanaConsultas vConsultas) {
		
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
						
						try {
							String clave = new String(passwordField.getPassword());
							vConsultas.loguear(clave);
		                    dispose(); // cierro la ventana
							JOptionPane.showMessageDialog( // muestro ventana de logueo exitoso
								    null, 
								    "Se ha logueado exitosamente", 
								    "Bienvenido",
								    JOptionPane.INFORMATION_MESSAGE);		                
		                    
						} catch (SQLException ex) {
							
							JOptionPane.showMessageDialog(null,
				                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
				                     "Error", JOptionPane.ERROR_MESSAGE);
					        System.out.println("SQLException: " + ex.getMessage());
					        System.out.println("SQLState: " + ex.getSQLState());
					        System.out.println("VendorError: " + ex.getErrorCode());
					        passwordField.setText("");
					        
						} catch (ClassNotFoundException ex) {
							JOptionPane.showMessageDialog(null,
				                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
				                     "Error", JOptionPane.ERROR_MESSAGE);
					        System.out.println("ClassNotFoundException: " + ex.getMessage());
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
	
}
