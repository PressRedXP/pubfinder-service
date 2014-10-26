import static spark.Spark.*;

public class Controller {

    public static void main(String[] args) {

        get("/status", (request, response) -> {
            return 0;
        });

    }
}
