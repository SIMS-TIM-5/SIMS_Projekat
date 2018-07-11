package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import controller.CentralaController;

public class CentralaView extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	private JPanel general_panel,right_panel;
	private JTable table;
	
	private String colums[] = {"Datum","Tablice","Uplata","Deonica"};
	private Object rows[][] = {{"a","s","d","asd"},{"a","s","d","asd"}};
	private TableModel model;
	
	public CentralaView(){
		super("Sistem naplatnih stanica - Operater centrale");
		model = new DefaultTableModel(rows,colums);
		table = new JTable(model);
		setLayout(new BorderLayout());
		general_panel = new JPanel();
		general_panel.setLayout(new GridLayout(1, 2));
		setSize(WIDTH,HEIGHT);
		//add(table, BorderLayout.CENTER);
		
		initComponents();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	
		
		setVisible(true);
	}
	
	
	private void initComponents(){
		right_panel = new JPanel();
		right_panel.setLayout(new GridLayout(2, 1));
		right_panel.add(new JLabel("Asd"));
		right_panel.add(new JLabel("Asd"));
		right_panel.add(new JLabel("Asd"));
		
		general_panel.add(table);
		general_panel.add(right_panel);
		add(general_panel, BorderLayout.CENTER);
	}
	
	
	public static void main(String[] args){
		CentralaView view = new CentralaView();
		CentralaController controller = new CentralaController(view);
	}
	

}
