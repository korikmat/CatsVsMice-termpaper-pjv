package game.fx.catsvsmice.model;

import javafx.scene.image.Image;

public class Mouse {
    private double posX = 80;
    private double posY = 420;

    private final Image mouseIMG = new Image("mouse.png", 113*0.7, 114*0.7, false, false);

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
    public double getWidth(){
        return mouseIMG.getWidth();
    }
    public double getHeight(){
        return mouseIMG.getHeight();
    }

    public Image getMouseIMG() {
        return mouseIMG;
    }
}
