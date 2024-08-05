package com.geplatform.geviews.clients;



import java.util.List;

public interface MicroserviceClient {



    /**
     * Invia una richiesta POST a un altro microservizio e ritorna la risposta deserializzata.
     *
     * @param url L'URL del microservizio.
     * @param request Il corpo della richiesta.
     * @param responseType Il tipo di risposta atteso.
     * @param <T> Il tipo della risposta.
     * @return La risposta deserializzata o null in caso di errore.
     */
    <T> List<T> postDataToAnotherService(String url, Object request, Class<T> responseType);

    /**
     * Invia una richiesta GET a un altro microservizio e ritorna la risposta come stringa.
     *
     * @param url L'URL del microservizio.
     * @return La risposta come stringa o null in caso di errore.
     */
    <T> T getDataFromAnotherService(String url, Class<T> responseType);

    Boolean deleteDataFromAnotherService(String url);
}
