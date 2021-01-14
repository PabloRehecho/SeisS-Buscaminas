package packVentanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;

import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class VAyuda extends JFrame {

	private JPanel contentPane;

	/**	
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAyuda frame = new VAyuda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VAyuda() {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[368.00]", "[][][][]"));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("AYUDA");
		contentPane.add(lblNewLabel, "cell 0 0,alignx center");
		
		JTextArea textArea = new JTextArea();
		int opcionMensajeAyuda= Buscaminas.getBuscaminas().conseguirMensajeAyuda();
		String mensajeAyuda;
		if (opcionMensajeAyuda==1)
		{
			setBounds(100, 100, 420, 570);
			mensajeAyuda="El juego consiste en despejar todas las casillas de \n"
					+ "una pantalla que no oculten una mina.\n"+ 
					"Algunas casillas tienen un n�mero, el cual indica la\n"
					+ "cantidad de minas que hay en las casillas\n"
					+ "circundantes.\n"+
							"As�, si una casilla tiene el n�mero 3, significa que de\n"
							+ "las ocho casillas que hay alrededor (si no es en una\n"
							+ "esquina o borde) hay 3 con minas y 5 sin minas.\n"
							+ "Si se descubre una casilla sin n�mero indica que\n"
							+ "ninguna de las casillas vecinas tiene mina y\n"
							+ "�stas se descubren autom�ticamente.\n"
							+ "Si se descubre una casilla con una mina se pierde\n"
							+ "la partida.\n"
							+ "Se puede poner una marca en las casillas que el\n"
							+ "jugador piensa que hay minas para ayudar a\n"
							+ "descubrir las que est�n cerca.";
		}
		else if (opcionMensajeAyuda==2)
		{
			setBounds(100, 100, 750, 450);
			mensajeAyuda="Entiende los principios detr�s de buscaminas. Cada juego de buscaminas \n " + 
						 "comienza con una cuadr�cula de cuadrados sin marcas. \n" +
						 "Despu�s de hacerle clic a uno de estos cuadrados, algunos desaparecer�n, \n"  + 
						 " otros permanecer�n en blanco y otros tendr�n n�meros en ellos. \n" +
						 "Es tu trabajo usar los n�meros para descubrir cu�les de los cuadrados en \n " + 
						 "blanco tienen minas y a cu�les puedes darle clic de forma segura.\n" + 
						 "Buscaminas es similar a un rompecabezas Sudoku en el que tu �xito \n" +
						 " depende mucho de tu capacidad para eliminar las posibles respuestas hasta que solo quede una.";
		}
		else if (opcionMensajeAyuda==3)
		{
			setBounds(100, 100, 650, 500);
			mensajeAyuda="Los n�meros que aparecen en la cuadr�cula al descubrirse los mosaicos \n " + 
						 " significan la cantidad de minas que tocan ese cuadrado, es decir, \n" +
						 "que si tienes descubierto un dos en una de las esquinas del tablero quiere \n"
						 + "decir que esos dos mosaicos tienen minas. Ten en cuenta que la �nica \n " + 
						 "pista del juego son los n�meros, por lo que deber�s usar tu l�gica y deducci�n \n"
						 + "para tener una idea de cu�l cuadro podr�s tocar y cu�l no.\n" + 
						 "Otra opci�n que puedes usar para ayudarte a dilucidar en cu�l cuadro \n "
						 + "existe una mina y en cu�l no,  es colocar un indicativo que aparecer� al darle \n " +
						 "doble click al bot�n derecho del rat�n. Este signo se ir� alternando a \n"
						 + "medida que presiones dicho bot�n, coloc�ndose una bandera, un signo de \n" + 
						 "interrogaci�n o quitando ambas opciones. Con estos s�mbolos podr�s ir \n"
						 + "descartando y marcando los mosaicos que creas que puedan tener una mina.";
		}
		else
		{
			setBounds(100, 100, 800, 700);
			mensajeAyuda="Empezando a jugar:\n" + 
						 "El tiempo empieza a correr cuando hacemos un click sobre alguno de los \n " 
						 + "casilleros que de momento estan tapados !!!.. en los cuales van aparecer \n"
						 + " numeros de distintos colores, y minas escondidas........ el objetivo del \n"
						 + "juego claro esta, es descubrir las minas sin que exploten \n" + 
						 "Con CLICK IZQUIERDO, DOY VUELTA UN CASILLERO, SI HAY UNA BOMBA EXPLOTA Y PIERDO !!!..\n" + 
						 "CON EL CLICK DERECHO, MARCO LAS BOMBAS, Y NO EXPLOTAN. SE LE PONE UNA BANDERITA... !!!\n" + 
						 "\n" + 
						 "Empiezo haciendo click en cualquier parte hasta que se abra de esta forma o similar:\n" + 
						 "\n" + 
						 "A cada Casillero lo rodean 8 casilleros mas !.... los numeros \n"
						 + " indican con cuantas bombas estan en contacto... ejemplo: SI veo \n "
						 + "un numero 1... significa q esta encontacto con 1 sola bomba de los 8 \n"
						 + "casilleros que esta en contacto........... si veo un numero 2, significa q \n"
						 + "esta en contacto con 2 bombas --->>> Osea hay 2 casilleros que tienen bomba y los otros 6 son numeros....\n" + 
						 "\n" + 
						 "SIEMPRE SIEMPRE. prestelen atencion a los numeros 1 que estan en las \n"
						 + "esquinias, con eso no se pueden confundir... por ejemplo arriba de todo \n"
						 + "a la derecha, tenemos cuatro numeros 1 .. y solamente 2 casilleros sin \n"
						 + " marcar..... por supuesto los numeros nos indican que todos estan en \n"
						 + " contactos con la misma bomba...... entonces? para ustedes cual \n"
						 + " seria?. la de arriba o la de abajo?..... a veeeeeeerrrrrrrr !!! \n";
		}
		textArea.setText(mensajeAyuda);
		textArea.setFont(new Font("Roboto Slab", Font.BOLD, 14));
		textArea.setEditable(false);
		contentPane.add(textArea, "cell 0 1");
		
		JLabel l1 = new JLabel("");
		contentPane.add(l1, "cell 0 3,alignx center");
		l1.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Ayuda.png")));
	}

}

