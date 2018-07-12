package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Korisnik;
import models.RegularnoMesto;
import models.Sistem;

public class JSONWriter {

	public static void upisiKorisnike() { 
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, ArrayList<Korisnik>> map = new HashMap<String, ArrayList<Korisnik>>();
		map.put("korisnici", Sistem.korisnici);	
		
		try {
			// TODO: promeni putanju do pravog fajla sa korisnicima !!!
			mapper.writeValue(new File("data/novi_korisnici.json"), map);
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa korisnika u JSON fajl.");
			e.printStackTrace();
		}
	}
	
	public static void upisiNaplatnaMesta() { 
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, ArrayList<RegularnoMesto>> map = new HashMap<String, ArrayList<RegularnoMesto>>();
		map.put("naplatnaMesta", Sistem.naplatnaMesta);	
		
		try {
			// TODO: promeni putanju do pravog fajla sa nap.mestima !!!
			mapper.writeValue(new File("data/nova_naplatna_mesta.json"), map);
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa naplatnih mesta u JSON fajl.");
			e.printStackTrace();
		}
	}
}
