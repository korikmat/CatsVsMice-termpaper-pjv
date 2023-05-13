package game.fx.catsvsmice.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class GameModel {
    private final List<Mouse> mice = new ArrayList<>(10);
    private final List<Mouse> diedMice = new ArrayList<>();

    private final List<Point> path = new ArrayList<>();
    private final List<Cat> cats = new ArrayList<>();
    private final Coins coins;
    private int miceNum;
    public GameModel(){
        for(int i = 0; i < 100; i++){
            mice.add(new Mouse());
        }
        miceNum = mice.size();
        putPath(mice.get(0).getX(), mice.get(0).getY());

        coins = new Coins(100);
    }
    public void moveMice(){
        if(miceNum > 0 && ((int)(Math.random()*20)) == 1){
            miceNum--;
            System.out.println(miceNum);
        }
        for(int i = mice.size()-1; i >= miceNum; i--){
            int currLine = mice.get(i).getCurrLine();
            if(path.size() > 1 && currLine+1 < path.size()){
                double x1 = path.get(currLine).getX();
                double y1 = path.get(currLine).getY();
                double x2 = path.get(currLine+1).getX();
                double y2 = path.get(currLine+1).getY();
                double lineLength = sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
                if(lineLength == 0){
                    mice.get(i).incrementCurrLine();
                    return;
                }
                double posX = mice.get(i).getX()+((x2-x1)/lineLength)*7;
                double posY = mice.get(i).getY()+((y2-y1)/lineLength)*7;

                double traveledLength = sqrt((x1-posX)*(x1-posX)+(y1-posY)*(y1-posY));

                if(traveledLength >= lineLength){
                    mice.get(i).incrementCurrLine();
                }

                mice.get(i).setXY(posX, posY);
            }
        }
    }

    public void tryToAttackMice(){
        for(Cat cat: cats){
            if(cat.isReady()) {
                for (Mouse mouse : mice) {
                    if (cat.mouseInRange(mouse.getX(), mouse.getY())) {

                        mouse.reduceLivesCount(1);
                        System.out.println("Lives left " + mouse.getLivesCount());
                        if (mouse.getLivesCount() <= 0) {
                            diedMice.add(mouse);
                        }
                        cat.updateTime();
                    }
                }
                mice.removeAll(diedMice);
                diedMice.clear();
            }
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
        if(coins.isPaid(25)){
            cats.add(new Cat(posX, posY));
        }
        else{
            System.out.println("Out of coins!");
        }

    }
    public Sprite[] getCatSprites(){
        Sprite[] sprites = new Sprite[cats.size()];
        for (int i = 0; i < cats.size(); i++) {
            sprites[i] = cats.get(i).getSprite();
        }
        return sprites;
    }

    public Sprite[] getMouseSprite(){
        Sprite[] sprites = new Sprite[mice.size()];
        for (int i = 0; i < mice.size(); i++){
            sprites[i] = mice.get(i).getSprite();
        }
        return sprites;
    }

    public int getCoinsCount(){
        return coins.getCount();
    }
}
