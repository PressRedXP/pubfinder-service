import java.util.ArrayList;
import java.util.List;

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

    public String getPlacesNearby(double latitude, double longitude, double radius, String type) {
        String urlString = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%f&types=%s&rankby=prominence&key=%s",
                latitude,
                longitude,
                radius,
                type,
                apiKey
        );
        System.out.println(urlString);

//        try {
            GoogleMapsResponse mapsResponse = restTemplate.getForObject(urlString, GoogleMapsResponse.class);
            System.out.println(mapsResponse.next_page_token);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getCause());
//            System.out.println(e.getStackTrace().toString());
//        }

        System.out.println("End of getPlacesNearby()");

        return mapsResponse.next_page_token;
    }
}
