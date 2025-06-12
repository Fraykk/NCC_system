package database;

import exception.DAOException;
import exception.DBConnectionException;
import org.junit.*;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class AutovetturaDAOTest {

    @BeforeClass
    public static void setupDatabase() throws Exception {
        try (Connection conn = DBManager.getConnectionForTest();
             Statement stmt = conn.createStatement()) {
            String schema = "CREATE TABLE IF NOT EXISTS Autovettura (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "modello VARCHAR(50), " +
                    "disponibile BOOLEAN)";
            stmt.execute(schema);
        }
    }

    @Before
    public void clearTable() throws Exception {
        try (Connection conn = DBManager.getConnectionForTest();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Autovettura;");
        }
    }

    @Test
    public void testIsDisponibileTrue() throws Exception {
        try (Connection conn = DBManager.getConnectionForTest();
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO Autovettura (modello, disponibile) VALUES ('Fiat Panda', TRUE);");
        }
        int idAuto = 1;
        boolean result = AutovetturaDAO.isDisponibile(idAuto);
        assertTrue(result);
    }

    @Test
    public void testIsDisponibileFalse() throws Exception {
        try (Connection conn = DBManager.getConnectionForTest();
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO Autovettura (modello, disponibile) VALUES ('Fiat 500', FALSE);");
        }
        int idAuto = 1;
        boolean result = AutovetturaDAO.isDisponibile(idAuto);
        assertFalse(result);
    }
}
