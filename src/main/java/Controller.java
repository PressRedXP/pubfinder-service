import static spark.Spark.*;

public class Controller {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));

        get("/status", (request, response) -> {
            return 0;
        });

    }
}
