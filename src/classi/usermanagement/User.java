package classi.usermanagement;

import java.util.*;

/**
 * Questa classe rappresenta un utente.
 * @author Jacopo Bendotti
 */
public class User implements OptionalData {
    private static ArrayList<User> usersList = new ArrayList<>();
    private String name;
    private String surname;
    private String email;
    private String password;

    //Constructor
    /**
     * Costruttore vuoto.
     */
    public User(){
        name = "";
        surname = "";
        email = "";
        password = "";
    }
    /**
     * Costruttore con parametri.
     * @param name Il name dell'utente.
     * @param surname Il surname dell'utente.
     * @param email L'indirizzo email dell'utente.
     * @param password La password dell'utente.
     */
    public User(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    //Default Methods
    /**
     * Verifica se due oggetti Classi.UserManagement.User sono uguali.
     * @param o L'oggetto da confrontare.
     * @return True se gli oggetti sono uguali, altrimenti False.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }
    /**
     * Restituisce una rappresentazione testuale dell'oggetto Classi.UserManagement.User.
     * @return La stringa che rappresenta l'oggetto Classi.UserManagement.User.
     */
    @Override
    public String toString() {
        return "\nClassi.UserManagement.Classi.UserManagement.User {" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "}";
    }
    /**
     * Crea una copia dell'oggetto Classi.UserManagement.User.
     * @return La copia dell'oggetto Classi.UserManagement.User.
     * @throws CloneNotSupportedException Se la clonazione non è supportata.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //metodo di istanza per verificare il login
    /**
     * Verifica se l'utente può effettuare l'accesso.
     * @return True se l'accesso è consentito, altrimenti False.
     */
    public boolean LoginVerification(){
        Scanner input = new Scanner(System.in);
        String email, password;
        boolean access = false;
        System.out.print("E-mail: ");
        email = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        try {
            for (User use : usersList) {
                if (use.email.equals(email) && use.password.equals(password)) {
                    access = true;
                    break;
                }
            }
            if (!access){
                throw new IllegalArgumentException("ERRORE. E-mail o Password sbagliati.");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return access;
    }

    //metodo di istanza per verificare l'autnticazione dello user per comprare gli articoli nel carrello
    /**
     * Verifica se l'utente può effettuare l'accesso.
     * @return True se l'accesso è consentito, altrimenti False.
     */
    public boolean LoginAutentication(){
        Scanner input = new Scanner(System.in);
        String email, password;
        boolean verified = false;
        System.out.print("Nome: ");
        name = input.nextLine();
        System.out.print("Cognome: ");
        surname = input.nextLine();
        System.out.print("E-mail: ");
        email = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        try {
            for (User use : usersList) {
                if (use.name.equals(name) && use.surname.equals(surname) && use.email.equals(email) && use.password.equals(password)) {
                    verified = true;
                    System.out.println("\nGrazie mille "+name+" per aver acquistato su ZalandoCOPY.\nLa consegna del tuo ordine è prevista tra 5 giorni lavorativi a partire da oggi.");
                    break;
                }
            }
            if (!verified){
                throw new IllegalArgumentException("ERRORE. Nome o Cognome o E-mail o Password sbagliati.");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return verified;
    }
    //----------------------------------------------------------------------------------------------------------------------
    //metodo statico chiamato nel main, usato per leggere i dati dell'utente da input
    /**
     * Legge i dati utente dall'input e crea un nuovo oggetto Utente.
     * Convalida l'input dell'utente per nome, cognome, e-mail e password.
     * Se un input non è valido, vengono generate le eccezioni appropriate.
     *
     * @throws IllegalArgumentException se i dati di input non sono validi.
     */
    public static void ReadingUserData(){
        Scanner input = new Scanner(System.in);
        String name="", surname="", email="", password="";
        boolean allOk = false;
        while(!allOk) {
            try {
                System.out.print("Nome: ");
                name = input.nextLine();
                if(name.matches("[a-zA-Z]+")){
                    allOk=true;
                }else{
                    allOk=false;
                    throw new IllegalArgumentException("Nome inserito non valido. Si prega di rinserire i dati.");
                }
                System.out.print("Cognome: ");
                surname = input.nextLine();
                if(surname.matches("[a-zA-Z]+")){
                    allOk=true;
                }else{
                    allOk=false;
                    throw new IllegalArgumentException("Cognome inserito non valido. Si prega di rinserire i dati.");
                }
                System.out.print("E-mail: ");
                email = input.nextLine();
                EmailValidator validator = new EmailValidator();
                if(validator.validate(email)){
                    allOk=true;
                }else{
                    allOk=false;
                    throw new IllegalArgumentException("email errata.");
                }
                System.out.print("Password: ");
                password = input.nextLine();
                if(password.length()>=8){
                    allOk=true;
                }else{
                    allOk=false;
                    throw new IllegalArgumentException("Password inserita troppo corta (minimo 8 caratteri). Si prega di rinserire i dati.");
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Registrazione avvenuta con successo.");
        User newUser = new User(name, surname, email, password);
        UserDataRegister(newUser);
    }

    //metodo statico usato per creare una lista di tutti i dati di ogni utente
    /**
     * Aggiunge un nuovo utente all'elenco degli utenti.
     *
     * @param newUser L'oggetto Classi.UserManagement.User da aggiungere.
     */
    public static void UserDataRegister(User newUser){
        usersList.add(newUser);
        //System.out.println(usersList.toString());
    }

    //Metodo ereditato dalla classe interfaccia Classi.UserManagement.Classi.UserManagement.OptionalData
    /**
     * Imposta il sesso dell'utente.
     *
     * @param ses Il valore del sesso da impostare (ad esempio, "maschio" o "femmina").
     */
    @Override
    public void setSex(String ses) {
    }
}