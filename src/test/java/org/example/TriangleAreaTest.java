package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    public void calculateAreaByBaseAndHeightTestCorrectArguments() {
        int a = 5;
        int b = 10;

        int expectedArea = 25;
        int actualArea = TriangleArea.calculateAreaByBaseAndHeight(a, b);

        assertEquals(expectedArea, actualArea);
    }

    @Test
    public void calculateAreaByBaseAndHeightTestWrongArguments() {
        int a = 5;
        int b = -10;

        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculateAreaByBaseAndHeight(a, b));
    }
}