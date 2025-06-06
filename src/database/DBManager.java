package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import exception.DAOException;
import exception.DBConnectionException;


public class DBManager {
	
private static Connection conn = null;
	
	private DBManager() {}
	
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            // Usa la modalità server TCP di H2, assicurati che il database sia avviato in modalità server
            conn = DriverManager.getConnection("jdbc:h2:tcp://192.168.1.10:9092/~/GestioneNoleggioDB", "sa", "");
        }
        return conn;
    }
	
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) {
				conn.close();
			}
	}

}
