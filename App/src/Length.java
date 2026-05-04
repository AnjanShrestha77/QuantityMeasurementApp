

public class Length {

    // Instance variables
    private double value;
    private LengthUnit unit;

    // Enum for units (base unit = inches)
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

    // Convert to base unit (inches) + rounding
    private double convertToBaseUnit() {
        double result = this.value * this.unit.getConversionFactor();
        return Math.round(result * 100.0) / 100.0;
    }

    // Compare method
    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        return Double.compare(this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()) == 0;
    }

    // equals() override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Length other = (Length) o;
        return this.compare(other);
    }

    // hashCode (important)
    @Override
    public int hashCode() {
        return Double.valueOf(convertToBaseUnit()).hashCode();
    }

    // Main method (demo)
    public static void main(String[] args) {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2));

        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length3.equals(length4));

        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length5.equals(length6));
    }
}