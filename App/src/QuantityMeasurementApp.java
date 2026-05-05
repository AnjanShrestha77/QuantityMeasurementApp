/**
 * Main class to demonstrate Quantity Measurement operations
 *
 * Features:
 * - Equality comparison
 * - Unit conversion
 * - Addition (same unit result)
 * - Addition (target unit result)
 *
 * @author Anjan
 * @version 1.0
 */
public class QuantityMeasurementApp {

    /**
     * Compare two quantities for equality
     */
    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.equals(quantity2);
    }

    /**
     * Convert quantity to target unit
     */
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        double convertedValue = quantity.convertTo(targetUnit);
        return new Quantity<>(convertedValue, targetUnit);
    }

    /**
     * Add two quantities and return result in first quantity's unit
     */
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.add(quantity2);
    }

    /**
     * Add two quantities and return result in target unit
     */
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2,
            U targetUnit) {

        return quantity1.add(quantity2, targetUnit);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // =========================
        // Equality Demo
        // =========================
        Quantity<WeightUnit> weightInGrams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> weightInKilograms =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        boolean areEqual = demonstrateEquality(weightInGrams, weightInKilograms);

        System.out.println("Are weights equal? " + areEqual);


        // =========================
        // Conversion Demo
        // =========================
        Quantity<WeightUnit> convertedWeight =
                demonstrateConversion(weightInGrams, WeightUnit.KILOGRAM);

        System.out.println("Converted Weight: "
                + convertedWeight.getValue() + " "
                + convertedWeight.getUnit().getUnitName());


        // =========================
        // Addition Demo (same unit)
        // =========================
        Quantity<WeightUnit> weightInPounds =
                new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> sumWeight =
                demonstrateAddition(weightInKilograms, weightInPounds);

        System.out.println("Sum Weight: "
                + sumWeight.getValue() + " "
                + sumWeight.getUnit().getUnitName());


        // =========================
        // Addition Demo (target unit)
        // =========================
        Quantity<WeightUnit> sumWeightInGrams =
                demonstrateAddition(weightInKilograms, weightInPounds, WeightUnit.GRAM);

        System.out.println("Sum Weight in Grams: "
                + sumWeightInGrams.getValue() + " "
                + sumWeightInGrams.getUnit().getUnitName());


        // =========================
        // Length Demo (extra test)
        // =========================
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

        boolean lengthEqual = demonstrateEquality(feet, inches);

        System.out.println("Are lengths equal? " + lengthEqual);
    }
}