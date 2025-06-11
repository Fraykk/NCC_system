package database;

import entity.EntityCliente;
import entity.EntityPrenotazione;
import entity.EntityPreventivo;
import exception.DAOException;
import exception.DBConnectionException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {
    public static List<EntityPrenotazione> leggiPrenotazioni() throws DAOException, DBConnectionException {
        List<EntityPrenotazione> prenotazione = new ArrayList<>();
        EntityPrenotazione eP;

        try { 
            Connection conn = DBManager.getConnection();

            String query = """
                            SELECT
                                P.IDPRENOTAZIONE,
                                P.DATACONFERMA,
                                P.ACCONTO,
                                PRE.PARTENZA,
                                PRE.DESTINAZIONE,
                                PRE.DATA AS DataPreventivo,
                                PRE.ORA AS OraPreventivo,
                                C."USER" AS UserCliente
                            FROM
                                Prenotazione AS P
                            JOIN
                                Preventivo AS PRE ON P.IDPREVENTIVO = PRE.IDPREVENTIVO
                            JOIN
                                Cliente AS C ON P.IDCLIENTE = C.IDCLIENTE;
                            """;

            try{
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet result = stmt.executeQuery();

                while(result.next()) {
                    eP = new EntityPrenotazione(result.getLong(1), new EntityPreventivo(result.getString(4), result.getString(5), result.getDate(6), result.getTime(7), new EntityCliente(null, null, null, 0)), result.getDate(2), result.getDouble(3));
                    eP.getPreventivo().getCliente().setUser(result.getString(8));
                    prenotazione.add(eP);
                }

            }catch(SQLException e) {
                throw new DAOException("Errore lettura prenotazioni dal database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return prenotazione;
    }

    public static void setConducente(long idPrenotazione, long idConducente) throws DAOException, DBConnectionException{
        try { 
            Connection conn = DBManager.getConnection();

            String query = "UPDATE PRENOTAZIONE SET IDCONDUCENTE = (?) WHERE IDPRENOTAZIONE = (?)";

            try{
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setLong(1, idConducente);
                stmt.setLong(2, idPrenotazione);

                stmt.executeUpdate();

            }catch(SQLException e) {
                throw new DAOException("Errore aggiunta preventivo al database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }
    }

    public static void setAutovettura(long idPrenotazione, String targa) throws DAOException, DBConnectionException{
        try { 
            Connection conn = DBManager.getConnection();

            String query = "UPDATE PRENOTAZIONE SET IDAUTOVETTURA = (?) WHERE IDPRENOTAZIONE = (?)";

            try{
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, targa);
                stmt.setLong(2, idPrenotazione);

                stmt.executeUpdate();

            }catch(SQLException e) {
                throw new DAOException("Errore aggiunta preventivo al database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }
    }
}
