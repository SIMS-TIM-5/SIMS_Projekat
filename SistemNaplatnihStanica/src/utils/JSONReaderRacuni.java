package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.NaplatnaStanica;
import models.Racun;
import models.Sistem;

public class JSONReaderRacuni {
	public static void procitajRacune(String path, Sistem sistem) {
		ObjectMapper mapper = new ObjectMapper();
		ListaRacuna lr = null;
		
		try {
			lr = mapper.readValue(new File(path), ListaRacuna.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Racun r : lr.getRacuni()) {
			for (NaplatnaStanica ns : sistem.stanice) {
				if (r.getIdStanice() == ns.getIdStanice()) {
					ns.dodajRacun(r);
				}
			}
		}
		
	}
	
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	static class ListaRacuna {
		private ArrayList<Racun> racuni;
		
		public ListaRacuna() {
		}

		public ArrayList<Racun> getRacuni() {
			return racuni;
		}
	}
}
