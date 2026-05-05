

public enum LengthUnit implements IMeasurable{

    // Base unit = FEET
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;


    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;

    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert to base unit (feet)
    public double convertToBaseUnit(double value) {
        double result = value * conversionFactor;
        return Math.round(result * 100.0) / 100.0;
    }

    // Convert from base unit (feet)
    public double convertFromBaseUnit(double baseValue) {
        double result = baseValue / conversionFactor;
        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}