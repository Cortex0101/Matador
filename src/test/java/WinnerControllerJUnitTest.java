import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
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
        CarView.carCount = 0; // Workaround
        players = new Player[]{
                new Player("A",6),
                new Player("B",8),
                new Player("C",7),
                new Player("D",4)
        };
        winnerController = new WinnerController(players);
    }

    @Test
    @DisplayName("Most valuable player is found with only 1 player having that balance")
    void singularMostValuablePlayer() {
        assertEquals(winnerController.getWinner(), players[1]);
    }

    @Test
    @DisplayName("Most valuable player is found with all players having equal balance and having to count property value")
    void multipleMostValuablePlayers() {
        // Player 1 and 2 has same balance
        players[0].getAccount().deposit(4);
        players[1].getAccount().deposit(4);

        // Player 1 has properties worth 2$
        GUI_Street street1 = (GUI_Street) (fieldModel.FieldInfo()[1]);
        GUI_Street street2 = (GUI_Street) (fieldModel.FieldInfo()[2]);
        street1.setOwnerName(players[0].getName());
        street2.setOwnerName(players[0].getName());

        // Player 2 has properties worth 3$
        GUI_Street street3 = (GUI_Street) (fieldModel.FieldInfo()[14]);
        street3.setOwnerName(players[1].getName());

        // Player 2 has a balance of 14 total
        assertEquals(players[1], winnerController.getWinner());
    }
}