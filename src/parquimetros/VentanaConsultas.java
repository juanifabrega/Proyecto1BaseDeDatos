package parquimetros;


import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import quick.dbtable.DBTable;
import javax.swing.JList;

public class VentanaConsultas extends JInternalFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JPanel panel;
	private JTextArea textArea;
	private JTable tabla;
	private JPanel panelListas;
    private static JList listaTablas;
    private static JList listaAtributos;
    private JScrollBar scrollBar;
	
	
	public VentanaConsultas() {
		 super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		 addInternalFrameListener(new InternalFrameAdapter() {
		 	@Override
		 	public void internalFrameClosing(InternalFrameEvent e) {
		 		dispose();
		 		try {
					BDD.desconectar();
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
		 
		 
		 
		//setBounds(100, 100, 776, 480);
		setBounds(100, 100, 853, 521);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		

        setVisible(false);
        setLocation(0, -12);
        
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));	        
        splitPane = new JSplitPane();
//        splitPane.setDividerLocation(0.6);
//        splitPane.setResizeWeight(0.6);  
        splitPane.setDividerLocation(getWidth()-200);
        getContentPane().add(splitPane);
        
        panel = new JPanel();
        splitPane.setLeftComponent(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.NORTH);
        
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

        
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
        
        

        // se define el modelo de la tabla
        final class TablaBarcosModel extends DefaultTableModel{
        
        }
        
        
  
        
        mostrarListas();
	}

	
	public void mostrarListas() {
		panelListas = new JPanel();
        splitPane.setRightComponent(panelListas);
        panelListas.setLayout(new GridLayout(2, 1, 0, 0));
        
        listaTablas = new JList();
        panelListas.add(listaTablas);  
        JScrollPane scroll_1 = new JScrollPane();
        scroll_1.setViewportView(listaTablas);
        listaTablas.setLayoutOrientation(JList.VERTICAL); 
        panelListas.add(scroll_1);     
        
        listaAtributos = new JList();
        panelListas.add(listaAtributos);
        JScrollPane scroll_2 = new JScrollPane();
        scroll_2.setViewportView(listaAtributos);
        listaAtributos.setLayoutOrientation(JList.VERTICAL);  
        panelListas.add(scroll_2);
	}
	
	public static void actualizarListas() {	
        
        try {
        	ResultSet resultado = BDD.ejecutarSentencia("SHOW tables;");
			DefaultListModel modelo = new DefaultListModel<>();
			while(resultado.next()) 
				modelo.addElement(resultado.getString(1));				
			listaTablas.setModel(modelo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        
        
        
        
	}
	
}
