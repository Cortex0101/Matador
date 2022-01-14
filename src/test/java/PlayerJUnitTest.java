import gui_fields.GUI_Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerJUnitTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Bob", 20);
    }

    @DisplayName("Player can roll dice")
    @Test
    void playerCanThrowDice() {
        int roll = player.getRaffleCup().shake();
        assertTrue(0 < roll && roll < 7);
    }
}