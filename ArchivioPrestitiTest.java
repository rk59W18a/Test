package test_1;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import dominio_5.Categoria;
import dominio_5.Film;
import dominio_5.Libro;
import logica_5.ArchivioPrestiti;
import logica_5.Fruitore;
import logica_5.Prestito;

public class ArchivioPrestitiTest {

	private ArchivioPrestiti arc;
	private Categoria c1;
	private Categoria c2;
	private Fruitore fru1;
	private Fruitore fru2;
	private Libro l;
	private Film fi;
	private Prestito p1; 
	private Prestito p2;
	
	@Before
	public void initialize() {
		c1 = new Categoria("Libri", 30, 30, 3, 3);
		c2 = new Categoria("Film", 15, 5, 2, 2);

		fru1 = new Fruitore("Marco", "Rossi", 1970, 11, 7, "m1793", "3s15t");
		fru2 = new Fruitore("Lorenzo", "Tadini", 1985, 1, 16, "fdsfs", "ca34d");
		
		Vector <String> aut = new Vector <String> ();
	    aut.add("E.Gamma");
	    aut.add("R.Helm");
	    aut.add("R.Johnson");
	    aut.add("J.Vlissides");
		Vector <String> att = new Vector <String> ();
		att.add("Matthew McConaughey");
		att.add("Anne Hathaway");
		
	    l = new Libro("Design Patterns", 1, "Didattica", 2002, "italiano", aut, 395, "Pearson");
		fi = new Film("Interstellar", 2, "Fantascienza", 2014, "italiano", "Christopher Nolan", att, 150);	

		p1 = new Prestito(c1,fru1,l);
		arc = new ArchivioPrestiti();
		arc.aggiungiPrestito(p1);
	}
	
	@Test
	public void aggiungeUnPrestitoNonPresente() {
		p2 = new Prestito(c2,fru2,fi);
		arc.aggiungiPrestito(p2);
	
		assertTrue(arc.getElencoPrestiti().contains(p2));
	}
	
	@Test
	public void aggiungeUnPrestitoNonPresenteConFruitoreSpecificato() {
		p2 = new Prestito(c2,fru1,fi);
		arc.aggiungiPrestito(p2);
	
		assertTrue(arc.getPrestiti("m1793").contains(p2));
	}
	
	@Test
	public void rimuoveUnPrestitoPresente() {
		arc.rimuoviPrestito(p1);
	
		assertFalse(arc.getElencoPrestiti().contains(p1));
	}
	
	@Test
	public void rimuoveUnPrestitoNonPresente() {
		arc.rimuoviPrestito(p2);
	
		assertFalse(arc.getElencoPrestiti().contains(p2));
	}

	@Test
	public void verificaPresenzaPrestito() {
		
		assertTrue(arc.verificaPresenza(l, "m1793"));
	}
	
	@Test
	public void verificaAssenzaPrestito() {
		
		assertFalse(arc.verificaPresenza(l, "fdsfs"));
	}
	
	@Test
	public void controlloPerUlteriorePrestito() {
		
		assertTrue(arc.controlloPerUlteriorePrestito(c1, "m1793"));
	}
	
	@Test
	public void controlloPerUlteriorePrestitoErrato() {
		
		arc.aggiungiPrestito(p1);
		arc.aggiungiPrestito(p1);

		assertFalse(arc.controlloPerUlteriorePrestito(c1, "m1793"));
	}
	
	@Test
	public void controlloPerDisponibilitaRisorsa() {
		
		p2 = new Prestito(c2,fru2,fi);
		arc.aggiungiPrestito(p2);

		assertTrue(arc.controlloDisponibilitaRisorsa(fi));
	}
	
	@Test
	public void controlloPerDisponibilitaRisorsaErrato() {
		
		p2 = new Prestito(c2,fru2,fi);
		arc.aggiungiPrestito(p2);
		arc.aggiungiPrestito(p2);

		assertFalse(arc.controlloDisponibilitaRisorsa(fi));
	}
	
	@Test
	public void controlloScadenzaPrestitoDataCoincidente() {
		
		arc.getElencoPrestiti().get(0).setDataDiScadenzaPrestito(LocalDate.now());
		arc.scadenzaPrestito();

		assertFalse(arc.getElencoPrestiti().contains(p1));
	}
	
	@Test
	public void controlloScadenzaPrestitoDataSuccessiva() {
		
		arc.getElencoPrestiti().get(0).setDataDiScadenzaPrestito(LocalDate.now().minusDays(1));
		arc.scadenzaPrestito();

		assertFalse(arc.getElencoPrestiti().contains(p1));
	}
	
	@Test
	public void controlloScadenzaPrestitoDataPrecedente() {
		
		arc.getElencoPrestiti().get(0).setDataDiScadenzaPrestito(LocalDate.now().plusDays(1));
		arc.scadenzaPrestito();

		assertTrue(arc.getElencoPrestiti().contains(p1));
	}
	
}
