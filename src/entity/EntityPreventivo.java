package entity;

public class EntityPreventivo {
    private int idPreventivo;
    private String partenza;
    private String destinazione;
    private String dataPrelievo;
    private double costo;

    public EntityPreventivo(int idPreventivo, String partenza, String destinazione, String dataPrelievo, double costo) {
        this.idPreventivo = idPreventivo;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.dataPrelievo = dataPrelievo;
        this.costo = costo;
    }

    public int getIdPreventivo() {
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

    public double getCosto() {
        return costo;
    }
}
