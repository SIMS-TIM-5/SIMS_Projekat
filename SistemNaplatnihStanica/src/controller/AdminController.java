package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import models.Korisnik;
import models.Mesto;
import models.NaplatnaStanica;
import models.RegularnoMesto;
import models.Sistem;
import models.TipKorisnika;
import utils.JSONWriter;
import view.AdminView;

public class AdminController {

	private static String[] korisniciHeader;
	private static String[] napStaniceHeader;
	private static String[] napMestaHeader;
	
	private AdminView view;
	
	public AdminController(AdminView view) {
		this.view = view;
		
		korisniciHeader = new String[] {"Username", "Tip", "Ime", "Prezime",
									"Broj telefona", "Adresa", "Mesto"};
		napStaniceHeader = new String[] {"ID stanice", "Naziv stanice", "Sef stanice", "Broj nap.mesta"};
		napMestaHeader = new String[] {"Aktivnost", "ID", "ID naplatne stanice", "Naziv stanice"};
		
		initTabelaKorisnici(0);
		initTabelaNaplatneStanice(1);
		initTabelaNaplatnaMesta(2);
		initTabelaDeonice(3);

		addBtnUnosListener();
		addBtnIzmenaListener();
		addBtnBrisanjeListener();
		addBtnOdjavaListener();
		
		setTabChangedListener();
	}

	private void addBtnUnosListener() {
		view.setBtnUnosListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (view.getSelectedTab()) {
				case 0: // Tabela korisnika
					dodajKorisnika();
					initTabelaKorisnici(0);
					break;

				case 1: // Tabela naplatnih stanica
					dodajNaplatnuStanicu();
					initTabelaNaplatneStanice(1);
					break;
					
				case 2: // Tabela naplatnih mesta
					dodajNaplatnoMesto();
					initTabelaNaplatnaMesta(2);
					break;
					
				case 3: // Tabela deonica
					dodajDeonicu();
					initTabelaDeonice(3);
					break;
				}
			}
		});
	}
	
	
	private void addBtnIzmenaListener() {
		// Naplatno mesto i deonica ne mogu da se menjaju
		view.setBtnIzmenaListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (view.getSelectedTab()) {
				case 0: // Tabela korisnika
					izmeniKorisnika();
					initTabelaKorisnici(0);
					break;
						
				case 1: // Tabela naplatnih stanica
					izmeniNaplatnuStanicu();
					initTabelaNaplatneStanice(1);
					break;
				}
			}
		});
	}
	
	private void addBtnBrisanjeListener() {
		view.setBtnBrisanjeListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedTab = view.getSelectedTab();
				int[] selectedRows = view.getSelectedRows(selectedTab);
				
				if (selectedRows.length == 0)
					return;
				
				for (int i = selectedRows.length - 1; i >= 0; --i) {
					view.obrisiRed(selectedTab, selectedRows[i]);
					obrisiIzListe(selectedTab, selectedRows[i]);
					System.out.println("Red " + selectedRows[i] + " obrisan.");
				}
				
				view.clearTableSelection(selectedTab);
			}
		});
	}
	
	// Trenutno, kada se aktivira "Odjavi se" dugme, ceo program ce se ugasiti
	private void addBtnOdjavaListener() {
		view.setBtnOdjavaListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	/**
	 * Otvara JOptionPane sa text field-ovima za unos podataka i dodaje korisnika u listu i fajl
	 */
	private void dodajKorisnika() {
		JTextField fieldKorIme = new JTextField();
		JTextField fieldSifra = new JTextField();
		JTextField fieldTip = new JTextField();
		JTextField fieldIme = new JTextField();
		JTextField fieldPrezime = new JTextField();
		JTextField fieldBrojTel = new JTextField();
		JTextField fieldMail = new JTextField();
		JTextField fieldAdresa = new JTextField();
		JTextField fieldNazivMesta = new JTextField();
		JTextField fieldPtt = new JTextField();

		Object[] message = {
				"Korisnicko ime:", fieldKorIme,
				"Sifra:", fieldSifra,
				"Tip:", fieldTip,
				"Ime:", fieldIme,
				"Prezime:", fieldPrezime,
				"Broj telefona:", fieldBrojTel,
				"Email:", fieldMail,
				"Adresa:", fieldAdresa,
				"Naziv mesta:", fieldNazivMesta,
				"PTT:", fieldPtt
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Dodavanje korisnika", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Korisnik k = new Korisnik(fieldIme.getText(), fieldPrezime.getText(), fieldBrojTel.getText(),
									  fieldMail.getText(), new Mesto(fieldAdresa.getText(), fieldNazivMesta.getText(), Integer.parseInt(fieldPtt.getText())), 
									  fieldKorIme.getText(), fieldSifra.getText(), TipKorisnika.valueOf(fieldTip.getText()));
			Sistem.dodajKorisnika(k);
			JSONWriter.upisiKorisnike();
		}
	}
	
	private void dodajNaplatnuStanicu() {
		JTextField fieldNazivStanice = new JTextField();
		JTextField fieldSefStanice = new JTextField();
		JTable tableSusedneStanice = new JTable();
		JScrollPane scroller = new JScrollPane(tableSusedneStanice);
		
		Object[][] data = parsirajListuNaplatnihStanica(Sistem.stanice);
		tableSusedneStanice.setModel(new DefaultTableModel(data, napStaniceHeader));
		
		Object[] message = {
				"Naziv stanice: ", fieldNazivStanice,
				"Korisnicko ime sefa", fieldSefStanice,
				"Susedne stanice", scroller
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Dodavanje naplatne stanice",
													JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Korisnik sef = null;
			for (int i = 0; i < Sistem.korisnici.size(); ++i) {
				if (Sistem.korisnici.get(i).getKorisnickoIme().equals(fieldSefStanice.getText()))
					sef = Sistem.korisnici.get(i);
			}
			
			NaplatnaStanica ns = new NaplatnaStanica(Sistem.stanice.size() + 1, fieldNazivStanice.getText(), sef);
			
			int[] selektovaneStanice = tableSusedneStanice.getSelectedRows();

			for (int i = 0; i < selektovaneStanice.length; ++i) {
				ns.dodajDeonicu(Sistem.stanice.get(selektovaneStanice[i]));
			}
			
			Sistem.dodajStanicu(ns);
			JSONWriter.upisiNaplatneStanice();
		}
	}
	
	private void dodajNaplatnoMesto() {
		JTable tableNapStanice = new JTable();
		JScrollPane scroller = new JScrollPane(tableNapStanice);
		
		Object[][] data = parsirajListuNaplatnihStanica(Sistem.stanice);
		tableNapStanice.setModel(new DefaultTableModel(data, napStaniceHeader));
		
		Object[] message = {
				"Naplatne stanice", scroller
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Dodavanje naplatnog mesta",
													JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			int index = tableNapStanice.getSelectedRow();
			NaplatnaStanica ns = Sistem.stanice.get(index);
			RegularnoMesto rm = new RegularnoMesto(true, String.valueOf(Sistem.naplatnaMesta.size() + 1), ns.getIdStanice());
			Sistem.dodajNaplatnoMesto(rm);
			ns.dodajNaplatnoMesto(rm);
			JSONWriter.upisiNaplatnaMesta();
			JSONWriter.upisiNaplatneStanice();
		}
	}
	
	private void dodajDeonicu() {
		// TODO
	}
	
	/**
	 * Otvara JOptionPane sa text field-ovima za izmenu podataka.
	 */
	private void izmeniKorisnika() {
		int[] selectedRows = view.getSelectedRows(0);
		
		if (selectedRows.length != 1) {
			JOptionPane.showMessageDialog(null, "Morate selektovari samo jednog korisnika.",
					"Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Korisnik k = Sistem.korisnici.get(selectedRows[0]);
		
		JTextField fieldSifra = new JTextField(k.getSifra());
		JTextField fieldTip = new JTextField(k.getTip().toString());

		Object[] message = {
				"Korisnicko ime:", new JLabel(k.getKorisnickoIme()),
				"Sifra:", fieldSifra,
				"Tip:", fieldTip
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Izmena korisnika", JOptionPane.OK_CANCEL_OPTION);
		
		while ((fieldSifra.getText().isEmpty() || fieldTip.getText().isEmpty()) &&
				option != JOptionPane.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Morate popuniti sva polja", "Greska", JOptionPane.ERROR_MESSAGE);
			option = JOptionPane.showConfirmDialog(null, message, "Izmena korisnika", JOptionPane.OK_CANCEL_OPTION);
		}
		
		if (option == JOptionPane.OK_OPTION) {
			k.setSifra(fieldSifra.getText());
			k.setTip(TipKorisnika.valueOf(fieldTip.getText()));
			JSONWriter.upisiKorisnike();
		}
	}
	
	private void izmeniNaplatnuStanicu() {
		int[] selectedRows = view.getSelectedRows(1);
		
		if (selectedRows.length != 1) {
			JOptionPane.showMessageDialog(null, "Morate selektovari samo jednu naplatnu stanicu.",
					"Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		NaplatnaStanica np = Sistem.stanice.get(selectedRows[0]);
		
		JTextField fieldNazivStanice = new JTextField(np.getNazivStanice());
		JTextField fieldSef = new JTextField(np.getSefStanice().getKorisnickoIme());
		
		Object[] message = {
				"Naziv stanice:", fieldNazivStanice,
				"Sef stanice:", fieldSef
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Izmena naplatne stanice", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Korisnik k = Sistem.nadjiKorisnika(fieldSef.getText());
			np.setNazivStanice(fieldNazivStanice.getText());
			np.setSefStanice(k);
			
			JSONWriter.upisiNaplatneStanice();
		}
	}
	
	/**
	 * Metoda koja brise entitet sa indexom (indexZaBrisanje) iz odgovarajuce liste
	 * koja se brisa na osnovu otvorenog taba.
	 */
	private void obrisiIzListe(int tab, int indexZaBrisanje) {
		switch (tab) {
		case 0: // Tablea korisnici
			Sistem.obrisiKorisnika(indexZaBrisanje);
			JSONWriter.upisiKorisnike();
			break;
			
		case 1: // Tabela naplatne stanice
			Sistem.obrisiNaplatnuStanicu(indexZaBrisanje);
			JSONWriter.upisiNaplatneStanice();
			break;
			
		case 2: // Tabela naplatna mesta
			Sistem.obrisiNaplatnoMesto(indexZaBrisanje);
			JSONWriter.upisiNaplatnaMesta();
			break;
			
		case 3: // Deonica
			// TODO
			break;
		}
	}
	
	/**
	 * Metoda od ArrayList<Korisnik> kreira matricu Stringova (ArrayList)
	 * i potom tu matricu konvertuje u Object[][] koji je potreban za JTable.
	 */
	private Object[][] parsirajListuKorisnika(ArrayList<Korisnik> lista) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		for (Korisnik k : lista) {
			ArrayList<String> podaci = new ArrayList<String>();
			podaci.add(k.getKorisnickoIme());
			podaci.add(k.getTip().toString());
			podaci.add(k.getIme());
			podaci.add(k.getPrezime());
			podaci.add(k.getBrojTelefona());
			podaci.add(k.getMesto().getAdresa());
			podaci.add(k.getMesto().getNazivMesta());
			
			data.add(podaci);
		}
		
		Object[][] podaci = new Object[data.size()][];
		for (int i = 0; i < data.size(); i++) {
		    ArrayList<String> row = data.get(i);
		    podaci[i] = row.toArray(new Object[row.size()]);
		}
		
		return podaci;
	}
	
	private Object[][] parsirajListuNaplatnihStanica(ArrayList<NaplatnaStanica> ns) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		for (NaplatnaStanica s : ns) {
			ArrayList<String> podaci = new ArrayList<String>();
			//{"ID stanice", "Naziv stanice", "Sef stanice", "Broj nap.mesta
			podaci.add(String.valueOf(s.getIdStanice()));
			podaci.add(s.getNazivStanice());
			podaci.add(s.getSefStanice().getIme() + " " + s.getSefStanice().getIme());
			podaci.add(String.valueOf(s.brojNaplatnihMesta()));
			
			data.add(podaci);
		}
		
		Object[][] podaci = new Object[data.size()][];
		for (int i = 0; i < data.size(); ++i) {
			ArrayList<String> row = data.get(i);
			podaci[i] = row.toArray(new Object[row.size()]);
		}
		
		return podaci;
	}
	
	private Object[][] parsirajListuNaplatnihMesta(ArrayList<RegularnoMesto> rm) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		for (RegularnoMesto r : rm) {
			ArrayList<String> podaci = new ArrayList<String>();
			podaci.add(String.valueOf(r.isAktivnost()));
			podaci.add(r.getId());
			podaci.add(String.valueOf(r.getIdStanice()));
			podaci.add(Sistem.nadjiStanicu(r.getIdStanice()).getNazivStanice());
			
			data.add(podaci);
		}
		
		Object[][] podaci = new Object[data.size()][];
		for (int i = 0; i < data.size(); ++i) {
			ArrayList<String> row = data.get(i);
			podaci[i] = row.toArray(new Object[row.size()]);
		}
		
		return podaci;
	}
	
	private void initTabelaKorisnici(int tab) {
		Object[][] data = parsirajListuKorisnika(Sistem.korisnici);
		view.setDataToTable(tab, korisniciHeader, data);
	}
	
	private void initTabelaNaplatneStanice(int tab) {
		Object[][] data = parsirajListuNaplatnihStanica(Sistem.stanice);
		view.setDataToTable(tab, napStaniceHeader, data);
	}
	
	private void initTabelaNaplatnaMesta(int tab) {
		Object[][] data = parsirajListuNaplatnihMesta(Sistem.naplatnaMesta);
		view.setDataToTable(tab, napMestaHeader, data);
	}
	
	private void initTabelaDeonice(int tab) {
		// TODO
	}
	
	// Kada se promeni tab procitati sve objekte i ubaciti ih u tabelu (== refresh)
	private void setTabChangedListener() {
		view.setTabChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				String[] header = null;
				Object[][] data = null;
				
				view.btnIzmeniEnable(true);
				
				switch (view.getSelectedTab()) {
				case 0: // Tabela korisnika
					initTabelaKorisnici(0);
					break;
					
					// TODO: ovo su samo test podaci, zameniti kasnije sa pravim metodama
					// koje ce parsirati objekte u odgovarajuci format za JTable
				case 1: // Tabela naplatnih stanica
					initTabelaNaplatneStanice(1);
					break;
					
				case 2: // Tabela naplatnih mesta
					initTabelaNaplatnaMesta(2);
					view.btnIzmeniEnable(false);
					break;
					
				case 3: // Tabela deonica
					initTabelaDeonice(3);
					view.btnIzmeniEnable(false);

					header = new String[] {"id", "pocetak", "kraj"};
					data = new Object[][] {
						{"1", "kg", "ns"},
						{"2", "kr", "bg"},
						{"3", "bg", "ni"}
					};
					view.setDataToTable(view.getSelectedTab(), header, data);
					break;
				}
			}
		});
	}
}
