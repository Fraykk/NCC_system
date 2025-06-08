package entity;

import java.sql.Date;
import java.sql.Time;

public class EntityPreventivo {
    private long id;
    private String partenza;
    private String destinazione;
    private Date data;
    private Time ora;
    private EntityCliente cliente;
    private int costo;

    public EntityPreventivo(String partenza, String destinazione, Date data, Time ora, EntityCliente cliente) {
        this.id = -1;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.data = data;
        this.ora = ora;
        this.cliente = cliente;
        this.costo = 0;
    }

    public String getPartenza() {
        return partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public Date getData() {
        return data;
    }

    public Time getOra() {
        return ora;
    }

    public EntityCliente getCliente() {
        return cliente;
    }

    public void setCliente(EntityCliente cliente) {
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
