package packCodigo;

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
	
	public boolean iniciarSesion(String pText, char[] pPassword) {
		return GestorUsuario.getGestorUsuario().iniciarSesion(pText,pPassword);
	}

	public void crearValores() 
	{
		//no se si es apropiado
		GestorJuego.getGestorJuego().crearValores();		
	}	
	

	public String[] extraerListaUsuarios() {
		return GestorJuego.getGestorJuego().extraerListaUsuarios();
	}
	
	public ResultSet extraerValores() 
	{
		return GestorJuego.getGestorJuego().extraerValores();
	}
	public void modificarValores (int[] pValores, String[] pNombres)
	{
		GestorJuego.getGestorJuego().modificarValores(pValores, pNombres);
	}
	
	public Partida obtenerPartida() {
		return GestorJuego.getGestorJuego().getPartida();
	}
	
	public void establecerNombreJugador(String pNombre) {
		GestorJuego.getGestorJuego().getPartida().establecerNombreJugador(pNombre);
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
	
	public void comenzarPartida() {
		GestorJuego.getGestorJuego().comenzarPartida();
	}

	public ResultSet obtenerPremios() {
		String email=GestorUsuario.getGestorUsuario().getUsuario();
		return GestorPremios.getGestorPremios().getPremios(email);
	}
	
	public boolean comprobarPremiosGanados(int[] hitos) {
		Set<String> nMios=new HashSet<String>();
		int i=0;
		int cond[];
		String nom[];
		String descr[];
		boolean nuevo=false;
		
		String email=GestorUsuario.getGestorUsuario().getUsuario();
		ResultSet todos, actuales;
		todos=GestorPremios.getGestorPremios().getTodosPremios();
		actuales=GestorPremios.getGestorPremios().getNombrePremios(email);
		cond=new int[20];
		descr=new String[20];
		nom=new String[20];
		try {
			while(todos.next()) {
				cond[i]=todos.getInt("Requisito");
				nom[i]=todos.getString("Nombre");
				descr[i]=todos.getString("Decripcion");
				i++;
			}
			todos.close();
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
				if(descr[i]=="Ganar en el nivel 1") {
					if(hitos[0]>=cond[i]){
						nuevo=true;
						GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
					}
				}
				else if(descr[i]=="Ganar en el nivel 2") {
					if(hitos[1]>=cond[i]){
						nuevo=true;
					}
				}
				else if(descr[i]=="Ganar en el nivel 3") {
					if(hitos[2]>=cond[i]){
						nuevo=true;
					}
				}
				else {
					if(hitos[3]>=cond[i]){
						nuevo=true;
					}
				}
				if(nuevo) {
					GestorPremios.getGestorPremios().ganarPremio(email, nom[i]);
				}
			}
			in++;
		}
		return nuevo;
	}
	public boolean crearCuenta(String text, char[] password) {
		return GestorUsuario.getGestorUsuario().crearCuenta(text, password);
	}

	public void setUsuario(String text) {
		GestorUsuario.getGestorUsuario().setUsuario(text);
	}

}
