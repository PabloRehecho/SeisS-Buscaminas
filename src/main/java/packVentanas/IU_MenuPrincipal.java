package packVentanas;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;

import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Dimension;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class IU_MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Choice choice;
	private JButton btnUsuarios;
	private JButton btnValores;
	private JButton btnRanking;
	private JButton btnPremio;
	private JButton btnOk;
	private JButton btnExit;
	private JLabel lblNombre;
	private JLabel lblNivel;
	private Image fondo;
	private JButton btnNewButton;
	private JButton btnCambioContraseña;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_MenuPrincipal frame = new IU_MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws NoArchivoAudioException 
	 */
	public IU_MenuPrincipal() throws NoArchivoAudioException {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		fondo = new ImageIcon(getClass().getResource("/Logo1.jpg")).getImage();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
			}
		};	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[188.00][][224.00]", "[60][60.00][61.00][50][50][50]"));
		contentPane.add(getBtnExit(), "cell 0 0,alignx center");
		contentPane.add(getLblNombre(), "flowx,cell 2 1,alignx center");
		contentPane.add(getLblNivel(), "flowx,cell 2 2,alignx center");
		contentPane.add(getTextField(), "cell 2 1,alignx center");
		contentPane.add(getChoice(), "cell 2 2,alignx center");
		contentPane.add(getBtnUsuarios(), "cell 0 2,alignx center");
		contentPane.add(getBtnValores(), "cell 0 3,alignx center");
		contentPane.add(getBtnOk(), "cell 2 3,alignx center");
		contentPane.add(getBtnRanking(), "cell 0 4,alignx center");
		contentPane.add(getBtnPremio(), "cell 0 5,alignx center");
		contentPane.add(getBtnCambioContraseña(), "cell 0 1,alignx center");
		setTitle("Menú Principal");
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setMaximumSize(new Dimension(200,50));
		}
		return textField;
	}
	private Choice getChoice() {
		if (choice == null) {
			choice = new Choice();
			String arr [] = {"1","2","3"};
			for(int i=0; i<arr.length; i++){
				choice.add(arr[i]);
			}
		}
		
		return choice;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Aceptar");
			btnOk.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					 if (e.getButton() == MouseEvent.BUTTON1) {
						 Buscaminas.getBuscaminas().comenzarPartida();
						 Buscaminas.getBuscaminas().establecerNombreJugador(getTextField().getText());
						 VBuscaminas vB = new VBuscaminas(Integer.parseInt(getChoice().getSelectedItem()));
						 vB.setVisible(true);
						 setVisible(false);
					 }
				}
			});
		}
		return btnOk;
	}
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Cerrar Sesión");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Buscaminas.getBuscaminas().cerrarSesion();
						IU_VentanaInicio login = new IU_VentanaInicio();
						login.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnExit;
	}
	

	private JButton getBtnUsuarios() {
		if (btnUsuarios == null) {
			btnUsuarios = new JButton("Mostrar usuarios");
			btnUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_VentanaUsuarios usuarios;
					try {
						usuarios = new IU_VentanaUsuarios();
						usuarios.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			});
		}
		return btnUsuarios;
	}
	private JButton getBtnValores() {
		if (btnValores == null) {
			btnValores = new JButton("Ver valores");
			btnValores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_VentanaValores valores;
					try {
						valores = new IU_VentanaValores();
						valores.setVisible(true);
						setVisible(false);
					} catch (NoArchivoAudioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			});
		}
		return btnValores;
	}
	private JButton getBtnRanking() {
		if (btnRanking == null) {
			btnRanking = new JButton("Ver Ranking");
			btnRanking.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_RankingUsuario ranking = new IU_RankingUsuario();
					ranking.setVisible(true);
				}
			});
		}
		return btnRanking;
	}
	
	private JButton getBtnPremio() {
		if (btnPremio == null) {
			btnPremio = new JButton("Ver Premios");
			btnPremio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_Premios premio = new IU_Premios();
					premio.setVisible(true);
				}
			});
		}
		return btnPremio;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Introduzca su nombre:");
			
		}
		return lblNombre;
	}
	private JLabel getLblNivel() {
		if (lblNivel == null) {
			lblNivel = new JLabel("Seleccione el nivel:");
		}
		return lblNivel;
	}
	private JButton getBtnCambioContraseña() {
		if (btnCambioContraseña == null) {
			btnCambioContraseña = new JButton("CambiarContrase\u00F1a");
			btnCambioContraseña.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						IU_CambioContraseña psw;
						psw = new IU_CambioContraseña();
						setVisible(false);
						psw.setVisible(true);
					} catch (NoArchivoAudioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


					
				}
			});
		}
		return btnCambioContraseña;
	}
}
