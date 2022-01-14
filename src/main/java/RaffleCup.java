public class RaffleCup {
    private final Die die1 = new Die();
    private final Die die2 = new Die();

    public RaffleCup() {
    }

    public int[] getIndividualEyes() {
        return new int[]{die1.getValue(), die2.getValue()};
    }

    public int getEyes() {
        return die1.getValue() + die2.getValue();
    }

    public int shake() {
        if (Debugger.getInstance().enabled()) {
            return Debugger.getInstance().getIntegerFromConsole();
        }

        die1.roll();
        die2.roll();
        return getEyes();
    }

    public boolean isDouble() {
        return die1.getValue() == die2.getValue();
    }
}
