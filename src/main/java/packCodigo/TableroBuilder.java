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
		if (pNivel==1)
		{
			elTablero = new Tablero(1,7,10);
		}
		else if (pNivel==2)
		{
			elTablero = new Tablero(2,10,15);
			
		}
		else 
		{
			elTablero = new Tablero(3,12,25);			
		}
		elTablero.generarMatriz();		
		return elTablero;
		
	}
	
}
