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
import java.util.ArrayList;

@SuppressWarnings("serial")
public class IU_VentanaUsuarios extends JFrame {

	private JPanel contentPane;
	private Image fondo;
	
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
		
		ResultSet rs =Buscaminas.getBuscaminas().extraerListaUsuarios();
		try {
			int i=0;
			while(rs.next()) {
				String nombre= rs.getString("email");
				JLabel l= new JLabel("" + nombre + "");
				l.setBounds(10, 25*i + 20, 145, 14);
				panel.add(l);
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/* JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 33, 145, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 58, 145, 14);
		panel.add(lblNewLabel_1); */
		
		
	}
}