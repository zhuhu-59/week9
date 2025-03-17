import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ChampionshipManager manager = ChampionshipManager.getInstance();

        // Create a vehicle
        RallyCar gravelCar1 = new GravelCar("Toyota", "Yaris", 340);
        RallyCar asphaltCar1 = new AsphaltCar("Hyundai", "i20", 380);

        // Registered drivers
        Driver ogier = new Driver("Sébastien Ogier", "France", gravelCar1);
        Driver rovanpera = new Driver("Kalle Rovanperä", "Finland", asphaltCar1);
        Driver tanak = new Driver("Ott Tänak", "Estonia", new GravelCar("Ford", "Puma", 350));
        Driver neuville = new Driver("Thierry Neuville", "Belgium", new AsphaltCar("Hyundai", "i20", 385));

        manager.registerDriver(ogier);
        manager.registerDriver(rovanpera);
        manager.registerDriver(tanak);
        manager.registerDriver(neuville);

        // Race 1: Rally Finland (Gravel)
        RaceResult race1 = new RallyRaceResult("Rally Finland", "Jyväskylä");
        race1.recordResult(ogier, 1);
        race1.recordResult(tanak, 2);
        race1.recordResult(rovanpera, 3);
        race1.recordResult(neuville, 4);
        manager.recordRaceResult(race1);

        // Race 2: Monte Carlo Rally (asphalt, change  vehicle)
        ogier.setCar(new AsphaltCar("Toyota", "GR Yaris", 380)); 
        RaceResult race2 = new RallyRaceResult("Monte Carlo Rally", "Monaco");
        race2.recordResult(rovanpera, 1);
        race2.recordResult(neuville, 2);
        race2.recordResult(ogier, 3);
        race2.recordResult(tanak, 4);
        manager.recordRaceResult(race2);

        // Display the standings
        System.out.println("===== CHAMPIONSHIP STANDINGS =====");
        List<Driver> standings = manager.getDrivers();
        for (int i = 0; i < standings.size(); i++) {
            Driver driver = standings.get(i);
            System.out.println((i + 1) + ". " + driver.getName() + " (" + driver.getCountry() + "): " + driver.getTotalPoints() + " points");
        }

        // Display the leader
        System.out.println("\n===== CHAMPIONSHIP LEADER =====");
        Driver leader = manager.getLeadingDriver();
        System.out.println(leader.getName() + " with " + leader.getTotalPoints() + " points");

        // Display statistics
        System.out.println("\n===== CHAMPIONSHIP STATISTICS =====");
        System.out.println("Total Drivers: " + ChampionshipManager.getTotalDrivers());
        System.out.println("Total Races: " + ChampionshipManager.getTotalRaces());
        System.out.printf("Average Points Per Driver: %.2f\n", ChampionshipStatistics.calculateAveragePoints(standings));
        System.out.println("Most Successful Country: " + ChampionshipStatistics.findMostSuccessfulCountry(standings));
        System.out.println("Total Championship Points: " + ChampionshipStatistics.calculateTotalPoints(standings));

        // Display the game results
        System.out.println("\n===== RACE RESULTS =====");
        for (RaceResult result : manager.getRaceResults()) {
            RallyRaceResult rallyResult = (RallyRaceResult) result; // Access single game points
            System.out.println("Race: " + rallyResult.getRaceName() + " (" + rallyResult.getLocation() + ")");
            rallyResult.getResults().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    Driver driver = entry.getKey();
                    int position = entry.getValue();
                    int points = rallyResult.getRacePoints().get(driver); // Get single game points
                    System.out.printf("    Position %d: %s - %d points\n", position, driver.getName(), points);
                });
        }
        // Display vehicle performance
        System.out.println("\n===== CAR PERFORMANCE RATINGS =====");
        System.out.println("Gravel Car Performance: " + gravelCar1.calculatePerformance());
        System.out.println("Asphalt Car Performance: " + asphaltCar1.calculatePerformance());
    }
}