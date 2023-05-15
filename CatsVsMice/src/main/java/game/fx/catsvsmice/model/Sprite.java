package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;
import javafx.scene.image.Image;

public class Sprite {
    private Image img;
    private double posX;
    private double posY;
    private boolean visible;
    private double circleRadius = 0;

    public void setImg(String picName, double scaleX, double scaleY){
        img = new Image(picName, GameView.WINDOW_WIDTH*scaleX, GameView.WINDOW_HEIGHT*scaleY, false,false);
    }
    public Image getImg(){
        return img;
    }

    public void setPosXY(double x, double y){
        posX = x;
        posY = y;
    }
    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }

    public void setCircleRadius(double radius){
        circleRadius = radius;
    }

    public double getCircleRadius() {
        return circleRadius;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }
    public boolean isVisible(){
        return visible;
    }
}
