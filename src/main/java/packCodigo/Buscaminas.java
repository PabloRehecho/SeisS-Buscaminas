package packCodigo;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
	public ResultSet obtenerPremios() {
		String email=GestorUsuario.getGestorUsuario().getUsuario();
		return GestorPremios.getGestorPremios().getPremios(email);
	}
	public void comprobarPremiosGanados(int[] hitos) {
		Set<String> nMios=new HashSet();
		int i=0;
		String cond[];
		String nom[];
		String gan[];
		String descr[];
		boolean nuevo=false;
		
		String email=GestorUsuario.getGestorUsuario().getUsuario();
		ResultSet todos, actuales;
		todos=GestorPremios.getGestorPremios().getTodosPremios();
		actuales=GestorPremios.getGestorPremios().getNombrePremios(email);
		cond=new String[20];
		descr=new String[20];
		nom=new String[20];
		try {
			while(todos.next()) {
				cond[i]=todos.getString("Requisito");
				nom[i]=todos.getString("Nombre");
				descr[i]=todos.getString("Decripcion");
				i++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			while(actuales.next()) {
				nMios.add(todos.getString("Nombre"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		int in=0;
		while(in<=i) {
			if(!nMios.contains(nom[i])) {
				if(descr[i]=="Ganar en nivel 1") {
					if(hitos[0]>=cond[i]){
						nuevo=true;
						GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
					}
				}
				else if(descr[i]=="Ganar en nivel 2") {
					if(hitos[1]>=cond[i]){
						nuevo=true;
						GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
				}
				else if(descr[i]=="Ganar en nivel 3") {
					if(hitos[2]>=cond[i]){
						nuevo=true;
						GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
				}
				else {
					if(hitos[3]>=cond[i]){
						nuevo=true;
						GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
				}
				
				if(nuevo) {
					GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
				}
			}
			in++;
		}
	}
}
