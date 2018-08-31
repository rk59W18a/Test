package test_5;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import dominio_5.Libro;
import dominio_5.Categoria;

public class CategoriaSenzaSottoCategorieTest {
	
	private Categoria cSenzaSottoc;
	private Libro l1;
	private Libro l2;

	@Before
	public void inizialize() {
		cSenzaSottoc = new Categoria("Libri", 30, 30, 3, 3);
		cSenzaSottoc.inizializzaElencoRisorse();
		
		Vector <String> aut1 = new Vector <String> ();
	    aut1.add("Antoine de Saint_Exupéry");
	    Vector <String> aut2 = new Vector <String> ();
	    aut2.add("J.R.R. Tolkien");
	    
	    l1 = new Libro("Il piccolo principe", 10, "Per ragazzi", 2015, "italiano", aut1, 137, "Newton Compton");
	    l2 = new Libro("Il signore degli anelli", 5, "Fantasy",2017, "italiano", aut2, 1264, "Bompiani");
	    
	    cSenzaSottoc.aggiungiRisorsa(l1);
	    cSenzaSottoc.aggiungiRisorsa(l2);
	}
	
	@Test
	public void aggiungeUnaNuovaRisorsa() {
		Vector <String> aut3 = new Vector <String> ();
	    aut3.add("George Orwell");
		Libro l3 = new Libro("Animal Farm", 8, "Classici", 2008, "english", aut3, 112, "Penguin Books");
		
		cSenzaSottoc.aggiungiRisorsa(l3);
        assertTrue(cSenzaSottoc.getElencoRisorse().contains(l3));
		   
	}
	
	@Test
	public void rimuoveUnaRisorsa() {
		cSenzaSottoc.rimuoviRisorsa(l2);
		assertFalse(cSenzaSottoc.getElencoRisorse().contains(l2));
	}
	
	@Test
	public void ricercaRisorsePerTitolo() {
		assertTrue(cSenzaSottoc.ricercaRisorse("Il piccolo principe", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerUnaParolaNelTitolo() {
		assertTrue(cSenzaSottoc.ricercaRisorse("piccolo", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerUnaParolaNelTitoloNessunRisultato() {
		assertFalse(cSenzaSottoc.ricercaRisorse("piccoli", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerTitoloInMaiuscolo() {
		assertTrue(cSenzaSottoc.ricercaRisorse("IL PICCOLO PRINCIPE", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerAnnoPubblicazione() {
		assertTrue(cSenzaSottoc.ricercaRisorse(2017, Categoria.RIC_PER_ANNOPUB).contains(l2));
	}
	
	@Test
	public void ricercaRisorsePerGenere() {
		assertTrue((cSenzaSottoc.ricercaRisorse("Horror", Categoria.RIC_PER_GENERE)).size() == 0);
	}
	
	@Test
	public void ricercaLibriPerAutore() {
		assertTrue(cSenzaSottoc.ricercaRisorse("Tolkien", Categoria.RIC_PER_AUTORE_I).contains(l2));
	}
	
	@Test
	public void ricercaLibriPerCaseEditrice() {
		assertTrue(cSenzaSottoc.ricercaRisorse("Newton Compton", Categoria.RIC_PER_CASAED).contains(l1));
	}
}
