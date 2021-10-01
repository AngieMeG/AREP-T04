package edu.escuelaing.arep.lbroundrobin;
import static spark.Spark.*;

import java.util.Arrays;

import com.mashape.unirest.http.exceptions.UnirestException;

import edu.escuelaing.arep.lbroundrobin.model.ServerConnection;

import java.util.ArrayList;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.Response;
import spark.Request;

/**
 * Clase balanceadora de request REST
 */
public class Balancer{
        public static void main(String[] args){
        port(getPort());
        staticFiles.location("/static");
        String url = "http://127.0.0.1:";
        ArrayList<String> portInstances = new ArrayList<>(Arrays.asList(url+"35001", url+"35002", url+"35003"));
        /*ArrayList<String> portInstances = new ArrayList<>(Arrays.asList(url+"4568"));*/
        ServerConnection server = new ServerConnection(portInstances);
        get("/messages", (req, res) -> handleGetMessage(req, res, server));
        post("/messages", (req, res) -> handlePostMessage(req, res, server));
    }

    /**
     * Maneja el protocolo GET para la aplicacion y se lo manda al correspondiente metodo de la clase que conecta con el servidor de la base de datos
     * @param req request
     * @param res, response del request
     * @param server, instancia del servidor a hacer el request
     * @return String, La respuesta dada por el servidor al request
     */
    public static String handleGetMessage(Request req, Response res, ServerConnection server) {
        String lbResponse;
        try {
            lbResponse = server.getRequest();
            return lbResponse;
        } catch (UnirestException e) {
            return "Error";
        }
    }

    /**
     * Maneja el protocolo POST para la aplicacion y se lo manda al correspondiente metodo de la clase que conecta con el servidor de la base de datos
     * @param req request
     * @param res, response del request
     * @param server, instancia del servidor a hacer el request
     * @return String, La respuesta dada por el servidor al request
     */
    public static String handlePostMessage(Request req, Response res, ServerConnection server) {
        System.out.println("ENTRO POST");
        try {
            server.postRequest(req.body());
            return handleGetMessage(req,res, server);
        } catch (UnirestException e) {
            return "Error";
        }
    }

    /**
     * Obtiene el puerto en el que se utilizara la aplicacion, de manera local el predeterminado sera 4567
     * @return
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}