public class Bank {
    public static boolean transferMoney(Account from, Account to, int amount) {
        if (!from.withdraw(amount)) {
            return false;
        }
        return to.deposit(amount);
    }

    public static boolean payBank(Account account, int amount) {
        return account.withdraw(amount);
    }
}