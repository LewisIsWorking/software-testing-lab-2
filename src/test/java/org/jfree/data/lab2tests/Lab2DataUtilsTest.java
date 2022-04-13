package org.jfree.data.lab2tests;

import junit.framework.TestCase;
import org.jfree.data.DataUtils;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lab2DataUtilsTest extends TestCase {

    private Values2D values2D;

    @BeforeEach
    public void setUp() {

        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        this.values2D = testValues;

        // Values 2D:
        // 01 | 02 | 03 | 04 | 05 |
        // 06 | 07 | 08 | 09 | 10 |

        testValues.addValue(1,1,1);
        testValues.addValue(2,1,2);
        testValues.addValue(3,1,3);
        testValues.addValue(4,1,4);
        testValues.addValue(5,1,5);

        testValues.addValue(6,2,1);
        testValues.addValue(7,2,2);
        testValues.addValue(8,2,3);
        testValues.addValue(9,2,4);
        testValues.addValue(10,2,5);

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

        assertEquals("Assert #1. ", 9.0, DataUtils.calculateColumnTotal(values2D, 1));

        assertNotSame("Assert #2. ", 5, DataUtils.calculateColumnTotal(values2D, 4));

        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            //Code under test
            assertNotSame("Assert #3. ", 5, DataUtils.calculateColumnTotal(values2D, -5));

        });
        Assertions.assertEquals("Index -5 out of bounds for length 5", thrown.getMessage());

    }

    // This tests calculateColumnTotal(value2d, int, int[])
    @Test
    public void testCalculateColumnTotal_values2d_int_intArray() throws IndexOutOfBoundsException {

        int[] validRows1 = {1, 2};

        // 1 + 6.
        assertEquals("Assert #1. ", 7.0, DataUtils.calculateColumnTotal(values2D, 1, validRows1));

        int[] validRows2 = {1};

        // 10.
        assertEquals("Assert #2. ", 10.0, DataUtils.calculateColumnTotal(values2D, 4, validRows2));

        int[] validRows3 = {1};

        // The column -5 doesn't exist.
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            //Code under test
            assertEquals("Assert #3. ", 5, DataUtils.calculateColumnTotal(values2D, -3, validRows3));

        });
        Assertions.assertEquals("Index -3 out of bounds for length 5", thrown.getMessage());

    }

    // This tests calculateRowTotal(value2d, int)
    @Test
    public void testCalculateRowTotal_values2D_int() {

        // Assert #1.
        assertEquals("Assert #1. ", 15.0, DataUtils.calculateRowTotal(values2D, 0));

        // Assert #2.
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            //Code under test
            assertNotSame("Assert #2. ", 1, DataUtils.calculateRowTotal(values2D, 6));

        });
        Assertions.assertEquals("Index 6 out of bounds for length 2", thrown.getMessage());

        // Assert #3.
        assertNotSame("Assert #3. ", 1, DataUtils.calculateRowTotal(values2D, 1));

    }

    // This tests calculateRowTotal(value2d, int, int[])
    @Test
    public void testCalculateRowTotal_values2D_int_intArray() {

        int[] validCols1 = {0, 1, 2, 3};

        assertEquals("Assert #1. ", 30.0, DataUtils.calculateRowTotal(values2D, 1, validCols1));

        int[] validCols2 = new int[]{5};

        assertNotSame("Assert #2. ", 5, DataUtils.calculateRowTotal(values2D, 6, validCols2));

        int[] validCols3 = new int[]{0};

        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            //Code under test
            assertEquals("Assert #3. ", 0, DataUtils.calculateRowTotal(values2D, -5, validCols3));

        });
        Assertions.assertEquals("Index -5 out of bounds for length 2", thrown.getMessage());

    }

    @Test
    public void testCreateNumberArray() {

        double[] data1 = new double[]{1,2,3};
        ArrayList<Double> expectedNumber1 = new ArrayList<>();
        expectedNumber1.add(1.0);
        expectedNumber1.add(2.0);
        expectedNumber1.add(3.0);

        Number[] actualNumber1_num = DataUtils.createNumberArray(data1);
        ArrayList<Double> actualNumber1_double = new ArrayList<>();

        int loop = 0;
        for (Number i: actualNumber1_num) {
            actualNumber1_double.add((Double) actualNumber1_num[loop]);
            loop = loop + 1;
        }

        // This test sees if the values input are the same as the output as the number class should mean they are the same.
        assertEquals("Assert #1. ", expectedNumber1, actualNumber1_double);

        // Assert #2.
        double[] data2 = new double[]{};
        Number[] actualNumber2_num =DataUtils.createNumberArray(data2);
        int actualNumber2_length = actualNumber2_num.length;
        assertEquals("Assert #2. ", 0, actualNumber2_length);

        // Assert #3.
        double[] data3 = new double[]{-1,-1};
        assertNotSame("Assert #3. ", null, DataUtils.createNumberArray(data3));

        // Assert #4.
        double[] data4 = new double[]{1,2,3};
        Number[] actualNumber4 =  DataUtils.createNumberArray(data4);

        // The same values as we think "create Number Array" will make.
        ArrayList<Number> expectedNumber4 = new ArrayList<>();
        expectedNumber4.add(1.0);
        expectedNumber4.add(2.0);
        expectedNumber4.add(3.0);

        loop = 0;
        boolean assert4Result = false;
        int correctCount = 0;
        for (Number i: actualNumber4) {

            System.out.println("actualNumber: " + actualNumber4[loop] + ". expectedNumber: " + expectedNumber4.get(loop));

            if(actualNumber4[loop].equals(expectedNumber4.get(loop))){
                assert4Result = true;
                correctCount = correctCount + 1;
            }
            else {
                assert4Result = false;
                break;
            }

            loop = loop + 1;
        }

        if(correctCount != actualNumber4.length)
        {
            assert4Result = false;
        }

        // This compares each value of the collections of Numbers, if all of them are the same
        assertTrue("Assert #4. ", assert4Result);

    }

    @Test
    public void testCreateNumberArray2D() {

    }

    @Test
    public void testGetCumulativePercentages() {

    }


}
