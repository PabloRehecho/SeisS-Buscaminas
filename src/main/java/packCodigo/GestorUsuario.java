package packCodigo;


import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import packVentanas.VMensaje;

//import com.restfb.DefaultFacebookClient;
//import com.restfb.FacebookClient;



public class GestorUsuario {

	private static GestorUsuario miGestorUsuario;
	private String email;
	
	private GestorUsuario() {}
	
	public static GestorUsuario getGestorUsuario() {
		if (miGestorUsuario == null) {
			miGestorUsuario = new GestorUsuario();
		}
		return miGestorUsuario;
	}
	
	public String getUsuario() {
		return email;
	}
	
	public void setUsuario(String pEmail) {
		email = pEmail;
	}

	

	
	public boolean iniciarSesion(String pEmail, String pContraseña) {
		try {
			ResultSet m = GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE Email='" + pEmail + "' AND Contrasena='" + pContraseña + "'");
			if (m.next()) {
				email=pEmail;
				m.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean crearCuenta(String pCorreo, String pContraseña, String pCopiaPassword) {
		try {
			
			String[] correo = pCorreo.split("@");
			if(correo.length==2 && correo[1].contains(".") && correo[1].indexOf(".")!=0 && correo[1].indexOf(".")!=correo[1].length()-1 &&
					pContraseña.length()>0 && pContraseña.equals(pCopiaPassword)){
				//si el coreo está en formato válido, hay una contraseña y las dos contraseñas son iguales
				
				ResultSet m = GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE Email='" + pCorreo + "'");
				if (m.next()){
					m.close();
					return false;
				}else {
					GestorBD.getGestorBD().execSQL2("INSERT INTO usuario (Email,Contrasena) VALUES ('" + pCorreo + "','" + pContraseña + "')");
					email=pCorreo;
					m.close();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}

	public boolean resetContraseña(String pCorreo) {
		String psw= crearNuevaContraseña();
		
		try {
			ResultSet m = GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE Email='" + pCorreo + "'");
			if (!m.next()){
				m.close();
				return false;
			}
			m.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Envío de correo
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.port", "587");
        prop.setProperty("main.smtp.auth", "true");
		
        Session sesion = Session.getDefaultInstance(prop);
        String correoEnvia = "SeisSBuscaminas@gmail.com";
        String contrasena = "6SBuscaminas";
        String receptor = pCorreo;
        String asunto = "Buscaminas reset de contraseña";
        String mensaje= "Tu nueva contraseña es: " + psw;
        
        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            GestorBD.getGestorBD().execSQL2("UPDATE usuario SET contrasena='" + psw + "' WHERE Email='" + pCorreo + "'");
            
            return true;
            
        } catch (AddressException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        }
		return false;
        
	}

	public String crearNuevaContraseña() {
		Random num = new Random();
		char[] psw = new char[7];
		for(int i=0; i<7; i++)
        {
			psw[i] = (char) ('a' + num.nextInt(26));
        }
		return String.valueOf(psw);
	}

	public boolean logearRedSocial() {
		return false;
	}
	
	public void cerrarSesion() {
		this.email = null;
	}

	
	public boolean cambioDeContraseña(String pAntigua, String pN1, String pN2) {
		try {
			ResultSet m = GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE email='" + email + "' and Contrasena='" + pAntigua + "'");
			if(pN1.length()>0 && pN1.contentEquals(pN2) && m.next()){
				GestorBD.getGestorBD().execSQL2("UPDATE usuario SET contrasena='" + pN1 + "' WHERE Email='" + email + "'");
				m.close();
				return true;
			}
			m.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public ResultSet extraerListaUsuarios() {
		return GestorBD.getGestorBD().execSQL("SELECT * FROM usuario");
		
	}
	
	public int extraerNivelUsuario(String pCorreo) {
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE Email='" + pCorreo + "';");
		try {
			rs.next();
			int a= rs.getInt("nivelInicial");
			rs.close();
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void actualizarNivelInicial(String pCorreo, int valorNivel) {
		GestorBD.getGestorBD().execSQL2("UPDATE usuario SET nivelInicial='" + valorNivel + "' WHERE Email='" + pCorreo + "'");
		
	}
	
	public void borrarUsuario(String pCorreo) {
		GestorBD.getGestorBD().execSQL2("DELETE FROM usuario WHERE Email='" + pCorreo +"';");
		
	}
	
	public int seleccionarNivelUsuario(String a) {
		ResultSet rs= GestorBD.getGestorBD().execSQL("SELECT * FROM usuario WHERE email='" + a + "';");
		int b;
		try {
			rs.next();
			b= rs.getInt("nivelInicial");
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=0;
		}
		return b;
	}
	
	public ResultSet getHitos(String pEmail) {
		ResultSet res=null;
		res=GestorBD.getGestorBD().execSQL("SELECT partidasganadas1, partidasganadas2, partidasganadas3, racha FROM usuario WHERE email='"+pEmail+"'");
		return res;
	}
	public void actualizarHitos(int[] hitos, String pEmail) {
		GestorBD.getGestorBD().execSQL2("UPDATE usuario SET partidasganadas1="+hitos[0]+", partidasganadas2="+hitos[1]+", partidasganadas3="+hitos[2]+", racha="+hitos[3]+" WHERE email='"+pEmail+"'");
	}

	

	
	

	
	

}
