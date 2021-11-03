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
        int roll = player.getDie().roll();
        assertTrue(0 < roll && roll < 7);
    }

    @DisplayName("Dice stores last roll correctly")
    @Test
    void diceStoresLastRoll() {
        int lastRoll = player.getDie().roll();
        assertEquals(lastRoll, player.getDie().getLastRoll());
    }
}