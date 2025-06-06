package database;

import java.sql.*;
import entity.EntityNoleggio;

public class NoleggioDAO {

    public static void inserisciNoleggio(EntityNoleggio n) throws SQLException {
        Connection conn = DBManager.getConnection();
        String query = "INSERT INTO Noleggio (idPreventivo, targaVettura, idConducente, idRimessa) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, n.getIdPreventivo());
        stmt.setString(2, n.getTargaVettura());
        stmt.setString(3, n.getIdConducente());
        stmt.setString(4, n.getIdRimessa());
        stmt.executeUpdate();
        stmt.close();
    }
}
