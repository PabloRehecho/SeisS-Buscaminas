package packVentanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.GestorUsuario;
import packCodigo.NoArchivoAudioException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import java.awt.Insets;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class IU_RedSocialLogIn extends JFrame {

	private JPanel contentPane;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;
	private JButton btnCancelar;
	private JButton btnGG;
	private JButton btnFB;
	private JButton btnTT;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_RedSocialLogIn frame = new IU_RedSocialLogIn();
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
	public IU_RedSocialLogIn() throws NoArchivoAudioException {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/Logo1.jpg")).getImage();


		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50][grow][50]", "[60.00][60][80][60][60.00]"));

		JLabel lblIniciarSesion = new JLabel("INICIAR SESI\u00D3N CON RED SOCIAL");
		lblIniciarSesion.setForeground(new Color(255, 255, 255));
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblIniciarSesion, "cell 1 0,alignx center");

		JLabel lblRedSocial = new JLabel("Elija una red social");
		lblRedSocial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRedSocial.setForeground(new Color(255, 255, 255));
		contentPane.add(lblRedSocial, "cell 1 1,alignx center,aligny bottom");
		contentPane.add(getBtnGG(), "flowx,cell 1 2,alignx center");
		contentPane.add(getHorizontalStrut_1(), "cell 1 2,alignx center");
		
		//botones RRSS
		contentPane.add(getBtnFB(), "cell 1 2,alignx center");
		
		horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut, "cell 1 2");
		contentPane.add(getBtnTT(), "cell 1 2,alignx center");
		//cancelar
		contentPane.add(getBtnCancelar(), "flowx,cell 1 4,alignx center");
		

	}
	
	private JButton getBtnGG() {
		if (btnGG == null) {
			btnGG = new JButton("");
			btnGG.setOpaque(false);
			btnGG.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnGG.setBorder(null);
			btnGG.setBackground(Color.WHITE);
			btnGG.setMinimumSize(new Dimension(1, 1));
			btnGG.setPreferredSize(new Dimension(70, 70));
			btnGG.setIcon(new ImageIcon(getClass().getResource("/gg.png")));
			btnGG.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						IU_VentanaInicio login = new IU_VentanaInicio();
						login.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnGG;
	}
	
	private JButton getBtnFB() {
		if (btnFB == null) {
			btnFB = new JButton("");
			btnFB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnFB.setToolTipText("");
			btnFB.setBorder(null);
			btnFB.setBackground(Color.WHITE);
			btnFB.setMargin(new Insets(4, 14, 0, 14));
			btnFB.setOpaque(false);
			btnFB.setMinimumSize(new Dimension(1, 1));
			btnFB.setPreferredSize(new Dimension(70, 70));
			btnFB.setIcon(new ImageIcon(getClass().getResource("/fb.png")));
			btnFB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VMensaje m = new VMensaje();
					m.setMensaje("No es posible por el momento iniciar sesión con Facebook");
					m.setVisible(true);
				}
			});
		}
		return btnFB;
	}
	
	private JButton getBtnTT() {
		if (btnTT == null) {
			btnTT = new JButton("");
			btnTT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnTT.setBorder(null);
			btnTT.setBackground(Color.WHITE);
			btnTT.setMinimumSize(new Dimension(1, 1));
			btnTT.setOpaque(false);
			btnTT.setPreferredSize(new Dimension(70, 70));
			btnTT.setIcon(new ImageIcon(getClass().getResource("/tt.png")));
			btnTT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VMensaje m = new VMensaje();
					m.setMensaje("No es posible por el momento iniciar sesión con Twitter");
					m.setVisible(true);
				}
			});
		}
		return btnTT;
	}
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						IU_VentanaInicio login = new IU_VentanaInicio();
						login.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnCancelar;
	}
	private Component getHorizontalStrut_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}
}