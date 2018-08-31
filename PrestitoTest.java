package test_5;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import dominio_5.Categoria;
import dominio_5.Libro;
import logica_5.Fruitore;
import logica_5.Prestito;

public class PrestitoTest {

	private Categoria c1;
	private Fruitore fru1;
	private Libro l;
	private Prestito p1; 
	
	@Before
	public void initialize() {
		c1 = new Categoria("Libri", 30, 30, 3, 3);

		fru1 = new Fruitore("Marco", "Rossi", 1970, 11, 7, "m1793", "3s15t");
		
		Vector <String> aut = new Vector <String> ();
	    aut.add("E.Gamma");
	    aut.add("R.Helm");
	    aut.add("R.Johnson");
	    aut.add("J.Vlissides");

		
	    l = new Libro("Design Patterns", 1, "Didattica", 2002, "italiano", aut, 395, "Pearson");

		p1 = new Prestito(c1,fru1,l);
	}
	
	@Test
	public void controlloProrogaPrestitoSeGiaEffettuata() {
		p1.setProrogaNonEffettuata(LocalDate.now());

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoSuccessivaAllaDataDiScadenza() {
		p1.setDataDiScadenzaPrestito(LocalDate.now().minusDays(1));

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoCoincidenteConLaDataDiScadenza() {
		p1.setDataDiScadenzaPrestito(LocalDate.now());

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoInEccessivoAnticipo() {
		p1.setDataDiScadenzaPrestito(LocalDate.now().plusDays(4));

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoCorretta1() {
		p1.setDataDiScadenzaPrestito(LocalDate.now().plusDays(3));

		assertTrue(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoCorretta2() {
		p1.setDataDiScadenzaPrestito(LocalDate.now().plusDays(1));

		assertTrue(p1.prorogaPrestito());
	}
	
}
