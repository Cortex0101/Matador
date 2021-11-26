public class Die {
    private final int MAX_ROLL = 6;

    private int value;

    public Die() {
        roll();
    }

    public int roll() {
        value = (int)(Math.random() * MAX_ROLL + 1); // Generates random number between (0 : MAX_ROLL) inclusive
        return value;
    }
}
