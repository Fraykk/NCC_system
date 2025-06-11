package boundary;

import java.util.Scanner;

public class MainMenu {
	// gestico l'istanza dello scanner direttamente nel main
	public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int scelta;
        boolean running = true;

        System.out.println("Benvenuto nell'applicazione di gestione noleggi!");

        while (running) {
            System.out.println("\n--- Menu Principale ---");
            System.out.println("1. Accedi come Cliente");
            System.out.println("2. Accedi come Impiegato");
            System.out.println("3. Esci dall'applicazione");
            System.out.print("Scegli un'opzione: ");

            try {
                scelta = Integer.parseInt(scanner.nextLine());

                switch (scelta) {
                    case 1:
                        BoundaryCliente.menuCliente();
                        break;
                    case 2:
                        BoundaryImpiegato.menuImpiegato();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Uscita dall'applicazione. Arrivederci!");
                        break;
                    default:
                        System.out.println("Scelta non valida. Per favore, inserisci un numero tra 1 e 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Per favore, inserisci un numero intero.");
            }
        }

        // gestico la chiusura dello scanner prima di chiudere l'app
        if (scanner != null) {
            scanner.close();
            System.out.println("Risorse liberate: Scanner chiuso.");
        }
    }
}