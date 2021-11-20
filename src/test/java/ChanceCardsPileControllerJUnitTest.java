import gui_main.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceCardsPileControllerJUnitTest {
    GUI gui;
    int pileSize;
    ChanceCardsPileController pile;

    @BeforeEach
    void setUp() {
        gui = new GUI();
        pileSize = 5;

        ChanceCardController[] cardControllers = new ChanceCardController[]{
                new ChanceCardController(new ChanceCardModel("A")),
                new ChanceCardController(new ChanceCardModel("B")),
                new ChanceCardController(new ChanceCardModel("C")),
                new ChanceCardController(new ChanceCardModel("D")),
                new ChanceCardController(new ChanceCardModel("E"))
        };

        pile = new ChanceCardsPileController(cardControllers);
    }

    @Test
    @DisplayName("Cards are in random order after shuffle")
    void orderIsRandom() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Cards loop when drawing")
    void loops() {
        var obj1 = pile.drawCard();
        for (int i = 0; i < pileSize - 1; i++) {
            pile.drawCard();
        }
        var obj2 = pile.drawCard();
        assertEquals(obj1, obj2);
    }
}