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

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class IU_VentanaUsuarios extends JFrame {

	private JPanel contentPane;
	private Image fondo;
	private JFrame frame = new JFrame();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_VentanaUsuarios frame = new IU_VentanaUsuarios();
					//frame.setVisible(true);
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
		frame.setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/wagruigi.png")).getImage();		
		//SONIDO FIN
		//codigo cogido de https://stackoverflow.com/questions/2287761/how-do-i-get-vertical-scrolling-to-jpanel
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
			};
		};
		GridLayout gl_panel = new GridLayout(0 ,3);
		gl_panel.setVgap(20);
		gl_panel.setHgap(50);
		panel.setLayout( gl_panel);
        panel.setBackground(Color.WHITE);
        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
        JScrollPane jsp=new JScrollPane(panel,v,h);
        jsp.setPreferredSize(new Dimension(600,600));
        jsp.setBounds(150,670,850,200);        
        frame.getContentPane().add(jsp); 
        frame.pack();
        frame.setVisible( true );
        frame.setTitle("Ventana Usuarios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 650, 400);
		

		ResultSet rs =Buscaminas.getBuscaminas().extraerListaUsuarios();
		try {
			int i=0;
			while(rs.next()) {
				String nombre= rs.getString("email");
				JButton l1= new JButton("" + nombre + "");
				JButton l2= new JButton("editar usuario");
				JButton l3= new JButton("borrar usuario");
				l1.setBackground(Color.LIGHT_GRAY);
				l2.setBackground(Color.LIGHT_GRAY);
				l3.setBackground(Color.LIGHT_GRAY);
				panel.add(l1);
				panel.add(l2);
				panel.add(l3);
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}