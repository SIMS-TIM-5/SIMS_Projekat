package models;

public abstract class Korisnik extends Osoba {

	protected String korisnickoIme;
	protected String sifra;
	protected TipKorisnika tip;
	
	public Korisnik(String ime, String prezime, String brojTelefona, String mail, Mesto mesto, String korisnickoIme,
			String sifra, TipKorisnika tip) {
		super(ime, prezime, brojTelefona, mail, mesto);
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.tip = tip;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public TipKorisnika getTip() {
		return tip;
	}

	public void setTip(TipKorisnika tip) {
		this.tip = tip;
	}
}
