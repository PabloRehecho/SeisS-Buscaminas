package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.GestorBD;
import packCodigo.GestorPremios;
import packCodigo.GestorUsuario;

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
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuariopremio");
		GestorUsuario.getGestorUsuario().crearCuenta("bb@b.b", "123", "123");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "BronceI");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "RachaI");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "PlataI");
		String[][] todos=GestorPremios.getGestorPremios().getPremios("bb@b.b");
		String[] nom=todos[0];
		int i=0;
		while(i<nom.length) {
			//System.out.println(nom[i]);
			i++;
		}
	}

	@Test
	public void testGetTodosPremios() {
		System.out.println();
	GestorPremios.getGestorPremios().crearPremios();
	GestorBD.getGestorBD().execSQL2("DELETE FROM usuariopremio");
	ResultSet todos=GestorPremios.getGestorPremios().getTodosPremios();
	try {
		while(todos.next()) {
			//System.out.println(todos.getString("Nombre"));
			//System.out.println(todos.getString("Descripcion"));
			}
		todos.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNombrePremios() {
		System.out.println("getNombrePremio");
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuariopremio");
		GestorUsuario.getGestorUsuario().crearCuenta("bb@b.b", "123", "123");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "OroI");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "PlataI");
		ResultSet todos=GestorPremios.getGestorPremios().getNombrePremios("bb@b.b");
		try {
			while(todos.next()) {
					//System.out.println(todos.getString("Nombre"));
			}
			todos.close();
		}catch (SQLException e) {
			//TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

	@Test
	public void testGanarPremio() {
		System.out.println("GanarPremio");
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuariopremio");
		GestorUsuario.getGestorUsuario().crearCuenta("bb@b.b", "123", "123");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "RachaI");
		GestorPremios.getGestorPremios().ganarPremio("bb@b.b", "BronceI");
		ResultSet todos=GestorPremios.getGestorPremios().getNombrePremios("bb@b.b");
		try {
			while(todos.next()) {
					//System.out.println(todos.getString("Nombre"));
			}
			todos.close();
		}catch (SQLException e) {
			//TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

	@Test
	public void testCrearPremios() {
	}

	@Test
	public void testComprobarPremio() {
		System.out.println("ComprobarPremio");
		GestorPremios.getGestorPremios().crearPremios();
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuariopremio");
		GestorUsuario.getGestorUsuario().crearCuenta("bb@b.b", "123", "123");
		int[] hit=new int[4];
		hit[0]=5;
		hit[1]=2;
		hit[2]=7;
		hit[3]=4;
		GestorPremios.getGestorPremios().comprobarPremio(hit, "bb@b.b");
		String[][] todos=GestorPremios.getGestorPremios().getPremios("bb@b.b");
		String[] nom=todos[0];
		int i=0;
		while(i<nom.length) {
			System.out.println(nom[i]);
			i++;
		}
	}
}

