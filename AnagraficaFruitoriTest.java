package test_2.punto2;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import logica.parte2.punto2.AnagraficaFruitori;
import logica.parte2.punto2.ArchivioStorico;
import logica.parte2.punto2.Fruitore;

public class AnagraficaFruitoriTest {

    private AnagraficaFruitori af;
    private ArchivioStorico as;
	private Fruitore f1;
	private Fruitore f2;
	
	@Before
    public void initialize() {
		af = new AnagraficaFruitori();
		as = new ArchivioStorico();
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
	public void fruitoreGiaPresente() {	
		assertTrue(af.verificaPresenza("Luca", "Rossi", LocalDate.of(1995, 8, 25)));	
	}
	
	@Test
	public void fruitoreOmonimoMaEtaDiversaNonPresente() {	
		assertFalse(af.verificaPresenza("Matteo", "Ferrari", LocalDate.of(1985, 4, 20)));	
	}
	
	@Test
	public void usernameGiaPresente() {
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
	
	@Test
	public void controlloDecadenzaFruitoreSuccessivoAllaDataDiScadenza() {
		
		fru1.setDataDiScadenza(LocalDate.now().minusDays(1));
		af.decadenzaFruitore(as);
		
		assertFalse(af.getElenco().contains(fru1));
	}
	
	@Test
	public void controlloDecadenzaFruitoreSuccessivoAllaDataDiScadenzaPerArchivioStorico() {
		
		fru1.setDataDiScadenza(LocalDate.now().minusDays(1));
		af.decadenzaFruitore(as);
		
		assertTrue(as.getDecadenzeFruitoriStoriche().getElenco().contains(fru1));
	}
	
	@Test
	public void controlloDecadenzaFruitoreCoincidenteConLaDataDiScadenza() {
		
		fru2.setDataDiScadenza(LocalDate.now());
		af.decadenzaFruitore(as);
		
		assertFalse(af.getElenco().contains(fru2));
	}
	
	@Test
	public void controlloDecadenzaFruitoreCoincidenteConLaDataDiScadenzaPerArchivioStorico() {
		
		fru2.setDataDiScadenza(LocalDate.now().minusDays(1));
		af.decadenzaFruitore(as);
		
		assertTrue(as.getDecadenzeFruitoriStoriche().getElenco().contains(fru2));
	}
	
	@Test
	public void controlloDecadenzaFruitorePrecedenteAllaDataDiScadenzaPerArchivioStorico() {
		
		fru1.setDataDiScadenza(LocalDate.now().plusDays(1));
		af.decadenzaFruitore(as);
		
		assertFalse(as.getDecadenzeFruitoriStoriche().getElenco().contains(fru1));
	}
	
	@Test
	public void controlloDecadenzaFruitorePrecedenteAllaDataDiScadenza() {
		
		fru1.setDataDiScadenza(LocalDate.now().plusDays(1));
		af.decadenzaFruitore(as);
		
		assertTrue(af.getElenco().contains(fru1));
	}
}
