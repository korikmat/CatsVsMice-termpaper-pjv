package game.fx.catsvsmice.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class GameModel {
    private final Mouse mouse = new Mouse();
    private final List<Point> path = new ArrayList<>();
    private final List<Cat> cats = new ArrayList<>();
    private int i = 0;
    public GameModel(){
        putPath(getMousePosX(), getMousePosY());
    }

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
    public void moveMouse(){

        if(path.size() > 1 && i+1 < path.size()){
            double x1 = path.get(i).getSprite().getPosX();
            double y1 = path.get(i).getSprite().getPosY();
            double x2 = path.get(i+1).getSprite().getPosX();
            double y2 = path.get(i+1).getSprite().getPosY();
            double lineLength = sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));

            double posX = getMousePosX()+((x2-x1)/lineLength)*7;
            double posY = getMousePosY()+((y2-y1)/lineLength)*7;

            double traveledLength = sqrt((x1-posX)*(x1-posX)+(y1-posY)*(y1-posY));

            if(traveledLength >= lineLength){
                i+=1;
            }
//            System.out.println(posX +" "+posY);
            mouse.setPosX(posX);
            mouse.setPosY(posY);

        }
    }
    public void putPath(double posX, double posY){
        path.add(new Point(posX, posY));
    }

    public Sprite[] getPathSprites(){
        Sprite[] sprites= new Sprite[path.size()];
        for (int i = 0; i < path.size(); i++) {
            sprites[i] = path.get(i).getSprite();
        }
        return sprites;
    }
    public void putCat(double posX, double posY) {
        cats.add(new Cat(posX, posY));
    }
    public Sprite[] getCatSprites(){
        Sprite[] sprites = new Sprite[cats.size()];
        for (int i = 0; i < cats.size(); i++) {
            sprites[i] = cats.get(i).getSprite();
        }
        return sprites;
    }

}
