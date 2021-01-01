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
		
		/*	Código de Prueba: 5.1.1
		 *	Descripción: Menú Principal sin iniciar sesión - Ver Ránking
		 *	Resultado Esperado: Pantalla Ránking Global
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 * 	Paso 1: Pulsar el botón "Ver ranking" en la ventana "Login"
		 * 	Paso 2: Comprobar que se abre la ventana del ranking global
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *
		 *	Resultado Obtenido: Pantalla Ránking Global
		 *	Observaciones: Correcto
		 */
		
		/*	Establecimiento de un usuario
		 */
		
		GestorUsuario.getGestorUsuario().setUsuario("usuario1@gmail.com");
		
		/*	Código de Prueba: 5.1.2.1
		 *	Descripción: Menú Principal usuario - Ver Ránking
		 *	Resultado Esperado: Pantalla Consulta Ránking
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión como usuario
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Comprobar que se abre la ventana de consulta de los rankings
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *
		 *	Resultado Obtenido: Pantalla Consulta Ránking
		 *	Observaciones: Correcto
		 */
		
		/*	Establecimiento de el administrador
		 */
		
		//
		GestorUsuario.getGestorUsuario().setUsuario("administrador@gmail.com");
		
		/*	Código de Prueba: 5.1.2.2
		 *	Descripción: Menú Principal administrador - Ver Ránking
		 *	Resultado Esperado: Pantalla Consulta Ránking
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión como administrador
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Comprobar que se abre la ventana de consulta de los rankings
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Pantalla Consulta Ránking
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.2.1
		 *	Descripción: Menú Consulta Ránking - Global
		 *	Resultado Esperado: Ránking Global
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Comprobar que se muestra el ranking global
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Global
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.2.2
		 *	Descripción: Menú Consulta Ránking - Personal
		 *	Resultado Esperado: Ránking Personal
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Comprobar que se muestra el ranking personal
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Personal
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.1.1
		 *	Descripción: Menú Ránking Global - Nivel 1
		 *	Resultado Esperado: Ránking Global (Nivel 1)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Nivel 1" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global del nivel 1
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Global (Nivel 1)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.1.2
		 *	Descripción: Menú Ránking Global - Nivel 2
		 *	Resultado Esperado: Ránking Global (Nivel 2)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Nivel 2" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global del nivel 2
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Global (Nivel 2)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.1.3
		 *	Descripción: Menú Ránking Global - Nivel 3
		 *	Resultado Esperado: Ránking Global (Nivel 3)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Nivel 3" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global del nivel 3
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Global (Nivel 3)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.1.4
		 *	Descripción: Menú Ránking Global - Absoluto
		 *	Resultado Esperado: Ránking Global (Absoluto)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Global" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Absoluto" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking global absoluto
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Global (Absoluto)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.2.1
		 *	Descripción: Menú Ránking Personal - Nivel 1
		 *	Resultado Esperado: Ránking Personal (Nivel 1)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Nivel 1" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal del nivel 1
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Personal (Nivel 1)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.2.2
		 *	Descripción: Menú Ránking Personal - Nivel 2
		 *	Resultado Esperado: Ránking Personal (Nivel 2)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Nivel 2" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal del nivel 2
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Personal (Nivel 2)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.2.3
		 *	Descripción: Menú Ránking Personal - Nivel 3
		 *	Resultado Esperado: Ránking Personal (Nivel 3)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Nivel 3" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal del nivel 3
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Personal (Nivel 3)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.3.2.4
		 *	Descripción: Menú Ránking Personal - Absoluto
		 *	Resultado Esperado: Ránking Personal (Absoluto)
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Ver ranking" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking Personal" en la ventana de consulta de los rankings
		 * 	Paso 4: Pulsar la opción "Absoluto" en la lista desplegable de la ventana de consulta de los rankings
		 * 	Paso 5: Comprobar que se muestra el ranking personal absoluto
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Ránking Personal (Absoluto)
		 *	Observaciones: Correcto
		 */
		
		/*	Código de Prueba: 5.4
		 *	Descripción: Pantalla Juego - Juego - Ver Ránking
		 *	Resultado Esperado: Pantalla Consulta Ránking
		 *
		 *	Pasos a seguir:
		 *	Paso Inicial: Ejecutar esta clase
		 *	Paso 1: Iniciar sesión
		 * 	Paso 2: Pulsar el botón "Aceptar" en la ventana "Menú Principal"
		 * 	Paso 3: Pulsar el botón "Ranking" del menú "Juego" en la ventana "Buscaminas"
		 * 	Paso 4: Comprobar que se muestra la ventana de consulta de los rankings
		 * 	Paso Final: Cerrar todas las ventanas que se han abierto
		 *	
		 *	Resultado Obtenido: Pantalla Consulta Ránking
		 *	Observaciones: Correcto
		 */
	}

}
