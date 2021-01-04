package packJUnits;

import static org.junit.Assert.*;

import org.junit.Test;

import packCodigo.GestorJuego;

public class GestorJuegoTest {

	@Test
	public void testGetGestorJuego() {
		assertNotNull(GestorJuego.getGestorJuego());
	}

	@Test
	public void testGetPartida() {
		assertNotNull(GestorJuego.getGestorJuego().getPartida());
	}

	@Test
	public void testComenzarPartida() {
		assertNotNull(GestorJuego.getGestorJuego().getPartida());
	}

}
