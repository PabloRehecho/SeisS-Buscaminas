package packVentanas;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class IU_OpcionPremios extends JFrame{
	private JPanel contentPane;
	private JLabel lblTexto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Premios frame=new IU_Premios();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	public IU_OpcionPremios() {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[78.00][249.00][81.00]", "[][][grow]"));
		
		lblTexto=new JLabel("Has ganado un premio, ¿Quieres ver los premios que tienes?");
		contentPane.add(lblTexto, "flowx, cell 1 0, alignx center, alignycenter");
	}
}
