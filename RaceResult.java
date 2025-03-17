import java.util.Map;

public interface RaceResult {
    void recordResult(Driver driver, int position);
    Map<Driver, Integer> getResults();
    String getRaceName();
    String getLocation();
}

