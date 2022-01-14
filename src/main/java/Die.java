public class Die {

    private int value;

    public Die() {
        roll();
    }

    public int roll() {
        final int MAX_ROLL = 6;
        value = (int)(Math.random() * MAX_ROLL + 1); // Generates random number between (0 : MAX_ROLL) inclusive
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
