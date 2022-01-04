public class RaffleCup {
    private Die die1;
    private Die die2;

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
