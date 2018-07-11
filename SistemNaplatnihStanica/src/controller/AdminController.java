package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Korisnik;
import models.Mesto;
import models.Sistem;
import models.TipKorisnika;
import utils.JSONWriter;
import view.AdminView;

public class AdminController {

	private static String[] korisniciHeader;
	
	private AdminView view;
	
	public AdminController(AdminView view) {
		this.view = view;
		
		korisniciHeader = new String[] {"Username", "Tip", "Ime", "Prezime",
									"Broj telefona", "Adresa", "Mesto"};
		
		initTabelaKorisnici();

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
				case 0: 
					dodajKorisnika();
					initTabelaKorisnici();
					break;
						
				// TODO: dodati i za ostale tab-ove (tabele) i kreiranje odgovarajucih objekata
				}
			}
		});
	}
	
	private void addBtnIzmenaListener() {
		view.setBtnIzmenaListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (view.getSelectedTab()) {
				case 0: // Tabela korisnika
					izmeniKorisnika();
					initTabelaKorisnici();
					break;
						
				// TODO: dodati i za ostale tab-ove (tabele)
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
		
		// TODO: treba izvrsiti proveru da li je unet tekst u sva polja
		// i da li korisnicko ime vec postoji !!!
		int option = JOptionPane.showConfirmDialog(null, message, "Dodavanje korisnika", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Korisnik k = new Korisnik(fieldIme.getText(), fieldPrezime.getText(), fieldBrojTel.getText(),
									  fieldMail.getText(), new Mesto(fieldAdresa.getText(), fieldNazivMesta.getText(), Integer.parseInt(fieldPtt.getText())), 
									  fieldKorIme.getText(), fieldSifra.getText(), TipKorisnika.valueOf(fieldTip.getText()));
			Sistem.korisnici.add(k);
			JSONWriter.upisiKorisnike();
		}
	}
	
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
	
	/**
	 * Metoda koja brise entitet sa indexom (indexZaBrisanje) iz odgovarajuce liste
	 * koja se brisa na osnovu otvorenog taba.
	 */
	private void obrisiIzListe(int tab, int indexZaBrisanje) {
		switch (tab) {
		case 0: // Tablea korisnici
			Sistem.korisnici.remove(indexZaBrisanje);
			JSONWriter.upisiKorisnike();
			break;
			
			// TODO: dodati za ostale tabele
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
	
	private void initTabelaKorisnici() {
		Object[][] data = parsirajListuKorisnika(Sistem.korisnici);
		view.setDataToTable(view.getSelectedTab(), korisniciHeader, data);
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
				case 0:
					initTabelaKorisnici();
					break;
					
					// TODO: ovo su samo test podaci, zameniti kasnije sa pravim metodama
					// koje ce parsirati objekte u odgovarajuci format za JTable
				case 1:
					header = new String[] {"id", "grad", "br. mesta"};
					data = new Object[][] {
						{"1", "ns", "3"},
						{"3", "bg", "2"}
					};
					view.setDataToTable(view.getSelectedTab(), header, data);
					break;
					
				case 2:
					header = new String[] {"id", "ime", "prezime"};
					data = new Object[][] {
						{"1", "Pera", "Peric"},
						{"2", "Marko", "Markovic"},
						{"3", "Filip", "Filipovic"}
					};
					view.setDataToTable(view.getSelectedTab(), header, data);
					break;
					
				case 3:
					header = new String[] {"id", "pocetak", "kraj"};
					data = new Object[][] {
						{"1", "kg", "ns"},
						{"2", "kr", "bg"},
						{"3", "bg", "ni"}
					};
					view.setDataToTable(view.getSelectedTab(), header, data);
					view.btnIzmeniEnable(false);
					break;
				}
			}
		});
	}
}
