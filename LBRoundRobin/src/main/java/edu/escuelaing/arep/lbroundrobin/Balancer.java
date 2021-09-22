package edu.escuelaing.arep.lbroundrobin;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
public class Balancer{
    public static void main(String... args){
        port(getPort());
        staticFiles.location("/resources/static");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}