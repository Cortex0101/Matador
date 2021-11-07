import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarModelJUnitTest {
    CarModel carModel;

    @BeforeEach
    void setUp() {
        carModel = new CarModel();
    }

    @DisplayName("Car can move")
    @Test
    void canMove() {
        this.carModel.move(6);
        assertEquals(6, this.carModel.getPosition());
    }

    @DisplayName("Car will wrap around board")
    @Test
    void willWrap() {
        this.carModel.move(26);
        assertEquals(2, this.carModel.getPosition());
    }

    @DisplayName("Car cant go out of range")
    @Test
    void staysInRange() {
        this.carModel.move(12345);
        assertTrue(this.carModel.getPosition() <= 23);
    }
}