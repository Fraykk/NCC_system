package control;

import java.util.Scanner;
import boundary.BoundaryImpiegato;
import boundary.BoundaryCliente;

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
                    //BoundaryImpiegato.preparaPreventivo(email, costoDouble, data);
                    break;
                case 2:
                    BoundaryCliente.richiediPreventivo();
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