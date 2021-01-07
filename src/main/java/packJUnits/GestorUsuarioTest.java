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
		
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "contrasena2", "contrasena2");
		
		//1.1.1
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "ContraseñaQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.2
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "contraseña2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.3
		GestorUsuario.getGestorUsuario().iniciarSesion("u1@prueba.com", "ContraseñaQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.4
		GestorUsuario.getGestorUsuario().iniciarSesion("u1@prueba.com", "contrasena2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "u1@prueba.com");
		
		GestorUsuario.getGestorUsuario().borrarUsuario("u1@prueba.com");
		
	}
	@Test
	public void testCrearCuenta() {
		
		//1.3.1
		GestorUsuario.getGestorUsuario().crearCuenta("usuarioInválido", "", "");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.2
		GestorUsuario.getGestorUsuario().crearCuenta("usuarioIInválido", "Contraseña", "");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.3
		GestorUsuario.getGestorUsuario().crearCuenta("u2@prueba.com", "", "");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.4
		GestorUsuario.getGestorUsuario().crearCuenta("u2@prueba.com", "Contraseña1", "Contraseña2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.5
		GestorUsuario.getGestorUsuario().crearCuenta("u2@prueba.com", "Contraseña", "Contraseña");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "u2@prueba.com");
		
		GestorUsuario.getGestorUsuario().borrarUsuario("u2@prueba.com");
	}

	@Test
	public void testResetContraseña() {
		
		GestorUsuario.getGestorUsuario().crearCuenta("u3@prueba.com", "Contraseña", "Contraseña");
		GestorUsuario.getGestorUsuario().cerrarSesion();
		
		//1.4.1
		assertFalse(GestorUsuario.getGestorUsuario().resetContraseña("correoInexistente"));
		//1.4.2
		GestorUsuario.getGestorUsuario().resetContraseña("u3@prueba.com");
		assertFalse(GestorUsuario.getGestorUsuario().iniciarSesion("u3@prueba.com", "Contraseña"));
		
		GestorUsuario.getGestorUsuario().borrarUsuario("u3@prueba.com");
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
