package packJUnits;

import static org.junit.Assert.*;

import org.junit.Test;

import packCodigo.ConexionMySQL;

public class ConexionMySQLTest {

	@Test
	public void testConectarMySQL() {
		ConexionMySQL sql = ConexionMySQL.getConexionMySQL();
		assertNotNull(sql.conectarMySQL());
	}

}
