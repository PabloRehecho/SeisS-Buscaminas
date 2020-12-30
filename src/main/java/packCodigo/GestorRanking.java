package packCodigo;

import java.sql.ResultSet;

public class GestorRanking {

	private static GestorRanking miGestorRanking;
	
	private GestorRanking() {}
	
	public static GestorRanking getGestorRanking() {
		if (miGestorRanking == null) {
			miGestorRanking = new GestorRanking();
		}
		return miGestorRanking;
	}
	
	public ResultSet mostrarRankingGlobal(int pNivel) {
		ResultSet rs = null;
		if (pNivel == 0) {
			rs = GestorBD.getGestorBD().execSQL("SELECT * FROM Ranking ORDER BY Puntuacion DESC");
		}
		else {
			rs = GestorBD.getGestorBD().execSQL("SELECT * FROM Ranking WHERE nivel = " + pNivel + " ORDER BY Puntuacion DESC");
		}
		return rs;
	}
	
	public ResultSet mostrarRankingPersonal(String pEmail, int pNivel) {
		ResultSet rs = null;
		if (pNivel == 0) {
			rs = GestorBD.getGestorBD().execSQL("SELECT * FROM Ranking WHERE emailJugador = '" + pEmail + "' ORDER BY Puntuacion DESC");
		}
		else {
			rs = GestorBD.getGestorBD().execSQL("SELECT * FROM Ranking WHERE emailJugador = '" + pEmail + "' AND nivel = " + pNivel + " ORDER BY Puntuacion DESC");
		}
		return rs;
	}
	
	public void actualizarRanking(String pEmail, String pNombre, int pPuntuacion, int pNivel, String pPartida) {
		if(pPartida.equals("Perdida")) {
			GestorBD.getGestorBD().execSQL2("INSERT INTO ranking (emailJugador, nombre, nivel) VALUES ('" + pEmail + "', '" + pNombre + "', " + pNivel + ")");
		}
		else if(pPartida.equals("Ganada")) {
			GestorBD.getGestorBD().execSQL2("INSERT INTO ranking (emailJugador, nombre, puntuacion, nivel) VALUES ('" + pEmail + "', '" + pNombre + "', " + pPuntuacion + ", " + pNivel + ")");
		}
	}
	
}
