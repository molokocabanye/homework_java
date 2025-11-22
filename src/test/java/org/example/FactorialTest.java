package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {

    @Test
    public void factorialTest() {
        int a = 5;

        int supposedResult = 120;
        int result = Factorial.factorial(a);
        assertEquals(result, supposedResult);
    }
}