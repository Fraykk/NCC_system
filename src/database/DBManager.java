package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
private static Connection conn = null;
	
	private DBManager() {}
	
    public static Connection getConnection() throws SQLException {
        System.out.println("Controllo connessione");
        if (conn == null || conn.isClosed()) {
            System.out.println("Connessione in corso...");
            // Usa la modalità server TCP di H2, assicurati che il database sia avviato in modalità server
            conn = DriverManager.getConnection("jdbc:h2:~/GestioneNoleggioDB.mv", "sa", "");
            System.out.println("Connessione effettuata");
        }
        return conn;
    }
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) {
				conn.close();
			}
	}

}
