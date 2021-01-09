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
public class IU_CambioContraseña extends JFrame {

	private JPanel contentPane;
	private Clip clip;
	private AudioInputStream ais;
	private Image fondo;
	private JPasswordField pswAntigua;
	private JPasswordField pswContraseña;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblNueva2;
	private JPasswordField pswRepiteContraseña;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_CambioContraseña frame = new IU_CambioContraseña();
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
	public IU_CambioContraseña() throws NoArchivoAudioException {
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

		JLabel lblAntigua = new JLabel("Contrase\u00F1a actual");
		lblAntigua.setForeground(new Color(255, 255, 255));
		contentPane.add(lblAntigua, "cell 1 1,aligny bottom");

		pswAntigua = new JPasswordField();
		pswAntigua.setToolTipText("");
		pswAntigua.setName("");
		contentPane.add(pswAntigua, "cell 1 2,growx");
		pswAntigua.setColumns(10);
		
		JLabel lblNueva = new JLabel("Nueva contrase\u00F1a");
		lblNueva.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNueva, "cell 1 3,aligny bottom");

		pswContraseña = new JPasswordField();
		contentPane.add(pswContraseña, "cell 1 4,growx");
		
		lblNueva2 = new JLabel("Repite la nueva contrase\u00F1a");
		lblNueva2.setForeground(Color.WHITE);
		contentPane.add(lblNueva2, "cell 1 5");
		
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
						IU_MenuPrincipal menu = new IU_MenuPrincipal();
						menu.setVisible(true);
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
						if(Buscaminas.getBuscaminas().cambioDeContraseña(String.copyValueOf(pswAntigua.getPassword()), 
								String.copyValueOf(pswContraseña.getPassword()), 
								String.copyValueOf(pswRepiteContraseña.getPassword()))) {
							
							VMensaje msg = new VMensaje();
							msg.setMensaje("Contraseña cambiada correctamente");
							IU_MenuPrincipal menu = new IU_MenuPrincipal();
							menu.setVisible(true);
							setVisible(false);
							msg.setVisible(true);
						}
						else {
							VMensaje msg = new VMensaje();
							msg.setMensaje("Datos introducidos incorrectos");
							msg.setVisible(true);
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