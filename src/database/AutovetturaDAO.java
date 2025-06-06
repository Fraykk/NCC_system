package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AutovetturaDAO {
     public static boolean vettureDisponibili(LocalDateTime dataOra) throws SQLException {
        Connection conn = DBManager.getConnection();
        String query = """
            SELECT COUNT(*) AS disponibili
            FROM Autovettura a
            WHERE a.targa NOT IN (
                SELECT n.targaVettura
                FROM Noleggio n
                WHERE n.dataOra = ?
            )
        """;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, Timestamp.valueOf(dataOra));
        ResultSet rs = stmt.executeQuery();
        boolean disponibile = false;
        if (rs.next()) {
            disponibile = rs.getInt("disponibili") > 0;
        }
        rs.close();
        stmt.close();
        return disponibile;
    }
}
