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
	
	public boolean iniciarSesion(String pText, String pPassword) {
		return GestorUsuario.getGestorUsuario().iniciarSesion(pText,pPassword);
	}
	

	public boolean logearRedSocial() {
		return GestorUsuario.getGestorUsuario().logearRedSocial();
	}

	public void cerrarSesion() {
		GestorUsuario.getGestorUsuario().cerrarSesion();
	}

	public boolean crearCuenta(String text, String password, String copiaPassword) {
		boolean a= GestorUsuario.getGestorUsuario().crearCuenta(text, password,copiaPassword);
		Buscaminas.getBuscaminas().crearValores();
		return a;
	}

	public void setUsuarioLogeado(String text) {
		GestorUsuario.getGestorUsuario().setUsuario(text);
	}
	
	public String getUsuarioLogeado() {
		return GestorUsuario.getGestorUsuario().getUsuario();
	}

	public boolean resetContraseña(String text) {
		return GestorUsuario.getGestorUsuario().resetContraseña(text);
	}
	
	public boolean cambioDeContraseña(String pAntigua, String pN1, String pN2) {
		return GestorUsuario.getGestorUsuario().cambioDeContraseña(pAntigua, pN1, pN2);
	}

	public void crearValores() 
	{
		//no se si es apropiado
		GestorJuego.getGestorJuego().crearValores();	
		GestorPremios.getGestorPremios().crearPremios();
	}	
	

	public ResultSet extraerListaUsuarios() {
		return GestorUsuario.getGestorUsuario().extraerListaUsuarios();
	}
	
	public int extraerNivelUsuario(String pCorreo) {
		return GestorUsuario.getGestorUsuario().extraerNivelUsuario(pCorreo);
	}
	
	public void actualizarNivelInicial(String pCorreo, int valorNivel) {
		GestorUsuario.getGestorUsuario().actualizarNivelInicial(pCorreo, valorNivel);
		
	}
	public ResultSet extraerValores() 
	{
		return GestorJuego.getGestorJuego().extraerValores();
	}
	public ResultSet extraerPersonalizacion() 
	{
		return GestorJuego.getGestorJuego().extraerPersonalizacion();
	}
	public void modificarPersonalizacion( int[] pValores) {
		GestorJuego.getGestorJuego().modificarPersonalizacion(pValores);
	}
	
	public void modificarValores (int[] pValores, String[] pNombres)
	{
		GestorJuego.getGestorJuego().modificarValores(pValores, pNombres);
	}	

	public void borrarUsuario(String pCorreo) 
	{
		GestorUsuario.getGestorUsuario().borrarUsuario(pCorreo);		
	}	
	
	public int seleccionarNivelUsuario() {
		String a= getUsuarioLogeado();
		return GestorUsuario.getGestorUsuario().seleccionarNivelUsuario(a);
	}

	public int conseguirMensajeAyuda() 
	{
		return GestorJuego.getGestorJuego().conseguirMensajeAyuda();
	}
	
	public int[] conseguirFilasColumnas(int pNivel) 
	{
		return GestorJuego.getGestorJuego().conseguirFilasColumnas(pNivel);
	}

	public int calcularMinas(int pNivel) 
	{
		return GestorJuego.getGestorJuego().calcularMinas(pNivel);
	}
	
	public Partida obtenerPartida() {
		return GestorJuego.getGestorJuego().getPartida();
	}
	
	public void comenzarPartida() {
		GestorJuego.getGestorJuego().comenzarPartida();
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
	
	

	public ResultSet obtenerPremios() {
		String email=GestorUsuario.getGestorUsuario().getUsuario();
		return GestorPremios.getGestorPremios().getPremios(email);
	}
	
	public boolean comprobarPremiosGanados(int[] hitos) {
		return GestorPremios.getGestorPremios().comprobarPremio(hitos);
	}

}
