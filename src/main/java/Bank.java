public class Bank {
    public static void transferMoney(Player from, Player to, int amount) {
        if (!from.getAccount().withdraw(amount)) {
            System.out.println("Player " + from.getName() + " ran out of money!");
        }
        to.getAccount().deposit(amount);
        to.updateAccountGUI();
        from.updateAccountGUI();
    }

    public static void payBank(Player from, int amount) {
        if (!from.getAccount().withdraw(amount)) {
            System.out.println("Player " + from.getName() + " ran out of money!");
        }
        from.updateAccountGUI();
    }

    public static void payPlayer(Player to, int amount) {
        to.getAccount().deposit(amount);
        to.updateAccountGUI();
    }
}