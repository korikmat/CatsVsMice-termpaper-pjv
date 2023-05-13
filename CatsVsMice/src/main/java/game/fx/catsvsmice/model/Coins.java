package game.fx.catsvsmice.model;

public class Coins {
    private int coinsCount;
    public Coins(int coins){
        coinsCount = coins;
    }

    public int getCount() {
        return coinsCount;
    }

    public boolean isPaid(int price){
        if(coinsCount-price < 0){
            return false;
        }
        coinsCount-=price;
        return true;
    }
}
