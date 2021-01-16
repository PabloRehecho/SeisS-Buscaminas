package packVentanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;

@SuppressWarnings("serial")
public class IU_Premios extends JFrame {
	private JPanel contentPane;
	//private final JButton Cerrar;
	private JFrame frame = new JFrame();
	private ArrayList<JLabel> listaImagen= new ArrayList<JLabel>();
	private ArrayList<JLabel> listaNombre= new ArrayList<JLabel>();
	private ArrayList<JLabel> listaDescripcion= new ArrayList<JLabel>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Premios frame=new IU_Premios();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	public IU_Premios() {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		frame.setIconImage(icon);
		contentPane = new JPanel();
		GridLayout gl_panel=new GridLayout(0,3);
		gl_panel.setVgap(20);
		gl_panel.setHgap(50);
		contentPane.setLayout(gl_panel);
		contentPane.setBackground(Color.WHITE);
	    int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
	    JScrollPane jsp=new JScrollPane(contentPane,v,h);
        jsp.setPreferredSize(new Dimension(600,600));
        jsp.setBounds(150,670,850,200);        
        frame.getContentPane().add(jsp); 
        frame.pack();
        frame.setVisible(true);
		frame.setTitle("Mis Premios");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 650, 400);
		
		String[][] res=Buscaminas.getBuscaminas().obtenerPremios();
		int i = 0;
		String[] resN=res[0];
		String[] resD=res[1];
		String[] resC=res[2];
		String[] resI=res[3];
		
		while(i<=resN.length) {
			String nombre=resN[i];
			String descr=resD[i];
			String cond=resC[i];
			String img=resI[i];
			ImageIcon imag = new ImageIcon(getClass().getResource("/"+img));
			JLabel imagen=new JLabel(imag);
			listaImagen.add(imagen);
			JLabel nomb=new JLabel(nombre);
			listaNombre.add(nomb);
			JLabel con=new JLabel(""+descr+" "+cond+" partidas");
			listaDescripcion.add(con);
			contentPane.add(imagen);
			contentPane.add(nomb);
			contentPane.add(con);
		}
	}
}
