package packJUnits;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import packCodigo.GestorUsuario;

public class GestorUsuarioTest {
	
	@Before
	public void setUp() throws Exception {
		GestorUsuario.getGestorUsuario().setUsuario(null);
	}
	
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

	@Test
	public void testGetHitos() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarHitos() {
		fail("Not yet implemented");
	}
	
	// Identificación:
	@Test
	public void testIniciarSesion() {
		
		GestorUsuario.getGestorUsuario().crearCuenta("usuario2", "contrasena2");
		
		//1.1.1
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "ContraseñaQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.2
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "contraseña2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.3
		GestorUsuario.getGestorUsuario().iniciarSesion("usuario2", "ContraseñaQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.4
		GestorUsuario.getGestorUsuario().iniciarSesion("usuario2", "contrasena2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "usuario2");
		
	}
	@Test
	public void testCrearCuenta() {
		GestorUsuario.getGestorUsuario().crearCuenta("usuario3", "contrasena3", null);
		
		//1.1.1
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "ContraseñaQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.2
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "contraseña2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.3
		GestorUsuario.getGestorUsuario().iniciarSesion("usuario2", "ContraseñaQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.4
		GestorUsuario.getGestorUsuario().iniciarSesion("usuario2", "contrasena2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "usuario2");
	}

	@Test
	public void testResetContraseña() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogearRedSocial() {
		fail("Not yet implemented");
	}

	@Test
	public void testExtraerListaUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testCerrarSesion() {
		fail("Not yet implemented");
	}

}
