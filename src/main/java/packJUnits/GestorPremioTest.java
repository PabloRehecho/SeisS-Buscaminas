package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.GestorBD;
import packCodigo.GestorPremios;

public class GestorPremioTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGestorPremios() {
		assertNotNull(GestorPremios.getGestorPremios());
	}

	@Test
	public void testGetPremios() {
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuarioPremio");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Bronce I");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Racha I");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Plata I");
		ResultSet todos=GestorPremios.getGestorPremios().getPremios("bb@b.b");
		try {
			while(todos.next()) {
				System.out.println(todos.getString("Nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTodosPremios() {
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuarioPremio");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Bronce I");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Racha I");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Plata I");
		ResultSet todos=GestorPremios.getGestorPremios().getNombrePremios("bb@b.b");
		try {
			while(todos.next()) {
				System.out.println(todos.getString("Nombre"));
				System.out.println(todos.getString("Descripcion"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNombrePremios() {
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuarioPremio");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Bronce I");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Racha I");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Plata I");
		ResultSet todos=GestorPremios.getGestorPremios().getNombrePremios("bb@b.b");
		try {
			while(todos.next()) {
				System.out.println(todos.getString("Nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGanarPremio() {
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuarioPremio");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "Racha I");
		ResultSet todos=GestorPremios.getGestorPremios().getNombrePremios("bb@b.b");
		try {
			while(todos.next()) {
				System.out.println(todos.getString("Nombre"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCrearPremios() {
	}

	@Test
	public void testComprobarPremio() {
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuarioPremio");
		int[] hit=new int[4];
		hit[0]=5;
		hit[0]=2;
		hit[0]=7;
		hit[0]=4;
		GestorPremios.getGestorPremios().comprobarPremio(hit, "bb@b.b");
		ResultSet todos=GestorPremios.getGestorPremios().getPremios("bb@b.b");
		try {
			while(todos.next()) {
				System.out.println(todos.getString("Nombre"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

