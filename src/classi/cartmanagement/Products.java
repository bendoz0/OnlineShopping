package classi.cartmanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Rappresenta una raccolta di prodotti.
 * Questa classe gestisce i dati di prodotto e fornisce metodi per la lettura da un file.
 * @author Jacopo Bendotti
 */
public class Products {
    private static ArrayList<Product> data = new ArrayList<>();
//----------------------------------------------------------------------------------------------------------------------
    //Constructor
    /**
     * Costruttore vuoto.
     */
    public Products() {
    }
//----------------------------------------------------------------------------------------------------------------------
    //Default Methods
    /**
     * Restituisce l'elenco dei prodotti in data.
     * @return L'elenco dei prodotti memorizzati in data.
     */
    public ArrayList<Product> getDati() {
        return data;
    }
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
    //metodo statico per lettura dal file di testo
    /**
     * Legge i dati dei prodotti dal file Storage.txt e popola la collezione.
     * Ogni riga del file rappresenta un prodotto con campi separati da '/'.
     * Il formato è: categoria/marca/prezzo/codice/quantità.
     * @throws IOException se si verifica un errore durante la lettura del file.
     */
    public static void shoppingMenu(){
        String[] element;
        String line = null;
        Product product;
        try {
            FileReader fileReader = new FileReader("src/classi/cartmanagement/Storage.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                element = line.split("/");
                String category = element[0];
                String brand = element[1];
                double price = Double.parseDouble(element[2]);
                String code = element[3];
                int quantity = Integer.parseInt(element[4]);
                product = new Product(category, brand, price, code, quantity);
                data.add(product);
                System.out.println("\n"+element[0]+", Marca: "+element[1]+", Prezzo: "+element[2]+", Codice Articolo: "+element[3]+", Quantità: "+element[4]);
            }
            bufferedReader.close();
        }catch(IOException ex) {
            System.out.println("Errore nella lettura del file 'Magazzino.txt'");
        }
    }
    //metodo statico per la scelta dell'articolo da aggiungere al carrello
    /**
     * Consente all'utente di aggiungere articoli al carrello.
     * Legge il codice articolo dall'input e li convalida rispetto ai prodotti disponibili.
     * Se il codice articolo è valido e la quantità richiesta è disponibile, aggiunge l'articolo al carrello.
     * In caso contrario, vengono generate eccezioni appropriate.
     * @throws IllegalArgumentException se il codice articolo non è valido o la quantità richiesta è insufficiente.
     */
    public static void itemChoice(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.print("\nSe desideri aggiungere al carrello qualche articolo inserisci il CODICE dell'articolo.\nAltrimenti inserisci '!'\nRisposta: ");
            String itemCode;
            boolean found = false, noBuy = true;
            while (true){
                itemCode = input.nextLine();
                if(itemCode.equals("!")){
                    noBuy = false;
                    break;
                }
                for(Product p: data){
                    if(itemCode.equalsIgnoreCase(p.getCode())){
                        found = true;
                        break;
                    }
                }
                if (found){
                    break;
                }
                System.out.println("Inserire codice articolo corretto");
            }
            try {
                if(noBuy){
                    if (!data.isEmpty()) {
                        System.out.println("Quanti ne vuoi di questo articolo? ");
                        int numberOfArticle;
                        if (input.hasNextInt()) {
                            numberOfArticle = input.nextInt();
                            input.nextLine();
                            if (numberOfArticle > 0) {
                                for (Product p : data) {
                                    if (p.getCode().equals(itemCode)) {
                                        if (numberOfArticle <= p.getQuantity()) {
                                            letturaRiscrittura(itemCode, numberOfArticle);
                                            ShoppingCart.findArticle(itemCode, numberOfArticle);
                                            break;
                                        } else {
                                            throw new IllegalArgumentException("Articoli insufficenti. Riporva.");
                                        }
                                    }
                                }
                            } else {
                                throw new IllegalArgumentException("numero degli articoli Impossibile");
                            }
                        } else {
                            throw new IllegalArgumentException("Inserisci un numero");
                        }
                        break;
                    } else {
                        throw new IllegalArgumentException("Codice articolo inserito Sbagliato. Riporva.");
                    }
                }
                break;
            }catch(IllegalArgumentException e){
                input.nextLine();
                System.out.println(e.getMessage());
            }
        }
    }
    //metodo statico per la riscrittura del file di testo sottraendo la qunatità
    /**
     * Legge i dati dal file, aggiorna la quantità di un articolo specifico e riscrive il file.
     * @param itemCode Il codice dell'articolo da cercare nel file.
     * @param numberOfArticle Il numero di articoli da sottrarre alla quantità originale.
     * @throws IOException se si verifica un errore durante la lettura o la scrittura del file.
     */
    public static void letturaRiscrittura(String itemCode, int numberOfArticle){
        try {
            // Legge il file e memorizza le lines in una lista
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/classi/cartmanagement/Storage.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            // Trova l'indice della line
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(itemCode)) {
                    // Estrai il valore quantita dalla line
                    String[] value = lines.get(i).split("/");
                    int originalValue = Integer.parseInt(value[value.length - 1].trim());

                    // Modifica la quantita sottraendo il numero di articoli
                    int newValue = originalValue - numberOfArticle;
                    value[value.length - 1] = String.valueOf(newValue);

                    // Aggiorna la line con la nuova quantita
                    lines.set(i, String.join("/", value));
                    break;
                }
            }
            // Riscrivi il file con la modifica
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/classi/cartmanagement/Storage.txt"));
            for (String changedLine : lines) {
                writer.write(changedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}