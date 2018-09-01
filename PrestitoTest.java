package test_2.punto5;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import dominio.parte2.punto5.Categoria;
import dominio.parte2.punto5.Libro;
import dominio.parte2.punto5.Persona;
import logica.parte2.punto5.Fruitore;
import logica.parte2.punto5.Prestito;
import utility.parte2.Data;

public class PrestitoTest {

	private Categoria c1;
	private Fruitore fru1;
	private Libro l;
	private Prestito p1; 
	
	@Before
	public void initialize() {
		c1 = new Categoria("Libri", 30, 30, 3, 3);

		fru1 = new Fruitore("Marco", "Rossi", 1970, 11, 7, "m1793", "3s15t");
		
		ArrayList <Persona> aut = new ArrayList <Persona> ();
	    aut.add(new Persona("E.", "Gamma"));
	    aut.add(new Persona("R.", "Helm"));
	    aut.add(new Persona("R.", "Johnson"));
	    aut.add(new Persona("J.", "Vlissides"));
	    
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
		p1.setDataDiScadenzaPrestito(Data.diminuisciDataAttualeNumeroGiorni(1));

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoCoincidenteConLaDataDiScadenza() {
		p1.setDataDiScadenzaPrestito(Data.getDataAttuale());

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoInEccessivoAnticipo() {
		p1.setDataDiScadenzaPrestito(Data.aumentaDataAttualeNumeroGiorni(4));

		assertFalse(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoCorretta1() {
		p1.setDataDiScadenzaPrestito(Data.aumentaDataAttualeNumeroGiorni(3));

		assertTrue(p1.prorogaPrestito());
	}
	
	@Test
	public void controlloProrogaPrestitoCorretta2() {
		p1.setDataDiScadenzaPrestito(Data.aumentaDataAttualeNumeroGiorni(1));

		assertTrue(p1.prorogaPrestito());
	}
	
}