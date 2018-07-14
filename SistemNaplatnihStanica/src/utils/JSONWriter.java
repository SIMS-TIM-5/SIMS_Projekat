package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Korisnik;
import models.NaplatnaStanica;
import models.Racun;
import models.RegularnoMesto;
import models.Sistem;

public class JSONWriter {

	public static void upisiKorisnike() { 
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, ArrayList<Korisnik>> map = new HashMap<String, ArrayList<Korisnik>>();
		map.put("korisnici", Sistem.korisnici);	
		
		try {
			mapper.writeValue(new File("data/korisnici.json"), map);
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
			mapper.writeValue(new File("data/naplatnaMesta.json"), map);
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa naplatnih mesta u JSON fajl.");
			e.printStackTrace();
		}
	}
	
	public static void upisiNaplatneStanice() { 
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, ArrayList<NaplatnaStanica>> map = new HashMap<String, ArrayList<NaplatnaStanica>>();
		map.put("listaPodataka", Sistem.stanice);	
		
		try {
			mapper.writeValue(new File("data/stanice.json"), map);
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa naplatnih stanica u JSON fajl.");
			e.printStackTrace();
		}
	}
	
	public static void upisiRacune() { 
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Racun> sviRacuni = new ArrayList<Racun>();
		
		for (NaplatnaStanica ns : Sistem.stanice) {
			for (Racun r : ns.getRacuni()) {
				sviRacuni.add(r);
			}
		}
		
		Map<String, ArrayList<Racun>> map = new HashMap<String, ArrayList<Racun>>();
		map.put("racuni", sviRacuni);	
		
		try {
			mapper.writeValue(new File("data/svi_racuni.json"), map);
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa racuna u JSON fajl.");
			e.printStackTrace();
		}
	}
}
