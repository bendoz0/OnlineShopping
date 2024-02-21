package classi.cartmanagement;

import java.util.Objects;

/**
 * Questa classe rappresenta un prodotto disponibile per l'acquisto.
 * @author Jacopo Bendotti
 */
public class Product {
    private String category;
    private String brand;
    private double price;
    private String code;
    private int quantity;
//----------------------------------------------------------------------------------------------------------------------
    //Constructor
    /**
     * Costruttore vuoto.
     */
    public Product() {
    }
    /**
     * Costruttore per creare un nuovo prodotto.
     * @param category  La categoria del prodotto.
     * @param brand     Il marchio del prodotto.
     * @param price     Il prezzo del prodotto.
     * @param code      Il codice univoco del prodotto.
     * @param quantity  La quantità disponibile del prodotto.
     */
    public Product(String category, String brand, double price, String code, int quantity) {
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.code = code;
        this.quantity = quantity;
    }
//----------------------------------------------------------------------------------------------------------------------
    //default methods
    /**
     * Restituisce la categoria del prodotto.
     * @return La categoria del prodotto.
     */
    public String getCategory() {return category;}
    /**
     * Restituisce il marchio del prodotto.
     * @return Il marchio del prodotto.
     */
    public String getBrand() {return brand;}
    /**
     * Restituisce il prezzo del prodotto.
     * @return Il prezzo del prodotto.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Restituisce il codice univoco del prodotto.
     * @return Il codice del prodotto.
     */
    public String getCode() {
        return code;
    }
    /**
     * Restituisce la quantità disponibile del prodotto.
     * @return La quantità del prodotto.
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Imposta la quantità del prodotto.
     * @param quantity La nuova quantità del prodotto.
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}
    /**
     * Verifica se l'oggetto specificato è uguale a questo prodotto.
     * @param o L'oggetto da confrontare con questo prodotto.
     * @return `true` se l'oggetto è uguale a questo prodotto, altrimenti `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && quantity == product.quantity && Objects.equals(category, product.category) && Objects.equals(brand, product.brand) && Objects.equals(code, product.code);
    }
    /**
     * Crea una copia superficiale di questo prodotto.
     * @return La copia superficiale dell'oggetto.
     * @throws CloneNotSupportedException Se la clonazione non è supportata.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Restituisce una rappresentazione testuale di questo prodotto.
     * @return Una stringa che descrive il prodotto con i suoi attributi.
     */
    @Override
    public String toString() {
        return "Classi.CartManagement.Product{" +
                "category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
