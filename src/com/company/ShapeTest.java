package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShapeTest {

    @BeforeEach
    public void setup() throws Exception {

    }

    @Test
    @DisplayName("Basic Testing With Assert")
    public void testReturnInt() {
        assertEquals(5, 5, "Testing 5");
    }
}