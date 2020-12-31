package packCodigo;

import java.sql.ResultSet;

public class GestorPremios {
	private static GestorPremios miGestorPremios;
	
	private GestorPremios() {}

	public static GestorPremios getGestorPremios() {
		if(miGestorPremios==null) {
			miGestorPremios=new GestorPremios();
		}
		return miGestorPremios;
	}
	
	public ResultSet getPremios(String pEmail) {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre, Descripcion, Requisito, Imagen FROM UsuarioPremio NATURAL JOIN Premio WHERE emailJugador="+pEmail+"");
		return rs;
	}
	public ResultSet getTodosPremios() {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre, Requisito, Descripcion FROM Premio");
		return rs;
	}
	public ResultSet getNombrePremios(String pEmail) {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre FROM UsuarioPremio NATURAL JOIN Premio WHERE emailJugador="+pEmail+"");
		return rs;
	}
	public void ganarPremio(String pEmail, String pNombre) {
		GestorBD.getGestorBD().execSQL("INSERT INTO UsuarioPremio (emailJugador, nombrePremio)"+"values("+pEmail+""+pNombre+")");
	}
}
