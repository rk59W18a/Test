package test_2.punto3;

import static org.junit.Assert.*;



import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto3.Categoria;
import dominio.parte2.punto3.Film;
import dominio.parte2.punto3.Ricerca;
import dominio.parte2.punto3.RicercaPerAttore;
import dominio.parte2.punto3.RicercaPerGenere;
import dominio.parte2.punto3.RicercaPerRegista;
import dominio.parte2.punto3.SottoCategoria;
import utility_2.SottoCategoriaException;

public class CategoriaConSottoCategorieTest {

	private Categoria cConSottoc;
	private SottoCategoria s1;
	private SottoCategoria s2;
	private Film f1;
	private Film f2;
	private Film f3;
	private Ricerca r;
	
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
	public void risorsaGiàPresente() {
		assertTrue(cConSottoc.verificaPresenza(f3.getTitolo()));
	}
	
	@Test
	public void risorsaNonPresente() {
		assertFalse(cConSottoc.verificaPresenza("Blade Runner"));
	}
	
	@Test
	public void ricercaFilmPerRegista() {
		r = new RicercaPerRegista("Kubrick");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f1));
	
	}
	
	@Test
	public void ricercaFilmPerAttore() {
		r = new RicercaPerAttore("AL Pacino");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f3));
	}
	
	@Test
	public void ricercaPiùFilmPerGenere() {
		r = new RicercaPerGenere("Horror");
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f1));
		assertTrue(cConSottoc.ricercaRisorsa(r).contains(f2));
	}
	
	@Test(expected = SottoCategoriaException.class)
	public void shouldThrowSottoCategoriaException1() throws Exception {
		s1.verificaPresenza("It");
	}	
	
	@Test(expected = SottoCategoriaException.class)
	public void shouldThrowSottoCategoriaException2() throws Exception {
		s1.aggiungiSottoCategoria(new SottoCategoria("Gialli"));
	}
	
}
