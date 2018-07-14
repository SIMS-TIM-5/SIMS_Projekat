package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Korisnik extends Osoba {

	protected String korisnickoIme;
	protected String sifra;
	protected TipKorisnika tip;
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(String ime, String prezime, String brojTelefona, String mail, Mesto mesto, String korisnickoIme,
			String sifra, TipKorisnika tip) {
		super(ime, prezime, brojTelefona, mail, mesto);
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + ", tip=" + tip + "]";
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Korisnik other = (Korisnik) obj;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		
		return true;
	}
}
