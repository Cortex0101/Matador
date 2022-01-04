import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaffleCupTest {
    RaffleCup raffleCup;

    @BeforeEach
    void setUp() {
        raffleCup = new RaffleCup();
    }

    @DisplayName("Can detect a double roll")
    @Test
    void doubleRollDetection() {
        do {
            raffleCup.shake();
        } while (raffleCup.getEyes() != 2 && raffleCup.getEyes() != 12);
        assertTrue(raffleCup.isDouble()); // If the sum of eyes were 2 or 12, we must of roll double 1 or 6...
    }
}