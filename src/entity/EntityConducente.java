package entity;

public class EntityConducente {
    private long id;
    private String nome;
    private String cognome;
    private EntityPatente entityPatente;

    public EntityConducente(long id, String nome, String cognome, EntityPatente entityPatente) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.entityPatente = entityPatente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
