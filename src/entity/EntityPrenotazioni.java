package entity;

public class EntityPrenotazioni {
    
    private int idPrenotazione;
    private String dataConferma;

    public EntityPrenotazioni(int idPrenotazione, String dataConferma) {
        this.idPrenotazione = idPrenotazione;
        this.dataConferma = dataConferma;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idprenotazione) {
        this.idPrenotazione = idprenotazione;
    }

    public String getDataconferma() {
        return dataConferma;
    }

    public void setDataconferma(String dataconferma) {
        this.dataConferma = dataconferma;
    }
}
