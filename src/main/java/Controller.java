import static spark.Spark.*;

import java.util.Optional;

public class Controller {
    private static GoogleMapsDao mapsDao = new GoogleMapsDao("AIzaSyB-s4fUCHKpcAWPfyp-fT-2WuykrWs55qo");

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));

        get("/status", (request, response) -> {
            return new StatusResponse(42);
        }, new JsonTransformer());

        get("/pubs", (request, response) -> {
            double latitude = 53.0;
            double longitude = -2;
            double radius = 5000;
            String type = "bar";
            Optional<InterestingPlace> place = mapsDao.getMostInterestingPlace(latitude, longitude, radius, type);

            if (place.isPresent()) {
                return "yay";
            }
            else {
                return "nay";
            }
        }, new JsonTransformer());

    }
}
