package entity;

import java.sql.Date;

public class EntityPrenotazione {
    
    private long id;
    private final Date dataConferma;
    private final double acconto;
    private final EntityPreventivo preventivo;
    private EntityAutovettura autovettura;
    private EntityConducente conducente;

    public EntityPrenotazione(long id, EntityPreventivo eP, Date dataConferma, double acconto) {
        this.id = id;
        this.preventivo = eP;
        this.acconto = acconto;
        this.dataConferma = dataConferma;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataconferma() {
        return dataConferma;
    }

    public double getAcconto() {
        return acconto;
    }

    public EntityPreventivo getPreventivo() {
        return preventivo;
    }

    public EntityAutovettura getAutovettura() {
        return autovettura;
    }

    public void setAutovettura(EntityAutovettura autovettura) {
        this.autovettura = autovettura;
    }

    public EntityConducente getConducente() {
        return conducente;
    }

    public void setConducente(EntityConducente conducente) {
        this.conducente = conducente;
    }
}
