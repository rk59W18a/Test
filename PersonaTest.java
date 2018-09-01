package test_2.punto5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominio.parte2.punto5.Persona;

public class PersonaTest {

	private Persona p1;
	private Persona p2;
	private Persona p3;

	@Before
	public void initialize() {
		
		p1 = new Persona("Leonardo", "Picchi");
		p2 = new Persona("Anna Maria", "Salogni");
		p3 = new Persona("Michele", "Del Colle");
	}
	
	@Test
	public void controlloPresenzaStringa1() {
		assertTrue(p1.verificaPresenzaStringa("Leo"));
	}
	
	@Test
	public void controlloPresenzaStringaConSpaziatura() {
		assertTrue(p1.verificaPresenzaStringa("Leonardo Picchi"));
	}
	
	@Test
	public void controlloPresenzaStringaConTrattino() {
		assertFalse(p1.verificaPresenzaStringa("Leonardo-Picchi"));
	}
	
	@Test
	public void controlloPresenzaStringaSenzaSpaziatura() {
		assertFalse(p1.verificaPresenzaStringa("LeonardoPicchi"));
	}
	
	@Test
	public void controlloPresenzaStringaConSpaziaturaPrecedente() {
		assertFalse(p1.verificaPresenzaStringa(" Leonardo"));
	}
	
	@Test
	public void controlloPresenzaStringaConNomeAttaccato() {
		assertFalse(p2.verificaPresenzaStringa("AnnaMaria Salogni"));
	}
	
	@Test
	public void controlloPresenzaStringa2() {
		assertTrue(p2.verificaPresenzaStringa("Anna Maria Salogni"));
	}
	
	@Test
	public void controlloPresenzaStringaConMaiuscole() {
		assertTrue(p3.verificaPresenzaStringa("MICHELE DEL COLLE"));
	}
	
	@Test
	public void controlloPresenzaStringaConMinuscole() {
		assertTrue(p3.verificaPresenzaStringa("michele del colle"));
	}

	@Test
	public void controlloPresenzaStringaConCognomeAttaccato() {
		assertFalse(p3.verificaPresenzaStringa("delcolle"));
	}

	@Test
	public void controlloPresenzaStringaConDoppiaSpaziatura() {
		assertFalse(p3.verificaPresenzaStringa("Michele  Del Colle"));
	}
}
