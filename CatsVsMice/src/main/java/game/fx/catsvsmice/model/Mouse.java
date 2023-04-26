package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;
import javafx.scene.image.Image;

public class Mouse {
    private double posX = GameView.WINDOW_WIDTH*(80.0/1920.0);
    private double posY = GameView.WINDOW_HEIGHT*(420.0/1080.0);
                                                                        // mouse width 79.1(1920)                  mouse height 79.8(1080)
    private final Image mouseIMG = new Image("mouse.png", GameView.WINDOW_WIDTH*(79.1/1920.0), GameView.WINDOW_HEIGHT*(79.8/1080.0), false, false);

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
