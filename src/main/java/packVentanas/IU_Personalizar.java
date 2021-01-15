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
public class IU_Personalizar extends JFrame {

	private JPanel contentPane;
	private Choice choiceMina;
	private Choice choiceCara;
	private Choice choiceSonido;
	private int[] personalizacion= new int[3];
	private Image fondo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Personalizar frame = new IU_Personalizar();
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
	public IU_Personalizar() throws NoArchivoAudioException {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/wagruigi.png")).getImage();

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
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
		
		ResultSet rs= Buscaminas.getBuscaminas().extraerPersonalizacion();
		try {
			rs.next();
			personalizacion[0]=rs.getInt("imagenMinas");
			personalizacion[1]=rs.getInt("imagenCara");
			personalizacion[2]=rs.getInt("sonido");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		choiceMina = new Choice();
		choiceMina.setBounds(10, 30, 86, 20);
		for(int i=1; i<=3; i++){
			choiceMina.add("" + i +"");
		}
		choiceMina.select(personalizacion[0]-1);
		panel.add(choiceMina);
		
		JLabel imagenMina = new JLabel();
		imagenMina.setBounds(150, 1, 40, 40);
		imagenMina.setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaMina" + personalizacion[0] + ".png")));
		panel.add(imagenMina);
		
		JLabel imagenMina1 = new JLabel();
		imagenMina1.setBounds(250, 1, 40, 40);
		imagenMina1.setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaPrimeraMina" + personalizacion[0] + ".png")));
		panel.add(imagenMina1);
		
		JLabel imagenMina2 = new JLabel();
		imagenMina2.setBounds(350, 1, 40, 40);
		imagenMina2.setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaNoMina" + personalizacion[0] + ".png")));
		panel.add(imagenMina2);
		
		choiceCara = new Choice();
		choiceCara.setBounds(10, 130, 86, 20);
		for(int i=1; i<=3; i++){
			choiceCara.add("" + i +"");
		}
		
		JLabel imagenCara = new JLabel();
		imagenCara.setBounds(150, 101, 40, 40);
		imagenCara.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Reset" + personalizacion[1] + ".png")));
		panel.add(imagenCara);
		
		JLabel imagenCara1 = new JLabel();
		imagenCara1.setBounds(250, 101, 40, 40);
		imagenCara1.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Victoria" + personalizacion[1] + ".png")));
		panel.add(imagenCara1);
		
		JLabel imagenCara2 = new JLabel();
		imagenCara2.setBounds(350, 101, 40, 40);
		imagenCara2.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Perder" + personalizacion[1] + ".png")));
		panel.add(imagenCara2);
		
		choiceCara.select(personalizacion[1]-1);
		panel.add(choiceCara);
		
		JButton btnSonido = new JButton("Sonido");
		btnSonido.setBackground(Color.GRAY);
		btnSonido.setForeground(Color.BLACK);
		btnSonido.setBounds(250, 201, 40, 40);
		panel.add(btnSonido);
		
		btnSonido.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
			        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/lose"+ personalizacion [2] + ".wav").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			         System.out.println("Error al reproducir el sonido.");
			       }
			     }
				
		});
		
		choiceSonido = new Choice();
		choiceSonido.setBounds(10, 230, 86, 20);
		for(int i=1; i<=3; i++){
			choiceSonido.add("" + i +"");
		}
		choiceSonido.select(personalizacion[2]-1);
		panel.add(choiceSonido);
		
		JButton btnVolverAlMenu = new JButton("Volver al menu");
		btnVolverAlMenu.setBackground(Color.RED);
		btnVolverAlMenu.setForeground(Color.BLACK);
		btnVolverAlMenu.setBounds(53, 317, 129, 23);
		panel.add(btnVolverAlMenu);
		
		btnVolverAlMenu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					IU_MenuPrincipal ventana = new IU_MenuPrincipal();
					ventana.setVisible(true);
					setVisible(false);

				} 
				catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
			}
		});
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBackground(Color.GREEN);
		btnGuardarCambios.setBounds(232, 317, 150, 23);
		panel.add(btnGuardarCambios);
		
		btnGuardarCambios.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
					{
						personalizacion[0]=Integer.parseInt(choiceMina.getSelectedItem());
						personalizacion[1]=Integer.parseInt(choiceCara.getSelectedItem());
						personalizacion[2]=Integer.parseInt(choiceSonido.getSelectedItem());
						Buscaminas.getBuscaminas().modificarPersonalizacion(personalizacion);
						IU_MenuPrincipal ventana = new IU_MenuPrincipal();
						ventana.setVisible(true);
						setVisible(false);
					} 
				catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
			}
				
		});
		
		
	}
}