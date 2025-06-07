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

    public EntityPreventivo(String partenza, String destinazione, Date data, Time ora, EntityCliente cliente) {
        this.id = -1;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.data = data;
        this.ora = ora;
        this.cliente = cliente;
    }

    public String getPartenza() {
        return partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public Date getDataPrelievo() {
        return data;
    }

    public Time getOra() {
        return ora;
    }

    public EntityCliente getCliente() {
        return cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
