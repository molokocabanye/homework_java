package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FactorialTest {

    @Test
    public void factorialTest() {
        int a = 5;

        int supposedResult = 120;
        int result = Factorial.factorial(a);
        Assertions.assertEquals(supposedResult, result);
    }
}