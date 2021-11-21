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
        // Set all players to have account of 10$
        for (Player player : players) {
            player.getAccount().withdraw(player.getAccount().getBalance());
            player.getAccount().deposit(10);
        }
        // Except player 4, who has balance of 8.
        players[3].getAccount().withdraw(2);

        GUI_Street street1 = (GUI_Street) (fieldModel.FieldInfo()[1]);
        GUI_Street street2 = (GUI_Street) (fieldModel.FieldInfo()[2]);
        street1.setOwnerName(players[0].getName());
        street2.setOwnerName(players[1].getName());

        // Player 1 and 2 has balance of 11 total

        GUI_Street street3 = (GUI_Street) (fieldModel.FieldInfo()[4]);
        GUI_Street street4 = (GUI_Street) (fieldModel.FieldInfo()[5]);
        street1.setOwnerName(players[2].getName());
        street2.setOwnerName(players[2].getName());

        // Player 3 has balance of 12 total

        GUI_Street street5 = (GUI_Street) (fieldModel.FieldInfo()[7]);
        GUI_Street street6 = (GUI_Street) (fieldModel.FieldInfo()[8]);
        street5.setOwnerName(players[3].getName());
        street6.setOwnerName(players[3].getName());

        // Player 4 has balance of 14 total

        GUI_Street street7 = (GUI_Street) (fieldModel.FieldInfo()[10]);
        street7.setOwnerName(players[0].getName());

        // Player 1 has balance of 13 total

        assertEquals(players[0], winnerController.getWinner());

        GUI_Street street = (GUI_Street) (fieldModel.FieldInfo()[13]);
        street.setOwnerName(players[1].getName());

        // Make sure to reset balances again as they were set by the previous call to getWinner()
        for (Player player : players) {
            player.getAccount().withdraw(player.getAccount().getBalance());
            player.getAccount().deposit(10);
        }
        players[3].getAccount().withdraw(2);

        // Player 2 has a balance of 14 total
        assertEquals(players[1], winnerController.getWinner());
    }
}