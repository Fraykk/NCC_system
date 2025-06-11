package entity;

public class EntityCliente {
    
    private final String nome;
    private final String cognome;
    private final String email;
    private final int telefono;
    private long id;
    private String user;
    private String pass;

    public EntityCliente(String nome, String cognome, String email, int telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.id = -1;
    }

    public void registrazione(String user, String pass){
        this.user = user;
        this.pass = pass;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }
    
}
