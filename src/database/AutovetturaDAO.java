package database;

import exception.DAOException;
import exception.DBConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutovetturaDAO {
    public static boolean isDisponibile() throws DAOException, DBConnectionException{
        boolean disponibile = false;

        try { 
            Connection conn = DBManager.getConnection();

            String query = "SELECT EXISTS (SELECT 1 FROM Autovettura WHERE disponibile = TRUE) AS is_available;";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet result = stmt.executeQuery();

                if(result.next()){
                    disponibile = result.getBoolean("is_available");
                }


            }catch(SQLException e) {
                throw new DAOException("Errore di lettura sul database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return disponibile;
    }
}
