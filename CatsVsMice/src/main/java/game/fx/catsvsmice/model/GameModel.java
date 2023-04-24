package game.fx.catsvsmice.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.List;

import static java.lang.Math.sqrt;

public class GameModel {
    private Mouse mouse = new Mouse();
    private Path path = new Path(getMousePosX(), getMousePosY());
    private int i = 0;

    public double getMousePosX(){
        return mouse.getPosX();
    }
    public double getMousePosY(){
        return mouse.getPosY();
    }
    public double getMouseWidth(){
        return mouse.getWidth();
    }
    public double getMouseHeight(){
        return mouse.getHeight();
    }
    public Image getMouseIMG(){
        return mouse.getMouseIMG();
    }
    public void moveMouseKeyboard(KeyEvent keyEvent){
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
    public void moveMouse(){

        if(getPath().size() > 2 && i+3 < getPath().size()){
            double x1 = getPath().get(i);
            double y1 = getPath().get(i+1);
            double x2 = getPath().get(i+2);
            double y2 = getPath().get(i+3);
            double len = sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));

            double posX = getMousePosX()+((x2-x1)/len)*7;
            double posY = ((y1-y2)/(x1-x2))*(posX-x1)+y1;

            double way = sqrt((x1-posX)*(x1-posX)+(y1-posY)*(y1-posY));

            if(way > len){
                i+=2;
            }
            mouse.setPosX(posX);
            mouse.setPosY(posY);

        }
    }
    public void putPath(double posX, double posY){
        path.setPoint(posX, posY);
    }

    public List<Double> getPath(){
        return path.getListOfPoints();
    }

}
