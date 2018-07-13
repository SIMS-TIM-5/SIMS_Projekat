package main;

import javax.swing.SwingUtilities;

import controller.LoginOperaterController;
import models.Sistem;
import view.OperaterLoginView;

public class OperaterProgram {
	
	public static void main(String[] args) {
		Sistem sistem = Sistem.getInstance();
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				OperaterLoginView view = new OperaterLoginView();
				LoginOperaterController controller = new LoginOperaterController(view);
			}
			
		});
		
	}
	
}
