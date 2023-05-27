import game.fx.catsvsmice.model.objects.Coins;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The type Coins test.
 */
public class CoinsTest {
    /**
     * Test spending money.
     */
    @Test
    public void testSpendingMoney(){
        Coins coins = new Coins(100);
        boolean result = coins.isPaid(100);

        assertTrue("coins should be paid, but were not", result);

        result = coins.isPaid( 50);
        assertFalse("coins should be paid, but were not", result);
    }

}
