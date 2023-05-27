package game.fx.catsvsmice.model.objects;

import game.fx.catsvsmice.model.Sprite;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Cat.
 * The object will have a radius of attack, size, texture, and reload time.
 * It has three states (waiting, attacking and ready).
 * When creating it, you can specify the positions at which it will appear.
 */
public class Cat {
    private static final double ATTACK_RADIUS = 75;
    private static final double CAT_SCALE_X = 100.0/1920.0;
    private static final double CAT_SCALE_Y = 100.0/1080.0;
    private static final String WHITE_CAT_PIC = "cat-white.gif";
    private static final String GRAY_CAT_PIC = "cat-grey.gif";
    private static final int NUM_OF_PICS = 2;
    private static final long CATS_COOL_DOWN = 2_000_000_000;
    private static String picName = WHITE_CAT_PIC;
    private final Sprite sprite;
    private long lastTime = 0;
    private long currTime;
    private State state;

    /**
     * The enum State.
     * Has three states WAITING, ATTACKING, READY
     */
    public enum State {
        /**
         * Waiting state.
         */
        WAITING,
        /**
         * Attacking state.
         */
        ATTACKING,
        /**
         * Ready state.
         */
        READY
    }

    /**
     * Instantiates a new Cat.
     * Creates a cat with a random texture (initially invisible).
     * Without coordinates.
     */
    public Cat(){
        sprite = new Sprite();
        sprite.setVisibility(false);
        selectCatKind();
        sprite.setImg(picName, CAT_SCALE_X, CAT_SCALE_Y);
    }

    /**
     * Instantiates a new Cat.
     * Creates a cat with a random texture.
     * With coordinates.
     *
     * @param posX the position x
     * @param posY the position y
     */
    public Cat(double posX, double posY) {
        sprite = new Sprite();
        sprite.setPosXY(posX, posY);
        selectCatKind();
        sprite.setImg(picName, CAT_SCALE_X, CAT_SCALE_Y);
    }

    private void selectCatKind(){
        switch (ThreadLocalRandom.current().nextInt(0, NUM_OF_PICS)){
            case 0:
                picName = WHITE_CAT_PIC;
                break;
            case 1:
                picName = GRAY_CAT_PIC;
                break;

            default:
                throw new IllegalStateException("Unexpected random value");
        }
    }

    /**
     * Set Cat positions x,y.
     *
     * @param posX the pos x
     * @param posY the pos y
     */
    public void setPosXY(double posX, double posY){
        sprite.setPosXY(posX, posY);
    }

    /**
     * Get sprite of Cat.
     *
     * @return the sprite of Cat
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     * Checks if the cat is ready for the next attack.
     *
     * @return true if Cat is ready, false otherwise
     */
    public boolean isReady(){
        currTime = System.nanoTime();
        return currTime - lastTime >= CATS_COOL_DOWN;
    }

    /**
     * Update last attack time.
     */
    public void updateTime(){
        lastTime = currTime;
    }

    /**
     * Checks if there is a mouse in the attack radius of the Cat
     *
     * @param mousePosX the mouse pos x
     * @param mousePosY the mouse pos y
     * @return true if mouse is in range, false otherwise
     */
    public boolean mouseInRange(double mousePosX, double mousePosY){
                // (x - a)^2 + (y - b)^2 <= R^2
        return (mousePosX-getCatPosX())*(mousePosX-getCatPosX()) + (mousePosY-getCatPosY())*(mousePosY-getCatPosY()) <= ATTACK_RADIUS * ATTACK_RADIUS;
    }

    private double getCatPosX(){
        return sprite.getPosX();
    }
    private double getCatPosY(){
        return sprite.getPosY();
    }

    /**
     * Get Cat attack radius double.
     *
     * @return the double
     */
    public double getAttackRadius(){
        return ATTACK_RADIUS;
    }

    /**
     * Increase circle radius.
     *
     * @param radius the radius
     */
    public void increaseCircleRadius(double radius){
        sprite.setCircleRadius(sprite.getCircleRadius() + radius);
    }

    /**
     * Get circle radius double.
     *
     * @return the double
     */
    public double getCircleRadius(){
        return sprite.getCircleRadius();
    }

    /**
     * Reset circle radius.
     *
     * @param radius the radius
     */
    public void resetCircleRadius(double radius){
        sprite.setCircleRadius(radius);
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Set visible.
     *
     * @param visible the visible
     */
    public void setVisible(boolean visible){
        sprite.setVisibility(visible);
    }
}
