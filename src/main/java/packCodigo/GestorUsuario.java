package packCodigo;


import java.sql.ResultSet;

import java.sql.SQLException;


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
		String pEmail=getUsuario();
		GestorBD.getGestorBD().execSQL("UPDATE Usuario SET PartidasGanadas1="+hitos[1]+", PartidasGanadas2="+hitos[2]+", PartidasGanadas3="+hitos[3]+"Racha="+hitos[4]+" WHERE email="+pEmail+"");
	}


	public boolean iniciarSesion(String pText, char[] pPassword) {
		String contraseña = "";
		for (int i=0;i<pPassword.length;i++){
			contraseña += pPassword[i];
		}
		try {
			if (GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE Email='" + pText + "' AND Contrasena='" + contraseña + "'").next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
