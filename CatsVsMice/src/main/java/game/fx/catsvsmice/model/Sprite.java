package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;
import javafx.scene.image.Image;

/**
 * The type Sprite.
 * Each game object has its coordinates, image, visibility state.
 */
public class Sprite {
    private Image img;
    private double posX;
    private double posY;
    private boolean visible;
    private double circleRadius = 0;

    /**
     * Set img.
     *
     * @param picName the pic name
     * @param scaleX  the scale x
     * @param scaleY  the scale y
     */
    public void setImg(String picName, double scaleX, double scaleY){
        img = new Image(picName, GameView.WINDOW_WIDTH*scaleX, GameView.WINDOW_HEIGHT*scaleY, false,false);
    }

    /**
     * Get img image.
     *
     * @return the image
     */
    public Image getImg(){
        return img;
    }

    /**
     * Set positions x, y.
     *
     * @param x the x
     * @param y the y
     */
    public void setPosXY(double x, double y){
        posX = x;
        posY = y;
    }

    /**
     * Get pos x double.
     *
     * @return the double
     */
    public double getPosX(){
        return posX;
    }

    /**
     * Get pos y double.
     *
     * @return the double
     */
    public double getPosY(){
        return posY;
    }

    /**
     * Set circle radius.
     *
     * @param radius the radius
     */
    public void setCircleRadius(double radius){
        circleRadius = radius;
    }

    /**
     * Gets circle radius.
     *
     * @return the circle radius
     */
    public double getCircleRadius() {
        return circleRadius;
    }

    /**
     * Set visibility.
     *
     * @param visible the visible
     */
    public void setVisibility(boolean visible){
        this.visible = visible;
    }

    /**
     * Is visible boolean.
     *
     * @return the boolean
     */
    public boolean isVisible(){
        return visible;
    }
}
