package game.fx.catsvsmice.model;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class Cheese {
    private final Sprite sprite;
    private int lives;
    private static final double CHEESE_POS_X = WINDOW_WIDTH*273.0/320.0;
    private static final double CHEESE_POS_Y = WINDOW_HEIGHT*74.0/180.0;
    private static final String  CHEESE_PIC = "cheese.png";
    private static final double CHEESE_SCALE_X = 15.0/320.0;
    private static final double CHESSE_SCALE_Y = 11.0/180;



    private final double sizeX;
    private final double sizeY;
    public Cheese(){
        sprite = new Sprite();
        sprite.setPosXY(CHEESE_POS_X, CHEESE_POS_Y);
        sprite.setImg(CHEESE_PIC, CHEESE_SCALE_X, CHESSE_SCALE_Y);
        sizeX = sprite.getImg().getWidth();
        sizeY = sprite.getImg().getHeight();
        this.lives = 101;
    }
    public Cheese(double sizeX, double sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        sprite = new Sprite();
        sprite.setPosXY(CHEESE_POS_X, CHEESE_POS_Y);
    }

    public int getLives() {
        return lives;
    }
    public void reduceLivesCount(int damage) {
        lives -= damage;
    }
    public void reset(int lives){
        this.lives = lives;
    }

    public boolean isEaten(){
        return lives <= 0;
    }

    public boolean isInside(double mousePosX, double mousePosY){
        return mousePosX >= sprite.getPosX() && mousePosX <= sprite.getPosX()+sizeX &&
                mousePosY >= sprite.getPosY() && mousePosY <= sprite.getPosY()+sizeY;
    }

    public Sprite getSprite() {
        return sprite;
    }


}
