package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;
import javafx.scene.image.Image;

public class Sprite {
    private Image img;
    private double posX;
    private double posY;

    public void setImg(String picName, double scaleX, double scaleY){
        img = new Image(picName, GameView.WINDOW_WIDTH*scaleX, GameView.WINDOW_HEIGHT*scaleY, false,false);
    }
    public Image getImg(){
        return img;
    }

    public void setXY(double x, double y){
        posX = x;
        posY = y;
    }
    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }

}
