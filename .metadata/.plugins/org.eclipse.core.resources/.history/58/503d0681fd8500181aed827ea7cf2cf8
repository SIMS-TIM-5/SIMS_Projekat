package models;

import java.util.ArrayList;

public class NaplatnaStanica {
	
	private static int id = 0;
	
	private int idStanice;
	private int nazivStanice;
	
	private ArrayList<Deonica> deonice;
	private ArrayList<NaplatnoMesto> naplatnaMesta;
	
	public NaplatnaStanica() {
		idStanice = ++id;
		deonice = new ArrayList<Deonica>();
		naplatnaMesta = new ArrayList<NaplatnoMesto>();
	}
	
	public void dodajRacun() {
		
	}
	
	public void dodajNaplatnoMesto() {
		
	}
	
	public void izmeniNaplatnoMesto(NaplatnoMesto mesto) {
		
	}
	
	public void obrisiNaplatnoMesto(NaplatnoMesto mesto) {
		naplatnaMesta.remove(mesto);
	}
	
	//metoda koja se poziva pri kreiranju deonice u toj stanici
	public void dodajDeonicu(NaplatnaStanica susednaStanica) {
		Deonica d = new Deonica(this, susednaStanica);
		dodajDeonicuObj(d);
		susednaStanica.dodajDeonicuObj(d);
	}
	
	//metoda za sigurno dodavanje u listu deonica
	public void dodajDeonicuObj(Deonica deonica) {
		deonice.add(deonica);
	}
	
	public void obrisiDeonicu(int idDeonice) {
		for (Deonica d : deonice) {
			if (d.getId() == idDeonice) {
				deonice.remove(d);
			}
		}
	}
	
}
