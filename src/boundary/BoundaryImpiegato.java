package boundary;

import control.GestioneNoleggio;
import java.util.List;
import java.util.Scanner;
import entity.EntityPreventivo;

public class BoundaryImpiegato {
    public static void menuImpiegato(){
        Scanner scanner = new Scanner(System.in);
        int scelta;
        List<EntityPreventivo> list = null;
        long idScelto;
        boolean correctID = false;

        while(true){
            System.out.println("Menu:");
            System.out.println("1. PreparaPreventivo");
            System.out.println("2. Nulla");
            System.out.print("Scegli un'opzione (1-2): ");

            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Riprova.");
                continue;
            }

            if(scelta == 1){
                GestioneNoleggio gN = GestioneNoleggio.getInstance(); 
                
                System.out.println("--- Lista Preventivi ---");
                
                try {
                    list = gN.ottieniPreventivi();

                    if (list.isEmpty()) {
                        System.out.println("Nessun preventivo disponibile.");
                        continue;
                    }

                    System.out.printf("%-5s %-15s %-20s %-12s %-8s%n",
                          "ID", "Partenza", "Destinazione", "Data", "Ora");
                    System.out.println("------------------------------------------------------------------");

                    for (EntityPreventivo eP : list) {
                        System.out.printf("%-5d %-15s %-20s %-12s %-8s%n",
                                        eP.getId(),
                                        eP.getPartenza(),
                                        eP.getDestinazione(),
                                        eP.getData() != null ? eP.getData().toString() : "N/D",
                                        eP.getOra() != null ? eP.getOra().toString() : "N/D"
                                        );
                    }

                } catch (Exception e) {
                    System.err.println("Errore durante il recupero o la visualizzazione dei preventivi: " + e.getMessage());
                    continue;
                }
                System.out.println("------------------------------------------------------------------");
                System.out.println();
                System.out.println("Scegli un preventivo in base all'ID");
                
                try{
                    while(!correctID){
                        idScelto = Long.parseLong(scanner.nextLine());
                        for(EntityPreventivo eP : list){
                            if(eP.getId() == idScelto){
                                correctID = true;
                                preparaPreventivo(eP, gN);
                                break;
                            }else{
                                System.out.println("Opzione non valida, riprova");
                        
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void preparaPreventivo(EntityPreventivo eP, GestioneNoleggio gN){
        Scanner scanner = new Scanner(System.in);
        boolean inputValido = false;
        int costo = 0;

        try {
            if(gN.autovetturaDisponibile()){
                System.out.println("Autovetture disponibile");

                while (!inputValido) {
                    System.out.print("Inserisci il costo: ");
                    String input = scanner.next();
                    try {
                        costo = Integer.parseInt(input);
                        inputValido = true;
                    } catch (NumberFormatException ex) {
                        System.out.println("Valore non valido. Inserisci un numero intero.");
                    }
                }
            }else{
                System.out.println("Non sono al momento disponibili autovetture");
                System.out.println("Invio alternative via mail");
            }
            eP.setCosto(costo);
            gN.inserisciCosto(eP);
            gN.associaCliente(eP);

            System.out.println("Invio mail con dati di noleggio all'indirizzo " + eP.getCliente().getEmail() + " in corso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
