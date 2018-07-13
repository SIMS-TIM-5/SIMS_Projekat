package models;

public class Deonica {
	
	private NaplatnaStanica stanica1;
	private NaplatnaStanica stanica2;
	
	private Cenovnik cenovnik;
	private int idDeonice;
	
	static private int id = 0;
	
	private Deonica() {}
	
	public Deonica(NaplatnaStanica s1, NaplatnaStanica s2) {
		idDeonice = ++id;
		stanica1 = s1;
		stanica2 = s2;
		cenovnik = new Cenovnik();
	}
	
	public void izmeniCenovnik(TipVozila tip, int cena) {
		cenovnik.cene.put(tip, cena);
	}
	
	public int getId() {
		return idDeonice;
	}

	public NaplatnaStanica getStanica1() {
		return stanica1;
	}

	public NaplatnaStanica getStanica2() {
		return stanica2;
	}
	
	
}
