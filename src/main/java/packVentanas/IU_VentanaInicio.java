package packVentanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.NoArchivoAudioException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Insets;

@SuppressWarnings("serial")
public class IU_VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JButton btnRanking;
	private JButton btnInicio;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;
	private JButton btnRedSocial;
	private JButton btnRegistrarse;
	private JLabel lblO;
	private JLabel lblTextoLogIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_VentanaInicio frame = new IU_VentanaInicio();
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
	public IU_VentanaInicio() throws NoArchivoAudioException {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/Logo1.jpg")).getImage();

		//SONIDO-INICIO		
				if (new File("sources/himno.wav").getAbsoluteFile() != null){
					try {
						ais = AudioSystem.getAudioInputStream(new File("src/main/resources/login.wav").getAbsoluteFile());
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
				}else {
					throw new NoArchivoAudioException();
				}
				clip.start();
				//SONIDO FIN
		
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[188.00,grow][][224.00]", "[60.00][60.00][15,grow][grow][40,grow][50]"));
		contentPane.add(getBtnNewButton(), "cell 0 1,alignx center,aligny bottom");
		contentPane.add(getLblO(), "cell 0 2,alignx center,aligny center");
		contentPane.add(getBtnRegistrarse(), "cell 0 3,alignx center,aligny top");
		contentPane.add(getLblTextoLogIn(), "flowx,cell 0 4,alignx center");
		contentPane.add(getBtnInicio(), "cell 0 4,alignx center");
		contentPane.add(getBtnRanking(), "cell 2 5,alignx center,aligny bottom");
	}
	
	private JButton getBtnRanking() {
		if (btnRanking == null) {
			btnRanking = new JButton("Ver Ranking");
			btnRanking.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnRanking.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_RankingCualquiera ranking = new IU_RankingCualquiera();
					ranking.setVisible(true);
					clip.stop();
				}
			});
		}
		return btnRanking;
	}
	
	private JButton getBtnInicio() {
		if (btnInicio == null) {
			btnInicio = new JButton("IniciarSesion");
			btnInicio.setMargin(new Insets(1, 2, 1, 2));
			btnInicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						IU_LogIn login = new IU_LogIn();
						login.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnInicio;
	}

	private JButton getBtnNewButton() {
		if (btnRedSocial == null) {
			btnRedSocial = new JButton("Entrar con red Social");
			btnRedSocial.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnRedSocial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						IU_RedSocialLogIn register = new IU_RedSocialLogIn();
						register.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnRedSocial;
	}
	private JButton getBtnRegistrarse() {
		if (btnRegistrarse == null) {
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						IU_Registro register = new IU_Registro();
						register.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnRegistrarse;
	}
	private JLabel getLblO() {
		if (lblO == null) {
			lblO = new JLabel("----------------------------------------");
			lblO.setForeground(new Color(255, 255, 255));
		}
		return lblO;
	}
	private JLabel getLblTextoLogIn() {
		if (lblTextoLogIn == null) {
			lblTextoLogIn = new JLabel("Si ya tienes cuenta, prueba a");
			lblTextoLogIn.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTextoLogIn.setForeground(new Color(204, 255, 255));
		}
		return lblTextoLogIn;
	}
}
