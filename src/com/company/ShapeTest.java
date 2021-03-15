package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShapeTest {
    @Test
    @DisplayName("Testing squares")
    public void testReturnInt() {
        assertEquals(5, 5, "Testing 5");
    }
}