package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import packCodigo.Buscaminas;
import packCodigo.GestorBD;
import packCodigo.GestorUsuario;

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
		
		//PRUEBA 1: Ranking Global vacío
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		ResultSet rs = Buscaminas.getBuscaminas().mostrarRanking("Global", 0);
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 2: Ranking Personal vacío
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorUsuario.getGestorUsuario().setUsuario("usuario1@gmail.com");
		rs = Buscaminas.getBuscaminas().mostrarRanking("Personal", 0);
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 3: Ranking Global no vacío
		Buscaminas.getBuscaminas().establecerNombreJugador("usuario1");
		Buscaminas.getBuscaminas().obtenerPartida().calcularPuntos();
		Buscaminas.getBuscaminas().obtenerPartida().inicioJuego(1);
		Buscaminas.getBuscaminas().obtenerPartida().gameOver();
		Buscaminas.getBuscaminas().actualizarRanking("Perdida");
		rs = Buscaminas.getBuscaminas().mostrarRanking("Global", 0);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 4: Ranking Personal no vacío
		rs = Buscaminas.getBuscaminas().mostrarRanking("Personal", 0);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActualizarRanking() {
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorUsuario.getGestorUsuario().setUsuario("usuario1@gmail.com");
		Buscaminas.getBuscaminas().establecerNombreJugador("usuario1");
		Buscaminas.getBuscaminas().obtenerPartida().calcularPuntos();
		Buscaminas.getBuscaminas().obtenerPartida().inicioJuego(1);
		Buscaminas.getBuscaminas().obtenerPartida().gameOver();
		Buscaminas.getBuscaminas().actualizarRanking("Perdida");
		ResultSet rs = Buscaminas.getBuscaminas().mostrarRanking("Global", 0);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testComenzarPartida() {
		Buscaminas.getBuscaminas().comenzarPartida();
		assertNotNull(Buscaminas.getBuscaminas().obtenerPartida());
	}

}
