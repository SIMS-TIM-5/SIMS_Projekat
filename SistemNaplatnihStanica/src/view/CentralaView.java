package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CentralaController;

public class CentralaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800; // 640
	private static final int HEIGHT = 600; // 480
	private static final int sirina_dugme = 130;
	private static final int visina_dugme = 30;
	private static final int margina = 10;

	private JPanel general_panel, right_panel;
	private JTable table;
	private JButton dugme, dugme2, dugme3;
	private JScrollPane tableScrollers;
	private JTextField datumP,datumK,stanica;
	private JRadioButton din,eur;
	private JLabel labDat1,labDat2,labStanica;

	private String colums[] = { "Datum", "Tablice", "Uplata", "Deonica" };
	private Object rows[][] = { { "a", "s", "d", "asd" }, { "a", "s", "d", "asd" } };
	private TableModel model;

	public CentralaView() {
		super("Sistem naplatnih stanica - Operater centrale");

		tableScrollers = new JScrollPane();
		model = new DefaultTableModel(rows, colums);
		table = new JTable(model);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		setLayout(new BorderLayout());
		general_panel = new JPanel();
		general_panel.setLayout(new GridLayout(1, 2));
		setSize(WIDTH, HEIGHT);
		// add(table, BorderLayout.CENTER);

		initComponents();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setResizable(false);
		setVisible(true);
	}

	private void initComponents() {
		right_panel = new JPanel();
		right_panel.setLayout(null);
		
		labDat1 = new JLabel("Od datuma: ");
		labDat2 = new JLabel("Do datuma: ");
		labStanica = new JLabel("Stanica: ");
		
		din = new JRadioButton("DIN");
		eur = new JRadioButton("EUR");
		
		ButtonGroup grupa = new ButtonGroup();
		grupa.add(din);
		grupa.add(eur);
		
		dugme = new JButton("Primeni datum");
		dugme2 = new JButton("Primeni stanicu");
		dugme3 = new JButton("Ponisti");
		
		datumP = new JTextField(11);
		datumK = new JTextField(11);
		stanica = new JTextField(11);

		right_panel.add(dugme);
		right_panel.add(dugme2);
		right_panel.add(dugme3);
		
		
		right_panel.add(datumP);
		right_panel.add(datumK);
		right_panel.add(stanica);
		
		right_panel.add(din);
		right_panel.add(eur);
		
		right_panel.add(labDat1);
		right_panel.add(labDat2);
		right_panel.add(labStanica);
		
		
		int sirina = WIDTH / 2;
		int visina_polja = ((HEIGHT / 3) / 2);
		
		
		din.setLocation((sirina/2)-sirina_dugme,(HEIGHT/3)*2 - visina_dugme);
		din.setSize(sirina_dugme, visina_dugme);
		
		eur.setLocation((sirina/2)-sirina_dugme,(HEIGHT/3)*2 - visina_dugme+visina_dugme);
		eur.setSize(sirina_dugme,visina_dugme);
		
		labDat1.setLocation(0,(HEIGHT/3)/3  - visina_dugme/2);
		labDat1.setSize(sirina_dugme,visina_dugme);
		
		datumP.setLocation((sirina/2) - sirina_dugme,(HEIGHT/3)/3  - visina_dugme/2);
		datumP.setSize(sirina_dugme,visina_dugme);
		
		labDat2.setLocation(0,(HEIGHT/3)/3 + (HEIGHT/3)/3 - visina_dugme/2);
		labDat2.setSize(sirina_dugme,visina_dugme);
		
		datumK.setLocation((sirina/2) - sirina_dugme,(HEIGHT/3)/3 + (HEIGHT/3)/3 - visina_dugme/2);
		datumK.setSize(sirina_dugme,visina_dugme);
		
		labStanica.setLocation(0,(HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme/2);
		labStanica.setSize(sirina_dugme,visina_dugme);
		
		stanica.setSize(sirina_dugme,visina_dugme);
		stanica.setLocation((sirina/2) - sirina_dugme,(HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme/2);
		
		dugme.setSize(sirina_dugme, visina_dugme);
		dugme.setLocation(sirina - (sirina_dugme + margina), ((HEIGHT / 3) / 2) - visina_dugme/2);

		dugme2.setLocation(sirina - (sirina_dugme + margina), (HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme/2);
		dugme2.setSize(sirina_dugme, visina_dugme);

		dugme3.setLocation((sirina/2) - sirina_dugme/2, (HEIGHT / 3) / 2 + 2 * (HEIGHT / 3) - visina_dugme/2);
		dugme3.setSize(sirina_dugme, visina_dugme);

		
		
		tableScrollers = new JScrollPane(table);
		general_panel.add(tableScrollers);
		general_panel.add(right_panel);
		add(general_panel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		CentralaView view = new CentralaView();
		CentralaController controller = new CentralaController(view);
	}

}
