package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void plusTest() throws ArithmeticException {
        int a = 1;
        int b = 2;

        int supposedResult = 3;
        int result = Calculator.plus(a, b);
        assertEquals(supposedResult, result);
    }

    @Test
    public void minusTest() throws ArithmeticException {
        int a = 1;
        int b = 2;

        int supposedResult = -1;
        int result = Calculator.minus(a, b);
        assertEquals(supposedResult, result);
    }

    @Test
    public void multiplyTest() throws ArithmeticException {
        int a = 1;
        int b = 2;

        int supposedResult = 2;
        int result = Calculator.multiply(a, b);
        assertEquals(supposedResult, result);
    }

    @Test
    public void divideTestCorrect() throws ArithmeticException {
        int a = 2;
        int b = 2;

        int supposedResult = 1;
        int result = Calculator.divide(a, b);
        assertEquals(supposedResult, result);
    }

    @Test
    public void divideTestIncorrect() throws ArithmeticException {
        int a = 2;
        int b = 0;

        assertThrows(ArithmeticException.class, () -> Calculator.divide(a, b));
    }
}