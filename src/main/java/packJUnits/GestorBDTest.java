package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import packCodigo.GestorBD;

public class GestorBDTest {

	@Test
	public void testGetGestorBD() {
		assertNotNull(GestorBD.getGestorBD());
	}

	@Test
	public void testExecSQL() {
		//PRUEBA 1: Base de Datos vacía
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		ResultSet rs = GestorBD.getGestorBD().execSQL("SELECT * FROM ranking ORDER BY Puntuacion DESC");
		try {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//PRUEBA 2: Base de Datos no vacía
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO ranking (emailJugador, nombre, nivel) VALUES ('usuario1@gmail.com', 'usuario1', 1)");
		rs = GestorBD.getGestorBD().execSQL("SELECT * FROM ranking ORDER BY Puntuacion DESC");
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testExecSQL2() {
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario");
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO ranking (emailJugador, nombre, nivel) VALUES ('usuario1@gmail.com', 'usuario1', 1)");
		ResultSet rs = GestorBD.getGestorBD().execSQL("SELECT * FROM ranking ORDER BY Puntuacion DESC");
		try {
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
