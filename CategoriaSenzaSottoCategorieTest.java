package test_2.punto4;

import static org.junit.Assert.*;



import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto4.Categoria;
import dominio.parte2.punto4.Libro;
import dominio.parte2.punto4.IRicercaStrategy;
import dominio.parte2.punto4.RicercaPerAnnoPubblicazioneStrategy;
import dominio.parte2.punto4.RicercaPerAutoreStrategy;
import dominio.parte2.punto4.RicercaPerCasaEditriceStrategy;
import dominio.parte2.punto4.RicercaPerGenereStrategy;
import dominio.parte2.punto4.RicercaPerTitoloStrategy;

public class CategoriaSenzaSottoCategorieTest {
	
    private Categoria cSenzaSottoc;
    private Libro l1; 
    private Libro l2;
    private IRicercaStrategy r;
    
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
	public void ricercaRisorsePerTitolo(){
		r = new RicercaPerTitoloStrategy("Il piccolo principe");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerUnaParolaNelTitolo() {
		r = new RicercaPerTitoloStrategy("piccolo");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerUnaParolaNelTitoloNessunRisultato() {
		r = new RicercaPerTitoloStrategy("piccoli");
		assertFalse(cSenzaSottoc.ricercaRisorsa(r).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerTitoloInMaiuscolo() {
		r = new RicercaPerTitoloStrategy("IL PICCOLO PRINCIPE");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l1));
	}
	
	@Test
	public void ricercaRisorsePerAnnoPubblicazione() {
		r = new RicercaPerAnnoPubblicazioneStrategy("2017");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l2));
	}
	
	@Test
	public void ricercaRisorsePerGenere() {
		r = new RicercaPerGenereStrategy("Horror");
		assertTrue((cSenzaSottoc.ricercaRisorsa(r)).size() == 0);
	}
	
	@Test
	public void ricercaLibriPerAutore() {
		r = new RicercaPerAutoreStrategy("Tolkien");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l2));
	}
	
	@Test
	public void ricercaLibriPerCaseEditrice() {
		r = new RicercaPerCasaEditriceStrategy("Newton Compton");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l1));
	}
}
