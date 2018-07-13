package models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaplatnaStanica {
	
	private int idStanice;
	private String nazivStanice;
	private Korisnik sefStanice;
	
	private ArrayList<Deonica> deonice;
	private ArrayList<NaplatnoMesto> naplatnaMesta;
	
	
	
	public NaplatnaStanica() {
		deonice = new ArrayList<Deonica>();
		naplatnaMesta = new ArrayList<NaplatnoMesto>();
	}
	
	
	
	public void dodajRacun() {
		
	}
	
	public void dodajNaplatnoMesto(NaplatnoMesto nm) {
		naplatnaMesta.add(nm);
	}
	
	public void izmeniNaplatnoMesto(NaplatnoMesto mesto) {
		
	}
	
	public void obrisiNaplatnoMesto(NaplatnoMesto mesto) {
		naplatnaMesta.remove(mesto);
	}
	
	//metoda koja se poziva pri kreiranju deonice u toj stanici
	public void dodajDeonicu(NaplatnaStanica susednaStanica, int cena) {
		for (Deonica d : deonice) {
			if ((d.getStanica1() == this && d.getStanica2() == susednaStanica) || 
					(d.getStanica2() == this && d.getStanica1() == susednaStanica)) {
				return;
			}
		}
		Deonica d = new Deonica(this, susednaStanica, cena);
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


	@JsonProperty("ID")
	public int getIdStanice() {
		return idStanice;
	}



	public void setIdStanice(int idStanice) {
		this.idStanice = idStanice;
	}


	@JsonProperty("Naziv")
	public String getNazivStanice() {
		return nazivStanice;
	}



	public void setNazivStanice(String nazivStanice) {
		this.nazivStanice = nazivStanice;
	}



	public Korisnik getSefStanice() {
		return sefStanice;
	}



	public void setSefStanice(Korisnik sefStanice) {
		this.sefStanice = sefStanice;
	}
	
	public ArrayList<Deonica> getDeonice() {
		return deonice;
	}



	public ArrayList<NaplatnoMesto> getNaplatnaMesta() {
		return naplatnaMesta;
	}



	@Override
	public String toString() {
		return "NaplatnaStanica [idStanice=" + idStanice + ", nazivStanice=" + nazivStanice + ", sefStanice="
				+ sefStanice + ", deonice=" + deonice + ", naplatnaMesta=" + naplatnaMesta + "]";
	}
	
	
	
}
