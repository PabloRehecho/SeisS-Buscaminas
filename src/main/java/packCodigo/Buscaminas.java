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
	
	public ResultSet mostrarRankingGlobal(int pNivel) {
		return GestorRanking.getGestorRanking().mostrarRankingGlobal(pNivel);
	}
	
	public ResultSet mostrarRankingPersonal(int pNivel) {
		String email = GestorUsuario.getGestorUsuario().getUsuario();
		return GestorRanking.getGestorRanking().mostrarRankingPersonal(email, pNivel);
	}

}
