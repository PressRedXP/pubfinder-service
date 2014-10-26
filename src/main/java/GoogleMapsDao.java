import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class GoogleMapsDao {
    private final String apiKey;
    private RestTemplate restTemplate;

    public GoogleMapsDao(String apiKey) {
        this.apiKey = apiKey;

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJacksonHttpMessageConverter messageConverter = new MappingJacksonHttpMessageConverter();
        messageConverters.add(messageConverter);

        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
    }

    public Optional<InterestingPlace> getMostInterestingPlace(double latitude, double longitude, double radius, String type) {
        String urlString = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&types=%s&rankby=distance&key=%s",
                latitude,
                longitude,
                type,
                apiKey
        );
        GoogleMapsResponse mapsResponse = null;

        try {
            mapsResponse = restTemplate.getForObject(urlString, GoogleMapsResponse.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace().toString());
        }

        if (mapsResponse != null && !mapsResponse.results.isEmpty()) {
            MapsResult mapsResult = mapsResponse.results.get(0);
            InterestingPlace place = new InterestingPlace(
                    mapsResult.name, mapsResult.geometry.location.lat, mapsResult.geometry.location.lng, mapsResult.vicinity);
            return Optional.of(place);
        }

        return Optional.empty();
    }
}
