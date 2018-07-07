package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				
				// TODO: proveriti ispravnost username + password
				// ako je ispravna kombinacija otvoriti odgovarajuci panel
				
				System.out.println("Username: " + username);
				System.out.println("Password: " + password);
				System.out.println("Login button activated");
			}
		});
	}
}
