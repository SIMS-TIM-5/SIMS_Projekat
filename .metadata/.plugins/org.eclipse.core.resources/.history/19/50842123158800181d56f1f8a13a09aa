package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import models.stanja.naplatnoMesto.StanjeNaplatnogMesta;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public abstract class NaplatnoMesto {

	protected boolean aktivnost;
	protected String id;
	protected int idStanice;
	
	public NaplatnoMesto() {
	}
	
	public NaplatnoMesto(boolean aktivnost, String id, int idStanice) {
		this.aktivnost = aktivnost;
		this.id = id;
		this.idStanice = idStanice;
	}
	
	public void displejZabranjenProlaz(){
		
	}
	
	public void displejDozvoljenProlaz(){
		
	}
	
	public void detekcijaVozila(){
		
	}
	
	public void prijavaKvara(){
		
	}
	
	public void brisanjeKvara(String id){
		
	}
	
	public void naplatiProlaz(){
		
	}
	
	public void podigniRampu(){
		
	}
	
	public void promeniStanjeNaplatnogMesta(StanjeNaplatnogMesta stanje){
		
	}

	public boolean isAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIdStanice() {
		return idStanice;
	}

	public void setIdStanice(int idStanice) {
		this.idStanice = idStanice;
	}
}
