

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    // ================= EQUALITY =================

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(2.0, VolumeUnit.LITRE));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(0.26417, VolumeUnit.GALLON));
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON),
                new Quantity<>(3.78541, VolumeUnit.LITRE));
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, LengthUnit.FEET));
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testEquality_NullComparison() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), null);
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(q, q);
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void testEquality_ZeroValue() {
        assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE),
                new Quantity<>(0.0, VolumeUnit.MILLILITRE));
    }

    @Test
    public void testEquality_NegativeVolume() {
        assertEquals(new Quantity<>(-1.0, VolumeUnit.LITRE),
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE));
    }

    @Test
    public void testEquality_LargeVolumeValue() {
        assertEquals(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1000.0, VolumeUnit.LITRE));
    }

    @Test
    public void testEquality_SmallVolumeValue() {
        assertEquals(new Quantity<>(0.001, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.MILLILITRE));
    }

    // ================= CONVERSION =================

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.GALLON);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.GALLON);

        assertEquals(0.26417, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(0.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(-1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.5, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ================= ADDITION =================

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.LITRE));

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.LITRE);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE),
                                VolumeUnit.GALLON);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    public void testAddition_WithZero() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_LargeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1e6, VolumeUnit.LITRE)
                        .add(new Quantity<>(1e6, VolumeUnit.LITRE));

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_SmallValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(0.001, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.002, VolumeUnit.LITRE));

        assertEquals(0.003, result.getValue(), EPSILON);
    }
}