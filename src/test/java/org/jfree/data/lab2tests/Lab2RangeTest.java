package org.jfree.data.lab2tests;

import junit.framework.TestCase;

import org.junit.jupiter.api.Assertions;
import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.jfree.chart.axis.LogarithmicAxisTest.EPSILON;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Lab2RangeTest extends TestCase {

    Range range5_10;
    Range range1_5;
    Range range_n1_n5;

    @BeforeEach
    public void setUp() {
        range5_10 = new Range(5, 10);
        range1_5 = new Range(1.0, 5.0);
        range_n1_n5 = new Range(-5.0, -1.0);
    }

    @AfterEach
    public void tearDown() {

    }

    //added in lab 3
    @Test
    public void testGetLowerBound() {

        assertNotSame(2.0, range1_5.getLowerBound());
        assertEquals(1.0, range1_5.getLowerBound());

    }

    //added in lab 3
    @Test
    public void testGetUpperBound() {

        // UpperBound gets the highest number.
        assertNotSame(2.0, range1_5.getUpperBound());
        assertEquals(5.0, range1_5.getUpperBound());

    }

    @Test
    public void testGetLength() {

        assertNotSame(2.0, range1_5.getLength());
        assertEquals(4.0, range1_5.getLength());

    }

    @Test
    public void testGetCentralValue() {

        // 6. False.
        assertNotSame("assertNotSame Passed. ", 6.0, range1_5.getCentralValue());

        // 3. True.
        assertEquals("assertEquals Passed. ", 3.0, range1_5.getCentralValue());

        // -2. False.
        assertNotSame("assertNotSame Passed. ", -2.0, range_n1_n5.getCentralValue());

        // -3. True.
        assertEquals("assertEquals Passed. ", -3.0, range_n1_n5.getCentralValue());

    }

    @Test
    public void testContains() {

        assertTrue("Assert #1. ", range1_5.contains(3));

        assertFalse("Assert #2. ", range1_5.contains(6));

        assertFalse("Assert #3. ", range1_5.contains(0));

        assertFalse("Assert #4. ", range1_5.contains(-5));

        assertNotSame("Assert #5. ", true, range1_5.contains(99999));

    }

    //added in lab 3
    @Test
    public void testIntersects() {

        assertEquals(true, range5_10.intersects(range5_10));
        assertNotSame(true, range1_5.intersects(range_n1_n5));

    }


    //added in lab 3
    @Test
    public void testConstrain() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;

        assertEquals(5.0, range1_5.constrain(doubleData1));
        assertNotSame(null, range1_5.constrain(doubleData2));

    }

    @Test
    public void testCombine() {

        Range range5_10 = new Range(5, 10);
        Range range1_5 = new Range(1, 5);
        Range range1_10 = new Range(1, 10);

        // Ranges: null and 5 -> 10. Expected: 5 -> 10.
        assertEquals("assertEquals Passed. ", range5_10, Range.combine(null,range5_10));

        // Ranges: Null and Null. Expected:  Null.
        assertNull("assertEquals Passed. ", Range.combine(null, null));

        //Ranges: 1 -> 5 and 1 -> 10. Expected: 1 -> 10
        assertEquals("assertEquals Passed. ", range1_10, Range.combine(range1_5,range1_10));

    }

    @Test
    public void testCombineIgnoringNaN()
    {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(5.0, 10.0);

        assertEquals("Assert Equals #1 ", range1, Range.combineIgnoringNaN(range1, null));
        assertEquals("Assert Equals #2 ", range2, Range.combineIgnoringNaN(null, range2));
        assertNull(Range.combine(null,null));
    }

    @Test
    public void testExpandToInclude() {

        Range range0_5 = new Range(0, 5);
        Range range1_5 = new Range(1, 5);
        Range range0_6 = new Range(0, 6);
        Range range1_6 = new Range(1, 6);
        Range range3_3 = new Range(3, 3);

        assertEquals("Pass. range3_3", range3_3, Range.expandToInclude(null, 3));

        assertEquals("Pass. range1_6", range1_6, Range.expandToInclude(range1_5, 6));

        assertNotSame("Pass. range0_6", range0_6, Range.expandToInclude(range0_5, 5));

    }

    @Test
    public void testExpand() {

        Range range_n5_n5 = new Range(-5, -5);
        Range range_n19_9 = new Range(-19, 9);
        Range range0_16 = new Range(0, 16);
        Range range1_5 = new Range(1, 5);
        Range range2_4 = new Range(2, 4);

        assertEquals("Assert #1. ", range0_16, Range.expand(range2_4, 1.0,6.0));

        assertEquals("Assert #2. ", range_n5_n5, Range.expand(range1_5,-1, -5 ));

        assertNotSame("Assert #3. ", range1_5, Range.expand(range1_5, 0.0, 0.0));

        assertEquals("Assert #4. ", range_n19_9, Range.expand(range1_5, 5, 1));

    }

    @Test
    public void testShift() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;

        // Range[10.0,15.0]
        Range range10_15 = new Range(10.0, 15.0);

        assertEquals("Assert #1. ",range10_15, Range.shift(range5_10,doubleData1));
        assertNotSame("Assert #2. ",null, Range.shift(range5_10, doubleData2));

        Range testRange1 = new Range(5.0, 10.0);
        Range testRange2 = Range.shift(testRange1, 20.0);
        assertEquals(25.0, testRange2.getLowerBound(), 0.001);
        assertEquals(30.0, testRange2.getUpperBound(), 0.001);

        testRange1 = new Range(0.0, 50.0);
        testRange2 = Range.shift(testRange1, 70.0, true);
        assertEquals(70.0, testRange2.getLowerBound(), 0.001);
        assertEquals(120.0, testRange2.getUpperBound(), 0.001);

        testRange1 = new Range(-10.0, 35.0);
        testRange2 = Range.shift(testRange1, 20.0, true);
        assertEquals(10.0, testRange2.getLowerBound(), 0.001);
        assertEquals(55.0, testRange2.getUpperBound(), 0.001);

        testRange1 = new Range(-50, 20.0);
        testRange2 = Range.shift(testRange1, -30.0, true);
        assertEquals(-80.0, testRange2.getLowerBound(), 0.001);
        assertEquals(-10.0, testRange2.getUpperBound(), 0.001);

        testRange1 = new Range(-69, 420.0);
        testRange2 = Range.shift(testRange1, 20.0, false);
        assertEquals(-49.0, testRange2.getLowerBound(), 0.001);
        assertEquals(440.0, testRange2.getUpperBound(), 0.001);

        testRange1 = new Range(-100.0, 20.0);
        testRange2 = Range.shift(testRange1, -30.0, false);
        assertEquals(-130.0, testRange2.getLowerBound(), 0.001);
        assertEquals(0.0, testRange2.getUpperBound(), 0.001);

    }

    @Test
    public void testScale() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;

        // Range[25.0,50.0]

        // Range[25.0,50.0]
        Range range25_50 = new Range(25.0, 50.0);

        assertEquals("Assert #1. ", range25_50, Range.scale(range5_10,doubleData1));
        assertNotSame("Assert #2. ",null, Range.scale(range5_10, doubleData1));

    }

    @Test
    public void testEquals() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;

        // Range[25.0,50.0]
        Range range25_50 = new Range(25.0, 50.0);

        assertFalse("Assert #1. ", range1_5.equals(range25_50));
        assertFalse("Assert #2. ", range5_10.equals(range1_5));

        assertEquals(range1_5, range1_5);
        assertEquals(range25_50, range25_50);

        assertFalse(range1_5.equals(10.0));

        Range testRange1 = new Range(12.0, 35.0);
        Range testRange2 = new Range(2.0, 36.0);
        assertFalse(testRange1.equals(testRange2));

    }

    @Test
    public void testIsNaNRange() {

        Range testRange1 = new Range(1.0, 5.0);
        Range testRange2 = new Range(5.5, 10.5);
        Range testRange3 = new Range(Double.NaN, 5.0);
        Range testRange4 = Range.combineIgnoringNaN(testRange1, testRange3);

        assertTrue("Assert True: ", new Range(Double.NaN, Double.NaN).isNaNRange());
        assertFalse("Assert False: " , new Range(1.0, 5.0).isNaNRange());

        assertEquals(1.0, testRange4.getLowerBound(), EPSILON);
        assertEquals(5.0, testRange4.getUpperBound(), EPSILON);

        Range testRange5 = new Range(1.7, Double.NaN);
        testRange4 = Range.combineIgnoringNaN(testRange5, testRange1);
        assertEquals(1.0, testRange4.getLowerBound(), EPSILON);
        assertEquals(5.0, testRange4.getUpperBound(), EPSILON);

    }

    @Test
    public void testHashCode() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;


        Range range25_50 = new Range(25.0, 50.0);
        Range range25_50_2 = new Range(25.0, 50.0);

        assertEquals(range25_50.hashCode(), range25_50_2.hashCode());
    }

    @Test
    public void testToString() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;

        // Range[25.0,50.0]
        Range range25_50 = new Range(25.0, 50.0);

        assertEquals("Assert #1. ", "Range[25.0,50.0]", range25_50.toString());
        assertNotSame("Assert #2. ",null, range25_50.toString());

    }

//    @Test
//    public void testMax()
//    {
//        double minDbl = 1.0;
//        double maxDbl = 5.0;
//
//        assertEquals("Assert #1. ", maxDbl, range1_5.max(minDbl, maxDbl));
//    }

//    @Test
//    public void testMin()
//    {
//        double minDbl = 1.0;
//        double maxDbl = 5.0;
//
//        assertEquals("Assert #1", minDbl, range1_5.min(minDbl, maxDbl));
//    }

    @Test
    public void testConstructor()
    {
        Range testRange = new Range(1.0, 5.0);
        
        assertNotNull("Assert Not Null #1: ", testRange);
        
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Code under test
        	Range testRange2 = new Range(10.0, 5.0);
            assertNotSame("Assert #3. ", testRange , testRange2);
        });
        Assertions.assertEquals("Range(double, double): require lower (10.0) <= upper (5.0).", thrown.getMessage());
    }

    @Test
    public void testShiftWithNoZeroCrossing()
    {
        double testVal = 1.0;
        double testDelta = 2.0;

        Range testRange = new Range(1.0, 5.0);
        Range testRange2 = Range.shift(testRange, 20.0, false);

        Range expectedRange = new Range(21.0,25.0);

        assertEquals("assertion #1",expectedRange, testRange2);

    }

    // Shift with no zero crossing: 40%

}
