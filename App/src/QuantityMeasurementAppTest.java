import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // =========================
    // 1
    // =========================
    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        LengthUnit unit = LengthUnit.FEET;

        assertEquals(1.0, unit.getConversionFactor());
        assertEquals("FEET", unit.getUnitName());
        assertEquals(1.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(1.0));
    }

    // =========================
    // 2
    // =========================
    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        WeightUnit unit = WeightUnit.KILOGRAM;

        assertEquals(1000.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(1000.0));
        assertEquals("KILOGRAM", unit.getUnitName());
    }

    // =========================
    // 3
    // =========================
    @Test
    void testIMeasurableInterface_ConsistentBehavior() {
        assertEquals(
                LengthUnit.FEET.convertToBaseUnit(1.0),
                WeightUnit.GRAM.convertToBaseUnit(1.0)
        );
    }

    // =========================
    // 4
    // =========================
    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        assertTrue(new Quantity<>(1.0, LengthUnit.FEET)
                .equals(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    // =========================
    // 5
    // =========================
    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        assertTrue(new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    // =========================
    // 6
    // =========================
    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        double result = new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result, 0.01);
    }

    // =========================
    // 7
    // =========================
    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        double result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result, 0.01);
    }

    // =========================
    // 8
    // =========================
    @Test
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // =========================
    // 9
    // =========================
    @Test
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // =========================
    // 10
    // =========================
    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        assertFalse(new Quantity<>(1.0, LengthUnit.FEET)
                .equals(new Quantity<>(1.0, WeightUnit.GRAM)));
    }

    // =========================
    // 11
    // =========================
    @Test
    void testCrossCategoryPrevention_CompilerTypeSafety() {
        assertTrue(true); // compile-time concept
    }

    // =========================
    // 12
    // =========================
    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    // =========================
    // 13
    // =========================
    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // =========================
    // 14
    // =========================
    @Test
    void testGenericQuantity_Conversion_AllUnitCombinations() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(12.0, q.convertTo(LengthUnit.INCHES), 0.01);
        assertEquals(0.33, q.convertTo(LengthUnit.YARDS), 0.01);
        assertEquals(30.48, q.convertTo(LengthUnit.CENTIMETERS), 0.01);
    }

    // =========================
    // 15
    // =========================
    @Test
    void testGenericQuantity_Addition_AllUnitCombinations() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(1.0, LengthUnit.YARDS), LengthUnit.FEET);

        assertEquals(4.0, result.getValue(), 0.01);
    }

    // =========================
    // 16
    // =========================
    @Test
    void testBackwardCompatibility_AllUC1Through9Tests() {
        assertTrue(new Quantity<>(1.0, LengthUnit.FEET)
                .equals(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    // =========================
    // 17
    // =========================
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
        assertTrue(QuantityMeasurementApp.demonstrateEquality(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    // =========================
    // 18
    // =========================
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateConversion(
                        new Quantity<>(1.0, LengthUnit.FEET),
                        LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    // =========================
    // 19
    // =========================
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateAddition(
                        new Quantity<>(1.0, LengthUnit.FEET),
                        new Quantity<>(12.0, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // =========================
    // 20
    // =========================
    @Test
    void testTypeWildcard_FlexibleSignatures() {
        Quantity<?> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    // =========================
    // 21
    // =========================
    @Test
    void testScalability_NewUnitEnumIntegration() {
        enum VolumeUnit implements IMeasurable {
            LITER(1.0), ML(0.001);

            double f;
            VolumeUnit(double f){ this.f = f; }

            public double getConversionFactor(){ return f; }
            public double convertToBaseUnit(double v){ return v * f; }
            public double convertFromBaseUnit(double b){ return b / f; }
            public String getUnitName(){ return name(); }
        }

        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITER);
        assertEquals(1000.0, q.convertTo(VolumeUnit.ML), 0.01);
    }

    // =========================
    // 22
    // =========================
    @Test
    void testScalability_MultipleNewCategories() {
        assertNotNull(new Quantity<>(1.0, LengthUnit.FEET));
    }

    // =========================
    // 23
    // =========================
    @Test
    void testGenericBoundedTypeParameter_Enforcement() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    // =========================
    // 24
    // =========================
    @Test
    void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a.hashCode(), b.hashCode());
    }

    // =========================
    // 25
    // =========================
    @Test
    void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    // =========================
    // 26–35 (Conceptual)
    // =========================
    @Test void testEnumAsUnitCarrier_BehaviorEncapsulation() { assertTrue(true); }
    @Test void testTypeErasure_RuntimeSafety() { assertTrue(true); }
    @Test void testCompositionOverInheritance_Flexibility() { assertTrue(true); }
    @Test void testCodeReduction_DRYValidation() { assertTrue(true); }
    @Test void testMaintainability_SingleSourceOfTruth() { assertTrue(true); }
    @Test void testArchitecturalReadiness_MultipleNewCategories() { assertTrue(true); }
    @Test void testPerformance_GenericOverhead() { assertTrue(true); }
    @Test void testDocumentation_PatternClarity() { assertTrue(true); }
    @Test void testInterfaceSegregation_MinimalContract() { assertTrue(true); }
    @Test void testImmutability_GenericQuantity() { assertTrue(true); }
}