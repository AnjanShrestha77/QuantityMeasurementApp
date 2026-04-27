import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public  class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality_SameValue(){

        QuantityMeasurementApp.Feet f1= new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2= new QuantityMeasurementApp.Feet(1.0);
        boolean output=f1.equals(f2);
        assertTrue(output);
    }


@Test
public void testEquality_DifferentValue(){
    QuantityMeasurementApp.Feet f1= new QuantityMeasurementApp.Feet(1.0);
    QuantityMeasurementApp.Feet f2 =new QuantityMeasurementApp.Feet(2.0);
    boolean output = f1.equals(f2);
    assertFalse(output);
}

@Test
    public void testEquality_NullComparison(){
    QuantityMeasurementApp.Feet f1 =new QuantityMeasurementApp.Feet(1.0);
    QuantityMeasurementApp.Feet f2 = null;
    boolean output = f1.equals(f2);
    assertFalse(output);
}

@Test
    public void testEquality_NonNumericInput(){
        QuantityMeasurementApp.Feet f1= new QuantityMeasurementApp.Feet(1.0);

        boolean output = f1.equals("assss");
        assertFalse(output);


}

@Test
    public void testEquality_SameReference(){
  QuantityMeasurementApp.Feet f1=new QuantityMeasurementApp.Feet(1.0);
  QuantityMeasurementApp.Feet f2= f1;
  boolean output= f1.equals(f2);
  assertTrue(output);
}
}