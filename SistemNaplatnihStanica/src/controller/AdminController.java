package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Korisnik;
import models.Sistem;
import view.AdminView;

public class AdminController {

	private static String[] korisniciHeader;
	
	private AdminView view;
	
	public AdminController(AdminView view) {
		this.view = view;
		
		korisniciHeader = new String[] {"Username", "Tip", "Ime", "Prezime",
									"Broj telefona", "Adresa", "Mesto"};
		
		initTabelaKorisnici();
		
		addBtnOdjavaListener();
		setTabChangedListener();
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
