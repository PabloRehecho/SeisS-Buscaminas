package packVentanas;

import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;

import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class IU_RankingCualquiera extends JFrame {

	private JPanel contentPane;
	private JLabel lblRanking;
	private JLabel lblNivel;
	private Choice choice;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_RankingCualquiera frame = new IU_RankingCualquiera();
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
	public IU_RankingCualquiera() {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[78.00][249.00][81.00]", "[][][grow]"));
		
		lblRanking = new JLabel("Ranking Global");
		contentPane.add(lblRanking, "cell 1 0,alignx center,aligny center");
		
		lblNivel = new JLabel("Nivel:");
		contentPane.add(lblNivel, "flowx,cell 2 0,alignx center,aligny center");
		contentPane.add(getChoice(), "cell 2 1,alignx center");
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, "cell 1 2,grow");
		mostrarRanking();
	}
	
	private Choice getChoice() {
		if (choice == null) {
			choice = new Choice();
			String arr [] = {"Absoluto","Nivel 1","Nivel 2","Nivel 3"};
			for(int i=0; i<arr.length; i++){
				choice.add(arr[i]);
			}
			choice.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mostrarRanking();
				}
			});
		}
		
		return choice;
	}
	
	private void mostrarRanking() {
		String eleccion = choice.getSelectedItem();
		int nivel = 0;
		if (eleccion.equals("Absoluto")) {
			nivel = 0;
		}
		else if (eleccion.equals("Nivel 1")) {
			nivel = 1;
		}
		else if (eleccion.equals("Nivel 2")) {
			nivel = 2;
		}
		else if (eleccion.equals("Nivel 3")) {
			nivel = 3;
		}
		textArea.setText("");
		ResultSet rs = Buscaminas.getBuscaminas().mostrarRanking("Global", nivel);
		try {
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				int puntuacion = rs.getInt("puntuacion");
				textArea.append(nombre + "			" + puntuacion + "\n");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
