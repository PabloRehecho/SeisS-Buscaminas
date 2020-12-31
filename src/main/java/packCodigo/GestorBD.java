package packCodigo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {

		private static GestorBD miGestorBD;
		
		private GestorBD() {}
		
		public static GestorBD getGestorBD() {
			if (miGestorBD == null) {
				miGestorBD = new GestorBD();
			}
			return miGestorBD;
		}
		
		public ResultSet execSQL(String sentencia) {
			ConexionMySQL sql = new ConexionMySQL();
			Connection conn = sql.conectarMySQL();
			Statement s = null;
			ResultSet rs = null;
			try {
				s = conn.createStatement();
				rs = s.executeQuery(sentencia);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		
		public void execSQL2(String sentencia) {
			ConexionMySQL sql = new ConexionMySQL();
			Connection conn = sql.conectarMySQL();
			Statement s = null;
			try {
				s = conn.createStatement();
				s.executeUpdate(sentencia);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}
