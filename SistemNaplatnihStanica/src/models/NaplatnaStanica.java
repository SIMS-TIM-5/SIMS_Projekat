package models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NaplatnaStanica {
	
	private int idStanice;
	private String nazivStanice;
	private Korisnik sefStanice;
	
	private ArrayList<Deonica> deonice;
	private ArrayList<NaplatnoMesto> naplatnaMesta;
	private ArrayList<Racun> racuni;
	
	
	public NaplatnaStanica() {
		deonice = new ArrayList<Deonica>();
		naplatnaMesta = new ArrayList<NaplatnoMesto>();
		racuni = new ArrayList<Racun>();
	}
	
	public NaplatnaStanica(int id, String naziv, Korisnik sef) {
		this();
		
		this.idStanice = id;
		this.nazivStanice = naziv;
		this.sefStanice = sef;
	}
	
	public void dodajRacun(Racun r) {
		racuni.add(r);
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
		if (susednaStanica == null) return;
		
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
	
	public void dodajDeonicu(NaplatnaStanica susednaStanica) {
		for (Deonica d : deonice) {
			if ((d.getStanica1() == this && d.getStanica2() == susednaStanica) || 
					(d.getStanica2() == this && d.getStanica1() == susednaStanica)) {
				return;
			}
		}
		Deonica d = new Deonica(this, susednaStanica);
		dodajDeonicuObj(d);
		susednaStanica.dodajDeonicuObj(d);
	}
	
	//metoda za sigurno dodavanje u listu deonica
	public void dodajDeonicuObj(Deonica deonica) {
		deonice.add(deonica);
	}
	
	public void obrisiDeonicu(int idDeonice) {
		int index = 0;
		
		for (Deonica d : deonice) {
			if (d.getId() == idDeonice) {
				break;
			}
			index++;
		}
		deonice.remove(index);
	}


	@JsonProperty("id")
	public int getIdStanice() {
		return idStanice;
	}



	public void setIdStanice(int idStanice) {
		this.idStanice = idStanice;
	}


	@JsonProperty("naziv")
	public String getNazivStanice() {
		return nazivStanice;
	}



	public void setNazivStanice(String nazivStanice) {
		this.nazivStanice = nazivStanice;
	}


	@JsonIgnore
	public Korisnik getSefStanice() {
		return sefStanice;
	}

	public String getSef() {
		return sefStanice.getKorisnickoIme();
	}

	public void setSefStanice(Korisnik sefStanice) {
		this.sefStanice = sefStanice;
	}
	
	@JsonIgnore
	public ArrayList<Deonica> getDeonice() {
		return deonice;
	}

	

	@JsonIgnore
	public ArrayList<NaplatnoMesto> getNaplatnaMesta() {
		return naplatnaMesta;
	}



	@Override
	public String toString() {
		return "NaplatnaStanica [idStanice=" + idStanice + ", nazivStanice=" + nazivStanice + ", sefStanice="
				+ sefStanice + ", deonice=" + deonice + ", naplatnaMesta=" + naplatnaMesta + "]";
	}
	
	
	public int brojNaplatnihMesta() {
		return naplatnaMesta.size();
	}
	
	public ArrayList<Deonica> getSusedneStanice() {
		return deonice;
	}
	
	public ArrayList<NaplatnoMesto> naplatnaMesta() {
		return naplatnaMesta;
	}
	
	public ArrayList<Deonica> deonice() {
		return deonice;
	}
	
	@JsonIgnore
	public ArrayList<Racun>  getRacuni() {
		return racuni;
	}
}
