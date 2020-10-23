package parquimetros;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import batallas.VentanaConsultas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private JMenuBar menu ;
	private JMenuItem mntmConsultasDeAdmin;
	private JMenuItem mntmInspector;
	private VentanaConsultas vConsultas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		initialize();
		

        vConsultas = new VentanaConsultas();
        vConsultas.setVisible(false);
        frame.add(vConsultas);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 853, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		mntmConsultasDeAdmin = new JMenuItem("Admin");
		Border borde = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY);
		mntmConsultasDeAdmin.setBorder(borde);
		mntmConsultasDeAdmin.setAlignmentY(SwingConstants.CENTER);
		menu.add(mntmConsultasDeAdmin);
		mntmConsultasDeAdmin.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Color c = mntmConsultasDeAdmin.getBackground();
				Color newColor = new Color(c.getRed()-40,c.getGreen()-40,c.getBlue()-40);
				mntmConsultasDeAdmin.setBackground(newColor);
				
				Border borde = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
				mntmConsultasDeAdmin.setBorder(borde);				
			}
		    @Override
		    public void mouseExited(MouseEvent e) {
		        mntmConsultasDeAdmin.setBackground(null);
				Border borde = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY);
				mntmConsultasDeAdmin.setBorder(borde);
		    }
			
		});		
		
		mntmConsultasDeAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("se abre ventana de admin");
             }
          });
	
		
		mntmInspector = new JMenuItem("Inspector");
		mntmInspector.setBorder(borde);
		menu.add(mntmInspector);
		mntmInspector.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Color c = mntmConsultasDeAdmin.getBackground();
				Color newColor = new Color(c.getRed()-40,c.getGreen()-40,c.getBlue()-40);
				mntmInspector.setBackground(newColor);
				
				Border borde = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
				mntmInspector.setBorder(borde);				
			}
		    @Override
		    public void mouseExited(MouseEvent e) {
		        mntmInspector.setBackground(null);
				Border borde = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY);
				mntmInspector.setBorder(borde);
		    }
			
		});			
		
		
		mntmInspector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("se abre ventana de inspector");
             }
          });
		
	}
	

}
