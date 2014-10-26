import static spark.Spark.*;

import java.util.Optional;

public class Controller {
    private static GoogleMapsDao mapsDao = new GoogleMapsDao("AIzaSyB-s4fUCHKpcAWPfyp-fT-2WuykrWs55qo");

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));

        get("/status", (request, response) -> {
            response.type("application/json");
            return new StatusResponse(42);
        }, new JsonTransformer());

        get("/pub/latitude/:lat/longitude/:long", (request, response) -> {
            response.type("application/json");
            double latitude = 53.0;
            double longitude = -2;
            double radius = 5000;
            String type = "bar";
            Optional<InterestingPlace> place = mapsDao.getMostInterestingPlace(latitude, longitude, radius, type);

            if (place.isPresent()) {
                return place.get();
            }
            else {
                response.status(204);
                return "";
            }
        }, new JsonTransformer());

    }
}
