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
public class IU_VentanaUsuarioEspecifico extends JFrame {

	private JPanel contentPane;
	private Choice choiceNivel;
	private int valorNivel;
	private Image fondo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final String pCorreo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_VentanaUsuarioEspecifico frame = new IU_VentanaUsuarioEspecifico(pCorreo);
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
	public IU_VentanaUsuarioEspecifico(final String pCorreo) throws NoArchivoAudioException {
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
				
		JLabel lblNivel = new JLabel("Nivel inicial preferido: ");
		lblNivel.setBounds(10, 4, 203, 14);
		panel.add(lblNivel);
		

		choiceNivel = new Choice();
		choiceNivel.setBounds(235, 1, 86, 20);
		for(int i=1; i<=3; i++){
			choiceNivel.add("" + i +"");
		}
		valorNivel=Buscaminas.getBuscaminas().extraerNivelUsuario(pCorreo);
		choiceNivel.select(valorNivel-1);
		panel.add(choiceNivel);
		
		JLabel lblCorreo = new JLabel("email: " + pCorreo);
		lblCorreo.setBounds(10, 54, 203, 14);
		panel.add(lblCorreo);
		
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
					IU_VentanaUsuarios ventana = new IU_VentanaUsuarios();
					//ventana.setVisible(true);
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
					valorNivel=Integer.parseInt(choiceNivel.getSelectedItem());
					Buscaminas.getBuscaminas().actualizarNivelInicial(pCorreo, valorNivel);
					IU_VentanaUsuarios ventana = new IU_VentanaUsuarios();
					//ventana.setVisible(true);
					setVisible(false);
				} 
				catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
			}			
			
		});
		
		
	}
}