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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CentralaController;
import models.Racun;
import models.Sistem;
import utils.JSONReaderRacuni;

public class CentralaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1280; // 640
	private static final int HEIGHT = 720; // 480
	private static final int sirina_dugme = 130;
	private static final int visina_dugme = 30;
	private static final int margina = 10;

	private JPanel general_panel, right_panel;
	private JTable table;
	private JButton dugmeDatum, dugmeStanica, dugmePonisti;
	private JScrollPane tableScrollers;
	private JTextField datumP, datumK, stanica;
	private JRadioButton din, eur;
	private JLabel labDat1, labDat2, labStanica;
	private ButtonGroup grupa;
	private RadioListener radioListener;

	public static String colums[] = { "Datum", "Tip", "Id stanice", "Deonica" };
	private Object rows[][] = {};
	private TableModel model;
	private ArrayList<Racun> listaRacuna;

	public CentralaView() {
		super("Sistem naplatnih stanica - Operater centrale");

		listaRacuna = new ArrayList<>();
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

		tableScrollers = new JScrollPane(table);
		general_panel.add(tableScrollers);
		general_panel.add(right_panel);
		add(general_panel, BorderLayout.CENTER);

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

	public void isprazniTabelu() {
		for (int i = table.getRowCount() - 1; i >= 0; i--) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
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

	public void ubaciUTabelu(String[] kolone, Object[][] redovi) {
		table.setModel(new DefaultTableModel(redovi, kolone));
	}

	public static void main(String[] args) {
		CentralaView view = new CentralaView();
		CentralaController controller = new CentralaController(view);
	}

}
