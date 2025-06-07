package entity;

public class EntityCliente {
    
    private String nome;
    private String cognome;
    private String email;
    private int telefono;
    private long id;

    public EntityCliente(String nome, String cognome, String email, int telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.id = -1;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefono() {
        return telefono;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
    
}
