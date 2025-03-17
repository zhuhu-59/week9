import java.util.*;

public class ChampionshipStatistics {
    public static double calculateAveragePoints(List<Driver> drivers) {
        return drivers.stream()
            .mapToInt(Driver::getTotalPoints)
            .average()
            .orElse(0.0);
    }

    public static String findMostSuccessfulCountry(List<Driver> drivers) {
        Map<String, Integer> countryPoints = new HashMap<>();
        for (Driver driver : drivers) {
            countryPoints.merge(driver.getCountry(), driver.getTotalPoints(), Integer::sum);
        }
        return Collections.max(countryPoints.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static int calculateTotalPoints(List<Driver> drivers) {
        return drivers.stream()
            .mapToInt(Driver::getTotalPoints)
            .sum();
    }
}