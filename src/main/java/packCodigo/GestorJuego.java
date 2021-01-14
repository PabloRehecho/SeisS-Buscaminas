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
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceI', 'Ganar en el nivel 1', 'premio bronce 5.png', 5)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceII', 'Ganar en el nivel 1', 'premio bronce 10.png', 10)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceIII', 'Ganar en el nivel 1', 'premio bronce 15.png', 15)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceIV', 'Ganar en el nivel 1', 'premio bronce 20.png', 20)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceV', 'Ganar en el nivel 1', 'premio bronce 25.png', 25)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroI', 'Ganar en el nivel 3', 'premio plata 5.png', 5)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroII', 'Ganar en el nivel 3', 'premio plata 10.png', 10)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroIII', 'Ganar en el nivel 3', 'premio plata 15.png', 15)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroIV', 'Ganar en el nivel 3', 'premio plata 20.png', 20)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroV', 'Ganar en el nivel 3', 'premio plata 25.png', 25)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataI', 'Ganar en el nivel 2', 'premio plata 5.png', 5)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataII', 'Ganar en el nivel 2', 'premio plata 10.png', 10)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataIII', 'Ganar en el nivel 2', 'premio plata 15.png', 15)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataIV', 'Ganar en el nivel 2', 'premio plata 20.png', 20");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataV', 'Ganar en el nivel 2', 'premio plata 25.png', 25)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaI', 'Gana partidas seguidas hasta', 'Racha1.png', 1)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaII', 'Gana partidas seguidas hasta', 'Racha2.png', 5)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaIII', 'Gana partidas seguidas hasta', 'Racha3.png', 8)");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaIV', 'Gana partidas seguidas hasta', 'Racha4.png', 10");
		GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaV', 'Gana partidas seguidas hasta', 'Racha5.png', 15)");
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
