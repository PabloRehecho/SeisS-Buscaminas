package packVentanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;
import packCodigo.Partida;
import packCodigo.Tablero;

@SuppressWarnings({ "serial", "deprecation" })
public class VBuscaminas extends JFrame implements ActionListener, Observer {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3, item4;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel[] Banderas = new JLabel[3];
	private JLabel[] Tiempo = new JLabel[3];
	private JPanel panel;
	private int fil;
	private int col;
	private JLabel[] lcasillas;
	private VBuscaminas vBusca = this;
	private Boolean juego = true;
	private Boolean finalizado = false;
	private Clip clip;
	private AudioInputStream ais;
	private int bomba = 0;
	private int[] personalizacion= new int[3];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VBuscaminas frame = new VBuscaminas(2);
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
	public VBuscaminas(int nivel) {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(nivel == 1){
			setBounds(100, 100, 1000, 1000);
		}else if(nivel == 2){
			setBounds(100, 100, 1500, 1500);
		} else if (nivel == 3) {
			setBounds(10, 10, 5000, 5000);
		}
		setTitle("Buscaminas");
		setResizable(false);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menu1 = new JMenu("Juego");
		menuBar.add(menu1);

		menu2 = new JMenu("Ayuda");
		menuBar.add(menu2);

		item1 = new JMenuItem("Nuevo");
		item1.addActionListener(this);
		menu1.add(item1);

		item2 = new JMenuItem("Ver");
		item2.addActionListener(this);
		menu2.add(item2);

		item3 = new JMenuItem("Ranking");
		item3.addActionListener(this);
		menu1.add(item3);

		item4 = new JMenuItem("Salir");
		item4.addActionListener(this);
		menu1.add(item4);

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new MigLayout("", "[200.00]", "[40.00][204.00]"));

		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_2, "cell 0 0,grow");

		panel_2.setLayout(new MigLayout("", "[20.00][20.00][17.00][][20][][]", "[]"));

		for (int i = 0; i < 3; i++) {
			JLabel j1 = new JLabel();
			Banderas[i] = j1;
			panel_2.add(j1, "cell " + i + " 0, grow");
			j1.setHorizontalAlignment(SwingConstants.LEFT);
		}
		ResultSet rs= Buscaminas.getBuscaminas().extraerPersonalizacion();
		System.out.println(rs);
		try {
			rs.next();
			personalizacion[0]=rs.getInt("imagenMinas");
			personalizacion[1]=rs.getInt("imagenCara");
			personalizacion[2]=rs.getInt("sonido");
			System.out.println(personalizacion[0]);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 0));
		panel_2.add(lblNewLabel, "cell 3 0,growx");
		lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Reset"+ personalizacion[1] +".png")));

		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Buscaminas.getBuscaminas().obtenerPartida().reset(vBusca);
				lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Reset"+ personalizacion[1] +".png")));
			}
		});

		for (int i = 4; i < 7; i++) {
			JLabel j1 = new JLabel();
			Tiempo[i - 4] = j1;
			panel_2.add(j1, "cell " + i + " 0, grow");
			j1.setHorizontalAlignment(SwingConstants.RIGHT);
		}

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, "cell 0 1,grow");

		iniciarCasillas(nivel);
		Buscaminas.getBuscaminas().obtenerPartida().inicioJuego(nivel);
		Buscaminas.getBuscaminas().obtenerPartida().anadirObservador(this);
		fil = Buscaminas.getBuscaminas().obtenerPartida().obtenerNumFilas();
		col = Buscaminas.getBuscaminas().obtenerPartida().obtenerNumColumnas();
		mostrarTablero();
		anadirCasillas();
	}

	private void iniciarCasillas(int pNivel) {
		int[] a = Buscaminas.getBuscaminas().conseguirFilasColumnas(pNivel);
		lcasillas = new JLabel[a[0] * a[1]];
	}

	private void mostrarTablero() {

		String SFila = "";
		String SCol = "";
		for (int i = 0; i <= fil; i++) {
			SFila = SFila + "[]";
			for (int j = 0; j <= col; j++) {
				SCol = SCol + "[]";
			}
		}
		panel.setLayout(new MigLayout("", SCol, SFila));
	}

	public void anadirCasillas() {
		String f = "";
		String c = "";
		int cont = 0;
		for (int i = 0; i <= col; i++) {
			f = Integer.toString(i);
			for (int j = 0; j <= fil; j++) {
				c = Integer.toString(j);
				JLabel l1 = new JLabel();
				lcasillas[cont] = l1;
				cont++;
				l1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				l1.setHorizontalAlignment(SwingConstants.CENTER);
				l1.setBackground(new Color(255, 255, 255));
				panel.add(l1, "cell" + f + " " + c);

				l1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3 && juego && !finalizado) {
							int a;
							int b;
							a = getx(buscarPosCasilla((JLabel) e.getSource()));
							b = gety(buscarPosCasilla((JLabel) e.getSource()));
							Buscaminas.getBuscaminas().obtenerPartida().ponerBandera(a, b);
							Buscaminas.getBuscaminas().obtenerPartida().comprobarJuego();
						} else if (e.getButton() == MouseEvent.BUTTON1 && juego && !finalizado) {
							int a;
							int b;
							a = getx(buscarPosCasilla((JLabel) e.getSource()));
							b = gety(buscarPosCasilla((JLabel) e.getSource()));
							Buscaminas.getBuscaminas().obtenerPartida().descubrirCasilla(a, b);
							Buscaminas.getBuscaminas().obtenerPartida().comprobarJuego();
						} else if (e.getButton() == MouseEvent.BUTTON2 && juego && !finalizado) {
							int a;
							int b;
							a = getx(buscarPosCasilla((JLabel) e.getSource()));
							b = gety(buscarPosCasilla((JLabel) e.getSource()));
							Buscaminas.getBuscaminas().obtenerPartida().descubrirTodosLosVecinos(a, b);
							Buscaminas.getBuscaminas().obtenerPartida().comprobarJuego();
						}
					}
				});
				l1.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png")));
			}
		}
	}

	private int gety(int pPos) {
		return (pPos / (fil + 1));

	}

	private int getx(int pPos) {
		if (pPos > 10) {
			return pPos % (fil + 1);
		} else {
			return (pPos % (fil + 1));
		}
	}

	private int buscarPosCasilla(JLabel source) {
		int pos = 0;
		while (lcasillas[pos] != source) {
			pos++;
		}
		return pos;
	}

	public void update(Observable o, Object arg) {
		String[]p = arg.toString().split(",");
		if(o instanceof Partida){ 
			   if(p.length==2){
				   if(p[1]!=null){
					   int aux;
					   int num = Integer.parseInt(p[1]);
					   for(int i=2; i>=0; i--){
						   aux = num%10;
						   num = num/10;
							Banderas[i].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Crono"+aux+".png")));			
						}
				   }
				   if(p[0]!=null){
					   int aux;
					   int num = Integer.parseInt(p[0]);
					   for(int i=2; i>=0; i--){
						   aux = num%10;
						   num = num/10;
							Tiempo[i].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Crono"+aux+".png")));			
						}
				   }
			   }else if(arg instanceof Boolean){
				   if(arg.toString().equals("false")){
					   juego = false;
					   try {
						   play(juego,o);
					   } catch (NoArchivoAudioException e) {
						   e.printStackTrace();
					   }
					   lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Perder" + personalizacion [0] + ".png")));
					   JOptionPane.showMessageDialog(null, "OOOHHHHH QUE PENA, HAS ENCONTRADO UNA MINA!!!");
					   Buscaminas.getBuscaminas().actualizarRanking("Perdida");
				   }
				   else {
					   juego = true;
					   finalizado = false;
					   bomba = 0;
					   habilitarCasillas();
				   }
			   } else if(p.length ==3){
				   int pos = calcularPosicion(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
				   if(p[2].toString().equals("PonerBandera")){
					   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaBandera.png")));
				   } else {
					   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png"))); 
				   } 

			   } else if(arg.equals("FINALIZADO")){
				   finalizado = true;
				   try {
					   play(finalizado, o);
				   } catch (NoArchivoAudioException e) {
					   e.printStackTrace();
				   }
				   lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Victoria" + personalizacion[1] + ".png")));
					Buscaminas.getBuscaminas().obtenerPartida().calcularPuntos();
					Buscaminas.getBuscaminas().actualizarRanking("Ganada");
				   mostrarRanking();
				   JOptionPane.showMessageDialog(null, "HAS RESUELTO CORRECTAMENTE!!!");

			   }
			} else if(o instanceof Tablero){
				if (p.length == 3){
				int pos = calcularPosicion(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
				  if(1<=Integer.parseInt(p[2]) && Integer.parseInt(p[2])<=8){
					  lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla"+Integer.parseInt(p[2])+".png")));
				    }else if(Integer.parseInt(p[2])==0){
				    	   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaVacia.png")));
				    }else if(Integer.parseInt(p[2])==10){
				    	if(bomba == 0){
				    		 lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaPrimeraMina" + personalizacion[0] + ".png")));
				    		 bomba++;
				    	} else {
				    		 lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaMina" + personalizacion[0] + ".png")));	  
				    	}
				    }else if(Integer.parseInt(p[2])==11){
				    	lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaNoMina" + personalizacion[0] + ".png")));
				    }
				if (p[2].toString().equals("PonerBandera")) {
					lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaBandera.png")));
				} else {
					lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png")));
				}

			} else if (arg.equals("FINALIZADO")) {
				finalizado = true;
				try {
					play(finalizado, o);
				} catch (NoArchivoAudioException e) {
					e.printStackTrace();
				}
				lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Victoria.png")));
				Buscaminas.getBuscaminas().obtenerPartida().calcularPuntos();
				Buscaminas.getBuscaminas().actualizarRanking("Ganada");
				mostrarRanking();
				JOptionPane.showMessageDialog(null, "HAS RESUELTO CORRECTAMENTE!!!");

			}
				}
			}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == item1) {
			Buscaminas.getBuscaminas().obtenerPartida().reset(vBusca);
		} else if (e.getSource() == item2) {
			VAyuda vA = new VAyuda();
			vA.setVisible(true);
		} else if (e.getSource() == item3) {
			mostrarRanking();
		} else if (e.getSource() == item4) {
			try {
				IU_MenuPrincipal principal = new IU_MenuPrincipal();
				principal.setVisible(true);
				setVisible(false);
			} catch (NoArchivoAudioException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void habilitarCasillas() {
		for (int i = 0; i < lcasillas.length; i++) {
			lcasillas[i].setEnabled(true);
			lcasillas[i].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png")));
		}
	}

	private int calcularPosicion(int pFila, int pCol) {
		int pos = 0;
		pos = (pCol * (fil + 1)) + pFila;
		return pos;
	}

	private void mostrarRanking() {
		IU_RankingUsuario ranking = new IU_RankingUsuario();
		ranking.setVisible(true);
	}
		
	private void play(boolean pB, Observable part) throws NoArchivoAudioException{
		if (pB==false){
			if (new File("sources/lose"+ personalizacion[2] + ".wav").getAbsoluteFile() != null){
				try {
					ais = AudioSystem.getAudioInputStream(new File("src/main/resources/lose"+ personalizacion[2] + ".wav").getAbsoluteFile());
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				try {
					clip.open(ais);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				throw new NoArchivoAudioException();
			}
		} else {
			if (new File("sources/win.wav").getAbsoluteFile() != null) {
				try {
					ais = AudioSystem.getAudioInputStream(new File("src/main/resources/win.wav").getAbsoluteFile());
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				try {
					clip.open(ais);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Partida partida = (Partida) part;
				partida.actualizarHitos();
			} else {
				throw new NoArchivoAudioException();
			}
		}
		clip.start();
	}
}
