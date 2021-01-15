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
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre, Descripcion, Requisito, Imagen FROM usuariopremio INNER JOIN Premio ON nombrePremio=nombre WHERE emailJugador='"+pEmail+"'");
		return rs;
	}
	public ResultSet getTodosPremios() {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre, Requisito, Descripcion FROM premio");
		return rs;
	}
	public ResultSet getNombrePremios(String pEmail) {
		ResultSet rs = null;
		rs=GestorBD.getGestorBD().execSQL("SELECT Nombre FROM usuariopremio INNER JOIN premio ON nombrePremio=nombre WHERE emailJugador='"+pEmail+"'");
		return rs;
	}
	public void ganarPremio(String pEmail, String pNombre) {
		GestorBD.getGestorBD().execSQL2("INSERT INTO usuariopremio (emailJugador, nombrePremio) values ('"+pEmail+"', '"+pNombre+"')");
	}
	
	public void crearPremios() {
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM premio");
		try {
			if (!rs.next())
			{
				GestorBD.getGestorBD().execSQL2("DELETE FROM premio");
				GestorBD.getGestorBD().execSQL2("ALTER TABLE premio CHANGE imagen imagen varchar(40)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceI', 'Ganar en el nivel 1', 'premio bronce 5.png', 5)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceII', 'Ganar en el nivel 1', 'premio bronce 10.png', 10)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceIII', 'Ganar en el nivel 1', 'premio bronce 15.png', 15)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceIV', 'Ganar en el nivel 1', 'premio bronce 20.png', 20)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('BronceV', 'Ganar en el nivel 1', 'premio bronce 25.png', 25)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroI', 'Ganar en el nivel 3', 'premio oro 5.png', 5)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroII', 'Ganar en el nivel 3', 'premio oro 10.png', 10)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroIII', 'Ganar en el nivel 3', 'premio oro 15.png', 15)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroIV', 'Ganar en el nivel 3', 'premio oro 20.png', 20)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('OroV', 'Ganar en el nivel 3', 'premio oro 25.png', 25)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataI', 'Ganar en el nivel 2', 'premio plata 5.png', 5)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataII', 'Ganar en el nivel 2', 'premio plata 10.png', 10)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataIII', 'Ganar en el nivel 2', 'premio plata 15.png', 15)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataIV', 'Ganar en el nivel 2', 'premio plata 20.png', 20)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('PlataV', 'Ganar en el nivel 2', 'premio plata 25.png', 25)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaI', 'Gana partidas seguidas hasta', 'Racha1.png', 1)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaII', 'Gana partidas seguidas hasta', 'Racha2.png', 5)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaIII', 'Gana partidas seguidas hasta', 'Racha3.png', 8)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaIV', 'Gana partidas seguidas hasta', 'Racha4.png', 10)");
				GestorBD.getGestorBD().execSQL2("INSERT INTO premio VALUES('RachaV', 'Gana partidas seguidas hasta', 'Racha5.png', 15)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean comprobarPremio(int[] hitos, String email) {
		Set<String> nMios=new HashSet<String>();
		int i=0;
		int cond[];
		String nom[];
		String descr[];
		boolean nuevo=false;
		
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
				nMios.add(actuales.getString("Nombre"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		int in=0;
		while(in<i) {
			if(!nMios.isEmpty()) {
				if(!nMios.contains(nom[in])) {
					if(descr[in]=="Ganar en el nivel 1") {
						if(hitos[0]>=cond[in]){
							nuevo=true;
						}
					}
					else if(descr[in]=="Ganar en el nivel 2") {
						if(hitos[1]>=cond[in]){
							nuevo=true;
						}
					}
					else if(descr[in]=="Ganar en el nivel 3") {
						if(hitos[2]>=cond[in]){
							nuevo=true;
						}
					}
					else {
						if(hitos[3]>=cond[in]){
							nuevo=true;
						}
					}
				}
			}
			if(nMios.isEmpty()) {
				if(descr[in]=="Ganar en el nivel 1") {
					if(hitos[0]>=cond[in]){
						nuevo=true;
					}
				}
				else if(descr[in]=="Ganar en el nivel 2") {
					if(hitos[1]>=cond[in]){
						nuevo=true;
					}
				}
				else if(descr[in]=="Ganar en el nivel 3") {
					if(hitos[2]>=cond[in]){
						nuevo=true;
					}
				}
				else {
					if(hitos[3]>=cond[in]){
						nuevo=true;
					}
				}
			}
			if(nuevo) {
				ganarPremio(email, nom[in]);
			}
			in++;
		}
		return nuevo;
	}
}
