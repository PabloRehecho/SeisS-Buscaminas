package packCodigo;

public class CasillaFactory {

	private static CasillaFactory miCasillaFactory = new CasillaFactory();

	private CasillaFactory() {

	}

	public static CasillaFactory getMiFactoria() {
		return miCasillaFactory;
	}

	public Casilla generarCasilla(String tipo) {
		Casilla cas = null;

		if (tipo == "Mina") {
			cas = new CasillaMina();
		} else if (tipo == "Numero") {
			cas = new CasillaNumero();
		} else if (tipo == "Vacia") {
			cas = new CasillaVacia();
		} else if (tipo == "MinaReset") {
			cas = new CasillaMinaReset();
		} else if (tipo == "Mina50") {
			cas = new CasillaMina50();
		}
		return cas;
	}

}
