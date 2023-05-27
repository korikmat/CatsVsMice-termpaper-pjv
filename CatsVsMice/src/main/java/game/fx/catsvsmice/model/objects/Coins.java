package game.fx.catsvsmice.model.objects;

/**
 * The type Coins.
 * Has count of coins.
 */
public class Coins {
    private int coinsCount;

    /**
     * Instantiates a new Coins.
     * You can set count of coins.
     *
     * @param coins the coins
     */
    public Coins(int coins){
        coinsCount = coins;
    }

    /**
     * Gets coins count.
     *
     * @return the coins count
     */
    public int getCount() {
        return coinsCount;
    }

    /**
     * Is paid.
     *
     * @param price the price
     * @return true if there was enough money to pay(count of coins - prise >= 0), false otherwise.
     */
    public boolean isPaid(int price){
        if(coinsCount-price < 0){
            return false;
        }
        coinsCount-=price;
        return true;
    }

    /**
     * Earn.
     *
     * @param wages the wages
     */
    public void earn(int wages){
        coinsCount+=wages;
    }

    /**
     * Reset coins count.
     *
     * @param coinsCount the coins count
     */
    public void reset(int coinsCount){
        this.coinsCount = coinsCount;
    }
}
