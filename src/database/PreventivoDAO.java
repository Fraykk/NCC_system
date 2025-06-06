package database;

import java.sql.*;
import entity.EntityPreventivo;
import exception.DAOException;
import exception.DBConnectionException;

public class PreventivoDAO {

    public static void inserisciPreventivo(EntityPreventivo p) throws SQLException {
        try { 
            Connection conn = DBManager.getConnection();

            String query = "INSERT INTO Preventivo (IDPREVENTIVO, PARTENZA, DESTINAZIONE, DATAPRELIEVO) VALUES (?, ?, ?, ?)";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, p.getIdPreventivo());
                stmt.setString(2, p.getPartenza());
                stmt.setString(3, p.getDestinazione());
                stmt.setString(4, p.getDataPrelievo());

                stmt.executeUpdate();
            }catch(SQLException e) {
                throw new DAOException("Errore scrittura biglietto");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
    }
}
