package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Mesto {

	private String adresa;
	private String nazivMesta;
	private int ptt;
	
	public Mesto() {
	}

	public Mesto(String adresa, String nazivMesta, int ptt) {
		this.adresa = adresa;
		this.nazivMesta = nazivMesta;
		this.ptt = ptt;
	}
	
	@Override
	public String toString() {
		return "Mesto [adresa=" + adresa + ", nazivMesta=" + nazivMesta + ", ptt=" + ptt + "]";
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getNazivMesta() {
		return nazivMesta;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

	public int getPtt() {
		return ptt;
	}

	public void setPtt(int ptt) {
		this.ptt = ptt;
	}
}
