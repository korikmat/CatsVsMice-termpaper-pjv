package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;

public class Mouse {
    private final Sprite sprite;
    private int livesCount;
    private int currLine;
    private State state;
    public enum State {
        WAITING, GOING, FINISHED, EATEN
    }
    public Mouse(){
        sprite = new Sprite();
        sprite.setPosXY(GameView.WINDOW_WIDTH*(80.0/1920.0), GameView.WINDOW_HEIGHT*(420.0/1080.0));
        sprite.setImg("mouse.png", 79.1/1920.0, 79.8/1080.0);

        currLine = 0;
        livesCount = 1;
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

    public boolean isDead(){
        return livesCount <= 0;
    }

    public void setState(State state){
        this.state = state;
    }
    public State getState(){
        return state;
    }
}
