package entity;

public class EntityAutovettura {
    private String targa;
    private String modello;
    private int posti;
    private int annoImmatricolazione;
    private String scadenzaAssicurazione;
    private String ultimaRimessa;

    public EntityAutovettura(String targa, String modello, int posti, int annoImmatricolazione, String scadenzaAssicurazione, String ultimaRimessa) {
        this.targa = targa;
        this.modello = modello;
        this.posti = posti;
        this.annoImmatricolazione = annoImmatricolazione;
        this.scadenzaAssicurazione = scadenzaAssicurazione;
        this.ultimaRimessa = ultimaRimessa;
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

    public String getScadenzaAssicurazione() {
        return scadenzaAssicurazione;
    }

    public String getUltimaRimessa() {
        return ultimaRimessa;
    }
}
