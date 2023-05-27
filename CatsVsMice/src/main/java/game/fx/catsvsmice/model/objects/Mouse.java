package game.fx.catsvsmice.model.objects;

import game.fx.catsvsmice.model.Sprite;
import game.fx.catsvsmice.view.GameView;

/**
 * The type Mouse.
 * The object has its original position, size,
 * texture, number of lives, as well as status.
 */
public class Mouse {
    private static final double MOUSE_POS_X = GameView.WINDOW_WIDTH*(80.0/1920.0);
    private static final double MOUSE_POS_Y = GameView.WINDOW_HEIGHT*(420.0/1080.0);
    private static final double MOUSE_SCALE_X = 79.1/1920.0;
    private static final double MOUSE_SCALE_Y = 79.8/1080.0;
    private static final String MOUSE_PIC = "mouse.png";
    private static final int LIVES_COUNT = 1;
    private final Sprite sprite;
    private final double direction;
    private int livesCount;
    private int currLine;
    private State state;

    /**
     * The enum State.
     * GOING, SCARED, READY_TO_EAT, FINISHED and OUT_OF_PLAYGROUND
     */
    public enum State {
        /**
         * Going state.
         */
        GOING,
        /**
         * Scared state.
         */
        SCARED,
        /**
         * Ready to eat state.
         */
        READY_TO_EAT,
        /**
         * Finished state.
         */
        FINISHED,
        /**
         * Out of playground state.
         */
        OUT_OF_PLAYGROUND
    }

    /**
     * Instantiates a new Mouse.
     * The constructor creates a sprite, sets the initial coordinates,
     * texture (initially visible object), the number of lives.
     * And randomly set the direction in which the mouse will run
     * when the cat frightens her.
     */
    public Mouse(){
        sprite = new Sprite();
        sprite.setPosXY(MOUSE_POS_X, MOUSE_POS_Y);
        sprite.setImg(MOUSE_PIC, MOUSE_SCALE_X, MOUSE_SCALE_Y);
        sprite.setVisibility(true);

        currLine = 0;
        livesCount = LIVES_COUNT;
        direction = Math.PI/2 + Math.random()*Math.PI/2;
    }

    /**
     * Get sprite sprite.
     *
     * @return the sprite
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     * Set positions x, y.
     *
     * @param posX the pos x
     * @param posY the pos y
     */
    public void setXY(double posX, double posY){
        sprite.setPosXY(posX, posY);
    }

    /**
     * Get position x double.
     *
     * @return the double
     */
    public double getX(){
        return sprite.getPosX();
    }

    /**
     * Get position y double.
     *
     * @return the double
     */
    public double getY(){
        return sprite.getPosY();
    }

    /**
     * Get curr line int.
     *
     * @return the int
     */
    public int getCurrLine(){
        return currLine;
    }

    /**
     * Increment curr line.
     */
    public void incrementCurrLine(){
        currLine++;
    }

    /**
     * Reduce lives count.
     *
     * @param damage the damage
     */
    public void reduceLivesCount(int damage){
        livesCount -= damage;
    }

    /**
     * Is scared boolean.
     *
     * @return true if mouse was scared(count of lives <= 0), false otherwise.
     */
    public boolean isScared(){
        return livesCount <= 0;
    }

    /**
     * Get direction double.
     *
     * @return the double
     */
    public double getDirection(){
        return direction;
    }

    /**
     * Set state.
     *
     * @param state the state
     */
    public void setState(State state){
        if(state == State.OUT_OF_PLAYGROUND){
            sprite.setVisibility(false);
        }
        this.state = state;

    }

    /**
     * Get state.
     *
     * @return the state
     */
    public State getState(){
        return state;
    }
}
