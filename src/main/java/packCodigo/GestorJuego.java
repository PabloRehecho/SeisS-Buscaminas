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
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM valores");
		try {
			if (!rs.next())
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs2= GestorBD.getGestorBD().execSQL("SELECT * FROM imagenesaudio");
		try {
			if (!rs2.next())
			{
				GestorBD.getGestorBD().execSQL2("DELETE FROM imagenesaudio");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,1,1,'a')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,1,2,'b')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,1,3,'c')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,2,1,'d')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,2,2,'e')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,2,3,'f')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,3,1,'g')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,3,2,'h')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(1,3,3,'i')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,1,1,'j')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,1,2,'k')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,1,3,'l')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,2,1,'m')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,2,2,'n')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,2,3,'o')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,3,1,'p')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,3,2,'q')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(2,3,3,'r')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(3,1,1,'s')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(3,2,1,'t')");
				GestorBD.getGestorBD().execSQL2("INSERT INTO imagenesaudio VALUES(3,3,1,'u')");
			} 
		}	catch (SQLException e) {
				e.printStackTrace();
			}
		
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
