package models;

import models.stanja.naplatnoMesto.StanjeNaplatnogMesta;

public abstract class NaplatnoMesto {

	protected boolean aktivnost;
	protected String id;
	
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
	
}
