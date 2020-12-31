package packCodigo;

import java.sql.ResultSet;

public class GestorUsuario {

	private static GestorUsuario miGestorUsuario;
	private String email;
	
	private GestorUsuario() {}
	
	public static GestorUsuario getGestorUsuario() {
		if (miGestorUsuario == null) {
			miGestorUsuario = new GestorUsuario();
		}
		return miGestorUsuario;
	}
	
	public String getUsuario() {
		return email;
	}
	
	public void setUsuario(String pEmail) {
		email = pEmail;
	}
	public ResultSet getHitos() {
		ResultSet res=null;
		String pEmail=getUsuario();
		res=GestorBD.getGestorBD().execSQL("SELECT PartidasGanadas1, PartidasGanadas2, PartidasGanadas3, Racha FROM Usuario, WHERE email="+pEmail+"");
		return res;
	}
	public void actualizarHitos(int[] hitos) {
		ResultSet res=null;
		String pEmail=getUsuario();
		res=GestorBD.getGestorBD().execSQL("UPDATE Usuario SET PartidasGanadas1="+hitos[1]+", PartidasGanadas2="+hitos[2]+", PartidasGanadas3="+hitos[3]+"Racha="+hitos[4]+" WHERE email="+pEmail+"");
	}
}
