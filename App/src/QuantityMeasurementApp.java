

public class QuantityMeasurementApp {

    // Equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    // Comparison using raw values
    public static boolean demonstrateLengthComparison(
            double value1, Length.LengthUnit unit1,
            double value2, Length.LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return demonstrateLengthEquality(l1, l2);
    }

    // Conversion (value-based)
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit fromUnit,
            Length.LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    // Conversion (object-based)
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    // 🔥 UC6 ADDITION METHOD
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        return length1.add(length2);
    }

    // Main method
    public static void main(String[] args) {

        // Equality
        System.out.println("Equality: " +
                demonstrateLengthComparison(1.0, Length.LengthUnit.FEET,
                        12.0, Length.LengthUnit.INCHES));

        // Conversion
        System.out.println("Conversion: " +
                demonstrateLengthConversion(3.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));

        // Addition examples
        System.out.println("Addition (Feet + Inches): " +
                demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.FEET),
                        new Length(12.0, Length.LengthUnit.INCHES)));

        System.out.println("Addition (Yard + Feet): " +
                demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.YARDS),
                        new Length(3.0, Length.LengthUnit.FEET)));
    }
}