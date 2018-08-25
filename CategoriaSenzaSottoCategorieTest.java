package test_2.punto5;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import dominio.parte2.punto5.Categoria;
import dominio.parte2.punto5.Libro;
import logica.parte2.punto5.Persona;
import dominio.parte2.punto5.IRicercaStrategy;
import dominio.parte2.punto5.RicercaPerAnnoPubblicazioneStrategy;
import dominio.parte2.punto5.RicercaPerArtistaStrategy;
import dominio.parte2.punto5.RicercaPerCasaEditriceStrategy;
import dominio.parte2.punto5.RicercaPerGenereStrategy;
import dominio.parte2.punto5.RicercaPerTitoloStrategy;

public class CategoriaSenzaSottoCategorieTest {
	
    private Categoria cSenzaSottoc;
    private Libro l1; 
    private Libro l2;
    private IRicercaStrategy r;
    
	@Before
	public void initialize() {
		cSenzaSottoc = new Categoria("Libri", 30, 30, 3, 3);
		cSenzaSottoc.inizializzaElencoRisorse();
		
	    ArrayList <Persona> aut1 = new ArrayList <Persona> ();
		aut1.add(new Persona("Antoine", "de Saint_Exupéry"));
		ArrayList <Persona> aut2 = new ArrayList <Persona> ();
		aut2.add(new Persona("J.R.R.", "Tolkien"));
		   
		l1 = new Libro("Il piccolo principe", 10, "Per ragazzi", 2015, "italiano", aut1, 137, "Newton Compton");
	    l2 = new Libro("Il signore degli anelli", 5, "Fantasy",2017, "italiano", aut2, 1264, "Bompiani");
	    
	    cSenzaSottoc.aggiungiRisorsa(l1);
	    cSenzaSottoc.aggiungiRisorsa(l2);
	}
	
	@Test
	public void aggiungeUnaNuovaRisorsa() {
		ArrayList <Persona> aut3 = new ArrayList <Persona> ();
	    aut3.add(new Persona("George", "Orwell"));
	    
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
	public void ricercaLibriPerArtista() {
		r = new RicercaPerArtistaStrategy("Tolkien");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l2));
	}
	
	@Test
	public void ricercaLibriPerCaseEditrice() {
		r = new RicercaPerCasaEditriceStrategy("Newton Compton");
		assertTrue(cSenzaSottoc.ricercaRisorsa(r).contains(l1));
	}
}
