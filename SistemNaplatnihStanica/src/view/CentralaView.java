package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CentralaController;
import javafx.scene.control.Button;
import models.Racun;

public class CentralaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1280; // 640
	private static final int HEIGHT = 720; // 480
	private static final int sirina_dugme = 130;
	private static final int visina_dugme = 30;
	private static final int margina = 10;

	private JPanel general_panel, right_panel, kvar_panel, kvar_dugmici;
	private JTable[] table;
	private JButton dugmeDatum, dugmeStanica, dugmePonisti, dugmeDatumKvar, dugmeStanicaKvar, dugmePonistiKvar;
	private JScrollPane[] tableScrollers;
	private JTextField datumP, datumK, stanica, kvarDatumP, kvarDatumK, kvarStanica;
	private JRadioButton din, eur;
	private JLabel labDat1, labDat2, labStanica;
	private ButtonGroup grupa;
	private RadioListener radioListener;
	private JTabbedPane tabPane;

	public static String colums[] = { "Datum", "Tip", "Id stanice", "Deonica" };
	private Object rows[][] = {};
	// private TableModel model;
	private ArrayList<Racun> listaRacuna;

	public CentralaView() {
		super("Sistem naplatnih stanica - Operater centrale");

		listaRacuna = new ArrayList<>();
		tableScrollers = new JScrollPane[2];
		// model = new DefaultTableModel(rows, colums);
		table = new JTable[2];

		setLayout(new BorderLayout());
		general_panel = new JPanel();
		general_panel.setLayout(new GridLayout(1, 2));
		setSize(WIDTH, HEIGHT);
		// add(table, BorderLayout.CENTER);

		initDataTables();
		initKvarPanel();
		initComponents();
		initTabs();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setResizable(false);
		setVisible(true);
	}

	private void initKvarPanel() {
		kvar_panel = new JPanel();

		kvar_panel.setLayout(new GridLayout(1, 2));

		kvar_dugmici = new JPanel();
		kvar_dugmici.setLayout(null);
		dugmeDatumKvar = new JButton("Primeni datum");
		dugmeStanicaKvar = new JButton("Primeni stanicu");
		dugmePonistiKvar = new JButton("Ponisti");
		labDat1 = new JLabel("Od datuma: ");
		labDat2 = new JLabel("Do datuma: ");
		labStanica = new JLabel("Stanica: ");

		kvarDatumP = new JTextField(11);
		kvarDatumK = new JTextField(11);
		kvarStanica = new JTextField(11);

		kvar_dugmici.add(labDat1);
		kvar_dugmici.add(labDat2);
		kvar_dugmici.add(labStanica);

		int sirina = WIDTH / 2;

		dugmePonistiKvar.setLocation((sirina / 2) - sirina_dugme / 2,
				(HEIGHT / 3) / 2 + 2 * (HEIGHT / 3) - visina_dugme / 2);
		dugmePonistiKvar.setSize(sirina_dugme, visina_dugme);

		kvarDatumP.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) / 3 - visina_dugme / 2);
		kvarDatumP.setSize(sirina_dugme, visina_dugme);

		kvarDatumK.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) / 3 + (HEIGHT / 3) / 3 - visina_dugme / 2);
		kvarDatumK.setSize(sirina_dugme, visina_dugme);

		kvarStanica.setSize(sirina_dugme, visina_dugme);
		kvarStanica.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme / 2);

		dugmeDatumKvar.setSize(sirina_dugme, visina_dugme);
		dugmeDatumKvar.setLocation(sirina - (sirina_dugme + margina), ((HEIGHT / 3) / 2) - visina_dugme / 2);

		dugmeStanicaKvar.setLocation(sirina - (sirina_dugme + margina),
				(HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme / 2);
		dugmeStanicaKvar.setSize(sirina_dugme, visina_dugme);

		labDat1.setLocation(0, (HEIGHT / 3) / 3 - visina_dugme / 2);
		labDat1.setSize(sirina_dugme, visina_dugme);

		labDat2.setLocation(0, (HEIGHT / 3) / 3 + (HEIGHT / 3) / 3 - visina_dugme / 2);
		labDat2.setSize(sirina_dugme, visina_dugme);

		labStanica.setLocation(0, (HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme / 2);
		labStanica.setSize(sirina_dugme, visina_dugme);

		kvar_dugmici.add(dugmeDatumKvar);
		kvar_dugmici.add(dugmeStanicaKvar);
		kvar_dugmici.add(dugmePonistiKvar);

		kvar_dugmici.add(kvarDatumK);
		kvar_dugmici.add(kvarDatumP);
		kvar_dugmici.add(kvarStanica);

		kvar_panel.add(tableScrollers[1]);
		kvar_panel.add(kvar_dugmici);

	}

	private void initComponents() {
		right_panel = new JPanel();
		right_panel.setLayout(null);

		labDat1 = new JLabel("Od datuma: ");
		labDat2 = new JLabel("Do datuma: ");
		labStanica = new JLabel("Stanica: ");

		din = new JRadioButton("DIN");
		din.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioListener.radioListenerOcc(grupa.getSelection().getActionCommand());

			}
		});

		eur = new JRadioButton("EUR");
		eur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radioListener.radioListenerOcc(grupa.getSelection().getActionCommand());

			}
		});

		grupa = new ButtonGroup();
		grupa.add(din);
		grupa.add(eur);
		// din.setSelected(true);
		din.setActionCommand("DIN");
		eur.setActionCommand("EUR");

		dugmeDatum = new JButton("Primeni datum");
		dugmeStanica = new JButton("Primeni stanicu");
		dugmePonisti = new JButton("Ponisti");

		datumP = new JTextField(11);
		datumK = new JTextField(11);
		stanica = new JTextField(11);

		right_panel.add(dugmeDatum);
		right_panel.add(dugmeStanica);
		right_panel.add(dugmePonisti);

		right_panel.add(datumP);
		right_panel.add(datumK);
		right_panel.add(stanica);

		right_panel.add(din);
		right_panel.add(eur);

		right_panel.add(labDat1);
		right_panel.add(labDat2);
		right_panel.add(labStanica);

		int sirina = WIDTH / 2;

		din.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) * 2 - visina_dugme);
		din.setSize(sirina_dugme, visina_dugme);

		eur.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) * 2 - visina_dugme + visina_dugme);
		eur.setSize(sirina_dugme, visina_dugme);

		labDat1.setLocation(0, (HEIGHT / 3) / 3 - visina_dugme / 2);
		labDat1.setSize(sirina_dugme, visina_dugme);

		datumP.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) / 3 - visina_dugme / 2);
		datumP.setSize(sirina_dugme, visina_dugme);

		labDat2.setLocation(0, (HEIGHT / 3) / 3 + (HEIGHT / 3) / 3 - visina_dugme / 2);
		labDat2.setSize(sirina_dugme, visina_dugme);

		datumK.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) / 3 + (HEIGHT / 3) / 3 - visina_dugme / 2);
		datumK.setSize(sirina_dugme, visina_dugme);

		labStanica.setLocation(0, (HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme / 2);
		labStanica.setSize(sirina_dugme, visina_dugme);

		stanica.setSize(sirina_dugme, visina_dugme);
		stanica.setLocation((sirina / 2) - sirina_dugme, (HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme / 2);

		dugmeDatum.setSize(sirina_dugme, visina_dugme);
		dugmeDatum.setLocation(sirina - (sirina_dugme + margina), ((HEIGHT / 3) / 2) - visina_dugme / 2);

		dugmeStanica.setLocation(sirina - (sirina_dugme + margina), (HEIGHT / 3) / 2 + (HEIGHT / 3) - visina_dugme / 2);
		dugmeStanica.setSize(sirina_dugme, visina_dugme);

		dugmePonisti.setLocation((sirina / 2) - sirina_dugme / 2,
				(HEIGHT / 3) / 2 + 2 * (HEIGHT / 3) - visina_dugme / 2);
		dugmePonisti.setSize(sirina_dugme, visina_dugme);

		// tableScrollers[0] = new JScrollPane(table[0]);
		general_panel.add(tableScrollers[0]);
		general_panel.add(right_panel);
		// add(general_panel, BorderLayout.CENTER);

	}

	private void initDataTables() {
		for (int i = 0; i < 2; i++) {
			table[i] = new JTable();

			table[i].setEnabled(false);
			table[i].getTableHeader().setReorderingAllowed(false);
			table[i].getTableHeader().setResizingAllowed(false);

			tableScrollers[i] = new JScrollPane(table[i]);
		}
	}

	public void initTabs() {
		tabPane = new JTabbedPane();

		tabPane.addTab("Karte", general_panel);
		tabPane.addTab("Kvar", kvar_panel);

		add(tabPane, BorderLayout.CENTER);
	}

	public void setBtnDatumListener(ActionListener al) {
		dugmeDatum.addActionListener(al);
	}

	public void setBtnStanicaListener(ActionListener al) {
		dugmeStanica.addActionListener(al);
	}

	public String getDatumP() {
		String vrednost = datumP.getText();
		return vrednost;
	}

	public String getDatumK() {
		String vrednost = datumK.getText();
		return vrednost;
	}

	public void setDatumKEmpty() {
		datumK.setText("");
	}

	public void setDatumPEmpty() {
		datumP.setText("");
	}

	public void setStanicaEmpty() {
		stanica.setText("");
	}

	public String getStanica() {
		String vrednost = stanica.getText();
		return vrednost;
	}

	public void setBtnPonisti(ActionListener al) {
		dugmePonisti.addActionListener(al);
	}

	public void isprazniTabelu(int br) {
		for (int i = table[br].getRowCount() - 1; i >= 0; i--) {
			DefaultTableModel model = (DefaultTableModel) table[br].getModel();
			model.removeRow(i);
		}
	}

	public void setListaRacuna(ArrayList<Racun> r) {
		listaRacuna = r;
	}

	public ArrayList<Racun> getListaRacuna() {
		return listaRacuna;
	}

	public void setRadioListener(RadioListener rs) {
		this.radioListener = rs;
	}

	public void ponistiRadioBtn() {
		grupa.clearSelection();
	}

	public void ubaciUTabelu(int br,String[] kolone, Object[][] redovi) {
		table[br].setModel(new DefaultTableModel(redovi, kolone));
	}

	public static void main(String[] args) {
		CentralaView view = new CentralaView();
		CentralaController controller = new CentralaController(view);
	}

}
