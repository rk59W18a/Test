package test_2.punto2;

import static org.junit.Assert.*;



import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto2.Categoria;
import dominio.parte2.punto2.Libro;

public class CategoriaSenzaSottoCategorieTest {
	
    private Categoria cSenzaSottoc;
    private Libro l1; 
    private Libro l2;
    
	@Before
	public void initialize() {
		cSenzaSottoc = new Categoria("Libri", 30, 30, 3, 3);
		cSenzaSottoc.inizializzaElencoRisorse();
		
		ArrayList <String> aut1 = new ArrayList <String> ();
	    aut1.add("Antoine de Saint_Exupéry");
	    ArrayList <String> aut2 = new ArrayList <String> ();
	    aut2.add("J.R.R. Tolkien");
	    
	    l1 = new Libro("Il piccolo principe", 10, "Per ragazzi", 2015, "italiano", aut1, 137, "Newton Compton");
	    l2 = new Libro("Il signore degli anelli", 5, "Fantasy",2017, "italiano", aut2, 1264, "Bompiani");
	    
	    cSenzaSottoc.aggiungiRisorsa(l1);
	    cSenzaSottoc.aggiungiRisorsa(l2);
	}
	
	@Test
	public void aggiungeUnaNuovaRisorsa() {
		ArrayList <String> aut3 = new ArrayList <String> ();
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
		assertTrue(cSenzaSottoc.ricercaRisorsa("Il piccolo principe", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerUnaParolaNelTitolo() {
		assertTrue(cSenzaSottoc.ricercaRisorsa("piccolo", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerUnaParolaNelTitoloNessunRisultato() {
		assertFalse(cSenzaSottoc.ricercaRisorsa("piccoli", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerTitoloInMaiuscolo() {
		assertTrue(cSenzaSottoc.ricercaRisorsa("IL PICCOLO PRINCIPE", Categoria.RIC_PER_TITOLO).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerAnnoPubblicazione() {
		assertTrue(cSenzaSottoc.ricercaRisorsa(2017, Categoria.RIC_PER_ANNOPUB).contains(l2));
	}
	
	@Test
	public void ricercaRisorsePerGenere() {
		assertTrue((cSenzaSottoc.ricercaRisorsa("Horror", Categoria.RIC_PER_GENERE)).size() == 0);
	}
	
	@Test
	public void ricercaLibriPerAutore() {
		assertTrue(cSenzaSottoc.ricercaRisorsa("Tolkien", Categoria.RIC_PER_AUTORE_I).contains(l2));
	}
	
	@Test
	public void ricercaLibriPerCaseEditrice() {
		assertTrue(cSenzaSottoc.ricercaRisorsa("Newton Compton", Categoria.RIC_PER_CASAED).contains(l1));
	}
}
