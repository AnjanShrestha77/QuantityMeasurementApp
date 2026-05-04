/**
 * @author : Anjan
 * @version : 8.0
 * @since UC8
 */

public class QuantityMeasurementApp {

    // Equality
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // Comparison
    public static boolean demonstrateLengthComparison(
            double v1, LengthUnit u1,
            double v2, LengthUnit u2) {

        return demonstrateLengthEquality(
                new Length(v1, u1),
                new Length(v2, u2)
        );
    }

    // Conversion
    public static Length demonstrateLengthConversion(
            double value, LengthUnit from, LengthUnit to) {

        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(
            Length length, LengthUnit to) {

        return length.convertTo(to);
    }

    // Addition (UC6)
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    // Addition (UC7)
    public static Length demonstrateLengthAddition(
            Length l1, Length l2, LengthUnit target) {

        return l1.add(l2, target);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Equality: " + demonstrateLengthEquality(l1, l2));
        System.out.println("Convert: " + demonstrateLengthConversion(l1, LengthUnit.INCHES));
        System.out.println("Add: " + demonstrateLengthAddition(l1, l2));
        System.out.println("Add Target: " + demonstrateLengthAddition(l1, l2, LengthUnit.YARDS));
    }
}