package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleAreaTest {

    @Test
    public void calculateAreaByBaseAndHeightTestCorrectArguments() {
        int a = 5;
        int b = 10;

        int expectedArea = 25;
        int actualArea = TriangleArea.calculateAreaByBaseAndHeight(a, b);

        assertEquals(actualArea, expectedArea);
    }

    @Test
    public void calculateAreaByBaseAndHeightTestWrongArguments() {
        int a = 5;
        int b = -10;

        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculateAreaByBaseAndHeight(a, b));
    }
}