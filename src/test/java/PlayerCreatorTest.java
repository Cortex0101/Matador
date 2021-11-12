import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCreatorTest {
    private PlayerCreator playerCreator;
    @BeforeEach
    void setUp() {

    }

    @Test
    void PlayersAreInitialized() {
        GUI gui = new GUI();
        GUI_Car car = new GUI_Car();
        GUI_Player player = new GUI_Player("testName",0,car);
        gui.addPlayer(player);
        gui.addPlayer(player);
        this.playerCreator = new PlayerCreator(gui);
        assertEquals(2,playerCreator.getPlayerCount());
    }

}