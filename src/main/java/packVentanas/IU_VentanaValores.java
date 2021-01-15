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
public class IU_VentanaValores extends JFrame {

	private JPanel contentPane;
	private Choice choiceNivel;
	private Choice choiceMinas1;
	private Choice choiceMinas2;
	private Choice choiceMinas3;
	private Choice choiceTamano1a;
	private Choice choiceTamano1b;
	private Choice choiceTamano2a;
	private Choice choiceTamano2b;
	private Choice choiceTamano3a;
	private Choice choiceTamano3b;
	private Choice choiceMensaje;
	private int[] valores= new int[11];
	private String[] nombreValores= new String [11];
	private Image fondo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_VentanaValores frame = new IU_VentanaValores();
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
	public IU_VentanaValores() throws NoArchivoAudioException {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/Logo1.png")).getImage();

		
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
		
		ResultSet rs= Buscaminas.getBuscaminas().extraerValores();
		try {
			int i=0;
			while(rs.next()) {
				valores[i]=rs.getInt("valor");
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblNivel = new JLabel("Nivel inicial de cada usuario: ");
		lblNivel.setBounds(10, 4, 203, 14);
		panel.add(lblNivel);
		
		JLabel lblMinas1 = new JLabel("Minas nivel 1");
		lblMinas1.setBounds(10, 29, 119, 14);
		panel.add(lblMinas1);
		
		JLabel lblMinas2 = new JLabel("Minas nivel 2");
		lblMinas2.setBounds(10, 54, 119, 14);
		panel.add(lblMinas2);
		
		JLabel lblMinas3 = new JLabel("Minas nivel 3");
		lblMinas3.setBounds(10, 79, 119, 14);
		panel.add(lblMinas3);
		
		JLabel lblTamano1 = new JLabel("Tamano nivel 1");
		lblTamano1.setBounds(10, 104, 162, 14);
		panel.add(lblTamano1);
		
		JLabel lblTamano2 = new JLabel("Tamano nivel 2");
		lblTamano2.setBounds(10, 129, 162, 14);
		panel.add(lblTamano2);
		
		JLabel lblTamano3 = new JLabel("Tamano nivel 3");
		lblTamano3.setBounds(10, 154, 162, 14);
		panel.add(lblTamano3);
		
		JLabel lblMensajeDeAyuda = new JLabel("Mensaje de ayuda");
		lblMensajeDeAyuda.setBounds(10, 179, 162, 14);
		panel.add(lblMensajeDeAyuda);
		
		choiceNivel = new Choice();
		choiceNivel.setBounds(235, 1, 86, 20);
		for(int i=1; i<=3; i++){
			choiceNivel.add("" + i +"");
		}
		choiceNivel.select(valores[10]-1);
		panel.add(choiceNivel);
		
		choiceMinas1 = new Choice();
		choiceMinas1.setBounds(139, 26, 86, 20);
		for(int i=1; i<=20; i++){
			choiceMinas1.add("" + i +"");
		}
		choiceMinas1.select(valores[7]-1);
		panel.add(choiceMinas1);
		
		choiceMinas2 = new Choice();
		choiceMinas2.setBounds(139, 51, 86, 20);
		for(int i=10; i<=45; i++){
			choiceMinas2.add("" + i +"");
		}
		choiceMinas2.select(valores[8]-10);
		panel.add(choiceMinas2);
		
		choiceMinas3 = new Choice();
		choiceMinas3.setBounds(139, 76, 86, 20);
		for(int i=30; i<=799; i++){
			choiceMinas3.add("" + i +"");
		}
		choiceMinas3.select(valores[9]-30);
		panel.add(choiceMinas3);
		
		choiceTamano1a = new Choice();
		choiceTamano1a.setBounds(182, 101, 86, 20);
		for(int i=1; i<=10; i++){
			choiceTamano1a.add("" + i +"");
		}
		choiceTamano1a.select(valores[3]-1);
		panel.add(choiceTamano1a);
		
		choiceTamano1b = new Choice();
		choiceTamano1b.setBounds(278, 101, 86, 20);
		for(int i=1; i<=15; i++){
			choiceTamano1b.add("" + i +"");
		}
		choiceTamano1b.select(valores[0]-1);
		panel.add(choiceTamano1b);
		
		choiceTamano2a = new Choice();
		choiceTamano2a.setBounds(182, 126, 86, 20);
		for(int i=5; i<=15; i++){
			choiceTamano2a.add("" + i +"");
		}
		choiceTamano2a.select(valores[4]-5);
		panel.add(choiceTamano2a);
		
		choiceTamano2b = new Choice();
		choiceTamano2b.setBounds(278, 126, 86, 20);
		for(int i=10; i<=30; i++){
			choiceTamano2b.add("" + i +"");
		}
		choiceTamano2b.select(valores[1]-10);
		panel.add(choiceTamano2b);
		
		choiceTamano3a = new Choice();
		choiceTamano3a.setBounds(182, 151, 86, 20);
		for(int i=12; i<=20; i++){
			choiceTamano3a.add("" + i +"");
		}
		choiceTamano3a.select(valores[5]-12);
		panel.add(choiceTamano3a);
		
		choiceTamano3b = new Choice();
		choiceTamano3b.setBounds(278, 151, 86, 20);
		for(int i=15; i<=40; i++){
			choiceTamano3b.add("" + i +"");
		}
		choiceTamano3b.select(valores[2]-15);
		panel.add(choiceTamano3b);
		
		choiceMensaje = new Choice();
		choiceMensaje.setBounds(235, 176, 86, 20);
		for(int i=1; i<=4; i++){
			choiceMensaje.add("" + i +"");
		}
		choiceMensaje.select(valores[6]-1);
		panel.add(choiceMensaje);
		
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
				if(Integer.parseInt(choiceMinas1.getSelectedItem())>=Integer.parseInt(choiceTamano1a.getSelectedItem())*Integer.parseInt(choiceTamano1b.getSelectedItem())) 
				{
					VMensaje error = new VMensaje();
					error.setMensaje("Demasiadas minas para ese tamaño en el nivel 1");
					error.setVisible(true);
				}
				else if(Integer.parseInt(choiceMinas2.getSelectedItem())>=Integer.parseInt(choiceTamano2a.getSelectedItem())*Integer.parseInt(choiceTamano2b.getSelectedItem())) 
				{
					VMensaje error = new VMensaje();
					error.setMensaje("Demasiadas minas para ese tamaño en el nivel 1");
					error.setVisible(true);
				}
				else if(Integer.parseInt(choiceMinas3.getSelectedItem())>=Integer.parseInt(choiceTamano3a.getSelectedItem())*Integer.parseInt(choiceTamano3b.getSelectedItem())) 
				{
					VMensaje error = new VMensaje();
					error.setMensaje("Demasiadas minas para ese tamaño en el nivel 1");
					error.setVisible(true);
				}
				else
				{
					try 
					{
						valores[0]=Integer.parseInt(choiceNivel.getSelectedItem());
						valores[1]=Integer.parseInt(choiceMinas1.getSelectedItem());
						valores[2]=Integer.parseInt(choiceMinas2.getSelectedItem());
						valores[3]=Integer.parseInt(choiceMinas3.getSelectedItem());
						valores[4]=Integer.parseInt(choiceTamano1a.getSelectedItem());
						valores[5]=Integer.parseInt(choiceTamano1b.getSelectedItem());
						valores[6]=Integer.parseInt(choiceTamano2a.getSelectedItem());
						valores[7]=Integer.parseInt(choiceTamano2b.getSelectedItem());
						valores[8]=Integer.parseInt(choiceTamano3a.getSelectedItem());
						valores[9]=Integer.parseInt(choiceTamano3b.getSelectedItem());
						valores[10]=Integer.parseInt(choiceMensaje.getSelectedItem());
						nombreValores[0]="nivel";
						nombreValores[1]="minas1";
						nombreValores[2]="minas2";
						nombreValores[3]="minas3";
						nombreValores[4]="filas1";
						nombreValores[5]="columnas1";
						nombreValores[6]="filas2";
						nombreValores[7]="columnas2";
						nombreValores[8]="filas3";
						nombreValores[9]="columnas3";
						nombreValores[10]="mensaje";
						Buscaminas.getBuscaminas().modificarValores(valores, nombreValores);
						IU_MenuPrincipal ventana = new IU_MenuPrincipal();
						ventana.setVisible(true);
						setVisible(false);
					} 
					catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
				}
				
			}
		});
		
		
	}
}