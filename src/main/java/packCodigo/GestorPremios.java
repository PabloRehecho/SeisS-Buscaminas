package packCodigo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class GestorPremios {
	private static GestorPremios miGestorPremios;
	
	private GestorPremios() {}

	public static GestorPremios getGestorPremios() {
		if(miGestorPremios==null) {
			miGestorPremios=new GestorPremios();
		}
		return miGestorPremios;
	}
	
	public ResultSet getPremios(String pEmail) {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre, Descripcion, Requisito, Imagen FROM UsuarioPremio NATURAL JOIN Premio WHERE emailJugador='"+pEmail+"'");
		return rs;
	}
	public ResultSet getTodosPremios() {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre, Requisito, Descripcion FROM Premio");
		return rs;
	}
	public ResultSet getNombrePremios(String pEmail) {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre FROM UsuarioPremio NATURAL JOIN Premio WHERE emailJugador='"+pEmail+"'");
		return rs;
	}
	public void ganarPremio(String pEmail, String pNombre) {
		GestorBD.getGestorBD().execSQL2("INSERT INTO UsuarioPremio (emailJugador, nombrePremio) values ('"+pEmail+"', '"+pNombre+"')");
	}
	public boolean comprobarPremio(int[] hitos) {
		Set<String> nMios=new HashSet<String>();
		int i=0;
		int cond[];
		String nom[];
		String descr[];
		boolean nuevo=false;
		
		String email=GestorUsuario.getGestorUsuario().getUsuario();
		ResultSet todos, actuales;
		todos=getTodosPremios();
		actuales=getNombrePremios(email);
		cond=new int[20];
		descr=new String[20];
		nom=new String[20];
		try {
			while(todos.next()) {
				cond[i]=todos.getInt("requisito");
				nom[i]=todos.getString("nombre");
				descr[i]=todos.getString("descripcion");
				i++;
			}
			todos.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			while(actuales.next()) {
				nMios.add(todos.getString("Nombre"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		int in=0;
		while(in<=i) {
			if(!nMios.isEmpty()) {
				if(!nMios.contains(nom[i])) {
					if(descr[i]=="Ganar en el nivel 1") {
						if(hitos[0]>=cond[i]){
							nuevo=true;
							ganarPremio(email, nom[i]);
						}
					}
				}
				else if(descr[i]=="Ganar en el nivel 2") {
					if(hitos[1]>=cond[i]){
						nuevo=true;
					}
				}
				else if(descr[i]=="Ganar en el nivel 3") {
					if(hitos[2]>=cond[i]){
						nuevo=true;
					}
				}
				else {
					if(hitos[3]>=cond[i]){
						nuevo=true;
					}
				}
				if(nuevo) {
					ganarPremio(email, nom[i]);
				}
			}
			in++;
		}
		return nuevo;
	}
}
