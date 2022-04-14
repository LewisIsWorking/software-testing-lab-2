package org.jfree.data.lab2tests;

import junit.framework.TestCase;
import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testGetLowerBound() {

        assertNotSame(2.0, range1_5.getLowerBound());
        assertEquals(1.0, range1_5.getLowerBound());

    }

    @Test
    public void testGetUpperBound() {

        // UpperBound gets the highest number.
        assertNotSame(2.0, range1_5.getUpperBound());
        assertEquals(5.0, range1_5.getUpperBound());

    }

    @Test
    public void testGetLength() {

        assertNotSame(2.0, range1_5.getLength());
        assertEquals(5.0, range1_5.getLength());

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

    @Test
    public void testIntersects() {

        assertEquals(true, range1_5.intersects(range5_10));
        assertNotSame(true, range1_5.intersects(range_n1_n5));

    }

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
    public void testCombineIgnoringNaN() {
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

    }

    @Test
    public void testScale() {

        double doubleData1 = 5.0;
        double doubleData2 = -5.0;

        // Range[25.0,50.0]
        Range range25_50 = new Range(25.0, 50.0);

        assertEquals("Assert #1. ", range25_50, Range.scale(range5_10,doubleData1));
        assertNotSame("Assert #2. ",null, Range.scale(range5_10, doubleData2));

    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testIsNaNRange() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
    }

}
