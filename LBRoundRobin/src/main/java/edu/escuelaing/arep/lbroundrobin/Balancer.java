package edu.escuelaing.arep.lbroundrobin;
import static spark.Spark.*;

import java.util.Arrays;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.escuelaing.arep.lbroundrobin.model.ServerConnection;

import java.util.ArrayList;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.Response;
import spark.Request;

public class Balancer{
        public static void main(String[] args){
        port(getPort());
        staticFiles.location("/static");
        String url = "http://127.0.0.1:";
        /*ArrayList<String> portInstances = new ArrayList<>(Arrays.asList(url+"35001", url+"35002", url+"35003"));*/
        ArrayList<String> portInstances = new ArrayList<>(Arrays.asList(url+"4568"));
        ServerConnection server = new ServerConnection(portInstances);
        get("/messages", (req, res) -> handleGetMessage(req, res, server));
        post("/messages", (req, res) -> handlePostMessage(req, res, server));
    }


    public static String handleGetMessage(Request req, Response res, ServerConnection server) {
        HttpResponse<String> lbResponse;
        try {
            lbResponse = server.getRequest();
            res.status(lbResponse.getStatus());
            System.out.println(lbResponse.getBody());
            return lbResponse.getBody();
        } catch (UnirestException e) {
            return "Error";
        }
    }

    public static String handlePostMessage(Request req, Response res, ServerConnection server) {
        HttpResponse<String> lbResponse;
        try {
            lbResponse = server.postRequest(req.body());
            res.status(lbResponse.getStatus());
            return handleGetMessage(req,res, server);
        } catch (UnirestException e) {
            return "Error";
        }
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}