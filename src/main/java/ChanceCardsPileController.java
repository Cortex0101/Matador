import java.util.*;

public class ChanceCardsPileController {
    private final Deque<ChanceCard> chanceCards;

    public ChanceCardsPileController(ChanceCard[] chanceCards) {
        shufflePile(chanceCards);
        this.chanceCards = new ArrayDeque<>(Arrays.asList(chanceCards));
    }

    public ChanceCard drawCard() {
        ChanceCard cc = this.chanceCards.pop();
        this.chanceCards.addLast(cc); // Place at the bottom of pile after drawing
        return cc;
    }

    public static void shufflePile(ChanceCard[] chanceCards) {
        Collections.shuffle(Arrays.asList(chanceCards));
    }
}
