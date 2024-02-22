package classi.cartmanagement;

import classi.usermanagement.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe rappresenta un carrello per gli acquisti.
 * @author Jacopo Bendotti
 */
public class ShoppingCart  extends Astract {
    private static double sum;
    private static HashMap<String, ArrayList<Product>> usersCart = new HashMap<>();
//----------------------------------------------------------------------------------------------------------------------
    //Constructor
    /**
     * Costruttore vuoto.
     * usato per creare riferirsi a questa classe.
     */
    public ShoppingCart() {
    }
//----------------------------------------------------------------------------------------------------------------------
    //default methods
    /**
     * Metodo equals non usato
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    /**
     * Metodo clone non usato
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Metodo toString non usato
     */
    @Override
    public String toString() {
        return super.toString();
    }
//----------------------------------------------------------------------------------------------------------------------
//metodo di istanza per la stampa del contenuto del carrello
    /**
     * metodo ereditato dalla classe astratta Astract
     * Stampa i dettagli dei prodotti nel carrello.
     * @return {@code true} se il carrello è vuoto, altrimenti {@code false}.
     */
    @Override
    public boolean printCart(){
        User emailKey = new User();
        String email = emailKey.getKey();
        if (usersCart.get(email).isEmpty()) {
            System.out.println("\nLa lista del carrello è vuota.");
            return true;
        }
        else {
            for (Map.Entry <String, ArrayList<Product>> entry : usersCart.entrySet()){
                if (entry.getKey().equals(email)) {
                    System.out.println("\nCarrello:\n");
                    for(Product elem : entry.getValue()){
                        System.out.println(elem.getCategory()+" "+elem.getBrand()+" - codice articolo: "+elem.getCode()+" - quantità: "+elem.getQuantity()+" - Prezzo Totale dell'articolo: "+(elem.getPrice()*elem.getQuantity()+"€"));
                        sum += elem.getPrice() * elem.getQuantity();
                    }
                }
            }
            String formattedValue = String.format("%.2f", sum);
            System.out.println("Prezzo TOTALE: " + formattedValue + "€");
            sum = 0;
            return false;
        }
    }
    //metodo di istanza per la verifica dello stato del carrello
    /**
     * metodo d'istanza che verifica sulla base dell'utente che ha accesso se il suo carrello è vuoto o no.
     * Se contiene qualcosa viene pulito, per simulare che ha comprato gli articoli.
     */
    public void emptyCart(){
        User emailKey = new User();
        String email = emailKey.getKey();
        if (usersCart.containsKey(email)) {
            if(usersCart.get(email).isEmpty()){
                System.out.println("Il carrello è vuoto non c'è nulla da comprare");
            }else{
                System.out.println("\nGrazie mille per aver acquistato su ZalandoCOPY.\nLa consegna del tuo ordine è prevista tra 5 giorni lavorativi a partire da oggi.");
                usersCart.get(email).clear();
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    //metodo statico per la ricerca del articolo scelto dall'utente
    /**
     * Trova un articolo nel catalogo in base al codice specificato e aggiunge la quantità specifica al carrello.
     * @param itemCode Il codice dell'articolo da cercare.
     * @param numberOfArticle La quantità di articoli da aggiungere al carrello.
     */
    public static void findArticle(String itemCode, int numberOfArticle){
        Products objProducts = new Products();
        ArrayList<Product> dataFromProducts = objProducts.getDati();
        for(Product p : dataFromProducts){
            if (p.getCode().equals(itemCode)){
                ShoppingCart.cartManagement(p, numberOfArticle);
                break;
            }
        }
    }
    //metodo statico per aggiungere l'articolo dell'utente all'carrello
    /**
     * Gestisce l'aggiunta di un prodotto al carrello.
     * @param p Il prodotto da aggiungere al carrello.
     * @param numberOfArticle La quantità di articoli da aggiungere.
     */
    public static void cartManagement(Product p, int numberOfArticle){
        p.setQuantity(numberOfArticle);
        String email = User.key;
        if (usersCart.containsKey(email)) {
            usersCart.get(email).add(p);
        }
    }
    //metodo statico per inizzializzare il carrello per ogni utente che si registra
    /**
     * metodo statico che crea un carrello apposta per ogni utente.
     */
    public static void fillMap (String mail){
        usersCart.putIfAbsent(mail, new ArrayList<Product>());
    }
}
