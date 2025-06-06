package boundary;

import entity.EntityPreventivo;
import database.PreventivoDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class BoundaryCliente {
    
    public static void richiediPreventivo() {
        EntityPreventivo eP = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci partenza: ");
        String partenza = scanner.nextLine();
        System.out.print("Inserisci costo: ");
        String destinazione = scanner.nextLine();
        System.out.print("Inserisci data: ");
        String data = scanner.nextLine();

        eP = new EntityPreventivo("4", partenza, destinazione, data);

        try{
            PreventivoDAO.inserisciPreventivo(eP);
        }catch(SQLException e){
            System.out.println("Errore");
        }
    }
}