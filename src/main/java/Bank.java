public class Bank {
    public static WinnerController winnerController;

    public static void transferMoney(Player from, Player to, int amount) {
        if (!from.getAccount().withdraw(amount)) {
            System.out.println("Player " + from.getName() + " ran out of money!");
            Player winner = winnerController.getWinner();
            System.out.println("Player " + winner.getName() + " has won with a total value of " + winner.getAccount().getBalance());
        }
        to.getAccount().deposit(amount);
        to.updateAccountGUI();
        from.updateAccountGUI();
    }

    public static void payBank(Player from, int amount) {
        if (!from.getAccount().withdraw(amount)) {
            System.out.println("Player " + from.getName() + " ran out of money!");
            Player winner = winnerController.getWinner();
            System.out.println("Player " + winner.getName() + " has won with a total value of " + winner.getAccount().getBalance());
        }
        from.updateAccountGUI();
    }

    public static void payPlayer(Player to, int amount) {
        to.getAccount().deposit(amount);
        to.updateAccountGUI();
    }
}