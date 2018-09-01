package test_2.punto5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto5.Categoria;
import dominio.parte2.punto5.Film;
import dominio.parte2.punto5.IRicercaStrategy;
import dominio.parte2.punto5.Persona;
import dominio.parte2.punto5.RicercaPerArtistaStrategy;
import dominio.parte2.punto5.RicercaPerGenereStrategy;
import dominio.parte2.punto5.RicercaPerRegistaStrategy;
import dominio.parte2.punto5.SottoCategoria;

public class CategoriaConSottoCategorieTest {

	private Categoria cConSottoc;
	private SottoCategoria s1;
	private SottoCategoria s2;
	private Film f1;
	private Film f2;
	private Film f3;
	private IRicercaStrategy r;
	
	@Before
	public void initialize() {
		cConSottoc = new Categoria("Film", 15, 5, 2, 2);
		cConSottoc.inizializzaElencoSottoCategorie();
		
		s1 = new SottoCategoria("Horror");
		s2 =  new SottoCategoria("Drammatico");
		cConSottoc.aggiungiSottoCategoria(s1);
		cConSottoc.aggiungiSottoCategoria(s2);
		
		ArrayList <Persona> att1 = new ArrayList <Persona> ();
		att1.add(new Persona("Jack", "Nicholson"));
		att1.add(new Persona("Shelley", "Duvall"));
		att1.add(new Persona("Danny", "Lloyd"));
		att1.add(new Persona("Scatman", "Crothers"));
		ArrayList <Persona> att2 = new ArrayList <Persona> ();
		att2.add(new Persona("Bill", "Skarsgard"));
		att2.add(new Persona("Jaeden", "Lieberher"));
		ArrayList <Persona> att3 = new ArrayList <Persona> ();
		att3.add(new Persona("Marlon", "Brando"));
		att3.add(new Persona("Al", "Pacino"));
		
		f1 = new Film("Shining", 2, "Horror", 1980, "italiano", new Persona("Stanley", "Kubrick"), att1, 145);
		f2 = new Film("It", 3, "Horror", 2017, "italiano", new Persona("Andy", "Muschietti"), att2, 130);
		f3 = new Film("Il padrino", 2, "Drammatico", 1972, "italiano", new Persona("Francis Ford", "Coppola"), att3, 180);	
		
		s1.aggiungiRisorsa(f1);
		s1.aggiungiRisorsa(f2);
		s2.aggiungiRisorsa(f3);
	}
	
	@Test
	public void risorsaGiaPresente() {
		assertTrue(cConSottoc.verificaPresenza(f3.getTitolo()));
	}
	
	@Test
	public void risorsaNonPresente() {
		assertFalse(cConSottoc.verificaPresenza("Blade Runner"));
	}
	
	@Test
	public void ricercaFilmPerRegista() {
		r = new RicercaPerRegistaStrategy("Kubrick");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f1));
	
	}
	
	@Test
	public void ricercaFilmPerArtistaNomeECognome() {
		r = new RicercaPerArtistaStrategy("AL Pacino");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaFilmPerArtistaSoloNome() {
		r = new RicercaPerArtistaStrategy("Al");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaFilmPerArtistaSoloCognome() {
		r = new RicercaPerArtistaStrategy("Pacino");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaFilmPerArtistaNomeECognomeSenzaSpazio() {
		r = new RicercaPerArtistaStrategy("AlPacino");
		assertFalse(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaPiuFilmPerGenere() {
		r = new RicercaPerGenereStrategy("Horror");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f1));
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f2));
	}
}