package parquimetros;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import quick.dbtable.DBTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class VentanaInspector extends JInternalFrame {

	
	private static final long serialVersionUID = 1L;
	private BDD bdd;
	private DBTable tabla;
	private LinkedList<String> listaPatentes;
	private JPanel panel;
	private static JComboBox comboBox;
	private int legajo;
	private JComboBox comboBox_2;
	private JComboBox comboBox_1;
	private JPanel panel_1;
	private JLabel lblCalle;
	private JLabel lblAltura;
	private JLabel lblNmParqumetros;
	private JButton btnMulta;
	private JTable table;
	
	
	public VentanaInspector() {
		 super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		 setVisible(false);
		 bdd = new BDD();
		 try {
			bdd.conectar("inspector", "inspector");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 listaPatentes = new LinkedList<String>();
		 
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
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        getContentPane().add(panel, BorderLayout.NORTH);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{245, 154, 159, 223, 0};
        gbl_panel.rowHeights = new int[] {36, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        lblCalle = new JLabel("Calle");
        GridBagConstraints gbc_lblCalle = new GridBagConstraints();
        gbc_lblCalle.insets = new Insets(0, 0, 5, 5);
        gbc_lblCalle.gridx = 1;
        gbc_lblCalle.gridy = 0;
        panel.add(lblCalle, gbc_lblCalle);
        
        comboBox = new JComboBox();
        comboBox.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBox_1.removeAllItems();
        		comboBox_2.removeAllItems();
        		String calle=(String) comboBox.getItemAt(comboBox.getSelectedIndex());
        		actualizarCombobox1(calle);
        	}
        });
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.gridx = 2;
        gbc_comboBox.gridy = 0;
        panel.add(comboBox, gbc_comboBox);
        
        JButton btnAgregarPatente = new JButton("Agregar patente");
        GridBagConstraints gbc_btnAgregarPatente = new GridBagConstraints();
        gbc_btnAgregarPatente.anchor = GridBagConstraints.WEST;
        gbc_btnAgregarPatente.insets = new Insets(0, 0, 5, 5);
        gbc_btnAgregarPatente.gridx = 0;
        gbc_btnAgregarPatente.gridy = 1;
        panel.add(btnAgregarPatente, gbc_btnAgregarPatente);
        btnAgregarPatente.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		listaPatentes.add(JOptionPane.showInputDialog("Agregar patente:"));
        	    System.out.println("Patente agregada: " + listaPatentes.getLast());        		
        	}
        });
        
        lblAltura = new JLabel("Altura");
        GridBagConstraints gbc_lblAltura = new GridBagConstraints();
        gbc_lblAltura.insets = new Insets(0, 0, 5, 5);
        gbc_lblAltura.gridx = 1;
        gbc_lblAltura.gridy = 1;
        panel.add(lblAltura, gbc_lblAltura);
        
        comboBox_1 = new JComboBox();
        comboBox_1.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBox_2.removeAllItems();
        		String calle=(String) comboBox.getItemAt(comboBox.getSelectedIndex());
        		String altura=(String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
        		actualizarCombobox2(calle,altura);
        	}
        });
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_1.gridx = 2;
        gbc_comboBox_1.gridy = 1;
        panel.add(comboBox_1, gbc_comboBox_1);
        
        btnMulta = new JButton("Generar multas");
        btnMulta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        GridBagConstraints gbc_btnMulta = new GridBagConstraints();
        gbc_btnMulta.anchor = GridBagConstraints.EAST;
        gbc_btnMulta.insets = new Insets(0, 0, 5, 0);
        gbc_btnMulta.gridx = 3;
        gbc_btnMulta.gridy = 1;
        panel.add(btnMulta, gbc_btnMulta);
        
        lblNmParqumetros = new JLabel("Parqu\u00EDmetro");
        GridBagConstraints gbc_lblNmParqumetros = new GridBagConstraints();
        gbc_lblNmParqumetros.insets = new Insets(0, 0, 0, 5);
        gbc_lblNmParqumetros.gridx = 1;
        gbc_lblNmParqumetros.gridy = 2;
        panel.add(lblNmParqumetros, gbc_lblNmParqumetros);
        
        
        comboBox_2 = new JComboBox();
        GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
        gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_2.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox_2.gridx = 2;
        gbc_comboBox_2.gridy = 2;
        panel.add(comboBox_2, gbc_comboBox_2);
        
        panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        
        table = new JTable();
        panel_1.add(table);
        
	}
	
	

	public void registrarAcceso(int id_parq) {
		String sql = "INSERT INTO accede(legajo,id_parq,fecha,hora) " +
					 "VALUES (" + legajo + ","+ id_parq +",CURDATE(),CURTIME());";		
		try {
			bdd.ejecutarModificacion(sql);
			bdd.limpiarModificacion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public boolean controlarUbicacion(String calle, int altura) {
		boolean controlSuperado = false;
		try {
			
			String sql_dia = "SELECT DAYOFWEEK(CURDATE()) dia;";
			ResultSet rs1 = bdd.ejecutarSentencia(sql_dia);
			rs1.next();
			int diaInt = rs1.getInt("dia");
			bdd.limpiarSentencia();
			String dia = "";
			switch(diaInt) {
				case 1: dia = "do"; break;
				case 2: dia = "lu"; break;
				case 3: dia = "ma"; break;
				case 4: dia = "mi"; break;
				case 5: dia = "ju"; break;
				case 6: dia = "vi"; break;
				case 7: dia = "sa"; break;				
			}				
			
			String sql_turno = "SELECT (CURTIME()>'08:00:00' AND CURTIME()<'13:59:00') turno_M,"
									+ "(CURTIME()>'14:00:00' AND CURTIME()<'20:00:00') turno_T;";
			ResultSet rs2 = bdd.ejecutarSentencia(sql_turno);
			rs2.next();
			int turno_m = rs2.getInt("turno_M");
			int turno_t = rs2.getInt("turno_T");
			bdd.limpiarSentencia();
			String turno = "";
			if(turno_m==1)
				turno = "M";
			else
				if(turno_t==1)
					turno = "T";						
			
			String sql = "SELECT legajo, calle, altura, dia, turno " +
						 "FROM asociado_con " +
						 "WHERE legajo=" + legajo + " AND " +
						 		"calle='" + calle + "' AND " +
						 		"altura=" + altura + " AND " +
						 		"dia='"+ dia + "' AND " +
						 		"turno='"+ turno +"';";
			ResultSet rs3 = bdd.ejecutarSentencia(sql);
			controlSuperado = rs3.next();
			bdd.limpiarSentencia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return controlSuperado;
	}
	
	public void generarMulta(){
		String calle=(String) comboBox.getItemAt(comboBox.getSelectedIndex());
		String altura=(String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
		int alturaa= Integer.parseInt(altura);
		if (controlarUbicacion(calle,alturaa)){
			String idparq=(String) comboBox_2.getItemAt(comboBox_2.getSelectedIndex());
			int idpark=Integer.parseInt(idparq);
			registrarAcceso(idpark);
			//generarMultas();
		}
		else{
			JOptionPane.showMessageDialog(null,"El inspector no esta autorizado a labrar multas en esta ubicacion.\n","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
	private void actualizarComboBox() {
		String sql = "SELECT DISTINCT calle " +
					 "FROM ubicaciones;";
		try {
			ResultSet rs = bdd.ejecutarSentencia(sql);
			System.out.println("Columnas "+rs.getMetaData().getColumnCount());			
			while(rs.next()) {
				comboBox.addItem(rs.getString("calle"));
			}
			bdd.limpiarSentencia();	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void actualizarCombobox1(String calle) {
		String sql = "SELECT altura " +
				     "FROM ubicaciones "+
				     "WHERE calle='" +calle+"';";
		try {
			ResultSet rs = bdd.ejecutarSentencia(sql);
				
			while(rs.next()) {
				
				comboBox_1.addItem(rs.getInt("altura")+"");
			}
			bdd.limpiarSentencia();	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	private void actualizarCombobox2(String calle, String altura){
		String sql="SELECT id_parq " +
				   "FROM parquimetros " +
				   "WHERE calle='" + calle + "' AND altura=" + altura + ";" ;
		try {
			ResultSet rs = bdd.ejecutarSentencia(sql);
			while(rs.next()) {
				comboBox_2.addItem(rs.getInt("id_parq"));
			}
			bdd.limpiarSentencia();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public boolean loguear(int leg, String clave) throws SQLException, ClassNotFoundException {
		boolean exito;	
		legajo = leg;
		bdd.conectar("inspector", "inspector");	
		
		String consultaSql = "SELECT legajo, password " +
				"FROM inspectores " +
				"WHERE legajo=" + leg + " AND " +
				"password=md5('" + clave + "');";
		
		ResultSet rs = bdd.ejecutarSentencia(consultaSql);
		exito = rs.next();		
		bdd.limpiarSentencia();
		
		try { // ésto es para mostrar la ventana cuando los datos son correctos
	    	if(exito) {
	    		System.out.println("aparece ventana");
	    		setVisible(true);
	    		setMaximum(true);
	            actualizarComboBox();
	    	}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}		
		return exito;
	}
	
	
	

}