package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import packCodigo.GestorBD;
import packCodigo.GestorRanking;

public class GestorRankingTest {

	@Test
	public void testGetGestorRanking() {
		assertNotNull(GestorRanking.getGestorRanking());
	}

	@Test
	public void testMostrarRankingGlobal() {
		
		//PRUEBA 1: Ranking Global absoluto vac�o
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		ResultSet rs = GestorRanking.getGestorRanking().mostrarRankingGlobal(0);
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 2: Ranking Global por nivel vac�o
		rs = GestorRanking.getGestorRanking().mostrarRankingGlobal(1);
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 3: Ranking Global absoluto no vac�o
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 0, 1, "Perdida");
		rs = GestorRanking.getGestorRanking().mostrarRankingGlobal(0);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 4: Ranking Global por nivel no vac�o
		rs = GestorRanking.getGestorRanking().mostrarRankingGlobal(1);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMostrarRankingPersonal() {
		
		//PRUEBA 1: Ranking Personal absoluto vac�o
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		ResultSet rs = GestorRanking.getGestorRanking().mostrarRankingPersonal("usuario1@gmail.com", 0);
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 2: Ranking Personal por nivel vac�o
		rs = GestorRanking.getGestorRanking().mostrarRankingPersonal("usuario1@gmail.com", 1);
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 3: Ranking Personal absoluto no vac�o
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 0, 1, "Perdida");
		rs = GestorRanking.getGestorRanking().mostrarRankingPersonal("usuario1@gmail.com", 0);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 4: Ranking Personal por nivel no vac�o
		rs = GestorRanking.getGestorRanking().mostrarRankingPersonal("usuario1@gmail.com", 1);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActualizarRanking() {
		
		//PRUEBA 1: Partida perdida
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 0, 1, "Perdida");
		ResultSet rs = GestorRanking.getGestorRanking().mostrarRankingPersonal("usuario1@gmail.com", 1);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 2: Partida ganada
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 0, 1, "Ganada");
		rs = GestorRanking.getGestorRanking().mostrarRankingPersonal("usuario1@gmail.com", 1);
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
