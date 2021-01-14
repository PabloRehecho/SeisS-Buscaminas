package packPlanPruebas;

import packCodigo.GestorBD;
import packCodigo.GestorRanking;
import packCodigo.GestorUsuario;
import packCodigo.Main;
import packCodigo.NoArchivoAudioException;
import packJUnits.GestorUsuarioTest;

public class PlanPruebasFuncionalidad1 {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/* Pruebas de la funcionalidad identificación (Por Xabier Fernandez)
		 * Se separarán las pruebas en diversas llamadas, todas ellas entorno a la identificación
		 * Se presentan los siguientes 6 casos de uso:
		 * 
		 * 
		 * 	Iniciar Sesion	*/
		//GestorUsuarioTest.testIniciarSesion();
	
		/*  Iniciar sesión con la Red Social	 */
		//GestorUsuarioTest.testLogearRedSocial();
		
		/*  Registrarse	 */	
		//GestorUsuarioTest.testCrearCuenta();		
		
		/*  Recuperar contraseña	 */
		//GestorUsuarioTest.testResetContraseña();
		
		/*  Cerrar sesión	 */
		//GestorUsuarioTest.testCerrarSesion();
		
		/*  Cambio Contraseña	*/
		//GestorUsuarioTest.testCambiarContraseña();
	}
}
