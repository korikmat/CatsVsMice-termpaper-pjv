package game.fx.catsvsmice.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Cats {
    private Image catIMG = new Image("cat.gif", 140, 140, false, false);
    private List<Double> catsPos= new ArrayList<>();

    public void setCat(double posX, double posY){
        catsPos.add(posX);
        catsPos.add(posY);
    }
    public List<Double> getListOfCatsPos() {
        return catsPos;
    }
    public Image getCatIMG(){
        return catIMG;
    }}
