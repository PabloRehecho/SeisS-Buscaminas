package packJUnits;

import static org.junit.Assert.*;

import org.junit.Test;

import packCodigo.GestorUsuario;

public class GestorUsuarioTest {

	@Test
	public void testGetGestorUsuario() {
		assertNotNull(GestorUsuario.getGestorUsuario());
	}

	@Test
	public void testGetUsuario() {
		GestorUsuario.getGestorUsuario().setUsuario("usuario1");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "usuario1");
	}

	@Test
	public void testSetUsuario() {
		GestorUsuario.getGestorUsuario().setUsuario("usuario1");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "usuario1");
	}

}
