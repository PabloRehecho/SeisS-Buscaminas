package packCodigo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorJuego {

	private static GestorJuego miGestorJuego;
	private Partida partida;
	
	private GestorJuego() {}
	
	public static GestorJuego getGestorJuego() {
		if (miGestorJuego == null) {
			miGestorJuego = new GestorJuego();
		}
		return miGestorJuego;
	}
	
	public ResultSet extraerValores() 
	{
		ResultSet rs = null;
		rs= GestorBD.getGestorBD().execSQL("SELECT * FROM valores");
		return rs;
	}

	public ResultSet extraerPersonalizacion()
	{
		ResultSet rs = null;
		String email = GestorUsuario.getGestorUsuario().getUsuario();
		rs= GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE email = '" + email + "'");
		return rs;
	}
	public void crearValores() 
	{		
		GestorBD.getGestorBD().execSQL2("DELETE FROM valores");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('nivel', 1) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('filas1', 7) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('filas2', 10) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('filas3', 12) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('columnas1', 10) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('columnas2', 15) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('columnas3', 25) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('minas1', 11) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('minas2', 32) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('minas3', 78) ");
		GestorBD.getGestorBD().execSQL2("INSERT INTO valores VALUES('mensaje', 1) ");
		GestorBD.getGestorBD().execSQL2("DELETE FROM imagenesaudio");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,1,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,1,2,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,1,3,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,2,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,2,2,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,2,3,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,3,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,3,2,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,3,3,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,1,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,1,2,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,1,3,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,2,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,2,2,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,2,3,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,3,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,3,2,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,3,3,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(3,1,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(3,2,1,'a')");
		GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(3,3,1,'a')");
	}
		
	public void modificarValores(int[] pValores, String[] pNombres) 
	{
		int i=0;
		while (i< pValores.length)
		{
			GestorBD.getGestorBD().execSQL2(" UPDATE valores SET valor= " + pValores[i] + " WHERE nombre='" + pNombres[i] + "';");
			i++;
		}
	}
	
	public int conseguirMensajeAyuda() 
	{
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM valores WHERE nombre='mensaje';");
		int opcion=0;
		try
		{
			rs.next();
			opcion=rs.getInt("valor");
			rs.close();
		} 
		catch (SQLException e) {e.printStackTrace();}		
		return opcion;
	}	
	
	public int[] conseguirFilasColumnas(int pNivel) 
	{
		int[] a= new int[2];
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM valores WHERE nombre='filas" + pNivel + "';");
		try
		{
			rs.next();
			a[0]=rs.getInt("valor");
			rs.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		rs= GestorBD.getGestorBD().execSQL("SELECT * FROM valores WHERE nombre='columnas" + pNivel + "';");
		try
		{
			rs.next();
			a[1]=rs.getInt("valor");
			rs.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		
		return a;
	}

	public int calcularMinas(int pNivel) {
		int a=0;
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM valores WHERE nombre='minas" + pNivel + "';");
		try
		{
			rs.next();
			a=rs.getInt("valor");
			rs.close();
		} 
		catch (SQLException e) {e.printStackTrace();}
		return a;
	}
	
	public Partida getPartida() {
		if (partida == null) {
			partida = new Partida();
		}
		return partida;
	}
	
	public void comenzarPartida() {
		partida = new Partida();
	}

	public void modificarPersonalizacion(int[] pValores) 
	{
		String email = GestorUsuario.getGestorUsuario().getUsuario();
		int i=0;
		while (i< pValores.length)
		{
			GestorBD.getGestorBD().execSQL2("UPDATE usuario SET imagenMinas='" + pValores[0] + "' WHERE Email='" + email + "'");
			GestorBD.getGestorBD().execSQL2("UPDATE usuario SET imagenCara='" + pValores[1] + "' WHERE Email='" + email + "'");
			GestorBD.getGestorBD().execSQL2("UPDATE usuario SET sonido='" + pValores[2] + "' WHERE Email='" + email + "'");
			i++;
		}
	}

	

	

	
}
