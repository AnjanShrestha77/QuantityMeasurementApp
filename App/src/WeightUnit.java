

public enum WeightUnit implements IMeasurable {

    // Base unit = GRAM
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert to base (grams)
    public double convertToBaseUnit(double value) {
        return Math.round(value * conversionFactor * 100.0) / 100.0;
    }

    // Convert from base (grams)
    public double convertFromBaseUnit(double baseValue) {
        return Math.round((baseValue / conversionFactor) * 100.0) / 100.0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}