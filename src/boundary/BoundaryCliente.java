package boundary;

import control.GestioneNoleggio;
import exception.OperationException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BoundaryCliente {
    // definisco lo scanner proprio della classe BoundaryCliente
    private static Scanner scanner = new Scanner(System.in);

    // definisco delle costanti per le opzioni del menu
    private static final int OPTION_RICHIEDI_PREVENTIVO = 1;
    private static final int OPTION_ACCESS = 2;
    private static final int OPTION_EXIT = 3;

    public static void menuCliente() {
        int scelta;
        while (true) {
            System.out.println("\n--- Menu Cliente ---");
            System.out.println(OPTION_RICHIEDI_PREVENTIVO + ". Richiedi Preventivo");
            System.out.println(OPTION_ACCESS + ". LogIn - SignIn");
            System.out.println(OPTION_EXIT + ". Torna al menu principale");
            System.out.print("Scegli un'opzione (1-" + OPTION_EXIT + "): ");
            
            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Riprova.");
                continue;
            }
            switch (scelta) {
                case OPTION_RICHIEDI_PREVENTIVO:
                    richiediPreventivo();
                    break;
                case OPTION_ACCESS:
                    System.out.println("Funzione non ancora implementata");
                    break;
                case OPTION_EXIT:
                    System.out.println("Uscita dal menu impiegato");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void richiediPreventivo(){
        try{
            String partenza = null;
            String destinazione = null;
            Date data = null;
            Time orario = null;
            String nome = null;
            String cognome = null;
            String email = null;
            int telefono = 0;        
            boolean inputValido = false;
            GestioneNoleggio noleggio = GestioneNoleggio.getInstance();
            final int CIFRE_TELEFONO = 10;

            while (!inputValido) {
                System.out.print("Inserisci partenza: ");
                partenza = scanner.nextLine();
                if (partenza.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$") && partenza.length() < 100) {
                    inputValido = true;
                } else {
                    System.out.println("La partenza non può contenere numeri o caratteri speciali. Riprova.");
                }
            }

            inputValido = false;
            while (!inputValido) {
                System.out.print("Inserisci destinazione: ");
                destinazione = scanner.nextLine();
                if (destinazione.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$") && destinazione.length() < 100) {
                    inputValido = true;
                } else {
                    System.out.println("La destinazione non può contenere numeri o caratteri speciali. Riprova.");
                }
            }
            
            inputValido = false;
            while (!inputValido) {
                try {
                    System.out.println("Inserisci la data (aaaa-MM-gg)");
                    String dataTemp = scanner.nextLine();
                    data = Date.valueOf(dataTemp);

                    System.out.println("Inserisci l'orario (HH:mm)");
                    String orarioTemp = scanner.nextLine();
                    orario = new Time(new SimpleDateFormat("HH:mm").parse(orarioTemp).getTime());
                    
                    inputValido = true;
                } catch (IllegalArgumentException | ParseException iE) {
                    System.out.println("Errore nell'acquisizione della data o dell'ora, riprovare..");
                    System.out.println();
                }
            }

            System.out.println("Inserimento dati personali");

            inputValido = false;
            while (!inputValido) {
                System.out.println("Inserisci nome");

                nome = scanner.nextLine();

                if (nome.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$") && nome.length() < 100) {
                    inputValido = true;
                } else {
                    System.out.println("Il nome non può contenere numeri o caratteri speciali. Riprova.");
                }
            }

            inputValido = false;
            while (!inputValido) {
                System.out.println("Inserisci cognome");

                cognome = scanner.nextLine();

                if (cognome.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$") && cognome.length() < 100) {
                    inputValido = true;
                } else {
                    System.out.println("Il cognome non può contenere numeri o caratteri speciali. Riprova.");
                }
            }
            
            inputValido = false;
            while (!inputValido) {
                System.out.println("Inserisci la email");

                email = scanner.nextLine();

                if (email.contains("@") && email.contains(".")) {
                    inputValido = true;
                } else {
                    System.out.println("Email non valida..");
                }
            }

            inputValido = false;
            while (!inputValido) {
                try {
                    System.out.println("Inserisci il numero di telefono (10 cifre):");
                    String input = scanner.nextLine(); 

                    if (input.length() != CIFRE_TELEFONO) { 
                        telefono = Integer.parseInt(input); 
                        inputValido = true;
                        System.out.println("Numero di telefono inserito: " + telefono);
                    } else {
                        System.out.println("Errore: Inserire esattamente 10 cifre numeriche.");
                        System.out.println();
                    }
                    
                } catch (NumberFormatException nE) {
                    System.out.println("Errore: Inserire un numero valido.");
                    System.out.println();
                }
            }
            noleggio.aggiungiPreventivo(partenza, destinazione, data, orario, nome, cognome, email, telefono);
        } catch (OperationException ope){
            System.out.println(ope);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Unexpected exception, riprovare..");
            System.out.println();
        }
    }
}