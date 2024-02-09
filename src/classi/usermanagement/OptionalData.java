package classi.usermanagement;

/**
 * Il metodo setSex consente di impostare il sesso di un'entità (ad esempio di un utente).
 * @author Jacopo Bendotti
 */
public interface OptionalData {
    /**
     * Imposta il sesso dell'entità.
     * @param ses Il valore del sesso da impostare (ad esempio, "maschio" o "femmina").
     */
    void setSex(String ses);
}
