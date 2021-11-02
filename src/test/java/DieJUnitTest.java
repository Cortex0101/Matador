import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DieJUnitTest {
    static Die die;

    @BeforeAll
    static void setup() {
        die = new Die();
    }

    /**
     * Tests die with Pearson's chi-squared test
     * https://en.wikipedia.org/wiki/Pearson's_chi-squared_test
     *
     * Test consists of 4 steps
     * 1. Tally up how many times each side was rolled (N = n_1 + n_2 + n_3 + n_4 + n_5 + n_6)
     * 2. Calculate the expected number of times each side should have rolled (n_exp = N / 6)
     * 3. Calculate difference between the expected and actual roll count (x_k = (n_k - n_exp)^2/(n_exp))
     * 4. Add the results to obtain test statistic (x = x_1 + x_2 + x_3 + x_4 + x_5 + x_6)
     *
     * Lastly the test statistic is compared to the expected using the table at https://www.itl.nist.gov/div898/handbook/eda/section3/eda3674.htm
     */
    @DisplayName("Passes pearson's chi-squared test")
    @Test
    void diceRandom() {
        final int NUM_ROLLS = 1000; // The higher, the better, obviously…

        // Count how many times each number is rolled
        int[] N = new int[6];
        for (int i = 0; i < NUM_ROLLS; i++) {
            N[die.roll() - 1]++;
        }

        // Expected rolls for each number
        final double N_EXP = NUM_ROLLS / 6.0;

        // Calculate the difference between each expected and actual number rolled, square it, and divide by expected
        double[] X = new double[6];
        for (int i = 0; i < 6; i++) {
            X[i] = Math.pow(N[i] - N_EXP, 2) / N_EXP;
        }

        // Add up results
        final double STATISTIC = Arrays.stream(X).sum();

        // Comparing to 10.645 as found in the link
        final double MIN_VALUE = 10.645;
        assertTrue(STATISTIC < MIN_VALUE);
    }
}