package database;

import entity.EntityPreventivo;
import exception.DAOException;
import exception.DBConnectionException;
import java.sql.*;

public class PreventivoDAO {

    public static long inserisciPreventivo(EntityPreventivo p) throws DAOException, DBConnectionException {
        long idGenerato = -1;
        try { 
            Connection conn = DBManager.getConnection();

            String query = "INSERT INTO PREVENTIVO (PARTENZA, DESTINAZIONE, DATA, ORA, IDCLIENTE) VALUES (?, ?, ?, ?, ?)";

            try{
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, p.getPartenza());
                stmt.setString(2, p.getDestinazione());
                stmt.setDate(3, p.getDataPrelievo());
                stmt.setTime(4, p.getOra());
                stmt.setLong(5, p.getCliente().getId());

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            idGenerato = rs.getLong(1); // Recupera il primo (e unico) ID generato
                            System.out.println("Preventivo aggiunto al database con ID: " + idGenerato);
                        }
                    } catch(Exception e){
                        System.out.println("Errore nel trovare ID");
                    }
                } else {
                    System.out.println("Nessun preventivo aggiunto (0 righe affette).");
                }
            }catch(SQLException e) {
                throw new DAOException("Errore aggiunta preventivo al database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return idGenerato;
    }
}
