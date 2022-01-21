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
            return GUIInstance.getInstance().getUserInteger("How many fields would you like to move?");
        }

        die1.roll();
        die2.roll();
        return getEyes();
    }

    public boolean isDouble() {
        if (Debugger.getInstance().enabled()) {
            return false; // Never allow double roles in debugging mode.
        }
        return die1.getValue() == die2.getValue();
    }
}
