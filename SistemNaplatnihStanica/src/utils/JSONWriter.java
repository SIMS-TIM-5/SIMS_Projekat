package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Korisnik;
import models.Sistem;

public class JSONWriter {

	public static void upisiKorisnike() { 
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, ArrayList<Korisnik>> map = new HashMap<String, ArrayList<Korisnik>>();
		map.put("korisnici", Sistem.korisnici);	
		
		try {
			// TODO: promeni putanju do pravog fajla sa korisnicima !!!
			mapper.writeValue(new File("data/KORISNICITEST.json"), map);
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa korisnika u JSON fajl.");
			e.printStackTrace();
		}
	}
}
