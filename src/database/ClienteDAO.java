package database;

import entity.EntityCliente;
import entity.EntityPreventivo;
import exception.DAOException;
import exception.DBConnectionException;
import java.sql.*;

public class ClienteDAO {

    public static long inserisciCliente(EntityCliente c) throws DAOException, DBConnectionException {
        long idGenerato = -1;

        try { 
            Connection conn = DBManager.getConnection();

            String query = "INSERT INTO CLIENTE (NOME, COGNOME, EMAIL, TELEFONO) VALUES (?, ?, ?, ?)";

            try{
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getCognome());
                stmt.setString(3, c.getEmail());
                stmt.setInt(4, c.getTelefono());

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            idGenerato = rs.getLong(1); // Recupera il primo (e unico) ID generato
                            System.out.println("Cliente aggiunto al database con ID: " + idGenerato);
                        }
                    } catch(Exception e){
                        System.out.println("Errore nel trovare ID");
                    }
                } else {
                    System.out.println("Nessun cliente aggiunto (0 righe affette).");
                }
            }catch(SQLException e) {
                throw new DAOException("Errore aggiunta cliente al database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
        return idGenerato;
    }

    public static EntityCliente getCliente(EntityPreventivo eP) throws DAOException, DBConnectionException{
        long idCliente;
        EntityCliente eC;

        try { 
            Connection conn = DBManager.getConnection();

            String query1 = "SELECT IDCLIENTE FROM PREVENTIVO WHERE IDPREVENTIVO = ?";
            String query2 = "SELECT * FROM CLIENTE WHERE IDCLIENTE = ?";

            try{
                PreparedStatement stmt1 = conn.prepareStatement(query1);
                PreparedStatement stmt2 = conn.prepareStatement(query2);

                stmt1.setLong(1, eP.getId());                

                ResultSet resultPreventivo = stmt1.executeQuery();
                if (resultPreventivo.next()){
                    idCliente = resultPreventivo.getLong(1);
                } else {
                    throw new DAOException("Preventivo non trovato con ID: " + eP.getId());
                }

                stmt2.setLong(1, idCliente);
                
                ResultSet resultCliente = stmt2.executeQuery();
                
                if (resultCliente.next()){
                    eC = new EntityCliente(resultCliente.getString(2), resultCliente.getString(3), resultCliente.getString(4), resultCliente.getInt(5));
                }else{
                    throw new DAOException("Cliente non trovato con ID: " + idCliente);
                }

            }catch(SQLException e) {
                throw new DAOException("Errore aggiunta preventivo al database");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

        return eC;
    }
}
