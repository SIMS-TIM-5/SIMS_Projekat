package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import models.TipKvara;

public class KvarDialog extends JDialog {
	
	private JTextArea opisArea;
	private JRadioButton kriticanRadio;
	private JRadioButton nekriticanRadio;
	private ButtonGroup kriticanGroup;
	private JButton btnPotvrdi;
	private JPanel panel;
	
	private KvarListener kvarListener;
	
	public KvarDialog(JFrame parent) {
		super(parent, "Kvar", true);
		
		setSize(350,250);
		setLocationRelativeTo(parent);
		
		
		opisArea = new JTextArea(10,15);
		opisArea.setPreferredSize(new Dimension(200, 200));
		opisArea.setBorder(BorderFactory.createTitledBorder("Opis"));
		kriticanRadio = new JRadioButton("Kriticnan");
		kriticanRadio.setActionCommand("KRITICAN");
		nekriticanRadio = new JRadioButton("Nekriticnan");
		nekriticanRadio.setActionCommand("NEKRITICAN");
		kriticanGroup = new ButtonGroup();
		kriticanGroup.add(kriticanRadio);
		kriticanGroup.add(nekriticanRadio);
		
		btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TipKvara tip = TipKvara.valueOf(kriticanGroup.getSelection().getActionCommand());
				String opis = opisArea.getText();
				kvarListener.kvarListenerOccured(tip, opis);
				setVisible(false);
			}
		});
		btnPotvrdi.setPreferredSize(new Dimension(100, 50));
		panel = new JPanel();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(opisArea), BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		panel.add(kriticanRadio, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		panel.add(nekriticanRadio, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weighty = 3;
		panel.add(btnPotvrdi, gc);
		
		
	}
	
	public void setKvarListener(KvarListener kvarListener) {
		this.kvarListener = kvarListener;
	}
	
}