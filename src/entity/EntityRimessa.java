package entity;

public class EntityRimessa {

    private String indirizzo;
    private String tipoRimessa;
    
    public EntityRimessa(String indirizzo, String tipoRimessa) {
        this.indirizzo = indirizzo;
        this.tipoRimessa = tipoRimessa;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getTipoRimessa() {
        return tipoRimessa;
    }
}