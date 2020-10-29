package parquimetros;


import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import quick.dbtable.DBTable;
import javax.swing.JList;

public class VentanaConsultas extends JInternalFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JPanel panel;
	private static JTextArea textArea;
	private DBTable tabla;
	private JPanel panelListas;
    private static JList listaTablas;
    private static JList listaAtributos;
    private BDD bdd;
    
	
	
	public VentanaConsultas(){
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
        splitPane = new JSplitPane();
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
        
        JButton btnEjecutar = new JButton("Ejecutar");
        btnEjecutar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		actualizarTabla();
        	}
        });
        panel_3.add(btnEjecutar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		textArea.setText("");
        	}
        });
        panel_3.add(btnBorrar);  
        
        JPanel panelAbajo= new JPanel();
        panelAbajo.setLayout(new BorderLayout(0,0));
        panel.add(panelAbajo, BorderLayout.CENTER);        

    	tabla = new DBTable();    	
    	panelAbajo.add(tabla);                   
        tabla.setEditable(false);          
        mostrarListas();
	}
	
	

	   private void actualizarTabla() {
	      try {    
	    	 // seteamos la consulta a partir de la cual se obtendrán los datos para llenar la tabla
	    	 tabla.setSelectSql(textArea.getText().trim());

	    	  // obtenemos el modelo de la tabla a partir de la consulta para 
	    	  // modificar la forma en que se muestran de algunas columnas  
	    	  tabla.createColumnModelFromQuery();    	    
	    	  for (int i = 0; i < tabla.getColumnCount(); i++) { 
	    		  
	    		  // para que muestre correctamente los valores de tipo TIME (hora)  		   		  
	    		 if	 (tabla.getColumn(i).getType()==Types.TIME)     		 
	    		    tabla.getColumn(i).setType(Types.CHAR);  
	    		 
	    		 // cambiar el formato en que se muestran los valores de tipo DATE
	    		 if	 (tabla.getColumn(i).getType()==Types.DATE)
	    		    tabla.getColumn(i).setDateFormat("dd/MM/YYYY");
	    		 
	          }  
	    	  // actualizamos el contenido de la tabla.
	    	  tabla.refresh();
	       }
	      catch (SQLException ex) {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState: " + ex.getSQLState());
	         System.out.println("VendorError: " + ex.getErrorCode());
	         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
	                                       ex.getMessage() + "\n", 
	                                       "Error al ejecutar la consulta.",
	                                       JOptionPane.ERROR_MESSAGE);
	      }
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
        
        
        // actualizo lista de tablas

        listaTablas.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 1) {
                 JList target = (JList)evt.getSource();
                 int index = target.locationToIndex(evt.getPoint());
                 if (index >= 0) {
                    String item = target.getModel().getElementAt(index).toString();                    
                    try {
                    	ResultSet resultado = bdd.ejecutarSentencia("DESCRIBE " + item + ";");
            			DefaultListModel modelo = new DefaultListModel<>();
            			while(resultado.next()) 
            				modelo.addElement(resultado.getString(1));				
            			listaAtributos.setModel(modelo);
            		} catch (SQLException e) {
            			e.printStackTrace();
            		}
                    
                 }
              }
           }
        });
	}
	
	
	public void loguear(String clave) throws ClassNotFoundException, SQLException {	
        
		bdd.conectar("admin",clave);	
		
        textArea.setText("SELECT *\nFROM multa;");
		
		// actualizo lista de tablas
        try {
        	ResultSet resultado = bdd.ejecutarSentencia("SHOW tables;");
			DefaultListModel modelo = new DefaultListModel<>();
			while(resultado.next()) 
				modelo.addElement(resultado.getString(1));				
			listaTablas.setModel(modelo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        

        // vinculo la tabla con la BDD
		try {
			bdd.vincularTabla(tabla);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {					
			JOptionPane.showMessageDialog(null,
                    "No se pudo conectar a la base de datos.\n" + ex.getMessage(),
                     "Error", JOptionPane.ERROR_MESSAGE);
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
		}
        
        setVisible(true);
	}
	
}
