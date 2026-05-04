

public class Length {

    // Instance variables
    private double value;
    private LengthUnit unit;

    // Enum for units (base unit = inches)
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

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
        return this.value * this.unit.getConversionFactor();
    }

    // Compare two Length objects
    public boolean compare(Length otherLength) {
        if (otherLength == null) return false;
        return Double.compare(this.convertToBaseUnit(),
                otherLength.convertToBaseUnit()) == 0;
    }

    // Override equals()
    @Override
    public boolean equals(Object o) {
        // Reflexive
        if (this == o) return true;

        // Null + type check
        if (o == null || getClass() != o.getClass()) return false;

        Length other = (Length) o;

        // Compare using base unit
        return Double.compare(this.convertToBaseUnit(),
                other.convertToBaseUnit()) == 0;
    }

    // HashCode (important for equals contract)
    @Override
    public int hashCode() {
        return Double.valueOf(convertToBaseUnit()).hashCode();
    }

    // Main method (already given in your code)
    public static void main(String[] args) {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + length1.equals(length2));
    }
}