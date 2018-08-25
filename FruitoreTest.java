package test_2.punto5;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import logica.parte2.punto5.Fruitore;

public class FruitoreTest {

	private Fruitore fru1;

	@Before
	public void initialize() {
		fru1 = new Fruitore("Marco", "Rossi", 1970, 11, 7, "m1793", "3s15t");
		
	}

	@Test
	public void controlloRinnovoIscrizioneSuccessivoAllaDataDiScadenza() {
		fru1.setDataDiScadenza(LocalDate.now().minusDays(1));

		assertFalse(fru1.rinnovaIscrizione());
	}
	
	@Test
	public void controlloRinnovoIscrizioneCoincidenteConLaDataDiScadenza() {
		fru1.setDataDiScadenza(LocalDate.now());

		assertFalse(fru1.rinnovaIscrizione());
	}
	
	@Test
	public void controlloRinnovoIscrizioneInEccessivoAnticipo() {
		fru1.setDataDiScadenza(LocalDate.now().plusDays(11));

		assertFalse(fru1.rinnovaIscrizione());
	}
	
	@Test
	public void controlloRinnovoIscrizioneCorretta1() {
		fru1.setDataDiScadenza(LocalDate.now().plusDays(10));

		assertTrue(fru1.rinnovaIscrizione());
	}
	
	@Test
	public void controlloRinnovoIscrizioneCorretta2() {
		fru1.setDataDiScadenza(LocalDate.now().plusDays(1));

		assertTrue(fru1.rinnovaIscrizione());
	}
}
