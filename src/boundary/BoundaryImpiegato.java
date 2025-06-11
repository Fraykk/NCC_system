package boundary;

import control.GestioneNoleggio;
import entity.EntityAutovettura;
import entity.EntityConducente;
import entity.EntityPrenotazione;
import entity.EntityPreventivo;
import java.util.List;
import java.util.Scanner;

public class BoundaryImpiegato {
    // definisco lo scanner proprio della classe BoundaryImpiegato
    private static Scanner scanner = new Scanner(System.in);

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
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Riprova.");
                continue;
            }

            switch (scelta){
                case OPTION_PREPARA_PREVENTIVO:
                    scegliPreventivo();
                    break;
                case OPTION_ASSEGNA_DATI_NOLEGGIO:
                    scegliPrenotazione();
                    break;
                case OPTION_EXIT:
                    System.out.println("Uscita dal menu impiegato");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    public static void preparaPreventivo(EntityPreventivo eP, GestioneNoleggio gN){
        System.out.println("\n--- Preparazione Preventivo per ID: " + eP.getId() + " ---");
        boolean inputValido = false;
        int costo = 0;

        try {
            if(gN.isAutovetturaDisponibile()){
                System.out.println("Autovetture disponibile");

                while (!inputValido) {
                    System.out.print("Inserisci il costo: ");
                    String input = scanner.nextLine();
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
                idScelto = Long.parseLong(scanner.nextLine());
                for(EntityPreventivo eP : list){
                    if(eP.getId() == idScelto){
                        correctID = true;
                        preparaPreventivo(eP, gN);
                        break;
                    }
                }
                if(!correctID)
                    System.out.println("Opzione non valida, riprova");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void scegliPrenotazione(){
        List<EntityPrenotazione> list = null;
        GestioneNoleggio gN = GestioneNoleggio.getInstance(); 
                
        System.out.println("--- Lista Prenotazioni ---");
                
        try {
            list = gN.ottieniPrenotazioni();

            if (list.isEmpty()) {
                System.out.println("Nessuna prenotazione disponibile.");
                return;
            }

            System.out.printf("%-5s %-15s %-15s %-20s %-12s %-8s%n",
                    "ID", "User", "Partenza", "Destinazione", "Data", "Ora");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            for (EntityPrenotazione eP : list) {
                System.out.printf("%-5d %-15s %-15s %-20s %-12s %-8s%n",
                                eP.getId(),
                                eP.getPreventivo().getCliente().getUser(),
                                eP.getPreventivo().getPartenza(),
                                eP.getPreventivo().getDestinazione(),
                                eP.getPreventivo().getData() != null ? eP.getPreventivo().getData().toString() : "N/D",
                                eP.getPreventivo().getOra() != null ? eP.getPreventivo().getOra().toString() : "N/D"
                                );
            }

        } catch (Exception e) {
            System.err.println("Errore durante il recupero o la visualizzazione dei preventivi: " + e.getMessage());
            return;
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println("Scegli un preventivo indicando l'ID");
        
        long idScelto;
        boolean correctID = false;

        try{
            while(!correctID){
                idScelto = Long.parseLong(scanner.nextLine());
                for(EntityPrenotazione eP : list){
                    if(eP.getId() == idScelto){
                        correctID = true;
                        assegnaDatiNoleggio(eP, gN);
                        break;
                    }
                }
                if(!correctID)
                    System.out.println("Opzione non valida, riprova");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void assegnaDatiNoleggio(EntityPrenotazione eP, GestioneNoleggio gN){
        System.out.println("\n--- Assegnazione dati per noleggio con ID: " + eP.getId() + " ---");
        boolean inputValido = false;
        int costo = 0;
        
        try {
            
            if(!gN.isAutovetturaDisponibile()){
                System.out.println("Errore: non sono al momento disponibili autovetture");
                return;
            }

            List<EntityAutovettura> list = gN.ottieniAutovetture();

            System.out.printf("%-10s %-15s %-8s %-20s%n",
                "Targa", "Modello", "Posti", "AnnoImmatricolazione");
            System.out.println("------------------------------------------------------------------");

            for (EntityAutovettura eA : list) {
                System.out.printf("%-10s %-15s %-8d %-20d%n",
                                eA.getTarga(),
                                eA.getModello(),
                                eA.getPosti(),
                                eA.getAnnoImmatricolazione()
                                );
            }

            inputValido = false;
            String targa;

            System.out.println("------------------------------------------------------------------");
            System.out.println();
            System.out.println("Scegli un'auto indicando la targa");

            while(!inputValido){
                targa = scanner.nextLine();
                for(EntityAutovettura eA : list){
                    if(eA.getTarga().equals(targa)){
                        inputValido = true;
                        eP.setAutovettura(eA);
                        break;
                    }
                }
                if(!inputValido)
                    System.out.println("Opzione non valida, riprova");
            }
            System.out.println("Auto aggiunta correttamente");

            List<EntityConducente> listC = gN.ottieniConducenti();

            System.out.printf("%-10s %-15s%n",
                "Nome", "Cognome");
            System.out.println("------------------------------------------------------------------");

            for (EntityConducente eC : listC) {
                System.out.printf("%-15s %-15s%n",
                                eC.getNome(),
                                eC.getCognome()
                                );
            }

            inputValido = false;
            String nome;

            System.out.println("------------------------------------------------------------------");
            System.out.println();
            System.out.println("Scegli un conducente indicando il nome");

            while(!inputValido){
                nome = scanner.nextLine();
                for(EntityConducente eC : listC){
                    if(eC.getNome().equals(nome)){
                        inputValido = true;
                        eP.setConducente(eC);
                        break;
                    }
                }
                if(!inputValido)
                    System.out.println("Opzione non valida, riprova");
            }

            gN.associaConducente(eP.getId(), eP.getConducente().getId());
            gN.associaAutovettura(eP.getId(), eP.getAutovettura().getTarga());

            System.out.println("Dati prenotazione aggiunti con successo");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
