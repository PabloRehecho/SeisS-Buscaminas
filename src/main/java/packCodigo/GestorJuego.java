package packCodigo;

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
	
	public void modificarValores(int[] pValores, String[] pNombres) 
	{
		int i=0;
		while (i< pValores.length)
		{
			/*System.out.print(pValores[i]);
			System.out.println(pNombres[i]);*/
			GestorBD.getGestorBD().execSQL2(" UPDATE valores SET valor= " + pValores[i] + " WHERE nombre=' " + pNombres[i] + " ';");
			i++;
		}
	}
	
	public Partida getPartida() {
		return partida;
	}
	
	public void comenzarPartida() {
		partida = new Partida();
	}
}
