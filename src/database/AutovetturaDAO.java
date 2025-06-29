package database;

import entity.EntityAutovettura;
import exception.DAOException;
import exception.DBConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<EntityAutovettura> getAutovettureDisponibili() throws DAOException, DBConnectionException{
        List<EntityAutovettura> autovetture = new ArrayList<>();
        EntityAutovettura eA;

        try { 
            Connection conn = DBManager.getConnection();

            String query = "SELECT TARGA, MODELLO, POSTI, ANNOIMMATRICOLAZIONE, SCADENZAASSICURAZIONE FROM AUTOVETTURA WHERE DISPONIBILE = TRUE";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet result = stmt.executeQuery();

                while(result.next()) {
                    eA = new EntityAutovettura(result.getString(1), result.getString(2), result.getInt(3), result.getInt(4), result.getDate(5), null);
                    autovetture.add(eA);
                }

            }catch(SQLException e) {
                throw new DAOException("Errore lettura preventivo dal database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return autovetture;
    }
}
