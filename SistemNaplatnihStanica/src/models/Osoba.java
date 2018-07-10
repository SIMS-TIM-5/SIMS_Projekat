package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public abstract class Osoba {

	protected String ime;
	protected String prezime;
	protected String brojTelefona;
	protected String mail;
	protected Mesto mesto;
	
	public Osoba() {
	}

	public Osoba(String ime, String prezime, String brojTelefona, String mail, Mesto mesto) {
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.mail = mail;
		this.mesto = mesto;
	}

	@Override
	public String toString() {
		return "Osoba [ime=" + ime + ", prezime=" + prezime + ", brojTelefona=" + brojTelefona + ", mail=" + mail
				+ ", mesto=" + mesto + "]";
	}
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
}
