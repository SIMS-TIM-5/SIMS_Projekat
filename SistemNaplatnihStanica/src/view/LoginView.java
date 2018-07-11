package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int  SCREEN_WIDTH     = 280;
	private static final int  SCREEN_HEIGHT    = 140;
	
	private JPanel panel;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	
	public LoginView() {
		setTitle("Sistem naplatnih stanica - Login");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel = new JPanel();
		//panel.setLayout(new GridLayout(3, 1));
		initComponents();
		
		add(panel);
		setVisible(true);
	}
	
	private void initComponents() {
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		btnLogin = new JButton("Login");
		
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(10, 10, 0, 0);
		panel.add(new JLabel("Username: "), gc);
		
		gc.weightx = 15;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(10, 0, 0, 10);
		panel.add(usernameField, gc);
		
		gc.weightx = 1;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 10, 0, 0);
		panel.add(new JLabel("Password: "), gc);
		
		gc.weightx = 15;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 10);
		panel.add(passwordField, gc);
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(btnLogin, gc);
	}
	
	public void setLoginBtnListener(ActionListener al) {
		btnLogin.addActionListener(al);
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	public void showDialogWrongLogin() {
		JOptionPane.showMessageDialog(null, "Uneli ste pogresno korisnicko ime ili lozinku",
				"Greska", JOptionPane.ERROR_MESSAGE);
	}
	
	// Samo za testiranje view-a
	public static void main(String args[]) {
		new LoginView();
	}
}
