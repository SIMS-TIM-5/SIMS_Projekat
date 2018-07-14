package models;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class SistemTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Sistem s = Sistem.getInstance();
	}
	
	@Test
	public void testDodajKorisnika() {
		Korisnik k = new Korisnik("Pera", "Pera", "123", "mail", new Mesto(), "pera", "123", TipKorisnika.ADMINISTRATOR);
		Korisnik k2 = new Korisnik("Pera", "Pera", "123", "mail", new Mesto(), "pera123123", "123", TipKorisnika.ADMINISTRATOR);

		assertEquals(false, Sistem.dodajKorisnika(k));
		assertEquals(false, Sistem.dodajKorisnika(null));
		assertEquals(true, Sistem.dodajKorisnika(k2));
	}

	@Test
	public void testObrisiKorisnika() {
		assertEquals(false, Sistem.obrisiKorisnika(null));
		assertEquals(false, Sistem.obrisiKorisnika(new Korisnik()));
		assertEquals(true, Sistem.obrisiKorisnika(new Korisnik("Miki", "Miki", "123", "mail", new Mesto(), "miki", "123", TipKorisnika.ADMINISTRATOR)));
	
		assertEquals(false, Sistem.obrisiKorisnika(100));
		assertEquals(true, Sistem.obrisiKorisnika(2));
	}
}
