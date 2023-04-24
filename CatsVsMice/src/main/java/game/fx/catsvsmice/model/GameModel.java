package game.fx.catsvsmice.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class GameModel {
    private Mouse mouse = new Mouse();

    public int getMousePosX(){
        return mouse.getPosX();
    }
    public int getMousePosY(){
        return mouse.getPosY();
    }
    public Image getMouseIMG(){
        return mouse.getMouseIMG();
    }
    public void changeState(KeyEvent keyEvent){
        switch (keyEvent.getCode()) {
            case W: {
                mouse.setPosY(mouse.getPosY()-10);
                break;
            }
            case D: {
                mouse.setPosX(mouse.getPosX()+10);
                break;
            }
            case S: {
                mouse.setPosY(mouse.getPosY()+10);
                break;
            }
            case A: {
                mouse.setPosX(mouse.getPosX()-10);
                break;
            }
        }
    }
}
