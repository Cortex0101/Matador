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
}