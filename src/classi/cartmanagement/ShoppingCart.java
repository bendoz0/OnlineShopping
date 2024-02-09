package classi.cartmanagement;

import java.util.ArrayList;

/**
 * Questa classe rappresenta un carrello per gli acquisti.
 * @author Jacopo Bendotti
 */
public class ShoppingCart  extends Astract {
    private static double sum;
    private static ArrayList<Product> cart = new ArrayList<>();

    /**
     * Restituisce la lista dei prodotti nel carrello.
     *
     * @return La lista dei prodotti nel carrello.
     */
    public ArrayList<Product> getCart() {return cart;}
    /**
     * Imposta la lista dei prodotti nel carrello.
     *
     * @param cart La nuova lista dei prodotti nel carrello.
     */
    public void setCart(ArrayList<Product> cart) {
        ShoppingCart.cart = cart;
    }

    /**
     * metodo ereditato dalla classe astratta Astract
     * Stampa i dettagli dei prodotti nel carrello.
     * @return `true` se il carrello è vuoto, altrimenti `false`.
     */
    @Override
    public boolean printCart(){
        if (cart.isEmpty()) {
            System.out.println("\nLa lista del carrello è vuota.");
            return true;
        }else {
            System.out.println("\nCarrello:\n");
            int c = 0;
            for (Product elem : cart) {
                //System.out.println(elem);
                System.out.println(elem.getCategory()+" "+elem.getBrand()+" - codice articolo: "+elem.getCode()+" - quantità: "+elem.getQuantity()+" - Prezzo Totale dell'articolo: "+(elem.getPrice()*elem.getQuantity()+"€"));
                sum += elem.getPrice() * elem.getQuantity();
                c++;
                if(c == cart.size()){
                    break;
                }
            }
            String formattedValue = String.format("%.2f", sum);
            System.out.println("Prezzo TOTALE: "+ formattedValue +"€");
            //metto sempre a 0 la varibile sum perchè se chiudo il carrello e poi lo riapro sum ricalcola il totale aggiungendolo a quello di prima
            sum = 0;
            return false;
        }
    }

    /**
     * Trova un articolo nel catalogo in base al codice specificato e aggiunge una quantità specifica al carrello.
     *
     * @param itemCode Il codice dell'articolo da cercare.
     * @param numberOfArticle La quantità di articoli da aggiungere al carrello.
     */
    public static void findArticle(String itemCode, int numberOfArticle){
        Products objProducts = new Products();
        ArrayList<Product> dataFromProducts = objProducts.getDati();
        //System.out.println(dataFromProducts);
        for(Product p : dataFromProducts){
            if (p.getCode().equals(itemCode)){
                //chiamare un altro metodo che tiene il registro degli articoli nel carrello con un arraylist, pasandogli "group" e la quantità.
                ShoppingCart.cartManagement(p, numberOfArticle);
                break;
            }
        }
        //System.out.println(gruppi);
    }

    /**
     * Gestisce l'aggiunta di un prodotto al carrello.
     *
     * @param p              Il prodotto da aggiungere al carrello.
     * @param numberOfArticle La quantità di articoli da aggiungere.
     */
    public static void cartManagement(Product p, int numberOfArticle){
        p.setQuantity(numberOfArticle);
        cart.add(p);
        //System.out.println(cart);
    }
}
