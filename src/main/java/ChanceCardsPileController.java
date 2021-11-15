import java.util.*;

public class ChanceCardsPileController {
    private Deque<ChanceCardController> chanceCards;

    public ChanceCardsPileController(ChanceCardController[] chanceCardControllers) {
        shufflePile(chanceCardControllers);
        chanceCards = new ArrayDeque<>(Arrays.asList(chanceCardControllers));
    }

    public ChanceCardController drawCard() {
        ChanceCardController ccc = this.chanceCards.pop();
        this.chanceCards.addLast(ccc); // Place at the bottom of pile after drawing
        return ccc;
    }

    public static void shufflePile(ChanceCardController[] chanceCardControllers) {
        Collections.shuffle(Arrays.asList(chanceCardControllers));
    }

    public void addCard(ChanceCardController chanceCardController) {
        this.chanceCards.addFirst(chanceCardController);
    }
}
