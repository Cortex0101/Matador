import gui_fields.GUI_Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerLostControllerJUnitTest {
    FieldModel fieldModel;
    Player[] players;
    PlayerLostController playerLostController;

    @BeforeEach
    void setUp() {
        fieldModel = new FieldModel();
        CarView.carCount = 0; // Workaround
        players = new Player[]{
                new Player("A",6),
                new Player("B",8),
                new Player("C",7),
                new Player("D",4)
        };
        playerLostController = new PlayerLostController();
    }

    @Test
    @DisplayName("Most valuable player is found with only 1 player having that balance")
    void singularMostValuablePlayer() {
    }

    @Test
    @DisplayName("Most valuable player is found with all players having equal balance and having to count property value")
    void multipleMostValuablePlayers() {

    }
}