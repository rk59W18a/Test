package test_2.punto4;

import static org.junit.Assert.*;



import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto4.Categoria;
import dominio.parte2.punto4.Film;
import dominio.parte2.punto4.Libro;
import logica.parte2.punto4.ArchivioStorico;
import logica.parte2.punto4.Fruitore;
import logica.parte2.punto4.Prestito;

public class ArchivioStoricoTest {

	private ArchivioStorico as;
	private Categoria c1;
	private Categoria c2;
	private Fruitore fru1;
	private Fruitore fru2;
	private Fruitore fru3;
	private Libro l;
	private Film fi;
	private Prestito p1; 
	private Prestito p2;
	private Prestito p3; 
	private Prestito p4;
	
	@Before
	public void initialize() {
		c1 = new Categoria("Libri", 30, 30, 3, 3);
		c2 = new Categoria("Film", 15, 5, 2, 2);

		fru1 = new Fruitore("Marco", "Rossi", 1970, 11, 7, "m1793", "3s15t");
		fru2 = new Fruitore("Lorenzo", "Tadini", 1985, 1, 16, "fdsfs", "ca34d");
		fru3 = new Fruitore("Eugenio", "Arici", 1954, 7, 24, "eugen", "vrsof");
		
		ArrayList <String> aut = new ArrayList <String> ();
	    aut.add("E.Gamma");
	    aut.add("R.Helm");
	    aut.add("R.Johnson");
	    aut.add("J.Vlissides");
		ArrayList <String> att = new ArrayList <String> ();
		att.add("Matthew McConaughey");
		att.add("Anne Hathaway");
		
	    l = new Libro("Design Patterns", 1, "Didattica", 2002, "italiano", aut, 395, "Pearson");
		fi = new Film("Interstellar", 2, "Fantascienza", 2014, "italiano", "Christopher Nolan", att, 150);	

		p1 = new Prestito(c1,fru1,l);
		p2 = new Prestito(c2,fru2,fi);
		p3 = new Prestito(c1,fru2,l);
		p4 = new Prestito(c2,fru1,fi);
		as = new ArchivioStorico();
	}
	
	@Test
	public void aggiungeUnaRisorsaRimossa() {
		as.aggiungiRisorsaRimossa(fi);
	
		assertTrue(as.getElencoRisorseRimosse().contains(fi));
	}
	
	@Test
	public void VerificaNumeroPrestitiPerAnnoSenzaAlcunPrestito() {
		assertEquals(as.numeroPrestitiPerAnno(LocalDate.now().getYear()), 0);
	}
	
	@Test
	public void VerificaNumeroPrestitiPerAnnoConAlcuniPrestiti() {
		as.getPrestitiStorici().aggiungiPrestito(p1);
		as.getPrestitiStorici().aggiungiPrestito(p2);
		as.getPrestitiStorici().aggiungiPrestito(p3);
		as.getPrestitiStorici().aggiungiPrestito(p4);

		assertEquals(as.numeroPrestitiPerAnno(LocalDate.now().getYear()), 4);
	}
	
	@Test
	public void VerificaNumeroProroghePerAnnoSenzaAlcunaProroghe() {
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p1);
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p2);

		assertEquals(as.numeroProroghePerAnno(LocalDate.now().getYear()), 0);
	}
	
	@Test
	public void VerificaNumeroProroghePerAnnoConAlcuneProroghe1() {
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p1);
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p2);
		p1.setProrogaNonEffettuata(LocalDate.now());
		p2.setProrogaNonEffettuata(LocalDate.now());
		
		assertEquals(as.numeroProroghePerAnno(LocalDate.now().getYear()), 2);
	}
	
	@Test
	public void VerificaNumeroProroghePerAnnoConAlcuneProroghe2() {
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p1);
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p2);
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p3);
	    as.getPrestitiConProrogheStoriche().aggiungiPrestito(p4);

		p1.setProrogaNonEffettuata(LocalDate.of(2015, 11, 14));
		p2.setProrogaNonEffettuata(LocalDate.of(2015, 5, 23));
		p3.setProrogaNonEffettuata(LocalDate.of(2011, 2, 4));
		
		assertEquals(as.numeroProroghePerAnno(2015), 2);
	}
	
	@Test
	public void VerificaNumeroPrestitiPerFruitorePerAnno1() {
		as.getPrestitiStorici().aggiungiPrestito(p1);
		as.getPrestitiStorici().aggiungiPrestito(p2);
		as.getPrestitiStorici().aggiungiPrestito(p3);
		as.getPrestitiStorici().aggiungiPrestito(p4);
		assertEquals(as.numeroPrestitiPerFruitorePerAnno(fru1, LocalDate.now().getYear()), 2);
	}
	
	@Test
	public void VerificaNumeroPrestitiPerFruitorePerAnno2() {
		as.getPrestitiStorici().aggiungiPrestito(p1);
		as.getPrestitiStorici().aggiungiPrestito(p2);
		as.getPrestitiStorici().aggiungiPrestito(p3);
		as.getPrestitiStorici().aggiungiPrestito(p4);
		assertEquals(as.numeroPrestitiPerFruitorePerAnno(fru1, LocalDate.now().minusYears(1).getYear()), 0);
	}
	
	@Test
	public void VerificaNumeroPrestitiPerFruitorePerAnno3() {
		as.getPrestitiStorici().aggiungiPrestito(p1);
		as.getPrestitiStorici().aggiungiPrestito(p2);
		as.getPrestitiStorici().aggiungiPrestito(p3);
		as.getPrestitiStorici().aggiungiPrestito(p4);
		assertEquals(as.numeroPrestitiPerFruitorePerAnno(fru3, LocalDate.now().getYear()), 0);
	}
	
}
