package packVentanas;

import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.NoArchivoAudioException;
import packCodigo.Partida;

import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IU_RankingUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem item1, item2;
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
					IU_RankingUsuario frame = new IU_RankingUsuario();
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
	public IU_RankingUsuario() {
		Image icon = new ImageIcon(getClass().getResource("/icono.png")).getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[78.00][249.00][81.00]", "[][][grow]"));
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		item1 = new JMenuItem("Ranking Personal");
		item1.addActionListener(this);
		menuBar.add(item1);
		
		item2 = new JMenuItem("Ranking Global");
		item2.addActionListener(this);
		menuBar.add(item2);
		
		lblRanking = new JLabel("Ranking Personal");
		contentPane.add(lblRanking, "cell 1 0,alignx center,aligny center");
		
		lblNivel = new JLabel("Nivel:");
		contentPane.add(lblNivel, "flowx,cell 2 0,alignx center,aligny center");
		contentPane.add(getChoice(), "cell 2 1,alignx center");
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, "cell 1 2,grow");
		mostrarRankingPersonal();
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
					mostrarRankingPersonal();
				}
			});
		}
		
		return choice;
	}
	
	private void mostrarRankingPersonal() {
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
		ResultSet rs = null;
		if(lblRanking.getText().equals("Ranking Personal")) {
			rs = Buscaminas.getBuscaminas().mostrarRankingPersonal(nivel);
		}
		else {
			rs = Buscaminas.getBuscaminas().mostrarRankingGlobal(nivel);
		}
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
	
	private void mostrarRankingGlobal() {
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
		ResultSet rs = Buscaminas.getBuscaminas().mostrarRankingGlobal(nivel);
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==item1) {
			lblRanking.setText("Ranking Personal");
        	mostrarRankingPersonal();
        }
		else if (e.getSource() == item2) {
			lblRanking.setText("Ranking Global");
        	mostrarRankingGlobal();
        }
	}

}
