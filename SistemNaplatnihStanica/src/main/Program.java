package main;

import controller.LoginController;
import models.Sistem;
import view.LoginView;

public class Program {

	public static void main(String[] args) {
		Sistem sistem = Sistem.getInstance();
		
		LoginView loginView = new LoginView();
		LoginController loginController = new LoginController(loginView);
	}
}
