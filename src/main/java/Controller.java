import static spark.Spark.*;

public class Controller {
    private static GoogleMapsDao mapsDao = new GoogleMapsDao("AIzaSyB-s4fUCHKpcAWPfyp-fT-2WuykrWs55qo");

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));

        get("/status", (request, response) -> {
            return 0;
        });

        get("/pubs", (request, response) -> {
            double latitude = 53.0;
            double longitude = -2;
            double radius = 5000;
            String type = "bar";
            return mapsDao.getPlacesNearby(latitude, longitude, radius, type);
        });

    }
}
