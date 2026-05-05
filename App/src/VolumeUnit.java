

public enum VolumeUnit implements IMeasurable {

    // Base unit = LITRE
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return Math.round(value * conversionFactor * 100.0) / 100.0;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return Math.round((baseValue / conversionFactor) * 100.0) / 100.0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}