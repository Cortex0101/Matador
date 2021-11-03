import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountJUnitTest {
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account(20);
    }

    @DisplayName("Account cant go negative")
    @Test
    void accountCantGoNegative() {
        assertFalse(account.withdraw(account.getBalance() + 1));
        assertFalse(account.deposit(-account.getBalance() + 1));
    }

    @DisplayName("Cant desposit negative amounts")
    @Test
    void cantDepositNegativeAmounts() {
        assertFalse(account.deposit(-1));
        assertFalse(account.deposit(Integer.MIN_VALUE));
    }

    @DisplayName("Cant withdraw negative amounts")
    @Test
    void cantWithdrawNegativeAmounts() {
        assertFalse(account.withdraw(-1));
        assertFalse(account.withdraw(Integer.MIN_VALUE));
    }
}