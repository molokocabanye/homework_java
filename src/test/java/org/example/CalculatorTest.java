package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CalculatorTest {

    @Test
    public void testPlus() {
        int a = 1;
        int b = 2;

        int supposedResult = 3;
        int result = Calculator.plus(a, b);
        assertEquals(result, supposedResult);
    }

    @Test
    public void testMinus() {
        int a = 2;
        int b = 2;

        int supposedResult = 0;
        int result = Calculator.minus(a, b);
        assertEquals(result, supposedResult);
    }

    @Test
    public void testMultiply() {
        int a = 10;
        int b = 2;

        int supposedResult = 20;
        int result = Calculator.multiply(a, b);
        assertEquals(result, supposedResult);
    }

    @Test
    public void testDivide() {
        int a = 2;
        int b = 2;

        int supposedResult = 1;
        int result = Calculator.divide(a, b);
        assertEquals(result, supposedResult);
    }

    @Test
    public void testDivideZeroDivide() {
        int a = 2;
        int b = 0;

        assertThrows(ArithmeticException.class, () -> Calculator.divide(a, b));
    }
}