package control;

import database.AutovetturaDAO;
import database.ClienteDAO;
import database.ConducenteDAO;
import database.PrenotazioneDAO;
import database.PreventivoDAO;
import entity.EntityAutovettura;
import entity.EntityCliente;
import entity.EntityConducente;
import entity.EntityPrenotazione;
import entity.EntityPreventivo;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class GestioneNoleggio {

    private static GestioneNoleggio gN = null;

    public static GestioneNoleggio getInstance(){
        if(gN == null)
            gN = new GestioneNoleggio();

        return gN;
    }

    public void aggiungiPreventivo( String partenza, String destinazione, Date data, Time ora, String nome, String cognome, String email, int telefono) throws OperationException{
        
        long idCliente = -1;
        long idPreventivo = -1;
        
        System.out.println("GestioneNoleggio");

        try {
            EntityCliente eC = new EntityCliente(nome, cognome, email, telefono);
            idCliente = ClienteDAO.inserisciCliente(eC);
            eC.setId(idCliente);

            EntityPreventivo eP = new EntityPreventivo(partenza, destinazione, data, ora, eC);
            idPreventivo = PreventivoDAO.inserisciPreventivo(eP);
            eP.setId(idPreventivo);
        }catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
    }

    public List<EntityPreventivo> ottieniPreventivi() throws OperationException{
        List<EntityPreventivo> list = null;

        try {
            list = PreventivoDAO.leggiPreventivi();
        } catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

        return list;
    }

    public List<EntityPrenotazione> ottieniPrenotazioni() throws OperationException{
        List<EntityPrenotazione> list = null;

        try {
            list = PrenotazioneDAO.leggiPrenotazioni();
        } catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

        return list;
    }

    public List<EntityAutovettura> ottieniAutovetture() throws OperationException {
        List<EntityAutovettura> eA = null;

        try {
            eA = AutovetturaDAO.getAutovettureDisponibili();
        } catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

        return eA;
    }

    public List<EntityConducente> ottieniConducenti() throws OperationException{
        List<EntityConducente> eC = null;

        try {
            eC = ConducenteDAO.getConducenti();
        } catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

        return eC;
    }

    public boolean isAutovetturaDisponibile() throws OperationException {
        boolean disponibile = false;

        try {
            disponibile = AutovetturaDAO.isDisponibile();
        } catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

        return disponibile;
    }

    public void inserisciCosto(EntityPreventivo eP) throws OperationException{
        try {
            PreventivoDAO.inserisciCosto(eP.getId(), eP.getCosto());
        } catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
    }

    public void associaCliente(EntityPreventivo eP) throws OperationException{
        EntityCliente eC = null;

        try {
            eC = ClienteDAO.getCliente(eP);
        }catch(DBConnectionException dbEx){
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

        eP.setCliente(eC);
    }

    public void inviaAlternative(){
        System.out.println("Invio alternative via mail");
    }

    public void associaConducente(long idP, long idC) throws OperationException{
        try{
            PrenotazioneDAO.setConducente(idP, idC);
        }catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
    }

    public void associaAutovettura(long idP, String targa) throws OperationException{
        try{
            PrenotazioneDAO.setAutovettura(idP, targa);
        }catch(DBConnectionException dbEx) {			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
    }
}