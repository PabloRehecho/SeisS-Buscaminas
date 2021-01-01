package packJUnits;

import static org.junit.Assert.*;

import org.junit.Test;

import packCodigo.Buscaminas;
import packCodigo.GestorBD;

public class BuscaminasTest {

	@Test
	public void testGetBuscaminas() {
		assertNotNull(Buscaminas.getBuscaminas());
	}

	@Test
	public void testObtenerPartida() {
		assertNotNull(Buscaminas.getBuscaminas().obtenerPartida());
	}

	@Test
	public void testEstablecerNombreJugador() {
		String nombreJugador = "usuario1";
		Buscaminas.getBuscaminas().establecerNombreJugador(nombreJugador);
		assertTrue(Buscaminas.getBuscaminas().obtenerPartida().obtenerNombreJugador().equals(nombreJugador));
	}

	@Test
	public void testMostrarRanking() {
		//PRUEBA 1: Ranking vacío
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
	}

	@Test
	public void testActualizarRanking() {
		fail("Not yet implemented");
	}

	@Test
	public void testComenzarPartida() {
		Buscaminas.getBuscaminas().comenzarPartida();
		assertNotNull(Buscaminas.getBuscaminas().obtenerPartida());
	}

}
