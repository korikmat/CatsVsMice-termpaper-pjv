package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;

public class Mouse {
    private final Sprite sprite;
    private int livesCount;
    private int currLine;
    private final double direction;
    private State state;
    private static final double attackRadius = 75;
    private static final double MOUSE_POS_X = GameView.WINDOW_WIDTH*(80.0/1920.0);
    private static final double MOUSE_POS_Y = GameView.WINDOW_HEIGHT*(420.0/1080.0);
    private static final double MOUSE_SCALE_X = 79.1/1920.0;
    private static final double MOUSE_SCALE_Y = 79.8/1080.0;
    private static final String MOUSE_PIC = "mouse.png";
    private static final int LIVES_COUNT = 1;

    public enum State {
        GOING, SCARED, READY_TO_EAT, FINISHED, OUT_OF_PLAYGROUND
    }
    public Mouse(){
        sprite = new Sprite();
        sprite.setPosXY(MOUSE_POS_X, MOUSE_POS_Y);
        sprite.setImg(MOUSE_PIC, MOUSE_SCALE_X, MOUSE_SCALE_Y);
        sprite.setVisibility(true);

        currLine = 0;
        livesCount = LIVES_COUNT;
        direction = Math.PI/2 + Math.random()*Math.PI/2;
    }
    public Sprite getSprite(){
        return sprite;
    }
    public void setXY(double posX, double posY){
        sprite.setPosXY(posX, posY);
    }
    public double getX(){
        return sprite.getPosX();
    }
    public double getY(){
        return sprite.getPosY();
    }
    public int getCurrLine(){
        return currLine;
    }
    public void incrementCurrLine(){
        currLine++;
    }

    public int getLivesCount() {
        return livesCount;
    }
    public void reduceLivesCount(int damage){
        livesCount -= damage;
    }

    public boolean isScared(){
        return livesCount <= 0;
    }

    public double getDirection(){
        return direction;
    }

    public void setState(State state){
        if(state == State.OUT_OF_PLAYGROUND){
            sprite.setVisibility(false);
        }
        this.state = state;

    }
    public State getState(){
        return state;
    }
}
