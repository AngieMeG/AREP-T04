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
        System.out.println(Unirest.get(url).asString());
        return "";
    }

    private String getServer(){
        String currentServer = instances.get(indexInstance);
        indexInstance = indexInstance + 1 > instances.size() ? 0 : indexInstance + 1;
        return currentServer;
    }
}
