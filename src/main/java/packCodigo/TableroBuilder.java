package packCodigo;


public class TableroBuilder {
	
	private Tablero elTablero;
	private static TableroBuilder miTablero;

	//-------------------------------------------//
	

	private TableroBuilder(){
		
	}
	
	public static TableroBuilder getTableroBuilder(){
		if (miTablero==null)
		{
			miTablero= new TableroBuilder();
		}		
		return miTablero;
	}
	
	public Tablero asignarTablero(int pNivel){
		//hay que hacer selects
		
		int[] a= new int[2];
		a= Buscaminas.getBuscaminas().conseguirFilasColumnas(pNivel);
		elTablero = new Tablero(pNivel,a[0],a[1]);			
		
		elTablero.generarMatriz();		
		return elTablero;
		
	}
	
}
