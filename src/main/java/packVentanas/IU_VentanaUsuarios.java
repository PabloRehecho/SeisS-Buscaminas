package packVentanas;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class IU_VentanaUsuarios extends JFrame {

	private JPanel contentPane;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;
	private String[] listaNombres;
	private JLabel[] listaCorreos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_VentanaUsuarios frame = new IU_VentanaUsuarios();
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
	public IU_VentanaUsuarios() throws NoArchivoAudioException {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/wagruigi.png")).getImage();
		//SONIDO-INICIO		
		if (new File("sources/login.wav").getAbsoluteFile() != null){
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
			}
		};	
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		listaNombres =Buscaminas.getBuscaminas().extraerListaUsuarios();
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 33, 145, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 58, 145, 14);
		panel.add(lblNewLabel_1);
		
		
	}
}