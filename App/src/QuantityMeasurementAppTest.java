

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-2;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.FEET);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.INCHES);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARDS);

        assertEquals(0.67,
                result.convertTo(Length.LengthUnit.YARDS).value,
                EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.CENTIMETERS);

        assertEquals(5.08,
                result.convertTo(Length.LengthUnit.CENTIMETERS).value,
                EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(2.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET),
                Length.LengthUnit.YARDS);

        assertEquals(new Length(3.0, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(2.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET),
                Length.LengthUnit.FEET);

        assertEquals(new Length(9.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        assertEquals(
                a.add(b, Length.LengthUnit.YARDS),
                b.add(a, Length.LengthUnit.YARDS)
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(0.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARDS);

        assertEquals(1.67,
                result.convertTo(Length.LengthUnit.YARDS).value,
                EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(-2.0, Length.LengthUnit.FEET),
                Length.LengthUnit.INCHES);

        assertEquals(new Length(36.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.demonstrateLengthAddition(
                    new Length(1.0, Length.LengthUnit.FEET),
                    new Length(12.0, Length.LengthUnit.INCHES),
                    null);
        });
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1000.0, Length.LengthUnit.FEET),
                new Length(500.0, Length.LengthUnit.FEET),
                Length.LengthUnit.INCHES);

        assertEquals(new Length(18000.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(12.0, Length.LengthUnit.INCHES),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARDS);

        assertEquals(0.67,
                result.convertTo(Length.LengthUnit.YARDS).value,
                EPSILON);
    }

}