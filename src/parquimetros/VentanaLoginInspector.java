package parquimetros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLoginInspector extends JDialog {


	private JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField;
	

	public VentanaLoginInspector(VentanaInspector ventanaInspector) {

		setTitle("Login inspector");
		setBounds(100, 100, 284, 114);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{82, 193, 0};
		gbl_contentPanel.rowHeights = new int[] {28, 23, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLegajo = new JLabel("Legajo");
			lblLegajo.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblLegajo = new GridBagConstraints();
			gbc_lblLegajo.fill = GridBagConstraints.BOTH;
			gbc_lblLegajo.insets = new Insets(0, 0, 5, 5);
			gbc_lblLegajo.gridx = 0;
			gbc_lblLegajo.gridy = 0;
			contentPanel.add(lblLegajo, gbc_lblLegajo);
		}
		{
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {	
			        char c = e.getKeyChar();
			        if (!(Character.isDigit(c) ||
			         	(c == KeyEvent.VK_BACK_SPACE) ||
			            (c == KeyEvent.VK_DELETE))) {
			             e.consume();
			        }
				}
			});
			
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.BOTH;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblClave = new JLabel("Clave");
			lblClave.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblClave = new GridBagConstraints();
			gbc_lblClave.fill = GridBagConstraints.BOTH;
			gbc_lblClave.insets = new Insets(0, 0, 0, 5);
			gbc_lblClave.gridx = 0;
			gbc_lblClave.gridy = 1;
			contentPanel.add(lblClave, gbc_lblClave);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.fill = GridBagConstraints.BOTH;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(passwordField, gbc_textField_1);
			passwordField.setColumns(10);
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
						

						String clave = new String(passwordField.getPassword());
						
						if(textField.getText().equals("") || clave.equals("")) {							
							JOptionPane.showMessageDialog(null,
				                    "No se pudo conectar a la base de datos.\n",
				                     "Error", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
					        passwordField.setText("");
						}
						else {
						
							int legajo = Integer.parseInt(textField.getText());
							try {
								if(ventanaInspector.loguear(legajo, clave)) {
									textField.setText("");
								    passwordField.setText("");
								    dispose(); 
									JOptionPane.showMessageDialog( 
										    null, 
										    "Se ha logueado exitosamente", 
										    "Bienvenido",
										    JOptionPane.INFORMATION_MESSAGE);									
								} else {
									JOptionPane.showMessageDialog(null,
								            "Los datos no son correctos.\n",
								             "Error", JOptionPane.ERROR_MESSAGE);
									textField.setText("");
								    passwordField.setText("");
								}
							} catch (HeadlessException ex) {							
								JOptionPane.showMessageDialog(null,
					                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
					                     "Error", JOptionPane.ERROR_MESSAGE);
						        System.out.println("Exception: " + ex.getMessage());
								textField.setText("");
						        passwordField.setText("");
							} catch (SQLException ex) {							
								JOptionPane.showMessageDialog(null,
					                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
					                     "Error", JOptionPane.ERROR_MESSAGE);
						        System.out.println("SQLException: " + ex.getMessage());
						        System.out.println("SQLState: " + ex.getSQLState());
						        System.out.println("VendorError: " + ex.getErrorCode());
								textField.setText("");
						        passwordField.setText("");
							} catch (ClassNotFoundException ex) {				
								JOptionPane.showMessageDialog(null,
					                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
					                     "Error", JOptionPane.ERROR_MESSAGE);
						        System.out.println("Exception: " + ex.getMessage());
								textField.setText("");
						        passwordField.setText("");
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
