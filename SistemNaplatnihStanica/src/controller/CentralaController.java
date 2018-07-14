package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Deonica;
import models.NaplatnaStanica;
import models.Racun;
import models.Sistem;
import sun.security.x509.IssuingDistributionPointExtension;
import view.CentralaView;
import view.RadioListener;

public class CentralaController {

	private CentralaView view;
	private Sistem sistem;

	public CentralaController(CentralaView view) {
		this.view = view;
		addBtnDatumListener();
		addBtnStanicaListener();
		addBtnPonistiListener();
		napuniListu();
		initTabele();
		view.setRadioListener(new RadioListener() {

			@Override
			public void radioListenerOcc(String s) {
				// TODO Auto-generated method stub
				System.out.println(s);
			}
		});
	}

	private void addBtnDatumListener() {
		view.setBtnDatumListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Datum dugme");
				Date datumK = null, datumP = null;
				SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy.");
				try {
					datumK = formatter.parse(view.getDatumK());
					datumP = formatter.parse(view.getDatumP());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				primeniDatum(datumP, datumK);
				initTabele();

				System.out.println(view.getDatumP());
				System.out.println(view.getDatumK());
			}
		});
	}

	private void addBtnStanicaListener() {
		view.setBtnStanicaListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Stanica dugme");
				System.out.println("Ocitao sam : " + view.getStanica());
				primeniStanicu(Integer.parseInt(view.getStanica()));
				initTabele();
				System.out.println(view.getListaRacuna().size());

			}
		});
	}

	private void addBtnPonistiListener() {
		view.setBtnPonisti(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Poinisti");
				view.isprazniTabelu();
				view.setStanicaEmpty();
				view.setDatumKEmpty();
				view.setDatumPEmpty();
				view.ponistiRadioBtn();
				napuniListu();
				initTabele();
				System.out.println(view.getListaRacuna().size());
			}
		});
	}

	private void napuniListu() {
		view.getListaRacuna().clear();
		sistem = Sistem.getInstance();
		for (int i = 0; i < Sistem.stanice.size(); i++) {
			for (int j = 0; j < Sistem.stanice.get(i).getRacuni().size(); j++) {
				view.getListaRacuna().add(Sistem.stanice.get(i).getRacuni().get(j));
			}
		}
	}

	private void primeniDatum(Date datumP, Date datumK) {
		// nije preporucljivo ali pomaze
		datumP.setHours(0);
		datumP.setMinutes(0);
		datumK.setHours(23);
		datumK.setMinutes(59);
		//
		ArrayList<Racun> pomocna = new ArrayList<>();
		for (int i = 0; i < view.getListaRacuna().size(); i++) {
			if (view.getListaRacuna().get(i).getDatumProlaska().before(datumK)
					&& view.getListaRacuna().get(i).getDatumProlaska().after(datumP)) {
				pomocna.add(view.getListaRacuna().get(i));
			}
		}
		view.setListaRacuna(pomocna);
	}
	
	private void primeniStanicu(int idStanice){
		int pid = idStanice;
		ArrayList<Racun> pomocna = new ArrayList<>();
		for(int i = 0; i < view.getListaRacuna().size();i++){
			System.out.println(view.getListaRacuna().size());
			int ppid = view.getListaRacuna().get(i).getIdStanice();
			if(view.getListaRacuna().get(i).getIdStanice() == idStanice){
				pomocna.add(view.getListaRacuna().get(i));
			}
		}
		view.setListaRacuna(pomocna);
	}
	
	private Object[][] parsirajListuRacuna(ArrayList<Racun> listaRacuna){
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		for(Racun r: listaRacuna){
			ArrayList<String> racunS = new ArrayList<String>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy.");
			racunS.add(String.valueOf(formatter.format(r.getDatumProlaska())));
			racunS.add(String.valueOf(r.getTipVozila()));
			racunS.add(String.valueOf(r.getIdStanice()));
			racunS.add(String.valueOf(r.getIdDeonice()));
			
			data.add(racunS);
		}
		
		Object[][] podaci = new Object[data.size()][];
		for (int i = 0; i < data.size(); ++i) {
			ArrayList<String> row = data.get(i);
			podaci[i] = row.toArray(new Object[row.size()]);
		}
		
		return podaci;
		
	}
	
	private void initTabele(){
		Object[][] data = parsirajListuRacuna(view.getListaRacuna());
		view.ubaciUTabelu(view.colums, data);
	}
	
	
}
