package entity;

public class EntityClienteRegistrato extends EntityCliente {

    private String user;
    private String password;

    public EntityClienteRegistrato(EntityCliente cliente, String user, String password) {
        super(cliente.getNome(), cliente.getCognome(), cliente.getEmail(), cliente.getTelefono());
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
