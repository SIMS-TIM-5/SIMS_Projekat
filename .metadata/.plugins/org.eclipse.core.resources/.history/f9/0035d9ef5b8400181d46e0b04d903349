package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int  SCREEN_WIDTH     = 400;
	private static final int  SCREEN_HEIGHT    = 100;
	
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
		
		setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		initComponents();
		
		add(panel);
		setVisible(true);
	}
	
	private void initComponents() {
		panel.add(new JLabel("Username:"));
		usernameField = new JTextField(20);
		panel.add(usernameField);
		
		panel.add(new JLabel("Password:"));
		passwordField = new JPasswordField(20);
		panel.add(passwordField);
	
		btnLogin = new JButton("Login");
		panel.add(btnLogin);

		add(panel, BorderLayout.CENTER);
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
	
	// Samo za testiranje view-a
	public static void main(String args[]) {
		new LoginView();
	}
}
