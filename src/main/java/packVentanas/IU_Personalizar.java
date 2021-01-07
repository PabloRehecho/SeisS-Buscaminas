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
		
		ResultSet rs= Buscaminas.getBuscaminas().extraerPersonalizacion();
		System.out.println(rs);
		try {
			rs.next();
			personalizacion[0]=rs.getInt("imagenMinas");
			personalizacion[1]=rs.getInt("imagenCara");
			personalizacion[2]=rs.getInt("sonido");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblMina = new JLabel("Mina seleccionada: " + personalizacion[0]);
		lblMina.setBounds(10, 4, 203, 14);
		panel.add(lblMina);
		
		/*JLabel lblMinas1 = new JLabel("Minas nivel 1: " + valores[7]);
		lblMinas1.setBounds(10, 29, 119, 14);
		panel.add(lblMinas1);
		
		JLabel lblMinas2 = new JLabel("Minas nivel 2: " + valores[8]);
		lblMinas2.setBounds(10, 54, 119, 14);
		panel.add(lblMinas2);
		
		JLabel lblMinas3 = new JLabel("Minas nivel 3: " + valores[9]);
		lblMinas3.setBounds(10, 79, 119, 14);
		panel.add(lblMinas3);
		
		JLabel lblTamano1 = new JLabel("Tamano nivel 1: " + valores[3] + " x " + valores[0]);
		lblTamano1.setBounds(10, 104, 162, 14);
		panel.add(lblTamano1);
		
		JLabel lblTamano2 = new JLabel("Tamano nivel 2: " + valores[4] + " x " + valores[1]);
		lblTamano2.setBounds(10, 129, 162, 14);
		panel.add(lblTamano2);
		
		JLabel lblTamano3 = new JLabel("Tamano nivel 3: " + valores[5] + " x " +  valores[2]);
		lblTamano3.setBounds(10, 154, 162, 14);
		panel.add(lblTamano3);
		
		JLabel lblMensajeDeAyuda = new JLabel("Mensaje de ayuda: " + valores[6]);
		lblMensajeDeAyuda.setBounds(10, 179, 162, 14);
		panel.add(lblMensajeDeAyuda);*/
		
		choiceMina = new Choice();
		choiceMina.setBounds(235, 1, 86, 20);
		for(int i=1; i<=3; i++){
			choiceMina.add("" + i +"");
		}
		choiceMina.select(personalizacion[0]-1);
		panel.add(choiceMina);
		
		choiceCara = new Choice();
		choiceCara.setBounds(235, 51, 86, 20);
		for(int i=1; i<=3; i++){
			choiceCara.add("" + i +"");
		}
		choiceCara.select(personalizacion[1]-1);
		panel.add(choiceCara);
		
		choiceSonido = new Choice();
		choiceSonido.setBounds(235, 101, 86, 20);
		for(int i=1; i<=3; i++){
			choiceSonido.add("" + i +"");
		}
		choiceSonido.select(personalizacion[2]-1);
		panel.add(choiceSonido);
		
		/*choiceMinas3 = new Choice();
		choiceMinas3.setBounds(139, 76, 86, 20);
		for(int i=30; i<=99; i++){
			choiceMinas3.add("" + i +"");
		}
		panel.add(choiceMinas3);
		
		choiceTamano1a = new Choice();
		choiceTamano1a.setBounds(182, 101, 86, 20);
		for(int i=1; i<=10; i++){
			choiceTamano1a.add("" + i +"");
		}
		panel.add(choiceTamano1a);
		
		choiceTamano1b = new Choice();
		choiceTamano1b.setBounds(278, 101, 86, 20);
		for(int i=1; i<=15; i++){
			choiceTamano1b.add("" + i +"");
		}
		panel.add(choiceTamano1b);
		
		choiceTamano2a = new Choice();
		choiceTamano2a.setBounds(182, 126, 86, 20);
		for(int i=5; i<=20; i++){
			choiceTamano2a.add("" + i +"");
		}
		panel.add(choiceTamano2a);
		
		choiceTamano2b = new Choice();
		choiceTamano2b.setBounds(278, 126, 86, 20);
		for(int i=10; i<=30; i++){
			choiceTamano2b.add("" + i +"");
		}
		panel.add(choiceTamano2b);
		
		choiceTamano3a = new Choice();
		choiceTamano3a.setBounds(182, 151, 86, 20);
		for(int i=15; i<=30; i++){
			choiceTamano3a.add("" + i +"");
		}
		panel.add(choiceTamano3a);
		
		choiceTamano3b = new Choice();
		choiceTamano3b.setBounds(278, 151, 86, 20);
		for(int i=15; i<=50; i++){
			choiceTamano3b.add("" + i +"");
		}
		panel.add(choiceTamano3b);
		
		choiceMensaje = new Choice();
		choiceMensaje.setBounds(235, 176, 86, 20);
		for(int i=1; i<=5; i++){
			choiceMensaje.add("" + i +"");
		}
		panel.add(choiceMensaje);*/
		
		JButton btnVolverAlMenu = new JButton("Volver al menu");
		btnVolverAlMenu.setBackground(Color.RED);
		btnVolverAlMenu.setForeground(Color.BLACK);
		btnVolverAlMenu.setBounds(53, 217, 129, 23);
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
		btnGuardarCambios.setBounds(232, 217, 150, 23);
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