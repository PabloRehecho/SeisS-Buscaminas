package packVentanas;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class IU_OpcionPremios extends JFrame{
	private JPanel contentPane;
	private JLabel lblTexto;
	private JButton btnMostrar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_OpcionPremios frame=new IU_OpcionPremios();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	public IU_OpcionPremios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 500, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[78.00][249.00][81.00]", "[][][grow]"));
		
		lblTexto=new JLabel("Has ganado un premio, �Quieres ver los premios que tienes?");
		contentPane.add(lblTexto, "flowx, cell 0 0, alignx center, alignycenter");
		
		contentPane.add(getBtnMostrar(), "cell 0 2,alignx center, alignycenter");
	}
	private JButton getBtnMostrar() {
			if (btnMostrar == null) {
				btnMostrar=new JButton("Mu�stramelo");
				btnMostrar.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						 if (e.getButton() == MouseEvent.BUTTON1) {
							 IU_Premios premio = new IU_Premios();
							 premio.setVisible(true);
						 }
					}
				});
		}
		return btnMostrar;
	}
}
