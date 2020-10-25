package parquimetros;


import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollBar;

public class VentanaConsultas extends JInternalFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JPanel panel;
	private JTextArea textArea;
	private JTable tabla;
	

/*
	public static void main(String[] args) {
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
		 
		//setBounds(100, 100, 776, 480);
		setBounds(100, 100, 853, 521);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		

        setVisible(false);
        setLocation(0, -12);
        
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));	        
        splitPane = new JSplitPane();
        splitPane.setDividerLocation(0.6);
        splitPane.setResizeWeight(0.6);        
        getContentPane().add(splitPane);
        
        panel = new JPanel();
        splitPane.setLeftComponent(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.NORTH);
        
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
        //panel_2.setBounds(0,0,1000,200);
        
        textArea = new JTextArea();
        panel_2.add(textArea);
        textArea.setColumns(5);
        textArea.setRows(10);
        textArea.setTabSize(3);
        textArea.setFont(new java.awt.Font("Monospaced",0,12));
        JScrollPane sp = new JScrollPane(textArea);
        panel_2.add(sp);
        
        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3);
        panel_3.setLayout(new GridLayout(0, 1, 0, 0));
        panel_3.setMaximumSize(new Dimension(100,60));
        
        JButton btnNewButton = new JButton("Ejecutar");
        panel_3.add(btnNewButton);

        JButton btnNewButton_2 = new JButton("Borrar");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		textArea.setText("");
        	}
        });
        panel_3.add(btnNewButton_2);
        
        //JScrollPane scrollPane = new JScrollPane();
        //panel.add(scrollPane, BorderLayout.CENTER);
        

        JPanel panelAbajo= new JPanel();
        panel.add(panelAbajo, BorderLayout.CENTER);
        
        tabla = new JTable();
        panelAbajo.add(tabla);
        tabla.setAutoCreateRowSorter(true); // activa el ordenamiento por columnas, para
        									// que se ordene al hacer click en una columna
        
        
        
        
        
        
        // panel listas
        JPanel panel_1 = new JPanel();
        splitPane.setRightComponent(panel_1);
		
	}

	
	

	 

	
	
	
}
