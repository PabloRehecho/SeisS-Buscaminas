package packVentanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class IU_VentanaValores extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNivel;
	private JTextField textFieldMinas1;
	private JTextField textFieldMinas2;
	private JTextField textFieldMinas3;
	private JTextField textFieldTamano1a;
	private JTextField textFieldTamano1b;
	private JTextField textFieldTamano2a;
	private JTextField textFieldTamano2b;
	private JTextField textFieldTamano3a;
	private JTextField textFieldTamano3b;
	private JTextField textFieldMensaje;
	private int[] valores= new int[11];
	private String[] nombreValores= new String [11];;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_VentanaValores frame = new IU_VentanaValores();
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
	public IU_VentanaValores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNivel = new JLabel("Nivel inicial de cada usuario");
		lblNivel.setBounds(10, 4, 203, 14);
		panel.add(lblNivel);
		
		JLabel lblMinas1 = new JLabel("Minas nivel 1");
		lblMinas1.setBounds(10, 29, 110, 14);
		panel.add(lblMinas1);
		
		JLabel lblMinas2 = new JLabel("Minas nivel 2");
		lblMinas2.setBounds(10, 54, 110, 14);
		panel.add(lblMinas2);
		
		JLabel lblMinas3 = new JLabel("Minas nivel 3");
		lblMinas3.setBounds(10, 79, 110, 14);
		panel.add(lblMinas3);
		
		JLabel lblTamano1 = new JLabel("Tamano nivel 1");
		lblTamano1.setBounds(10, 104, 110, 14);
		panel.add(lblTamano1);
		
		JLabel lblTamano2 = new JLabel("Tamano nivel 2");
		lblTamano2.setBounds(10, 129, 110, 14);
		panel.add(lblTamano2);
		
		JLabel lblTamano3 = new JLabel("Tamano nivel 3");
		lblTamano3.setBounds(10, 154, 110, 14);
		panel.add(lblTamano3);
		
		JLabel lblMensajeDeAyuda = new JLabel("Mensaje de ayuda");
		lblMensajeDeAyuda.setBounds(10, 179, 110, 14);
		panel.add(lblMensajeDeAyuda);
		
		textFieldNivel = new JTextField();
		textFieldNivel.setBounds(235, 1, 86, 20);
		panel.add(textFieldNivel);
		textFieldNivel.setColumns(10);
		
		textFieldMinas1 = new JTextField();
		textFieldMinas1.setBounds(139, 26, 86, 20);
		panel.add(textFieldMinas1);
		textFieldMinas1.setColumns(10);
		
		textFieldMinas2 = new JTextField();
		textFieldMinas2.setColumns(10);
		textFieldMinas2.setBounds(139, 51, 86, 20);
		panel.add(textFieldMinas2);
		
		textFieldMinas3 = new JTextField();
		textFieldMinas3.setColumns(10);
		textFieldMinas3.setBounds(139, 76, 86, 20);
		panel.add(textFieldMinas3);
		
		textFieldTamano1a = new JTextField();
		textFieldTamano1a.setColumns(10);
		textFieldTamano1a.setBounds(139, 101, 86, 20);
		panel.add(textFieldTamano1a);
		
		textFieldTamano1b = new JTextField();
		textFieldTamano1b.setColumns(10);
		textFieldTamano1b.setBounds(235, 101, 86, 20);
		panel.add(textFieldTamano1b);
		
		textFieldTamano2a = new JTextField();
		textFieldTamano2a.setColumns(10);
		textFieldTamano2a.setBounds(139, 126, 86, 20);
		panel.add(textFieldTamano2a);
		
		textFieldTamano2b = new JTextField();
		textFieldTamano2b.setColumns(10);
		textFieldTamano2b.setBounds(235, 126, 86, 20);
		panel.add(textFieldTamano2b);
		
		textFieldTamano3a = new JTextField();
		textFieldTamano3a.setColumns(10);
		textFieldTamano3a.setBounds(139, 151, 86, 20);
		panel.add(textFieldTamano3a);
		
		textFieldTamano3b = new JTextField();
		textFieldTamano3b.setColumns(10);
		textFieldTamano3b.setBounds(235, 151, 86, 20);
		panel.add(textFieldTamano3b);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setColumns(10);
		textFieldMensaje.setBounds(139, 176, 86, 20);
		panel.add(textFieldMensaje);
		
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
					setVisible(false);
				} 
				catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
			}
		});
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBackground(Color.GREEN);
		btnGuardarCambios.setBounds(232, 217, 113, 23);
		panel.add(btnGuardarCambios);
		
		btnGuardarCambios.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					valores[0]=Integer.parseInt(textFieldNivel.getText());
					valores[1]=Integer.parseInt(textFieldMinas1.getText());
					valores[2]=Integer.parseInt(textFieldMinas2.getText());
					valores[3]=Integer.parseInt(textFieldMinas3.getText());
					valores[4]=Integer.parseInt(textFieldTamano1a.getText());
					valores[5]=Integer.parseInt(textFieldTamano1b.getText());
					valores[6]=Integer.parseInt(textFieldTamano2a.getText());
					valores[7]=Integer.parseInt(textFieldTamano2b.getText());
					valores[8]=Integer.parseInt(textFieldTamano3a.getText());
					valores[9]=Integer.parseInt(textFieldTamano3b.getText());
					valores[10]=Integer.parseInt(textFieldMensaje.getText());
					nombreValores[0]="nivel";
					nombreValores[1]="minas1";
					nombreValores[2]="minas2";
					nombreValores[3]="minas3";
					nombreValores[4]="filas1";
					nombreValores[5]="columnas1";
					nombreValores[6]="filas2";
					nombreValores[7]="columnas2";
					nombreValores[8]="filas3";
					nombreValores[9]="columnas3";
					nombreValores[10]="mensaje";
					Buscaminas.getBuscaminas().modificarValores(valores, nombreValores);
					IU_MenuPrincipal ventana = new IU_MenuPrincipal();
					ventana.setVisible(true);
					setVisible(false);
				} 
				catch (NoArchivoAudioException e1)	{e1.printStackTrace();}
			}
		});
		
		
	}
}