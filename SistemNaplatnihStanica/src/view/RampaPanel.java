package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RampaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel cenaLabel;
	private JLabel cenaValLabel;
	private JLabel uplatioLabel;
	private JLabel kusurLabel;
	private JLabel kusurValLabel;
	
	private JTextField uplatioField;
	
	private JButton btnKvar;
	private JButton btnRampa;
	
	private JRadioButton dinValuta;
	private JRadioButton eurValuta;
	private ButtonGroup valutaGroup;
	
	private RampaListener rampaListener;
	
	
	public RampaPanel() {
		
		setBorder(BorderFactory.createTitledBorder("Info o rampi"));
		
		cenaLabel = new JLabel("Cena: ");
		cenaValLabel = new JLabel("");
		uplatioLabel = new JLabel("Uplatio: ");
		kusurLabel = new JLabel("Kusur: ");
		kusurValLabel = new JLabel("");
		
		
		uplatioField = new JTextField(10);
		
		btnKvar = new JButton("Kvar");
		btnKvar.setPreferredSize(new Dimension(0, 40));
		btnKvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Kvar");
			}
			
		});
		
		btnRampa = new JButton("Rampa");
		btnRampa.setPreferredSize(new Dimension(0, 40));
		btnRampa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cena;
				if ((cena = cenaValLabel.getText()).equals("")) {
					return;
				}
				String valuta = valutaGroup.getSelection().getActionCommand();
				String uplatio;
				if ((uplatio = uplatioField.getText()).equals("")) {
					return;
				}
				DolazakEvent event = new DolazakEvent(this, Integer.valueOf(cena), valuta, Integer.valueOf(uplatio));
				rampaListener.rampaListenerOccured(event);
			}
			
		});
		
		dinValuta = new JRadioButton("DIN");
		dinValuta.setActionCommand("DIN");
		dinValuta.setSelected(true);
		eurValuta = new JRadioButton("EUR");
		eurValuta.setActionCommand("EUR");
		valutaGroup = new ButtonGroup();
		valutaGroup.add(dinValuta);
		valutaGroup.add(eurValuta);
		
		layoutSetup();
		
	}
	
	public void layoutSetup() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(cenaLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cenaValLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(uplatioLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(uplatioField, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(kusurLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(kusurValLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_END;
		add(dinValuta, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		add(eurValuta, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weighty = 1.2;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(10, 0, 0, 0);
		add(btnRampa, gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.weighty = 1.2;
		gc.gridwidth = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnKvar, gc);
	}
	
	public JLabel getCenaLabel() {
		return cenaValLabel;
	}
	
	public void setRampaListener(RampaListener rampaListener) {
		this.rampaListener = rampaListener;
	}
	
	public void setKusur(String kusur) {
		this.kusurValLabel.setText(kusur);
	}
	
	public void resetField() {
		this.uplatioField.setText("");
	}
}
