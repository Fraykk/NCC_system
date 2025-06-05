package database;

import entity.EntityCliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<EntityCliente> loadAll() {
        List<EntityCliente> clienti = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getConnection();
            String sql = "SELECT * FROM Cliente";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                
                EntityCliente cliente = new EntityCliente(nome, cognome, email, telefono);
                clienti.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                // La connessione non viene chiusa qui per permettere riutilizzo
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clienti;
    }
}