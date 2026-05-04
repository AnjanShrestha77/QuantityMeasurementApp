

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0,
                Length.convert(24.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0,
                Length.convert(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0,
                Length.convert(72.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                Length.convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES),
                1e-3); // relaxed tolerance due to floating precision
    }

    @Test
    public void testConversion_FeetToYard() {
        assertEquals(2.0,
                Length.convert(6.0, Length.LengthUnit.FEET, Length.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        double original = 5.0;

        double converted = Length.convert(original,
                Length.LengthUnit.FEET, Length.LengthUnit.INCHES);

        double back = Length.convert(converted,
                Length.LengthUnit.INCHES, Length.LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0,
                Length.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0,
                Length.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(1.0, null, Length.LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(1.0, Length.LengthUnit.FEET, null);
        });
    }

    @Test
    public void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        });
    }

    @Test
    public void testConversion_PrecisionTolerance() {
        double result = Length.convert(2.54,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES);

        assertEquals(1.0, result, 1e-3);
    }
}