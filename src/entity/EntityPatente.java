package entity;

public class EntityPatente {
    private String numeroPatente;
    private String categoria;
    private String enteRilascio;
    private String dataRilascio;
    private String dataScadenza;

    public EntityPatente(String numeroPatente, String categoria, String enteRilascio, String dataRilascio, String dataScadenza) {
        this.numeroPatente = numeroPatente;
        this.categoria = categoria;
        this.enteRilascio = enteRilascio;
        this.dataRilascio = dataRilascio;
        this.dataScadenza = dataScadenza;
    }

    public String getNumeroPatente() {
        return numeroPatente;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEnteRilascio() {
        return enteRilascio;
    }

    public String getDataRilascio() {
        return dataRilascio;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }
}
