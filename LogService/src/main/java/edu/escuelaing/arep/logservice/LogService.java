package edu.escuelaing.arep.logservice;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import com.google.gson.Gson;

import spark.Response;
import spark.Request;

import org.bson.Document;
import java.text.ParseException;
import java.util.List;

import edu.escuelaing.arep.logservice.services.MongoService;
public class LogService {
    private static final String USED_COLLECTION = "Messages";
    private static MongoService mongoService;

    public static void main(String[] args){
        port(getPort());
        mongoService = new MongoService(USED_COLLECTION);
        post("/messages", (req,res) -> postHandler(req, res));
        get("/messages", (req,res) -> getHandler(res));
    }


    private static String postHandler(Request req, Response res){
        String response = "";
        if (req.body() == null && req.body().equals("")) {
            res.type("text/plain");
            response = "Invalid request";
        } else{
            res.status(201);
            res.type("application/json");
            try {
                mongoService.saveMessage(req.body());
            } catch (ParseException e) {
                res.type("text/plain");
                response = "Invalid request";
            }
        }
        return response;
    }
    
    private static String getHandler(Response res){
        List<Document> messagesList = mongoService.getLastMessages();
        res.status(200);
        res.type("application/json");
        return new Gson().toJson(messagesList);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}
