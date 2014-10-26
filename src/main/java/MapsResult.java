import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MapsResult {
    public String name;
    public Geometry geometry;
}
