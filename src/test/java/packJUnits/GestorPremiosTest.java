package packJUnits;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.GestorBD;
import packCodigo.GestorPremios;

public class GestorPremiosTest {

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
		GestorPremios.getGestorPremios().ganarPremio("administrador@gmail.com", "BronceI");
		ResultSet res=GestorPremios.getGestorPremios().getPremios("administrador@gmail.com");
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuariopremio");
		try {
			assertFalse(res.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTodosPremios() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombrePremios() {
		fail("Not yet implemented");
	}

	@Test
	public void testGanarPremio() {
		fail("Not yet implemented");
	}

}
