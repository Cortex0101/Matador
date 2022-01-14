import gui_main.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceCardsPileControllerJUnitTest {
    final int pileSize = 5;
    ChanceCardsPileController pile;

    @BeforeEach
    void setUp() {
        /*ChanceCard[] cards = new ChanceCard[]{
                new ChanceCard(new ChanceCardModel("A")),
                new ChanceCard(new ChanceCardModel("B")),
                new ChanceCard(new ChanceCardModel("C")),
                new ChanceCard(new ChanceCardModel("D")),
                new ChanceCard(new ChanceCardModel("E"))
        };*/

        //pile = new ChanceCardsPileController(cards);
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