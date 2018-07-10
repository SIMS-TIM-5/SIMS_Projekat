package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Korisnik;

public class JSONReader {

	/**
	 * Cita korisnike iz fajla cija putanja se prosledi kao parametar.
	 */
	public static ArrayList<Korisnik> procitajKorisnike(String path) {
		ObjectMapper mapper = new ObjectMapper();
		ListaKorisnika lk = null;
		
		try {
			lk = mapper.readValue(new File(path), ListaKorisnika.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lk.getKorisnici();
	}

	/**
	 * Pomocna klasa za citanje JSON fajla sa korisnicima
	 */
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	static class ListaKorisnika {
		private ArrayList<Korisnik> korisnici;
		
		public ListaKorisnika() {
		}

		public ArrayList<Korisnik> getKorisnici() {
			return korisnici;
		}
	}
}
