package edu.escuelaing.arep.lbroundrobin.model;
import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Clase encargada de mandar los request al servidor que maneja la base de datos
 */
public class ServerConnection {
    private ArrayList<String> instances = new ArrayList<>();
    private int indexInstance;
    private static final String QUERY = "/messages";

    /**
     * Crea el servidor con las urls de las instancias a manejar
     * @param instances
     */
    public ServerConnection(ArrayList<String> instances) {
        this.instances = instances;
        indexInstance = 0;
    }

    /**
     * Manda una peticion GET al servidor de la base de datos
     * @return la respuesta obtenida del servidor
     * @throws UnirestException
     */
    public String getRequest() throws UnirestException{
        String url =  getServer() + QUERY;
        System.out.println("URL: " + url);
        HttpResponse<String> response = Unirest.get(url).asString() ;
        return response.getBody();
    }

    
    /**
     * Manda una peticion POST al servidor de la base de datos
     * @return la respuesta obtenida del servidor
     * @throws UnirestException
     */
    public String postRequest(String body) throws UnirestException{
        System.out.println("postRequest");
        String url =  getServer() + QUERY;
        HttpResponse<String> response = Unirest.post(url).body(body).asString() ;
        return response.getBody();
    }


    /**
     * Cambia de instancia del servidor, corresponidente al algoritmo RoundRobin
     * @return devuelve el servidor al que se le debera hacer la peticion
     */
    private String getServer(){
        String currentServer = instances.get(indexInstance);
        indexInstance = indexInstance + 1 >= instances.size() ? 0 : indexInstance + 1;
        return currentServer;
    }
}
