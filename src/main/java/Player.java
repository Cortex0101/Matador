public class Player {
    private final String name;
    private final Die die;
    private final Account account;

    public Player(String name, int initialCapital) {
        this.name = name;
        this.die = new Die();
        this.account = new Account(initialCapital);
    }

    public Die getDie() {
        return die;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
