package parquimetros;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaEstacionar extends JInternalFrame {

	private BDD bdd;
	private JTable tabla;
	private TableModel modeloTabla;
	
	private JComboBox cbCalle, cbAltura, cbParquimetro, cbTarjeta;
	
	
	public VentanaEstacionar() {
		super("", false, // resizable
	               true,  // closable
	               false, // maximizable
	               false); // iconifiable
		setVisible(false);
		setBounds(100, 100, 853, 521);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocation(0, -12);
        getContentPane().setLayout(new BorderLayout(0, 0));

		bdd = new BDD();
        
        
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 150, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{32, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JLabel lblCalle = new JLabel("Calle");
        lblCalle.setFont(new Font("Dialog", Font.BOLD, 16));
        GridBagConstraints gbc_lblCalle = new GridBagConstraints();
        gbc_lblCalle.anchor = GridBagConstraints.SOUTH;
        gbc_lblCalle.insets = new Insets(0, 0, 5, 5);
        gbc_lblCalle.gridx = 1;
        gbc_lblCalle.gridy = 0;
        panel.add(lblCalle, gbc_lblCalle);
        
        cbCalle = new JComboBox();
        GridBagConstraints gbc_cbCalle = new GridBagConstraints();
        gbc_cbCalle.anchor = GridBagConstraints.SOUTH;
        gbc_cbCalle.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbCalle.insets = new Insets(0, 0, 5, 5);
        gbc_cbCalle.gridx = 2;
        gbc_cbCalle.gridy = 0;
        panel.add(cbCalle, gbc_cbCalle);
        cbCalle.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		cbCalle.removeAllItems();
        		cbParquimetro.removeAllItems();
        		String calle=(String) cbCalle.getItemAt(cbCalle.getSelectedIndex());
        		actualizarAltura(calle);
        	}
        });
        
        
        JLabel lblAltura = new JLabel("Altura");
        GridBagConstraints gbc_lblAltura = new GridBagConstraints();
        gbc_lblAltura.anchor = GridBagConstraints.SOUTH;
        gbc_lblAltura.insets = new Insets(0, 0, 5, 5);
        gbc_lblAltura.gridx = 1;
        gbc_lblAltura.gridy = 1;
        panel.add(lblAltura, gbc_lblAltura);
        lblAltura.setFont(new Font("Dialog", Font.BOLD, 16));
        
        cbAltura = new JComboBox();
        GridBagConstraints gbc_cbAltura = new GridBagConstraints();
        gbc_cbAltura.anchor = GridBagConstraints.SOUTH;
        gbc_cbAltura.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbAltura.insets = new Insets(0, 0, 5, 5);
        gbc_cbAltura.gridx = 2;
        gbc_cbAltura.gridy = 1;
        panel.add(cbAltura, gbc_cbAltura);
        
        JLabel lblParquimetro = new JLabel("Parquimetro");
        GridBagConstraints gbc_lblParquimetro = new GridBagConstraints();
        gbc_lblParquimetro.anchor = GridBagConstraints.SOUTH;
        gbc_lblParquimetro.insets = new Insets(0, 0, 5, 5);
        gbc_lblParquimetro.gridx = 1;
        gbc_lblParquimetro.gridy = 2;
        panel.add(lblParquimetro, gbc_lblParquimetro);
        lblParquimetro.setFont(new Font("Dialog", Font.BOLD, 16));
        
        cbParquimetro = new JComboBox();
        GridBagConstraints gbc_cbParquimetro = new GridBagConstraints();
        gbc_cbParquimetro.anchor = GridBagConstraints.SOUTH;
        gbc_cbParquimetro.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbParquimetro.insets = new Insets(0, 0, 5, 5);
        gbc_cbParquimetro.gridx = 2;
        gbc_cbParquimetro.gridy = 2;
        panel.add(cbParquimetro, gbc_cbParquimetro);
        
        JLabel lblTarjeta = new JLabel("Tarjeta");
        GridBagConstraints gbc_lblTarjeta = new GridBagConstraints();
        gbc_lblTarjeta.anchor = GridBagConstraints.SOUTH;
        gbc_lblTarjeta.insets = new Insets(0, 0, 5, 5);
        gbc_lblTarjeta.gridx = 1;
        gbc_lblTarjeta.gridy = 3;
        panel.add(lblTarjeta, gbc_lblTarjeta);
        lblTarjeta.setFont(new Font("Dialog", Font.BOLD, 16));
        
        cbTarjeta = new JComboBox();
        GridBagConstraints gbc_cbTarjeta = new GridBagConstraints();
        gbc_cbTarjeta.anchor = GridBagConstraints.SOUTH;
        gbc_cbTarjeta.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbTarjeta.insets = new Insets(0, 0, 5, 5);
        gbc_cbTarjeta.gridx = 2;
        gbc_cbTarjeta.gridy = 3;
        panel.add(cbTarjeta, gbc_cbTarjeta);
        
        JButton btnNewButton = new JButton("Abrir / cerrar estacionamiento");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.gridwidth = 2;
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 5;
        panel.add(btnNewButton, gbc_btnNewButton);
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
               
               

        final class TablaDeApertura extends DefaultTableModel{
   	        private Class[] types;
            private boolean[] canEdit;
            
            TablaDeApertura(){
            	super(new String[][] {},
            		  new String[]{"Tipo", "Exito", "Tiempo"});
            	types = new Class[] {java.lang.String.class, 
            	                     java.lang.String.class,
            	                     java.lang.String.class       	                     
            	};
            	canEdit = new boolean[] { false, false, false};
            };             	
        		             
            public Class getColumnClass(int columnIndex){
               return types[columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex){
               return canEdit[columnIndex];
            }         	          	            	
        };    
        final class TablaDeCierre extends DefaultTableModel{
   	        private Class[] types;
            private boolean[] canEdit;
            
            TablaDeCierre(){
            	super(new String[][] {},
            		  new String[]{"Tipo", "Tiempo", "Saldo"});
            	types = new Class[] {java.lang.String.class, 
            	                     java.lang.String.class,
            	                     java.lang.Integer.class       	                     
            	};
            	canEdit = new boolean[] { false, false, false};
            };             	
        		             
            public Class getColumnClass(int columnIndex){
               return types[columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex){
               return canEdit[columnIndex];
            }         	          	            	
        };
        
        
        // Si es de Apertura
        modeloTabla = new TablaDeApertura(); 
        // Sino, si es de Cierre, entonces
        modeloTabla = new TablaDeCierre(); 
        
        
        tabla = new JTable(); 
        scrollPane.setViewportView(tabla);              
        tabla.setModel(modeloTabla); 
        tabla.setAutoCreateRowSorter(true); 
		 
		 
		
	}

}
