

public class QuantityMeasurementApp {

    // Equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    // Comparison (value-based)
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

    // 🔥 UC7 ADDITION (MAIN METHOD)
    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2,
            Length.LengthUnit targetUnit) {

        return length1.add(length2, targetUnit);
    }

    // Main method (optional demo)
    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Feet: " +
                demonstrateLengthAddition(l1, l2, Length.LengthUnit.FEET));

        System.out.println("Inches: " +
                demonstrateLengthAddition(l1, l2, Length.LengthUnit.INCHES));

        System.out.println("Yards: " +
                demonstrateLengthAddition(l1, l2, Length.LengthUnit.YARDS));
    }
}