
public class QuantityMeasurementApp {

    // ================= WEIGHT METHODS (UC9) =================

    /**
     * Demonstrate weight equality between two Weight instances.
     */
    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        return weight1.equals(weight2);
    }

    /**
     * Demonstrate weight comparison between two weights specified by value and unit.
     */
    public static boolean demonstrateWeightComparison(
            double value1, WeightUnit unit1,
            double value2, WeightUnit unit2) {

        Weight w1 = new Weight(value1, unit1);
        Weight w2 = new Weight(value2, unit2);

        return demonstrateWeightEquality(w1, w2);
    }

    /**
     * Demonstrate weight conversion from one unit to another.
     */
    public static Weight demonstrateWeightConversion(
            double value,
            WeightUnit fromUnit,
            WeightUnit toUnit) {

        Weight weight = new Weight(value, fromUnit);
        return weight.convertTo(toUnit);
    }

    /**
     * Demonstrate weight conversion from one Weight instance to another unit.
     */
    public static Weight demonstrateWeightConversion(
            Weight weight,
            WeightUnit toUnit) {

        return weight.convertTo(toUnit);
    }

    /**
     * Demonstrate addition of second Weight to first Weight.
     */
    public static Weight demonstrateWeightAddition(
            Weight weight1,
            Weight weight2) {

        return weight1.add(weight2);
    }

    /**
     * Demonstrate addition with target unit.
     */
    public static Weight demonstrateWeightAddition(
            Weight weight1,
            Weight weight2,
            WeightUnit targetUnit) {

        return weight1.add(weight2, targetUnit);
    }


    // ================= LENGTH METHODS (FROM UC8) =================

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(
            Length length,
            LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2) {

        return length1.add(length2);
    }

    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2,
            LengthUnit targetUnit) {

        return length1.add(length2, targetUnit);
    }


    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        // -------- LENGTH DEMO --------
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " +
                demonstrateLengthEquality(l1, l2));

        System.out.println("Length Conversion: " +
                demonstrateLengthConversion(l1, LengthUnit.INCHES));

        System.out.println("Length Addition: " +
                demonstrateLengthAddition(l1, l2));

        System.out.println("Length Addition (Target Unit): " +
                demonstrateLengthAddition(l1, l2, LengthUnit.YARDS));


        // -------- WEIGHT DEMO --------
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " +
                demonstrateWeightEquality(w1, w2));

        System.out.println("Weight Conversion: " +
                demonstrateWeightConversion(w1, WeightUnit.GRAM));

        System.out.println("Weight Addition: " +
                demonstrateWeightAddition(w1, w2));

        System.out.println("Weight Addition (Target Unit): " +
                demonstrateWeightAddition(w1, w2, WeightUnit.GRAM));
    }
}