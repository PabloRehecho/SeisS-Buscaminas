package packCodigo;

import packVentanas.IU_VentanaInicio;

public class Main {
	public static void main(String[] args) 
			throws NoArchivoAudioException {
		//Buscaminas.getBuscaminas().crearValores(); sirve para crear valores porque la base de datos no los guarda
		IU_VentanaInicio login = new IU_VentanaInicio();
		login.setVisible(true);
	}
}
