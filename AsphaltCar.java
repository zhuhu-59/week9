public class AsphaltCar extends RallyCar {
    public AsphaltCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }

    @Override
    public double calculatePerformance() {
        double performance = horsepower * 1.3 - 22;
        return Math.round(performance * 10) / 10.0; // Keep 1 decimal place
    }
}