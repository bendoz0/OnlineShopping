package democlothingstore;

import classi.cartmanagement.Products;
import classi.cartmanagement.ShoppingCart;
import classi.usermanagement.User;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // variabile start per la condizione dello switch
        String start;
        User objectUser = new User();
        do{
            System.out.print("\nBenvenuto su ZalandoCOPY\nMessaggio: Per effettuare lo shopping sul nostro sito è necessario registrarsi. :)\nVuoi registrarti? Yes/No\nSe sei gia registrato: LogIn/Exit\nRisposta: ");
            start = input.nextLine();
            switch(start.toLowerCase()){
                // caso per registrarsi come utente
                case "yes":
                    //richiamo della classe ReadingUserData, sono richiesti i dati per registrare l'utente
                    User.ReadingUserData();
                    // implementazione dell'interfaccia
                    String choiceSex;
                    while (true){
                        System.out.print("Vuoi inserire il sesso? Y/N ");
                        choiceSex = input.nextLine();
                        if(choiceSex.equalsIgnoreCase("Y")){
                            System.out.print("Inserisci sesso (maschio/femmina): ");
                            String type = input.nextLine();
                            if (type.equals("maschio") || type.equals("femmina")){
                                objectUser.setSex(type);
                                break;
                            }
                            else{
                                System.out.println("Genere inserito non corretto");
                            }
                        }else if (choiceSex.equalsIgnoreCase("N")){
                            break;
                        }
                    }
                    break;

                // in questo case se start == "out" non mettendo il break esegue il case "no", per terminare il programma
                case "exit":
                case "no":
                    System.out.println("È stato bello averti qui sul nostro sito. Torna presto a trovarci!!");
                    break;

                // questo case serve per loggarsi ed accedere poi allo shop
                case "login":
                    // richiamo del metodo LoginVerification per inserire email e password dell'utente appena registrato e verificare che si è registrato
                    boolean access = objectUser.LoginVerification();
                    if (access){
                        String category;
                        // accesso al sistema di shopping
                        do {
                            System.out.print("\nMenù abbigliamento, inserisci il numero della category.\n1 - Shopping\n2 - Carrello\n3 - LogOut\nOpzione Numero: ");
                            category = input.nextLine();
                            switch (category) {
                                case "1":
                                    // richiamo del metodo MenuShopping per stampare tutti gli articoli presenti nel file Magazzino.txt
                                    Products.shoppingMenu();
                                    // richiamo del metodo sceltaArticolo per definire se si vuole oppure no comprare un articolo e in caso quanti articoli di quel tipo
                                    Products.itemChoice();
                                    break;

                                case "2":
                                    ShoppingCart objectShoppingCart = new ShoppingCart();
                                    // richiamo del metodo printCart per stampare lo stato del Carrello con gli articoli salvati
                                    boolean value = objectShoppingCart.printCart();
                                    if (!value) {
                                        String option;
                                        do {
                                            System.out.print("\nOpzioni:\t1 - Compra\t2 - GoBack\nOpzione Numero: ");
                                            option = input.nextLine();
                                            switch (option) {
                                                case "1":
                                                    //richiamo del metodo per inserire i dati dell'utente per la verifica per poterli comprare
                                                    boolean verify = objectUser.LoginAutentication();
                                                    if (verify) {
                                                        //svuoto il carrello dell'utente
                                                        objectShoppingCart.emptyCart();
                                                    }
                                                    break;
                                                case "2":
                                                    break;
                                                default:
                                                    System.out.println("Opzione non valida. Riprova.");
                                            }
                                        } while (!option.equalsIgnoreCase("2"));
                                    }
                                    break;

                                case "3":
                                    System.out.println("Ritrono alla pagina di login.");
                                    break;
                            }
                        }while(!category.equalsIgnoreCase("3"));
                    }
                    break;

                default:
                    System.out.println("Opzione Inesistente. Riprova.");
                    break;
            }
        }while(!start.equalsIgnoreCase("no") && !start.equalsIgnoreCase("exit"));
    }
}