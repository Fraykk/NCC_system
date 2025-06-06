package entity;

import exception.DAOException;
import exception.DBConnectionException;
import database.PreventivoDAO;

public class EntityPreventivo {
    private String idPreventivo;
    private String partenza;
    private String destinazione;
    private String dataPrelievo;

    public EntityPreventivo(String idPreventivo, String partenza, String destinazione, String dataPrelievo) {
        this.idPreventivo = idPreventivo;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.dataPrelievo = dataPrelievo;
    }

    public String getIdPreventivo() {
        return idPreventivo;
    }

    public String getPartenza() {
        return partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public String getDataPrelievo() {
        return dataPrelievo;
    }

    public void creaPreventivo() throws DAOException, DBConnectionException {
        PreventivoDAO.inserisciPreventivo(this);
    }
}
