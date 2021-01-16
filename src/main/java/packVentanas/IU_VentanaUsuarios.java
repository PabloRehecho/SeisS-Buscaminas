package packVentanas;


import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;

import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;

import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class IU_VentanaUsuarios extends JFrame {


	private Image fondo;
	private JFrame frame = new JFrame();
	private ArrayList<JLabel> listaCorreos= new ArrayList<JLabel>();
	private ArrayList<JButton> listaEdicion= new ArrayList<JButton>();
	private ArrayList<JButton> listaBorrado= new ArrayList<JButton>();
	
	
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
		fondo = new ImageIcon(getClass().getResource("/Logo1.jpg")).getImage();		
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
        frame.setVisible(true);
        frame.setTitle("Ventana Usuarios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 650, 400);
		
		

		ResultSet rs =Buscaminas.getBuscaminas().extraerListaUsuarios();

		try 
		{
			int i=0;
			while(rs.next()) 
			{
				String nombre= rs.getString("email");
				if (!Buscaminas.getBuscaminas().getUsuarioLogeado().equals(nombre))
				{
					JLabel l1= new JLabel("" + nombre + "");
					JButton l2= new JButton("editar usuario");
					JButton l3= new JButton("borrar usuario");
					l2.setBackground(Color.LIGHT_GRAY);
					l3.setBackground(Color.LIGHT_GRAY);
					listaCorreos.add(l1);
					listaEdicion.add(l2);
					listaBorrado.add(l3);
					l2.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e) 
						{
							try
							{
								int posicion= listaEdicion.indexOf(e.getSource());
								IU_VentanaUsuarioEspecifico ventana= new IU_VentanaUsuarioEspecifico(listaCorreos.get(posicion).getText());
								ventana.setVisible(true);
								frame.setVisible(false);

							} 
							catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
						}
					});
					
					l3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e) 
						{						
							int posicion= listaBorrado.indexOf(e.getSource());
							Buscaminas.getBuscaminas().borrarUsuario(listaCorreos.get(posicion).getText());
							VMensaje mensaje= new VMensaje();
							mensaje.setMensaje("Usuario eliminado correctamente");
							IU_MenuPrincipal ventana;
							try {
								ventana = new IU_MenuPrincipal();
								ventana.setVisible(true);
								frame.setVisible(false);
								mensaje.setVisible(true);
							} catch (NoArchivoAudioException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					});
			        
					panel.add(l1);
					panel.add(l2);
					panel.add(l3);
				}
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		
	
			
				
				
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
					frame.setVisible(false);

				} 
				catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
			}
		});
	}	
}