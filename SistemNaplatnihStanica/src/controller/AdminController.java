package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.AdminView;

public class AdminController {

	private AdminView view;
	
	public AdminController(AdminView view) {
		this.view = view;
		
		setTabChangedListener();
	}

	// Kada se promeni tab procitati sve objekte i ubaciti ih u tabelu
	private void setTabChangedListener() {
		view.setTabChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				String[] header = null;
				Object[][] data = null;
				
				view.btnIzmeniEnable(true);
				
				switch (view.getSelectedTab()) {
				case 0:
					// TODO: samo test podaci, obrisati kasnije ili zameniti pravim vrednostima
					header = new String[] {"id", "ime", "prezime"};
					data = new Object[][] {
						{"1", "Pera", "Peric"},
						{"2", "Marko", "Markovic"},
						{"3", "Filip", "Filipovic"}
					};
					break;
					
				case 1:
					header = new String[] {"id", "grad", "br. mesta"};
					data = new Object[][] {
						{"1", "ns", "3"},
						{"3", "bg", "2"}
					};
					break;
					
				case 2:
					header = new String[] {"id", "ime", "prezime"};
					data = new Object[][] {
						{"1", "Pera", "Peric"},
						{"2", "Marko", "Markovic"},
						{"3", "Filip", "Filipovic"}
					};
					break;
					
				case 3:
					header = new String[] {"id", "pocetak", "kraj"};
					data = new Object[][] {
						{"1", "kg", "ns"},
						{"2", "kr", "bg"},
						{"3", "bg", "ni"}
					};
					view.btnIzmeniEnable(false);
					break;
				}
				
				view.setDataToTable(view.getSelectedTab(), header, data);
			}
		});
	}
}
