import gui_fields.GUI_Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinnerControllerJUnitTest {
    FieldModel fieldModel;
    Player[] players;
    WinnerController winnerController;

    @BeforeEach
    void setUp() {
        fieldModel = new FieldModel();
        players = new Player[]{
                new Player("A",6, fieldModel.FieldInfo()),
                new Player("B",8, fieldModel.FieldInfo()),
                new Player("C",7, fieldModel.FieldInfo()),
                new Player("D",4, fieldModel.FieldInfo())
        };
        winnerController = new WinnerController(players, fieldModel);
    }

    @Test
    @DisplayName("Most valuable player is found with only 1 player having that balance")
    void singularMostValuablePlayer() {
        assertEquals(winnerController.getPlayerWithMostValuableAccount(), players[1]);
    }
}