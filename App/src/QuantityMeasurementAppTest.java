
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    // ================= SUBTRACTION =================

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    public void testSubtraction_SameUnit_LitreMinusLitre() {
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE),
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(3.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(120.0, LengthUnit.INCHES)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(60.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.FEET);

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(3000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(-2.0, LengthUnit.FEET));

        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertNotEquals(a, b);
    }

    @Test
    public void testSubtraction_NullOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET).subtract(null);
        });
    }

    @Test
    public void testSubtraction_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(new Quantity<>(5.0, LengthUnit.FEET), null);
        });
    }


    @Test
    public void testSubtraction_CrossCategory() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            length.subtract(weight);
        });
    }

    @Test
    public void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(7.0, result.getValue(), EPSILON);
    }

    // ================= DIVISION =================

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        assertEquals(5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)),
                EPSILON);
    }

    @Test
    public void testDivision_SameUnit_LitreDividedByLitre() {
        assertEquals(2.0,
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(5.0, VolumeUnit.LITRE)),
                EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_FeetDividedByInches() {
        assertEquals(1.0,
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)),
                EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_KilogramDividedByGram() {
        assertEquals(1.0,
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(2000.0, WeightUnit.GRAM)),
                EPSILON);
    }

    @Test
    public void testDivision_RatioGreaterThanOne() {
        assertEquals(5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)),
                EPSILON);
    }

    @Test
    public void testDivision_RatioLessThanOne() {
        assertEquals(0.5,
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)),
                EPSILON);
    }

    @Test
    public void testDivision_RatioEqualToOne() {
        assertEquals(1.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)),
                EPSILON);
    }

    @Test
    public void testDivision_NonCommutative() {
        double a = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(5.0, LengthUnit.FEET));

        double b = new Quantity<>(5.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertNotEquals(a, b);
    }

    @Test
    public void testDivision_ByZero() {
        assertThrows(ArithmeticException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(new Quantity<>(0.0, LengthUnit.FEET));
        });
    }

    @Test
    public void testDivision_NullOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET).divide(null);
        });
    }

    @Test

    public void testDivision_CrossCategory() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            length.divide(weight);
        });
    }
    // ================= INTEGRATION =================

    @Test
    public void testSubtractionAndDivision_Integration() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(4.0, result, EPSILON);
    }

    @Test
    public void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                a.add(b).subtract(b);

        assertEquals(a.getValue(), result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        a.subtract(b);

        assertEquals(10.0, a.getValue(), EPSILON);
    }

    @Test
    public void testDivision_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        a.divide(b);

        assertEquals(10.0, a.getValue(), EPSILON);
    }
}