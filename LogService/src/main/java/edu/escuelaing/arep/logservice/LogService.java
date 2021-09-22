package LogService.src.main.java.edu.escuelaing.arep.logservice;

public class LogService {
    public static void main(String... args){
        port(getPort());
        get("message", (req,res) -> {
            
        };
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
