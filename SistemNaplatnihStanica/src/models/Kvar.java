package models;

import java.util.Date;

public class Kvar {

	private TipKvara tipKvara;
	private String opis;
	private String idKvara;
	private Date datum;
	
	public Kvar(TipKvara tipKvara, String opis, String idKvara, Date datum) {
		super();
		this.tipKvara = tipKvara;
		this.opis = opis;
		this.idKvara = idKvara;
		this.datum = datum;
	}

	public TipKvara getTipKvara() {
		return tipKvara;
	}

	public void setTipKvara(TipKvara tipKvara) {
		this.tipKvara = tipKvara;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getIdKvara() {
		return idKvara;
	}

	public void setIdKvara(String idKvara) {
		this.idKvara = idKvara;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
}
