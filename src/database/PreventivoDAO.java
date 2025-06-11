package database;

import entity.EntityPreventivo;
import exception.DAOException;
import exception.DBConnectionException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                stmt.setDate(3, p.getData());
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

    public static List<EntityPreventivo> leggiPreventivi() throws DAOException, DBConnectionException {
        List<EntityPreventivo> preventivi = new ArrayList<>();
        EntityPreventivo eP;

        try { 
            Connection conn = DBManager.getConnection();

            String query = "SELECT * FROM PREVENTIVO";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet result = stmt.executeQuery();

                if(result.next()) {
                    eP = new EntityPreventivo(result.getString(2), result.getString(3), result.getDate(4), result.getTime(5), null);
                    eP.setId(result.getLong(1));
                    preventivi.add(eP);
                }

            }catch(SQLException e) {
                throw new DAOException("Errore lettura preventivo dal database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return preventivi;
    }

    public static void inserisciCosto(long id, int costo) throws DAOException, DBConnectionException{
        try { 
            Connection conn = DBManager.getConnection();

            String query = "UPDATE PREVENTIVO SET COSTO = ? WHERE IDPREVENTIVO = ?";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, costo);
                stmt.setLong(2, id);

                stmt.executeUpdate();

                System.out.println("Costo aggiunto correttamente");
                
            }catch(SQLException e) {
                throw new DAOException("Errore aggiunta costo preventivo al database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
    }
}
