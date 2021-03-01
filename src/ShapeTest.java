import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ShapeTest {
    private Shape myShape;

    @BeforeEach
    public void setup() throws Exception {
        myShape = new Shape();
    }

    @Test
    @DisplayName("Basic Testing With Assert")
    public void testReturnInt() {
        assertEquals(5, myShape.returnint(5), "Testing 5");
    }
}