package org.jfree.data.lab2tests;

import junit.framework.TestCase;
import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest extends TestCase {

    Range range;

    @BeforeEach
    public void setUp() {
        range = new Range(1.0, 5.0);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void getLowerBound() {

        assertEquals(1.0, range.getLowerBound());

    }

    @Test
    void getUpperBound() {

        // UpperBound gets the highest number.
        assertEquals(5.0, range.getUpperBound());

    }

    @Test
    void getLength() {
    }

    @Test
    void getCentralValue() {
    }

    @Test
    void contains() {
    }

    @Test
    void intersects() {
    }

    @Test
    public void testIntersects() {
    }

    @Test
    void constrain() {
    }

    @Test
    void combine() {
    }

    @Test
    void combineIgnoringNaN() {
    }

    @Test
    void expandToInclude() {
    }

    @Test
    void expand() {
    }

    @Test
    void shift() {
    }

    @Test
    public void testShift() {
    }

    @Test
    void scale() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    void isNaNRange() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
    }
}
