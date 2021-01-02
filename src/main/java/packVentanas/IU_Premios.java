package packVentanas;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;

@SuppressWarnings("serial")
public class IU_Premios extends JFrame {
	private JPanel contentPane;
	private JLabel lblTitulo;
	
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
	public IU_Premios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[78.00][249.00][81.00]", "[][][grow]"));
		
		lblTitulo=new JLabel("Mis Premios");
		contentPane.add(lblTitulo, "flowx, cell 1 0, alignx center, alignycenter");
		
		mostrarPremios();
	}
	
	private void mostrarPremios() {
		ResultSet res=Buscaminas.getBuscaminas().obtenerPremios();
		int i=1;
		try {
			while(res.next()) {
				String nombre=res.getString("Nombre");
				String descr=res.getString("Descripcion");
				int cond=res.getInt("Requisito");
				String img=res.getString("Imagen");
				ImageIcon imag = new ImageIcon(getClass().getResource("/"+img));
				JLabel imagen=new JLabel(imag);
				contentPane.add(imagen, "flowx, cell 0 "+i+", alignx center, alignycenter");
				contentPane.add(new JLabel(nombre), "flowx, cell 1"+i+", alignx center, alignycenter");
				contentPane.add(new JLabel(descr+" "+cond), "flowx, cell 1"+i+", alignx center, alignycenter");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
