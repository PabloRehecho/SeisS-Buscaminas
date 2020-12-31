package packCodigo;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
