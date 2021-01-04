package packCodigo;

import java.sql.ResultSet;

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
	}
		
	public String[] extraerListaUsuarios() {
		// TODO Auto-generated method stub
		return null;
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
	
	public Partida getPartida() {
		if (partida == null) {
			partida = new Partida();
		}
		return partida;
	}
	
	public void comenzarPartida() {
		partida = new Partida();
	}


	
}
