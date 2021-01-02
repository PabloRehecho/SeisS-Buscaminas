package packVentanas;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton btnCerrar;
	
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
		
		contentPane.add(getBtnMostrar(), "cell 0 1,alignx center, alignycenter");
		contentPane.add(getBtnCerrar(), "cell 0 1,alignx center, alignycenter");
	}
	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Muéstramelos");
			btnMostrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_Premios premio = new IU_Premios();
					premio.setVisible(true);
				}
			});
		}
		return btnMostrar;
	}
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return btnMostrar;
	}
}
