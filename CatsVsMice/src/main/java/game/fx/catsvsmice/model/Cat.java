package game.fx.catsvsmice.model;

import java.util.Random;

public class Cat {
    private static final double attackRadius = 75;
    private static final double CAT_SCALE_X = 100.0/1920.0;
    private static final double CAT_SCALE_Y = 100.0/1080.0;
    private static final String WHITE_CAT_PIC = "cat-white.gif";
    private static final String GRAY_CAT_PIC = "cat-grey.gif";
    private static final int NUM_OF_PICS = 2;



    private static final long CATS_COOL_DOWN = 2_000_000_000;



    private long lastTime = 0;
    private long currTime;
    private final Sprite sprite;
    private State state;
    Random random = new Random();

    String picName = WHITE_CAT_PIC;


    public enum State {
        WAITING, ATTACKING, READY
    }
    public Cat(){
        sprite = new Sprite();
        sprite.setVisibility(false);
        selectCatKind();
        sprite.setImg(picName,CAT_SCALE_X, CAT_SCALE_Y);
    }

    public Cat(double posX, double posY) {
        sprite = new Sprite();
        sprite.setPosXY(posX, posY);
        selectCatKind();
        sprite.setImg(picName,CAT_SCALE_X, CAT_SCALE_Y);
    }

    private void selectCatKind(){
        switch (random.nextInt(NUM_OF_PICS)){
            case 0:
                picName = WHITE_CAT_PIC;
                break;
            case 1:
                picName = GRAY_CAT_PIC;
                break;
        }
    }
    public void setPosXY(double posX, double posY){
        sprite.setPosXY(posX, posY);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public boolean isReady(){
        currTime = System.nanoTime();
        return currTime - lastTime >= CATS_COOL_DOWN;
    }
    public void updateTime(){
        lastTime = currTime;
    }
    public boolean mouseInRange(double mousePosX, double mousePosY){
                // (x - a)^2 + (y - b)^2 <= R^2
        return (mousePosX-getCatPosX())*(mousePosX-getCatPosX()) + (mousePosY-getCatPosY())*(mousePosY-getCatPosY()) <= attackRadius*attackRadius;
    }


    private double getCatPosX(){
        return sprite.getPosX();
    }
    private double getCatPosY(){
        return sprite.getPosY();
    }

    public double getAttackRadius(){
        return attackRadius;
    }
    public void increaseCircleRadius(double radius){
        sprite.setCircleRadius(sprite.getCircleRadius() + radius);
    }
    public double getCircleRadius(){
        return sprite.getCircleRadius();
    }
    public void resetCircleRadius(double radius){
        sprite.setCircleRadius(radius);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
    public void setVisible(boolean visible){
        sprite.setVisibility(visible);
    }
}
