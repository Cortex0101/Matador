public class Player {
    private final String name;
    private final Die die;

    public Player(String name) {
        this.name = name;
        this.die = new Die();
    }

    public Die getDie() {
        return die;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
