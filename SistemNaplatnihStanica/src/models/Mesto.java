package models;

public class Mesto {

	private String adresa;
	private String nazivMesta;
	private int ptt;
	
	private Mesto() {
	}

	public Mesto(String adresa, String nazivMesta, int ptt) {
		this.adresa = adresa;
		this.nazivMesta = nazivMesta;
		this.ptt = ptt;
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
