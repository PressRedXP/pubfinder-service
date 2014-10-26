import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleMapsResponse {
    public String next_page_token;
    @JsonProperty("results") public List<MapsResult> results;
}
