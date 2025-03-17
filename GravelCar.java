public class GravelCar extends RallyCar {
    public GravelCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }

    @Override
    public double calculatePerformance() {
        double performance = horsepower * 1.1 + 49.5; // 340 * 1.1 + 49.5 = 423.5
        return Math.round(performance * 10) / 10.0; // Keep 1 decimal place
    }
}