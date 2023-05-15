package game.fx.catsvsmice.model;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class Cheese {
    private final Sprite sprite;
    private int lives;
    public Cheese(){
        sprite = new Sprite();
        sprite.setPosXY(WINDOW_WIDTH*273.0/320.0, WINDOW_HEIGHT*74.0/180.0);
        sprite.setImg("cheese.png", 15.0/320.0, 11.0/180);
        this.lives = 5;
    }

    public int getLives() {
        return lives;
    }
    public void reduceLivesCount(int damage) {
        lives -= damage;
    }

    public boolean isInside(double mousePosX, double mousePosY){
        return mousePosX >= sprite.getPosX() && mousePosX <= sprite.getPosX()+sprite.getImg().getWidth() &&
                mousePosY >= sprite.getPosY() && mousePosY <= sprite.getPosY()+sprite.getImg().getHeight();
    }

    public Sprite getSprite() {
        return sprite;
    }


}
