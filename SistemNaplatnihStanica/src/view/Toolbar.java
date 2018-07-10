package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Toolbar extends JPanel {
	
	private JButton btnOdjava;
	private JLabel operaterLabel;
	
	public Toolbar() {
		
		setPreferredSize(new Dimension(0, 40));
		
		operaterLabel = new JLabel();
		
		btnOdjava = new JButton("Odjava");
		btnOdjava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Odjava");
			}
			
		});
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		add(btnOdjava);
	}
}
