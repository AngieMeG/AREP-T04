package edu.escuelaing.arep.logservice;

import static spark.Spark.get;
import static spark.Spark.port;
public class LogService {
    public static void main(String... args){
        port(getPort());
        get("/", (req,res) -> welcomePage());
    }


    private static String welcomePage(){
        return "";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
