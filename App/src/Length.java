
/**
 * Quantity Length class supporting:
 * - Equality (UC3)
 * - Extended units (UC4)
 * - Conversion (UC5)
 * - Addition (UC6)
 */
public class Length {

    double value;
    private LengthUnit unit;

    // Enum with base unit = inches
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
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
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

    // Convert to another unit (UC5)
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Length(convertedValue, targetUnit);
    }

    // STATIC convert API (UC5)
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        double baseValue = value * source.getConversionFactor();
        return baseValue / target.getConversionFactor();
    }

    // ================== UC6 ADD METHOD ==================
    public Length add(Length thatLength) {

        if (thatLength == null) {
            throw new IllegalArgumentException("Second operand cannot be null");
        }

        // Step 1: Convert both to base unit
        double thisBase = this.convertToBaseUnit();
        double thatBase = thatLength.convertToBaseUnit();

        // Step 2: Add
        double sumBase = thisBase + thatBase;

        // Step 3: Convert back to this.unit
        double resultValue = sumBase / this.unit.getConversionFactor();

        resultValue = Math.round(resultValue * 100.0) / 100.0;

        return new Length(resultValue, this.unit);
    }

    // toString
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }

    @Override
    public int hashCode() {
        return Double.valueOf(convertToBaseUnit()).hashCode();
    }
}