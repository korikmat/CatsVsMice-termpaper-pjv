package game.fx.catsvsmice.model;

import game.fx.catsvsmice.view.GameView;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Cats {
    private List<Image> catIMGs = new ArrayList<>();
                                                                                //cat width 140px(1920)                   cat height 140px(1080)
    private Image whiteCatIMG = new Image("cat-white.gif", GameView.WINDOW_WIDTH*(140.0/1920.0), GameView.WINDOW_HEIGHT*(140.0/1080.0), false, false);
    private Image greyCatIMG = new Image("cat-grey.gif", GameView.WINDOW_WIDTH*(140.0/1920.0), GameView.WINDOW_HEIGHT*(140.0/1080.0), false, false);

    private List<Double> catsPos= new ArrayList<>();

    public Cats() {
        catIMGs.add(whiteCatIMG);
        catIMGs.add(greyCatIMG);
    }

    public void setCat(double posX, double posY){
        catsPos.add(posX);
        catsPos.add(posY);
    }
    public List<Double> getListOfCatsPos() {
        return catsPos;
    }
    public Image getCatIMG(){
//        Collections.shuffle(catIMGs);
        return catIMGs.get(0);
    }
}
