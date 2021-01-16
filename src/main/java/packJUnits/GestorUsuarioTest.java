package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import packCodigo.GestorBD;
import packCodigo.GestorUsuario;

public class GestorUsuarioTest {
	
	@Before
	public void setUp() throws Exception {
		GestorUsuario.getGestorUsuario().setUsuario(null);
		GestorUsuario.getGestorUsuario().borrarUsuario("u1@prueba.com");
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
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "123", "123");
		int[] hit=new int[4];
		hit[0]=7;
		hit[1]=3;
		hit[2]=4;
		hit[3]=5;
		int[]hitos=new int[4];
		GestorUsuario.getGestorUsuario().actualizarHitos(hit,"u1@prueba.com");
		ResultSet rs=GestorUsuario.getGestorUsuario().getHitos("u1@prueba.com");
		try {
			while (rs.next()) {
				hitos[0] = rs.getInt("PartidasGanadas1");
				hitos[1] = rs.getInt("PartidasGanadas2");
				hitos[2] = rs.getInt("PartidasGanadas3");
				hitos[3] = rs.getInt("Racha");
				System.out.println(""+hitos[0]+" "+hitos[1]+" "+hitos[2]+" "+hitos[3]+"");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testActualizarHitos() {
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "123", "123");
		int[] hit=new int[4];
		hit[0]=10;
		hit[1]=4;
		hit[2]=1;
		hit[3]=6;
		int[]hitos=new int[4];
		GestorUsuario.getGestorUsuario().actualizarHitos(hit,"u1@prueba.com");
		ResultSet rs=GestorUsuario.getGestorUsuario().getHitos("u1@prueba.com");
		try {
			while (rs.next()) {
				hitos[0] = rs.getInt("PartidasGanadas1");
				hitos[1] = rs.getInt("PartidasGanadas2");
				hitos[2] = rs.getInt("PartidasGanadas3");
				hitos[3] = rs.getInt("Racha");
				System.out.println(""+hitos[0]+" "+hitos[1]+" "+hitos[2]+" "+hitos[3]+"");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Identificaci�n:
	@Test
	public void testIniciarSesion() {

		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "contrasena2", "contrasena2");
		GestorUsuario.getGestorUsuario().cerrarSesion();
		//1.1.1
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "Contrase�aQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.2
		GestorUsuario.getGestorUsuario().iniciarSesion("usuarioInexistente", "contrase�a2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.3
		GestorUsuario.getGestorUsuario().iniciarSesion("u1@prueba.com", "Contrase�aQueNoExiste");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.1.4
		GestorUsuario.getGestorUsuario().iniciarSesion("u1@prueba.com", "contrasena2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "u1@prueba.com");
		
	}
	
	@Test
	public void testCrearCuenta() {
		
		//1.3.1
		GestorUsuario.getGestorUsuario().crearCuenta("usuarioInv�lido", "", "");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.2
		GestorUsuario.getGestorUsuario().crearCuenta("usuarioInv�lido", "Contrase�a", "");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.3
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "", "");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.4
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "Contrase�a1", "Contrase�a2");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
		//1.3.5
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "Contrase�a", "Contrase�a");
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), "u1@prueba.com");

	}
	
	@Test
	public void testcrearNuevaContrase�a() {
		for (int i=0;i<100;i++) {
			assertNotNull(GestorUsuario.getGestorUsuario().crearNuevaContrase�a());
			assertFalse(GestorUsuario.getGestorUsuario().crearNuevaContrase�a().equals(""));
		}
	}

	@Test
	public void testResetContrase�a() {

		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "Contrase�a", "Contrase�a");
		GestorUsuario.getGestorUsuario().cerrarSesion();
		
		//1.4.1
		assertFalse(GestorUsuario.getGestorUsuario().resetContrase�a("correoInexistente"));
		//1.4.2
		GestorUsuario.getGestorUsuario().resetContrase�a("u1@prueba.com");
		assertFalse(GestorUsuario.getGestorUsuario().iniciarSesion("u1@prueba.com", "Contrase�a"));
		
	}

	@Test
	public void testCambioDeContrase�a() {
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "Contrase�a", "Contrase�a");

		try {
			//1.6.1
			GestorUsuario.getGestorUsuario().cambioDeContrase�a("", "", "");
			ResultSet a = GestorBD.getGestorBD().execSQL("SELECT contrasena FROM usuario WHERE email='u1@prueba.com'");
			a.next();
			assertEquals(a.getString("contrasena"), "Contrase�a");
	
			//1.6.2
			GestorUsuario.getGestorUsuario().cambioDeContrase�a("Contrase�a", "", "");			
			a = GestorBD.getGestorBD().execSQL("SELECT contrasena FROM usuario WHERE email='u1@prueba.com'");
			a.next();
			assertEquals(a.getString("contrasena"), "Contrase�a");
			
			//1.6.3
			GestorUsuario.getGestorUsuario().cambioDeContrase�a("Contrase�a", "c1", "c2");			
			a = GestorBD.getGestorBD().execSQL("SELECT contrasena FROM usuario WHERE email='u1@prueba.com'");
			a.next();
			assertEquals(a.getString("contrasena"), "Contrase�a");
			
			//1.6.4
			GestorUsuario.getGestorUsuario().cambioDeContrase�a("Contrase�a", "c3", "c3");			
			a = GestorBD.getGestorBD().execSQL("SELECT contrasena FROM usuario WHERE email='u1@prueba.com'");
			a.next();
			assertEquals(a.getString("contrasena"), "c3");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCerrarSesion() {
		GestorUsuario.getGestorUsuario().crearCuenta("u1@prueba.com", "Contrase�a", "Contrase�a");
		
		//1.5.1
		GestorUsuario.getGestorUsuario().cerrarSesion();
		assertEquals(GestorUsuario.getGestorUsuario().getUsuario(), null);
	}

}
