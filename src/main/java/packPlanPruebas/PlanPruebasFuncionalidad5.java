package packPlanPruebas;

import packCodigo.GestorBD;
import packCodigo.GestorRanking;
import packCodigo.GestorUsuario;
import packCodigo.Main;
import packCodigo.NoArchivoAudioException;

public class PlanPruebasFuncionalidad5 {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		/*	Vaciado de la base de datos para que no haya conflictos
		 */
		
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario;");
		
		/*	Volcado de datos en la base de datos para las comprobaciones
		 */
		
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('usuario1@gmail.com', 'contrasena1');");
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (email, contrasena) VALUES ('administrador@gmail.com', 'contrasena0');");
		try {
			GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 100, 1, "Ganada");
			Thread.sleep(1*1000);
			GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 200, 2, "Ganada");
			Thread.sleep(1*1000);
			GestorRanking.getGestorRanking().actualizarRanking("usuario1@gmail.com", "usuario1", 300, 3, "Ganada");
			Thread.sleep(1*1000);
			GestorRanking.getGestorRanking().actualizarRanking("administrador@gmail.com", "administrador", 100, 1, "Ganada");
			Thread.sleep(1*1000);
			GestorRanking.getGestorRanking().actualizarRanking("administrador@gmail.com", "administrador", 200, 2, "Ganada");
			Thread.sleep(1*1000);
			GestorRanking.getGestorRanking().actualizarRanking("administrador@gmail.com", "administrador", 300, 3, "Ganada");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		/*	Inicio del Plan de Pruebas
		 */
		
		Main main = new Main();
		try {
			main.main(args);
		} catch (NoArchivoAudioException e) {
			e.printStackTrace();
		}
		
		/*	C�digo de Prueba: 5.1.1
		 *	Descripci�n: Men� Principal sin iniciar sesi�n - Ver R�nking
		 *	Resultado Esperado: Pantalla R�nking Global
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 * 	Paso 1: Pulsar el bot�n "Ver ranking" en la ventana "Login"
		 * 	Paso 2: Comprobar que se abre la ventana del ranking global
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *
		 *	Resultado Obtenido: Pantalla R�nking Global
		 *	Observaciones: Correcto
		 */
		
		/*	Establecimiento de un usuario
		 */
		
		GestorUsuario.getGestorUsuario().setUsuario("usuario1@gmail.com");
		
		/*	C�digo de Prueba: 5.1.2.1
		 *	Descripci�n: Men� Principal usuario - Ver R�nking
		 *	Resultado Esperado: Pantalla Consulta R�nking
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n como usuario
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Comprobar que se abre la ventana de consulta de los rankings
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *
		 *	Resultado Obtenido: Pantalla Consulta R�nking
		 *	Observaciones: Correcto
		 */
		
		/*	Establecimiento de el administrador
		 */
		
		//
		GestorUsuario.getGestorUsuario().setUsuario("administrador@gmail.com");
		
		/*	C�digo de Prueba: 5.1.2.2
		 *	Descripci�n: Men� Principal administrador - Ver R�nking
		 *	Resultado Esperado: Pantalla Consulta R�nking
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n como administrador
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Comprobar que se abre la ventana de consulta de los rankings
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Pantalla Consulta R�nking
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.2.1
		 *	Descripci�n: Men� Consulta R�nking - Global
		 *	Resultado Esperado: R�nking Global
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Comprobar que se muestra el ranking global
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Global
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.2.2
		 *	Descripci�n: Men� Consulta R�nking - Personal
		 *	Resultado Esperado: R�nking Personal
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Comprobar que se muestra el ranking personal
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Personal
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.1.1
		 *	Descripci�n: Men� R�nking Global - Nivel 1
		 *	Resultado Esperado: R�nking Global (Nivel 1)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Nivel 1" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global del nivel 1
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Global (Nivel 1)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.1.2
		 *	Descripci�n: Men� R�nking Global - Nivel 2
		 *	Resultado Esperado: R�nking Global (Nivel 2)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Nivel 2" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global del nivel 2
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Global (Nivel 2)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.1.3
		 *	Descripci�n: Men� R�nking Global - Nivel 3
		 *	Resultado Esperado: R�nking Global (Nivel 3)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Nivel 3" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global del nivel 3
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Global (Nivel 3)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.1.4
		 *	Descripci�n: Men� R�nking Global - Absoluto
		 *	Resultado Esperado: R�nking Global (Absoluto)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Absoluto" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global absoluto
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Global (Absoluto)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.2.1
		 *	Descripci�n: Men� R�nking Personal - Nivel 1
		 *	Resultado Esperado: R�nking Personal (Nivel 1)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Nivel 1" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal del nivel 1
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Personal (Nivel 1)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.2.2
		 *	Descripci�n: Men� R�nking Personal - Nivel 2
		 *	Resultado Esperado: R�nking Personal (Nivel 2)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Nivel 2" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal del nivel 2
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Personal (Nivel 2)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.2.3
		 *	Descripci�n: Men� R�nking Personal - Nivel 3
		 *	Resultado Esperado: R�nking Personal (Nivel 3)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Nivel 3" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal del nivel 3
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Personal (Nivel 3)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.3.2.4
		 *	Descripci�n: Men� R�nking Personal - Absoluto
		 *	Resultado Esperado: R�nking Personal (Absoluto)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Ver ranking" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opci�n "Absoluto" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal absoluto
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: R�nking Personal (Absoluto)
		 *	Observaciones: Correcto
		 */
		
		/*	C�digo de Prueba: 5.4
		 *	Descripci�n: Pantalla Juego - Juego - Ver R�nking
		 *	Resultado Esperado: Pantalla Consulta R�nking
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesi�n
		 * 	Paso 2: Pulsar el bot�n "Aceptar" en la ventana "Men� Principal"
		 * 	Paso 3: Pulsar el bot�n "Ranking" del men� "Juego" en la ventana "Buscaminas"
		 * 	Paso 4: Comprobar que se muestra la ventana de consulta de los rankings
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Pantalla Consulta R�nking
		 *	Observaciones: Correcto
		 */
	}

}
