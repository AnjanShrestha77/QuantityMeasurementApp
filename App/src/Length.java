

public class Length {

    // Instance variables
    double value;
    private LengthUnit unit;

    // Enum (base unit = inches)
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // Convert to base unit (inches)
    private double convertToBaseUnit() {
        double result = value * unit.getConversionFactor();
        return Math.round(result * 100.0) / 100.0;
    }

    // Compare method
    private boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        return Double.compare(this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()) == 0;
    }

    // equals override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length other = (Length) o;
        return compare(other);
    }

    // Convert to another unit
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Length(convertedValue, targetUnit);
    }

    // ================= UC6 (existing) =================
    public Length add(Length thatLength) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        double sumBase = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
        double resultValue = sumBase / this.unit.getConversionFactor();

        resultValue = Math.round(resultValue * 100.0) / 100.0;

        return new Length(resultValue, this.unit);
    }

    // ================= UC7 (MAIN PART) =================
    public Length add(Length length, LengthUnit targetUnit) {

        if (length == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addAndConvert(length, targetUnit);
    }

    // Helper method
    private Length addAndConvert(Length length, LengthUnit targetUnit) {

        double thisBase = this.convertToBaseUnit();
        double otherBase = length.convertToBaseUnit();

        double sumBase = thisBase + otherBase;

        double resultValue = convertFromBaseToTargetUnit(sumBase, targetUnit);

        return new Length(resultValue, targetUnit);
    }

    // Base → target conversion
    private double convertFromBaseToTargetUnit(double lengthInInches,
                                               LengthUnit targetUnit) {

        double result = lengthInInches / targetUnit.getConversionFactor();

        return Math.round(result * 100.0) / 100.0;
    }

    // toString
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}