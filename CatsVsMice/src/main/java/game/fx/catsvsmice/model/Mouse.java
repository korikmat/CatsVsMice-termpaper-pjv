package game.fx.catsvsmice.model;

import javafx.scene.image.Image;

public class Mouse {
    private int posX = 40;
    private int posY = 400;

    private final Image mouseIMG = new Image("mouse.png", 113*0.7, 114*0.7, false, false);

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Image getMouseIMG() {
        return mouseIMG;
    }
}
