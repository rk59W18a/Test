package test_2.punto4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto4.Categoria;
import dominio.parte2.punto4.Film;
import dominio.parte2.punto4.IRicercaStrategy;
import dominio.parte2.punto4.RicercaPerAttoreStrategy;
import dominio.parte2.punto4.RicercaPerGenereStrategy;
import dominio.parte2.punto4.RicercaPerRegistaStrategy;
import dominio.parte2.punto4.SottoCategoria;

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
		
		ArrayList <String> att1 = new ArrayList <String> ();
		att1.add("Jack Nicholson");
		att1.add("Shelley Duvall");
		att1.add("Danny Lloyd");
		att1.add("Scatman Crothers");
		ArrayList <String> att2 = new ArrayList <String> ();
		att2.add("Bill Skarsgard");
		att2.add("Jaeden Lieberher");
		ArrayList <String> att3 = new ArrayList <String> ();
		att3.add("Marlon Brando");
		att3.add("Al Pacino");
		
		f1 = new Film("Shining", 2, "Horror", 1980, "italiano", "Stanley Kubrick", att1, 145);
		f2 = new Film("It", 3, "Horror", 2017, "italiano", "Andy Muschietti", att2, 130);
		f3 = new Film("Il padrino", 2, "Drammatico", 1972, "italiano", "Francis Ford Coppola", att3, 180);	
		
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
	public void ricercaFilmPerAttore() {
		r = new RicercaPerAttoreStrategy("AL Pacino");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaFilmPerArtistaSoloNome() {
		r = new RicercaPerAttoreStrategy("Al");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaFilmPerArtistaSoloCognome() {
		r = new RicercaPerAttoreStrategy("Pacino");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaFilmPerArtistaNomeECognomeSenzaSpazio() {
		r = new RicercaPerAttoreStrategy("AlPacino");
		assertFalse(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaPiuFilmPerGenere() {
		r = new RicercaPerGenereStrategy("Horror");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f1));
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f2));
	}
}