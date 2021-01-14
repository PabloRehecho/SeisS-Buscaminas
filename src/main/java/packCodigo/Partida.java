package packCodigo;

import java.sql.ResultSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import packVentanas.IU_OpcionPremios;
import packVentanas.VBuscaminas;

@SuppressWarnings("deprecation")
public class Partida extends Observable implements Observer {

	private Tablero tablero;
	private int nivel;
	private int contMinas;
	private Timer timer = new Timer();// Aqui va el tiempo
	private boolean juego;
	private float tiempoTrans;
	private int contBanderas = 0;
	private int puntuacion;
	private boolean finalizado = false;
	private String nombreJugador;

	/****************
	 * CONSTRUCTORA *
	 ****************/
	public Partida() {
	}

	/************************
	 * *
	 * 
	 * @return *
	 ************************/
	private void setContMinas() {
		contMinas = tablero.minas().size();
	}

	/** Iniciamos el juego **/
	public void inicioJuego(int pNivel) {
		setNivel(pNivel);
		setJuego(true);
		iniciarTablero(pNivel);
		setContMinas();
		contBanderas = contMinas;
		crono();
	}

	/** Iniciar el tablero **/

	private void iniciarTablero(int pNivel) {
		tablero = TableroBuilder.getTableroBuilder().asignarTablero(pNivel);
	}

	/************************************************************
	 * Resetea el Buscaminas haciendo una nueva instancia de * tablero, casilla,
	 * casillasVacias, lCasillasVisitadas * y lCasillasVacias volviendo a calcular
	 * el numero de * minas. El tiempo se resetea. * *
	 ************************************************************/
	public void reset(VBuscaminas vBuscaminas) {
		iniciarTablero(nivel);
		tablero.addObserver(vBuscaminas);
		setContMinas();
		contBanderas = contMinas;
		tiempoTrans = -1;
		timer.cancel();
		crono();
		tablero.addObserver(this);
		setJuego(true);
		setFinalizado(false);
	}

	public void resetearTablero(VBuscaminas vBuscaminas) {
		iniciarTablero(nivel);
		tablero.deleteObservers();
		tablero.addObserver(vBuscaminas);
		setContMinas();
		contBanderas = contMinas;
		tablero.addObserver(this);
		setJuego(true);

	}

	public void contMinasMitad() {
		contBanderas = contBanderas / 2;
	}

	/** SetJuego **/
	private void setJuego(boolean pJuego) {
		this.juego = pJuego;
		setChanged();
		notifyObservers(juego);
	}

	/********************
	 * @param pNivel *
	 ********************/
	private void setNivel(int pNivel) {
		nivel = pNivel;
	}

	public void descubrirCasilla(int pFila, int pCol) {
		tablero.descubrirCasilla(pFila, pCol);
	}

	/**
	 * 
	 */
	public void gameOver() {
		timer.cancel();
		tablero.mostrarTablero();
		setJuego(false);

	}

	public int obtenerNumFilas() {

		return tablero.obtenerNumFilas();
	}

	public int obtenerNumColumnas() {

		return tablero.obtenerNumColumnas();
	}

	public boolean getJuego() {
		return juego;
	}

	public void ponerBandera(int fila, int col) {
		int aux = contBanderas;
		if (0 <= contBanderas) {
			tablero.ponerBandera(fila, col);
			if (contBanderas < aux) {
				setChanged();
				notifyObservers(fila + "," + col + "," + "PonerBandera");
			} else if (contBanderas > aux) {
				setChanged();
				notifyObservers(fila + "," + col + "," + "QuitarBandera");
			}
		}

	}

	private void crono() {

		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				String texto;
				tiempoTrans++;
				texto = "" + (int) tiempoTrans;
				if (tiempoTrans < 10) {
					setChanged();
					notifyObservers("00" + texto + "," + contBanderas);
				} else if (tiempoTrans < 100) {
					setChanged();
					notifyObservers("0" + texto + "," + contBanderas);
				} else {
					setChanged();
					notifyObservers(texto + "," + contBanderas);
				}
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}

	public void update(Observable pObservable, Object pObjeto) {
		if (pObservable instanceof Tablero) {
			String[] p = pObjeto.toString().split(",");
			if (p[1].equals("BANDERA") && p[0].equals("true")) {
				if (contBanderas > 0) {
					contBanderas--;
				}
			} else if (p[1].equals("BANDERA") && p[0].equals("false")) {
				if (contBanderas < contMinas) {
					contBanderas++;
				}
			}
		}
	}

	public void update50(Observable pObservable, Object pObjeto) {
		if (pObservable instanceof Tablero) {
			String[] p = pObjeto.toString().split(",");
			if (p[1].equals("BANDERA") && p[0].equals("true")) {
				if (contBanderas > 0) {
					contBanderas--;
				}
			} else if (p[1].equals("BANDERA") && p[0].equals("false")) {
				if (contBanderas < contMinas) {
					contBanderas++;
				}
			}
		}
	}

	public void anadirObservador(VBuscaminas vBuscaminas) {
		addObserver(vBuscaminas);
		tablero.addObserver(vBuscaminas);
		tablero.addObserver(this);
	}

	public void establecerNombreJugador(String text) {
		if (text.equals("")) {
			nombreJugador = "Desconocido";
		} else {
			nombreJugador = text;
		}
	}

	public String obtenerNombreJugador() {
		return nombreJugador;
	}

	public int obtenerBanderas() {
		return contBanderas;
	}

	public int obtenerPuntuacion() {
		return puntuacion;
	}

	public int obtenerNivel() {
		return nivel;
	}

	public void comprobarJuego() {
		if (tablero.getContadorCasillasDescubrir() == contMinas) {
			boolean fin = tablero.comprobarJuego();
			setFinalizado(fin);
		}

	}

	private void setFinalizado(boolean fin) {
		this.finalizado = fin;
		if (finalizado) {
			timer.cancel();
			setChanged();
			notifyObservers("FINALIZADO");
		}
	}

	public void calcularPuntos() {
		if (!finalizado) {
			puntuacion = 0;
		} else {
			puntuacion = (int) ((((6000 - tiempoTrans) * Math.sqrt(nivel)) / 10) - (int) tiempoTrans);
		}
	}

	public void descubrirTodosLosVecinos(int a, int b) {
		tablero.descubrirTodosLosVecinos(a, b);
	}

	public void actualizarHitos() {
		ResultSet rs = GestorUsuario.getGestorUsuario().getHitos();
		int[] hitos = null;
		hitos = new int[4];
		try {
			while (rs.next()) {
				hitos[0] = rs.getInt("PartidasGanadas1");
				hitos[1] = rs.getInt("PartidasGanadas2");
				hitos[2] = rs.getInt("PartidasGanadas3");
				hitos[3] = rs.getInt("Racha");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (nivel == 1) {
			hitos[0]++;
		} else if (nivel == 2) {
			hitos[1]++;
		} else if (nivel == 3) {
			hitos[2]++;
		}
		hitos[3]++;
		GestorUsuario.getGestorUsuario().actualizarHitos(hitos);
		if (Buscaminas.getBuscaminas().comprobarPremiosGanados(hitos)) {
			IU_OpcionPremios Oprem = new IU_OpcionPremios();
			Oprem.setVisible(true);
		}

	}

}
