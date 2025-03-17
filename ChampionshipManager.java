import java.util.*;

public class ChampionshipManager {
    private static ChampionshipManager instance;
    private List<Driver> drivers = new ArrayList<>();
    private List<RaceResult> raceResults = new ArrayList<>();
    private static int totalDrivers = 0;
    private static int totalRaces = 0;

    private ChampionshipManager() {}

    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
        totalDrivers++;
    }

    public void recordRaceResult(RaceResult result) {
        raceResults.add(result);
        totalRaces++;
    }
    
    public List<RaceResult> getRaceResults() {
        return new ArrayList<>(raceResults);
    }

    public List<Driver> getDrivers() {
        // Sort by points in descending order
        drivers.sort((d1, d2) -> d2.getTotalPoints() - d1.getTotalPoints());
        return new ArrayList<>(drivers);
    }

    public static int getTotalDrivers() { return totalDrivers; }
    public static int getTotalRaces() { return totalRaces; }

    public Driver getLeadingDriver() {
        return getDrivers().stream()
            .findFirst()
            .orElse(null);
    }
}