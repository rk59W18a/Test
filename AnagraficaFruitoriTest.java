package test_1;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import logica_5.AnagraficaFruitori;
import logica_5.Fruitore;

public class AnagraficaFruitoriTest {

    private AnagraficaFruitori af;
    private Fruitore f1;
    private Fruitore f2;
	
	@Before
    public void initialize() {
		af = new AnagraficaFruitori();
		f1 = new Fruitore("Luca", "Rossi", 1995, 8, 25, "lc9", "000");
		f2 = new Fruitore("Laura", "Bianchi", 1985, 5, 10, "lau", "bi5");
		
		af.aggiungiFruitore(f1);
		af.aggiungiFruitore(f2);
	}
	
	@Test
	public void aggiungeUnNuovoFruitore() {
		Fruitore f = new Fruitore("Matteo", "Ferrari", 1985, 4, 16, "mat8","rt56");
		af.aggiungiFruitore(f);
	
		assertTrue(af.getElenco().contains(f));	
	}
	
	@Test
	public void fruitoreGiàPresente() {	
		assertTrue(af.verificaPresenza("Luca", "Rossi", LocalDate.of(1995, 8, 25)));	
	}
	
	@Test
	public void fruitoreOmonimoMaEtàDiversaNonPresente() {	
		assertFalse(af.verificaPresenza("Matteo", "Ferrari", LocalDate.of(1985, 4, 20)));	
	}
	
	@Test
	public void usernameGiàPresente() {
		assertTrue(af.verificaStessoUsername(f1.getUsername()));
	}
	
	@Test
	public void usernameNonPresente() {
		assertFalse(af.verificaStessoUsername("001"));
	}
	
	@Test
	public void accediConSuccesso() {
		assertTrue(af.accedi(f2.getUsername(), f2.getPassword()));
	}
	
	@Test
	public void accediSenzaSuccesso() {
		assertFalse(af.accedi(f2.getUsername(), "op3"));
	}

}
