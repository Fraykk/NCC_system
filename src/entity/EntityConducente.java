package entity;

public class EntityConducente {
    private String nome;
    private String cognome;
    private EntityPatente entityPatente;

    public EntityConducente(String nome, String cognome, EntityPatente entityPatente) {
        this.nome = nome;
        this.cognome = cognome;
        this.entityPatente = entityPatente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public EntityPatente getEntityPatente() {
        return entityPatente;
    }

    public void setEntityPatente(EntityPatente entityPatente) {
        this.entityPatente = entityPatente;
    }
}
