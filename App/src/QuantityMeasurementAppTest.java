

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    // ================= EQUALITY =================

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        assertEquals(new Weight(1000.0, WeightUnit.GRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Length(1.0, LengthUnit.FEET));
    }

    @Test
    public void testEquality_NullComparison() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM), null);
    }

    @Test
    public void testEquality_SameReference() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w, w);
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(1.0, null);
        });
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void testEquality_ZeroValue() {
        assertEquals(new Weight(0.0, WeightUnit.KILOGRAM),
                new Weight(0.0, WeightUnit.GRAM));
    }

    @Test
    public void testEquality_NegativeWeight() {
        assertEquals(new Weight(-1.0, WeightUnit.KILOGRAM),
                new Weight(-1000.0, WeightUnit.GRAM));
    }

    @Test
    public void testEquality_LargeWeightValue() {
        assertEquals(new Weight(1000000.0, WeightUnit.GRAM),
                new Weight(1000.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testEquality_SmallWeightValue() {
        assertEquals(new Weight(0.001, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.GRAM));
    }

    // ================= CONVERSION =================

    @Test
    public void testConversion_PoundToKilogram() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_KilogramToPound() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);

        assertEquals(2.20, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        Weight result = new Weight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        Weight result = new Weight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        Weight result = new Weight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ================= ADDITION =================

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertEquals(4.41, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    public void testAddition_WithZero() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(-2000.0, WeightUnit.GRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_LargeValues() {
        Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
                .add(new Weight(1e6, WeightUnit.KILOGRAM));

        assertEquals(2e6, result.getValue(), EPSILON);
    }
}