import java.util.HashMap;
import java.util.Map;

public class RallyRaceResult implements RaceResult {
    private String raceName;
    private String location;
    private Map<Driver, Integer> positions = new HashMap<>(); //Store the ranking
    private Map<Driver, Integer> racePoints = new HashMap<>(); //Store single-game points

    public RallyRaceResult(String raceName, String location) {
        this.raceName = raceName;
        this.location = location;
    }

    @Override
    public void recordResult(Driver driver, int position) {
        positions.put(driver, position);
        int[] pointsTable = {25, 18, 15, 12};
        if (position <= pointsTable.length) {
            int points = pointsTable[position - 1];
            racePoints.put(driver, points); //Record single game points
            driver.addPoints(points);       // Add to total score
        }
    }

    @Override
    public Map<Driver, Integer> getResults() {
        return new HashMap<>(positions);
    }

    // Get single game points
    public Map<Driver, Integer> getRacePoints() {
        return new HashMap<>(racePoints);
    }

    @Override
    public String getRaceName() { return raceName; }

    @Override
    public String getLocation() { return location; }
}
