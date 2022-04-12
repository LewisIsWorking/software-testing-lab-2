package org.jfree.data.lab2tests;

import junit.framework.TestCase;
import org.jfree.data.DataUtils;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class DataUtilsTest extends TestCase {

    private Values2D values2D;

    @BeforeEach
    public void setUp() {

        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        this.values2D = testValues;
        testValues.addValue(5,1,1);

    }

    @AfterEach
    public void tearDown() {

        this.values2D = null;

    }

    @Test
    public void testEqual() {

    }

    @Test
    public void testClone() {
    }

    // This tests calculateColumnTotal(value2d, int)
    @Test
    public void testCalculateColumnTotal_values2D_int_() throws IndexOutOfBoundsException {

        assertEquals("Assert #1. ", 5, DataUtils.calculateColumnTotal(values2D, 1));

        assertNotSame("Assert #2. ", 5, DataUtils.calculateColumnTotal(values2D, 6));

        assertNotSame("Assert #3. ", 5, DataUtils.calculateColumnTotal(values2D, -5));

    }

    // This tests calculateColumnTotal(value2d, int, int[])
    @Test
    public void testCalculateColumnTotal_values2d_int_intArray() throws IndexOutOfBoundsException {

        int[] validRows1 = new int[]{1};

        assertEquals("Assert #1. ", 5, DataUtils.calculateColumnTotal(values2D, 1, validRows1));

        int[] validRows2 = new int[]{1};

        assertEquals("Assert #2. ", 5, DataUtils.calculateColumnTotal(values2D, 6, validRows2));

        int[] validRows3 = new int[]{9};

        assertEquals("Assert #3. ", 5, DataUtils.calculateColumnTotal(values2D, -5, validRows3));

    }

    // This tests calculateRowTotal(value2d, int)
    @Test
    public void testCalculateRowTotal_values2D_int() {

        assertEquals("Assert #1. ", 5, DataUtils.calculateRowTotal(values2D, 1));

        assertNotSame("Assert #2. ", 5, DataUtils.calculateRowTotal(values2D, 6));

        assertNotSame("Assert #3. ", 5, DataUtils.calculateRowTotal(values2D, -5));

    }

    // This tests calculateRowTotal(value2d, int, int[])
    @Test
    public void testCalculateRowTotal_values2D_int_intArray() {

        int[] validCols1 = new int[]{1};

        assertEquals("Assert #1. ", 5, DataUtils.calculateRowTotal(values2D, 1, validCols1));

        int[] validCols2 = new int[]{5};

        assertNotSame("Assert #2. ", 5, DataUtils.calculateRowTotal(values2D, 6, validCols2));

        int[] validCols3 = new int[]{0};

        assertEquals("Assert #3. ", 0, DataUtils.calculateRowTotal(values2D, -5, validCols3));

    }

    @Test
    public void testCreateNumberArray() {

        double[] data1 = new double[]{2,2};

        assertEquals("Assert #1. ", new double[]{2.0,2.0}, DataUtils.createNumberArray(data1));

        assertNotSame("Assert #2. ", null, DataUtils.createNumberArray(null));

        double[] data2 = new double[]{-1,-1};

        assertNotSame("Assert #3. ", null, DataUtils.createNumberArray(data2));

    }

    @Test
    public void testCreateNumberArray2D() {

    }

    @Test
    public void testGetCumulativePercentages() {

    }
}
