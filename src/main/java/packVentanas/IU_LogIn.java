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

@SuppressWarnings("serial")
public class IU_LogIn extends JFrame {

	private JPanel contentPane;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;
	private JTextField txtCorreo;
	private JPasswordField pswContrase�a;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnRecuperar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_LogIn frame = new IU_LogIn();
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
	public IU_LogIn() throws NoArchivoAudioException {
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
		contentPane.setLayout(new MigLayout("", "[50][grow][50]", "[60.00][60][60][60][60][60][60.00]"));

		JLabel lblIniciarSesion = new JLabel("INICIAR SESI\u00D3N");
		lblIniciarSesion.setForeground(new Color(255, 255, 255));
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblIniciarSesion, "cell 1 0,alignx center");

		JLabel lblCorreo = new JLabel("Correo electr\u00F3nico");
		lblCorreo.setForeground(new Color(255, 255, 255));
		contentPane.add(lblCorreo, "cell 1 1,aligny bottom");

		txtCorreo = new JTextField();
		txtCorreo.setToolTipText("");
		txtCorreo.setName("");
		contentPane.add(txtCorreo, "cell 1 2,growx");
		txtCorreo.setColumns(10);

		JLabel lblContrase�a = new JLabel("Contrase\u00F1a");
		lblContrase�a.setForeground(new Color(255, 255, 255));
		contentPane.add(lblContrase�a, "cell 1 3,aligny bottom");

		pswContrase�a = new JPasswordField();
		contentPane.add(pswContrase�a, "cell 1 4,growx");
		
		contentPane.add(getBtnRecuperar(), "cell 1 5,alignx center,aligny top");

		//aceptar-cancelar
		contentPane.add(getBtnCancelar(), "flowx,cell 1 6,alignx center");
		contentPane.add(getBtnAceptar(), "cell 1 6,alignx center");
	}
	
	private JButton getBtnRecuperar() {
		if (btnRecuperar == null) {
			btnRecuperar = new JButton("Recuperar contrase�a");
			btnRecuperar.setFont(new Font("Tahoma", Font.PLAIN, 9));
			btnRecuperar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						IU_ResetContrase�a login = new IU_ResetContrase�a();
						login.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnRecuperar;
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

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(Buscaminas.getBuscaminas().iniciarSesion(txtCorreo.getText(), String.copyValueOf(pswContrase�a.getPassword()))) {
							IU_MenuPrincipal principal = new IU_MenuPrincipal();
							principal.setVisible(true);
							setVisible(false);
						}else {
							VMensaje error = new VMensaje();
							error.setMensaje("Correo o contrase�a incorrectas");
							error.setVisible(true);
						}

					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnAceptar;
	}
}