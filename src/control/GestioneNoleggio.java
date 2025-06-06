package control;

import java.util.Scanner;
import entity.EntityPreventivo;
import exception.*;

public class GestioneNoleggio {

    private static GestioneNoleggio gN = null;

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int scelta = 0;
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. PreparaPreventivo");
            System.out.println("2. RichiediPreventivo");
            System.out.println("3. Opzione 3");
            System.out.print("Scegli un'opzione (1-3): ");
            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Riprova.");
                continue;
            }
            switch (scelta) {
                case 1:
                    // BoundaryImpiegato.preparaPreventivo(email, costoDouble, data);
                    break;
                case 2:
                    EntityPreventivo eP = null;

                    System.out.print("Inserisci partenza: ");
                    String partenza = scanner.nextLine();
                    System.out.print("Inserisci destinazione: ");
                    String destinazione = scanner.nextLine();
                    System.out.print("Inserisci data: ");
                    String data = scanner.nextLine();

                    eP = new EntityPreventivo("4", partenza, destinazione, data);
                    try {
                        eP.creaPreventivo();
                    } catch (DBConnectionException e) {
                        System.out.println(e.getMessage());
                    } catch (DAOException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Errore generico: " + e.getMessage());
                    }
                    break;
                case 3:
                    // assegnaDatiNoleggio();
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
}