public class Account {
    private int balance;

    public Account(int startingCapital) {
        balance = startingCapital;
    }

    /**
     * Transaction will not succeed if a negative amount is entered
     *
     * @param amount to deposit
     * @return true if transaction was succesfull, false otherwise
     */
    public boolean deposit(int amount) {
        if (amountIsNegative(amount)) {
            return false;
        }

        balance += amount;
        return true;
    }

    /**
     * Transaction will not succeed if a negative amount is entered
     * Additionally money will not be withdrawn if the balance is too small
     *
     * @param amount to withdraw
     * @return true if transaction was succesfull, false otherwise
     */
    public boolean withdraw(int amount) {
        if (amountIsNegative(amount)) {
            return false;
        } else if (amountIsNegative(balance - amount)) {
            return false;
        }

        balance -= amount;
        return true;
    }

    private boolean amountIsNegative(int amount) {
        return amount < 0;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
