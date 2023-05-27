package game.fx.catsvsmice.model.objects;

import game.fx.catsvsmice.model.Sprite;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

/**
 * The type Cheese.
 * The object has coordinates, texture, and dimensions.
 *
 */
public class Cheese {
    private static final double CHEESE_POS_X = WINDOW_WIDTH*273.0/320.0;
    private static final double CHEESE_POS_Y = WINDOW_HEIGHT*74.0/180.0;
    private static final String  CHEESE_PIC = "game_cheese.png";
    private static final double CHEESE_SCALE_X = 15.0/320.0;
    private static final double CHEESE_SCALE_Y = 11.0/180;
    private final double sizeX;
    private final double sizeY;
    private final Sprite sprite;
    private int lives;

    /**
     * Instantiates a new Cheese.
     * In the constructor set the coordinates, texture, size and number of lives.
     */
    public Cheese(){
        sprite = new Sprite();
        sprite.setPosXY(CHEESE_POS_X, CHEESE_POS_Y);
        sprite.setImg(CHEESE_PIC, CHEESE_SCALE_X, CHEESE_SCALE_Y);
        sizeX = sprite.getImg().getWidth();
        sizeY = sprite.getImg().getHeight();
        this.lives = 101;
    }

    /**
     * Instantiates a new Cheese.
     * In the constructor set the coordinates, texture and number of lives.
     * You can set the size.
     *
     * @param sizeX the size x
     * @param sizeY the size y
     */
    public Cheese(double sizeX, double sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        sprite = new Sprite();
        sprite.setPosXY(CHEESE_POS_X, CHEESE_POS_Y);
    }

    /**
     * Gets lives.
     *
     * @return the actual lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Reduce lives count.
     *
     * @param damage the damage
     */
    public void reduceLivesCount(int damage) {
        lives -= damage;
    }

    /**
     * Reset lives count.
     *
     * @param lives the lives
     */
    public void reset(int lives){
        this.lives = lives;
    }

    /**
     * Is eaten.
     *
     * @return true if lives <= 0, false otherwise.
     */
    public boolean isEaten(){
        return lives <= 0;
    }

    /**
     * Is mouse inside.
     *
     * @param mousePosX the mouse pos x
     * @param mousePosY the mouse pos y
     * @return true if mouse is inside, false otherwise.
     */
    public boolean isInside(double mousePosX, double mousePosY){
        return mousePosX >= sprite.getPosX() && mousePosX <= sprite.getPosX()+sizeX &&
                mousePosY >= sprite.getPosY() && mousePosY <= sprite.getPosY()+sizeY;
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }


}
