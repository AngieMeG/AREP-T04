package edu.escuelaing.arep.lbroundrobin.model;
import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ServerConnection {
    private ArrayList<String> instances = new ArrayList<>();
    private int indexInstance;
    private static final String QUERY = "/messages";

    public ServerConnection(ArrayList<String> instances) {
        this.instances = instances;
        indexInstance = 0;
    }

    public String getRequest() throws UnirestException{
        String url =  getServer() + QUERY;
        System.out.println("URL: " + url);
        HttpResponse<String> response = Unirest.get(url).asString() ;
        return response.getBody();
    }

    public String postRequest(String body) throws UnirestException{
        System.out.println("postRequest");
        String url =  getServer() + QUERY;
        HttpResponse<String> response = Unirest.post(url).body(body).asString() ;
        return response.getBody();
    }

    private String getServer(){
        String currentServer = instances.get(indexInstance);
        indexInstance = indexInstance + 1 >= instances.size() ? 0 : indexInstance + 1;
        return currentServer;
    }
}
