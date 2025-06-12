package database;

import entity.EntityConducente;
import exception.DAOException;
import exception.DBConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConducenteDAO {
    public static boolean isDisponibile() throws DAOException, DBConnectionException{
        boolean disponibile = false;

        try { 
            Connection conn = DBManager.getConnection();

            String query = "SELECT EXISTS (SELECT 1 FROM CONDUCENTE WHERE disponibile = TRUE) AS is_available;";

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

    public static List<EntityConducente> getConducenti() throws DAOException, DBConnectionException{
        List<EntityConducente> conducente = new ArrayList<>();
        EntityConducente eC;

        try { 
            Connection conn = DBManager.getConnection();

            String query = "SELECT IDCONDUCENTE, NOME, COGNOME FROM CONDUCENTE WHERE DISPONIBILE = TRUE";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet result = stmt.executeQuery();

                while(result.next()) {
                    eC = new EntityConducente(result.getLong(1), result.getString(2), result.getString(3), null);
                    conducente.add(eC);
                }

            }catch(SQLException e) {
                throw new DAOException("Errore lettura preventivo dal database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return conducente;
    }
}
