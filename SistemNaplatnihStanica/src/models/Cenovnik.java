package models;

import java.util.Date;
import java.util.HashMap;

public class Cenovnik {
	public Date datumVazenja;
	public HashMap<TipVozila, Integer> cene;
	
	public Cenovnik() {
		cene = new HashMap<TipVozila, Integer>();
	}
}
