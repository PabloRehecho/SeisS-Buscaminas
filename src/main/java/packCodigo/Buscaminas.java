package packCodigo;

import java.sql.ResultSet;

public class Buscaminas {
	
	private static Buscaminas miBuscaminas;
	
	private Buscaminas() {}
	
	public static Buscaminas getBuscaminas() {
		if(miBuscaminas == null) {
			miBuscaminas = new Buscaminas();
		}
		return miBuscaminas;
	}
	
	public ResultSet mostrarRanking(String pTipo, int pNivel) {
		ResultSet rs = null;
		if (pTipo.equals("Global")) {
			rs = GestorRanking.getGestorRanking().mostrarRankingGlobal(pNivel);
		}
		else if (pTipo.equals("Personal")) {
			String email = GestorUsuario.getGestorUsuario().getUsuario();
			rs = GestorRanking.getGestorRanking().mostrarRankingPersonal(email, pNivel);
		}
		return rs;
	}
	
	public void actualizarRanking(String pPartida) {
		String email = GestorUsuario.getGestorUsuario().getUsuario();
		String nombreJugador = GestorJuego.getGestorJuego().getPartida().obtenerNombreJugador();
		int puntuacion = GestorJuego.getGestorJuego().getPartida().obtenerPuntuacion();
		int nivel = GestorJuego.getGestorJuego().getPartida().obtenerNivel();
		GestorRanking.getGestorRanking().actualizarRanking(email, nombreJugador, puntuacion, nivel, pPartida);
	}
	
	public Partida obtenerPartida() {
		return GestorJuego.getGestorJuego().getPartida();
	}
	
	public void establecerNombreJugador(String pNombre) {
		GestorJuego.getGestorJuego().getPartida().establecerNombreJugador(pNombre);
	}
	
	public void comenzarPartida() {
		GestorJuego.getGestorJuego().comenzarPartida();
	}

}
