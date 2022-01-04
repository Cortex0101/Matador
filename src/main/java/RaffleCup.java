public class RaffleCup {
    private final Die die1 = new Die();
    private final Die die2 = new Die();

    public RaffleCup() {
        shake();
    }

    public int getEyes() {
        return die1.getEyeCount() + die2.getEyeCount();
    }

    public int shake() {
        die1.roll();
        die2.roll();
        return getEyes();
    }

    public boolean isDouble() {
        return die1.getEyeCount() == die2.getEyeCount();
    }
}
