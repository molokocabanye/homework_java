package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IntegerComparatorTest {

    @Test
    public void compareTestEqualIntegers() {
        int a = 1;
        int b = 1;

        int supposedResult = 0;
        int result = IntegerComparator.compare(a, b);
        assertEquals(result, supposedResult);
    }

    @Test
    public void compareTestFirstIntBigger() {
        int a = 2;
        int b = 1;

        int supposedResult = 1;
        int result = IntegerComparator.compare(a, b);
        assertEquals(result, supposedResult);
    }

    @Test
    public void compareTestSecondIntBigger() {
        int a = 2;
        int b = 3;

        int supposedResult = -1;
        int result = IntegerComparator.compare(a, b);
        assertEquals(result, supposedResult);
    }
}