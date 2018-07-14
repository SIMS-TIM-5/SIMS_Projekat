package view;

import java.util.EventObject;

public class DolazakEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private int iznos;
	private String valuta;
	private int uplatio;
	
	public DolazakEvent(Object source) {
		super(source);
	}
	
	public DolazakEvent(Object source, int iznos, String valuta, int uplatio) {
		super(source);
		this.iznos = iznos;
		this.valuta = valuta;
		this.uplatio = uplatio;
	}

	public int getIznos() {
		return iznos;
	}

	public String getValuta() {
		return valuta;
	}

	public int getUplatio() {
		return uplatio;
	}
	
}
