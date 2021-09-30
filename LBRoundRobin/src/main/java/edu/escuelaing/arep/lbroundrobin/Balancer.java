package edu.escuelaing.arep.lbroundrobin;
import static spark.Spark.*;

import java.util.Arrays;

import com.mashape.unirest.http.exceptions.UnirestException;

import edu.escuelaing.arep.lbroundrobin.model.ServerConnection;

import java.util.ArrayList;

import spark.Response;
import spark.Request;

public class Balancer{
        public static void main(String[] args){
        port(getPort());
        staticFiles.location("/static");
        String url = "http://172.17.0.1:";
        /*ArrayList<String> portInstances = new ArrayList<>(Arrays.asList(url+"35001", url+"35002", url+"35003"));*/
        ArrayList<String> portInstances = new ArrayList<>(Arrays.asList(url+"4568"));
        ServerConnection server = new ServerConnection(portInstances);
        get("/messages", (req, res) -> handleMessage(req, res, server));
    }

    public static String handleMessage(Request req, Response res, ServerConnection server) {
        try {
            System.out.println(server.getRequest());
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return req.queryParams("firstname") + " " +
                req.queryParams("lastname");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}