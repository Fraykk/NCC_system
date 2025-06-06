package boundary;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class BoundaryImpiegato {
    public static void preparaPreventivo(String emailCliente, double costo, String dataOra) {
        try {
            boolean disponibile = VetturaDAO.vettureDisponibili(dataOra);

            if (disponibile) {
                String id = SequenzaIDDAO.generaNuovoID();
                EntityPreventivo p = new EntityPreventivo(id, costo, emailCliente, "INVIATO");
                PreventivoDAO.inserisciPreventivo(p);
                System.out.println("Preventivo generato con ID: " + id);
            } else {
                String messaggio = "Al momento non ci sono vetture disponibili per la data e ora richieste. Ti invitiamo a selezionare un'altra data.";
                AlternativeDAO.inviaAlternativa(emailCliente, messaggio);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante la preparazione del preventivo: " + e.getMessage());
        }
    }
}
