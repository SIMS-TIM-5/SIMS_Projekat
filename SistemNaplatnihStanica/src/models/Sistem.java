package models;

import java.util.ArrayList;

import utils.JSONReader;
import utils.JSONReaderRacuni;
import utils.JSONReaderStanice;

public class Sistem {

	private static Sistem instance;
	
	public static ArrayList<Korisnik> korisnici;
	public static ArrayList<NaplatnaStanica> stanice;
	public static ArrayList<RegularnoMesto> naplatnaMesta;
	
	public static Sistem getInstance() {
		if (instance == null) {
			instance = new Sistem();
		}
		return instance;
	}
	
	private Sistem() {
		korisnici = JSONReader.procitajKorisnike("data/korisnici.json");
		stanice = JSONReaderStanice.procitajStanice("data/stanice.json", this);
		naplatnaMesta = JSONReader.procitajRegularnaMesta("data/naplatnaMesta.json", this);
		JSONReaderRacuni.procitajRacune("data/svi_racuni.json", this);
	}
	
	public static void dodajKorisnika(Korisnik k) {
		if (k == null || korisnici.contains(k))
	        return;
		
	    korisnici.add(k);
	}
	
	public static void obrisiKorisnika(Korisnik k) {
		if (k == null || !korisnici.contains(k))
			return;
		
		korisnici.remove(k);
	}
	
	public static void obrisiKorisnika(int index) {
		if (index >= korisnici.size())
			return;
		
		korisnici.remove(index);
	}
	
	public static void dodajNaplatnoMesto(RegularnoMesto r) {
		if (r == null || naplatnaMesta.contains(r))
			return;
		
		naplatnaMesta.add(r);
	}
	
	public static void obrisiNaplatnoMesto(RegularnoMesto r) {
		if (r == null || !naplatnaMesta.contains(r))
			return;
		
		naplatnaMesta.remove(r);
	}
	
	public static void obrisiNaplatnoMesto(int index) {
		if (index >= naplatnaMesta.size())
			return;
		
		RegularnoMesto rm = naplatnaMesta.get(index);
		NaplatnaStanica st = nadjiStanicu(rm.getIdStanice());
		
		st.obrisiNaplatnoMesto(rm);
		
		naplatnaMesta.remove(index);
	}
	
	public static void dodajStanicu(NaplatnaStanica ns) {
		if (ns == null || stanice.contains(ns))
			return;
		
		stanice.add(ns);
	}
	
	public static void obrisiNaplatnuStanicu(int index) {
		if (index >= stanice.size())
			return;
		
		NaplatnaStanica ns = stanice.get(index);
		ArrayList<NaplatnoMesto> mesta = ns.naplatnaMesta();
		ArrayList<Integer> zaBrisanje = new ArrayList<Integer>();
		
		for (NaplatnoMesto m : mesta) {
			for (int i = 0; i < naplatnaMesta.size(); ++i) {
				if (naplatnaMesta.get(i).getId().equals(m.getId())) {
					zaBrisanje.add(i);
				}
			}
		}
		
		for (int i = zaBrisanje.size() - 1; i >= 0; --i) {
			naplatnaMesta.remove(zaBrisanje.get(i).intValue());
		}

		stanice.remove(index);
	}
	
	public static NaplatnaStanica nadjiStanicu(int id) {
		for (int i = 0; i < stanice.size(); ++i) {
			if (stanice.get(i).getIdStanice() == id) 
				return stanice.get(i);
		}
		
		return null;
	}
	
	public static Korisnik nadjiKorisnika(String korIme) {
		for (int i = 0; i < korisnici.size(); ++i) {
			if (korisnici.get(i).getKorisnickoIme().equals(korIme))
				return korisnici.get(i);
		}
		
		return null;
	}
	
	public static void obrisiDeonicu(int indexZaBrisanje) {
		int i = 0;
		
		for (NaplatnaStanica ns : stanice) {
			int idDeonice = 0;
			
			for (Deonica d : ns.deonice()) {
				if (i == indexZaBrisanje) {
					idDeonice = d.getId();
					break;
				}
				
				++i;
			}
			
			ns.obrisiDeonicu(idDeonice);
		}
	}
}
