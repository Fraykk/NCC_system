package boundary;

import control.GestioneNoleggio;
import exception.OperationException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BoundaryCliente {

    public static void menuCliente() {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. RichiediPreventivo");
            System.out.println("2. LogIn - SignIn");
            System.out.print("Scegli un'opzione (1-2): ");
            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Riprova.");
                continue;
            }
            switch (scelta) {
                case 1:
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

                        while (!inputValido) {
                            System.out.print("Inserisci partenza: ");
                            partenza = scanner.nextLine();
                            if (partenza.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$")) {
                                inputValido = true;
                            } else {
                                System.out.println("La partenza non può contenere numeri o caratteri speciali. Riprova.");
                            }
                        }

                        inputValido = false;
                        while (!inputValido) {
                            System.out.print("Inserisci destinazione: ");
                            destinazione = scanner.nextLine();
                            if (destinazione.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$")) {
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
                                System.out.println("Errore nell'acquisizione della data, riprovare..");
                                System.out.println();
                            }
                        }

                        System.out.println("Inserimento dati personali");

                        inputValido = false;
                        while (!inputValido) {
                            System.out.println("Inserisci nome");

                            nome = scanner.nextLine();

                            if (nome.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$")) {
                                inputValido = true;
                            } else {
                                System.out.println("Il nome non può contenere numeri o caratteri speciali. Riprova.");
                            }
			            }

                        inputValido = false;
                        while (!inputValido) {
                            System.out.println("Inserisci nome");

                            cognome = scanner.nextLine();

                            if (cognome.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]+$")) {
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
                                System.out.println("Inserisci il numero di telefono");
                                telefono = Integer.parseInt(scanner.nextLine());
                                inputValido = true;
                            } catch (NumberFormatException nE) {
                                System.out.println("Errore, inserire un numero valido");
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

                    break;

                    /* 
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
                    */
                case 2:
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
}