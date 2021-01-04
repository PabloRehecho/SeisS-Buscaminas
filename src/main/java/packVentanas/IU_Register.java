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

@SuppressWarnings("serial")
public class IU_Register extends JFrame {

	private JPanel contentPane;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;
	private JTextField txtCorreo;
	private JPasswordField pswContraseña;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblContraseña2;
	private JPasswordField pswRepiteContraseña;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Register frame = new IU_Register();
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
	public IU_Register() throws NoArchivoAudioException {
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
		contentPane.setLayout(new MigLayout("", "[50][grow][50]", "[60.00][60][60][60][60][60][60][60][60.00]"));

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
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setForeground(new Color(255, 255, 255));
		contentPane.add(lblContraseña, "cell 1 3,aligny bottom");

		pswContraseña = new JPasswordField();
		contentPane.add(pswContraseña, "cell 1 4,growx");
		
		lblContraseña2 = new JLabel("Repite la contrase\u00F1a");
		lblContraseña2.setForeground(Color.WHITE);
		contentPane.add(lblContraseña2, "cell 1 5");
		
		pswRepiteContraseña = new JPasswordField();
		contentPane.add(pswRepiteContraseña, "cell 1 6,growx");

		//aceptar-cancelar
		contentPane.add(getBtnCancelar(), "flowx,cell 1 8,alignx center");
		contentPane.add(getBtnAceptar(), "cell 1 8,alignx center");
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
						if (!esCorreo(txtCorreo.getText())) {
							VMensaje error = new VMensaje();
							error.setMensaje("Correo no válido");
							error.setVisible(true);
						}
						else if(String.valueOf(pswContraseña.getPassword()).length()==0) {
							VMensaje error = new VMensaje();
							error.setMensaje("Contraseña no válida");
							error.setVisible(true);
						}
						else if (!String.valueOf(pswContraseña.getPassword()).equals(String.valueOf(pswRepiteContraseña.getPassword()))){
							VMensaje error = new VMensaje();
							error.setMensaje("Las contraseñas no coinciden");
							error.setVisible(true);
						}
						else if(!Buscaminas.getBuscaminas().crearCuenta(txtCorreo.getText(), pswContraseña.getPassword())) {
							VMensaje error = new VMensaje();
							error.setMensaje("Ya existe una cuenta con este correo");
							error.setVisible(true);
						}
						else {
							Buscaminas.getBuscaminas().setUsuario(txtCorreo.getText());
							IU_MenuPrincipal menu = new IU_MenuPrincipal();
							menu.setVisible(true);
							setVisible(false);
						}
						
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}

				private boolean esCorreo(String text) {
					String[] correo = text.split("@");
					if(correo.length==2 && correo[1].contains(".")) {
						//Por razones que todavía desconozco no funcionaba el .split("."), por lo que haré algo a mano
						return (correo[1].indexOf(".")!=0 && correo[1].indexOf(".")!=correo[1].length()-1);
							
						
					}
					return false;
					//return (correo.length==2 && (!correo[0].contains(".") && correo[1].split(".").length==2));
				}
			});
		}
		return btnAceptar;
	}
}