

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-2;

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(2.0, Length.LengthUnit.FEET));

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length result = new Length(6.0, Length.LengthUnit.INCHES)
                .add(new Length(6.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length result = new Length(12.0, Length.LengthUnit.INCHES)
                .add(new Length(1.0, Length.LengthUnit.FEET));

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length result = new Length(1.0, Length.LengthUnit.YARDS)
                .add(new Length(3.0, Length.LengthUnit.FEET));

        assertEquals(new Length(2.0, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length result = new Length(2.54, Length.LengthUnit.CENTIMETERS)
                .add(new Length(1.0, Length.LengthUnit.INCHES));

        assertEquals(5.08,
                result.convertTo(Length.LengthUnit.CENTIMETERS).value,
                EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    public void testAddition_WithZero() {
        Length result = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(0.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NegativeValues() {
        Length result = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(-2.0, Length.LengthUnit.FEET));

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, Length.LengthUnit.FEET).add(null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        Length result = new Length(1e6, Length.LengthUnit.FEET)
                .add(new Length(1e6, Length.LengthUnit.FEET));

        assertEquals(new Length(2e6, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_SmallValues() {
        Length result = new Length(0.001, Length.LengthUnit.FEET)
                .add(new Length(0.002, Length.LengthUnit.FEET));

        assertEquals(0.003,
                result.convertTo(Length.LengthUnit.FEET).value,
                EPSILON);
    }

}