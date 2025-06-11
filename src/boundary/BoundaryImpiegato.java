package boundary;

import control.GestioneNoleggio;
import entity.EntityPreventivo;
import java.util.List;

public class BoundaryImpiegato {
    // definisco delle costanti per le opzioni del menu
    private static final int OPTION_PREPARA_PREVENTIVO = 1;
    private static final int OPTION_ASSEGNA_DATI_NOLEGGIO = 2;
    private static final int OPTION_EXIT = 3;

    public static void menuImpiegato(){
        int scelta;

        while(true){
            System.out.println("\n--- Menu Impiegato ---");
            System.out.println(OPTION_PREPARA_PREVENTIVO + ". Prepara Preventivo");
            System.out.println(OPTION_ASSEGNA_DATI_NOLEGGIO + ". Assegna Dati Noleggio");
            System.out.println(OPTION_EXIT + ". Torna al menu principale");
            System.out.print("Scegli un'opzione (1-" + OPTION_EXIT + "): ");

            try {
                scelta = Integer.parseInt(MainMenu.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Riprova.");
                continue;
            }

            switch (scelta){
                case OPTION_PREPARA_PREVENTIVO:
                    scegliPreventivo();
                    break;
                case OPTION_ASSEGNA_DATI_NOLEGGIO:
                    //assegnaDatiNoleggio();
                    break;
                case OPTION_EXIT:
                    System.out.println("Uscita dal menu impiegato");
                    return;
                default:
                    System.out.println("Selezione non valida");
            }
        }
    }

    public static void preparaPreventivo(EntityPreventivo eP, GestioneNoleggio gN){
        System.out.println("\n--- Preparazione Preventivo per ID: " + eP.getId() + " ---");
        boolean inputValido = false;
        int costo = 0;

        try {
            if(gN.autovetturaDisponibile()){
                System.out.println("Autovetture disponibile");

                while (!inputValido) {
                    System.out.print("Inserisci il costo: ");
                    String input = MainMenu.scanner.next();
                    try {
                        costo = Integer.parseInt(input);
                        inputValido = true;
                    } catch (NumberFormatException ex) {
                        System.out.println("Valore non valido. Inserisci un numero intero.");
                    }
                }
            }else{
                System.out.println("Non sono al momento disponibili autovetture");
                gN.inviaAlternative();
            }
            eP.setCosto(costo);
            gN.inserisciCosto(eP);
            gN.associaCliente(eP);

            System.out.println("Preventivo preparato con successo");
            System.out.println("Invio mail con dati di noleggio all'indirizzo " + eP.getCliente().getEmail() + " in corso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void scegliPreventivo(){
        List<EntityPreventivo> list = null;
        GestioneNoleggio gN = GestioneNoleggio.getInstance(); 
                
        System.out.println("--- Lista Preventivi ---");
                
        try {
            list = gN.ottieniPreventivi();

            if (list.isEmpty()) {
                System.out.println("Nessun preventivo disponibile.");
                return;
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
            return;
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println("Scegli un preventivo in base all'ID");
        
        long idScelto;
        boolean correctID = false;

        try{
            while(!correctID){
                idScelto = Long.parseLong(MainMenu.scanner.nextLine());
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
