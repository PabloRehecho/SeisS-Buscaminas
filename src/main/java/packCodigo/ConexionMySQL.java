package packCodigo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    // Librer�a de MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "bboa77kjhz2m6q9etok2";

    // Host
    public String hostname = "bboa77kjhz2m6q9etok2-mysql.services.clever-cloud.com";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "u9v67rjsklxrcudg";

    // Clave de usuario
    public String password = "hmX4DdIkTWJVuRmA6kWw";

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
        	Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
