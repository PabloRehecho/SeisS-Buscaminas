package packCodigo;

import packVentanas.IU_VentanaInicio;

public class Main {
	public static void main(String[] args) 
			throws NoArchivoAudioException {
		Buscaminas.getBuscaminas().crearValores();
		IU_VentanaInicio login = new IU_VentanaInicio();
		login.setVisible(true);
	}
}
