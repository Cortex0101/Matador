public class RaffleCup {
    private final Die die1 = new Die();
    private final Die die2 = new Die();

    public RaffleCup() {
        shake();
    }

    public int[] getIndividualEyes() {
        return new int[]{die1.getValue(), die2.getValue()};
    }

    public int getEyes() {
        return die1.getValue() + die2.getValue();
    }

    public int shake() {
        die1.roll();
        die2.roll();
        return getEyes();
    }

    public boolean isDouble() {
        return die1.getValue() == die2.getValue();
    }
}
