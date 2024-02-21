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
    /**
     * Verifica le credenziali di un utente se è registrato al sito.
     * @return access qunado le credenziali sono presenti nel registro quindi access == true.
     */
    boolean LoginVerification();
}
