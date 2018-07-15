package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Korisnik;
import models.Sistem;
import models.TipKorisnika;
import view.AdminView;
import view.CentralaView;
import view.LoginView;

public class LoginController {

	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
		
		addLoginBtnListener();
	}
	
	private void addLoginBtnListener() {
		view.setLoginBtnListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = view.getUsername();
				String password = view.getPassword();
				boolean pronadjenKorisnik = false;
				
				for (Korisnik k : Sistem.korisnici) {
					if (!k.getKorisnickoIme().equals(username) || !k.getSifra().equals(password))
						continue;
						
					if (k.getTip() == TipKorisnika.ADMINISTRATOR) {
						view.setVisible(false);
						AdminView adminView = new AdminView();
						AdminController controller = new AdminController(adminView);
						System.out.println("Prozor za Admina je pokrenut.");
					} else if (k.getTip() == TipKorisnika.OPERATER_CENTRALE) {
						view.setVisible(false);
						CentralaView centralaView = new CentralaView(k);
						CentralaController controller = new CentralaController(centralaView);
						System.out.println("Prozor za Operatera centrale je pokrenut.");
					} else if (k.getTip() == TipKorisnika.SEF_NAPLATNE_STANICE) {
						// TODO: odavne se pokrece frame za sefa
						// ne zaboraviti view.setvisible(false)
						view.setVisible(false);
						CentralaView centralaView = new CentralaView(k);
						CentralaController controller = new CentralaController(centralaView);
						System.out.println("Prozor za Sefa naplatne stanice je pokrenut");
					}
					
					pronadjenKorisnik = true;
				}
				
				if (!pronadjenKorisnik)
					view.showDialogWrongLogin();
			}
		});
	}
}
