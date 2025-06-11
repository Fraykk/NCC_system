package entity;

import java.util.Date;

public class EntityAutovettura {
    private final String targa;
    private final String modello;
    private final int posti;
    private final int annoImmatricolazione;
    private Date scadenzaAssicurazione;
    private EntityRimessa rimessa;

    public EntityAutovettura(String targa, String modello, int posti, int annoImmatricolazione, Date scadenzaAssicurazione, EntityRimessa rimessa) {
        this.targa = targa;
        this.modello = modello;
        this.posti = posti;
        this.annoImmatricolazione = annoImmatricolazione;
        this.scadenzaAssicurazione = scadenzaAssicurazione;
        this.rimessa = rimessa;
    }

    public String getTarga() {
        return targa;
    }

    public String getModello() {
        return modello;
    }

    public int getPosti() {
        return posti;
    }

    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public Date getScadenzaAssicurazione() {
        return scadenzaAssicurazione;
    }

    public EntityRimessa getRimessa() {
        return rimessa;
    }

    public void setScadenzaAssicurazione(Date scadenzaAssicurazione) {
        this.scadenzaAssicurazione = scadenzaAssicurazione;
    }

    public void setIdRimessa(EntityRimessa rimessa) {
        this.rimessa = rimessa;
    }
}
