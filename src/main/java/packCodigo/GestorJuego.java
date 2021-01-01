package packCodigo;

public class GestorJuego {

	private static GestorJuego miGestorJuego;
	private Partida partida;
	
	private GestorJuego() {
		partida = new Partida();
	}
	
	public static GestorJuego getGestorJuego() {
		if (miGestorJuego == null) {
			miGestorJuego = new GestorJuego();
		}
		return miGestorJuego;
	}
	
	public Partida getPartida() {
		return partida;
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
	
}
