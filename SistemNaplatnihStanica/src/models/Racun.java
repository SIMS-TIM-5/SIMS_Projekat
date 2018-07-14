package models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Racun {
	
	private TipVozila tipVozila;
	private int idDeonice;
	private String idNaplatnogMesta;
	private Date datumProlaska;
	private int idStanice;
	
	public Racun() {
		
	}
	
	public Racun(TipVozila tipVozila, int idDeonice, String idNaplatnogMesta, Date datumProlaska, int idStanice) {
		super();
		this.tipVozila = tipVozila;
		this.idDeonice = idDeonice;
		this.idNaplatnogMesta = idNaplatnogMesta;
		this.datumProlaska = datumProlaska;
		this.idStanice = idStanice;
	}

	public TipVozila getTipVozila() {
		return tipVozila;
	}

	public int getIdDeonice() {
		return idDeonice;
	}

	public String getIdNaplatnogMesta() {
		return idNaplatnogMesta;
	}

	public Date getDatumProlaska() {
		return datumProlaska;
	}

	public int getIdStanice() {
		return idStanice;
	}
	
	
}