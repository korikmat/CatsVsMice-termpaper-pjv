package game.fx.catsvsmice.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class GameModel {
    private final List<Mouse> mice = new ArrayList<>();
    private final List<Mouse> diedMice = new ArrayList<>();

    private final List<Point> path = new ArrayList<>();
    private final List<Cat> cats = new ArrayList<>();
    private final Cat catPreview = new Cat();;
    private final Coins coins;
    private final Cheese cheese;
    private int miceNum;
    private GameStage gameStage;
    public enum GameStage{
        PATH_CREATING, CATS_PUTTING, MICE_ATTACKING
    }
    public GameModel(){
        for(int i = 0; i < 100; i++){
            mice.add(new Mouse());
            mice.get(mice.size()-1).setState(Mouse.State.WAITING);
        }
        miceNum = mice.size();
        putPath(mice.get(0).getX(), mice.get(0).getY());

        coins = new Coins(100);
        cheese = new Cheese();
    }

    public void advanceState(int gameStage, double computerMousePosX, double computerMousePosY){
        if(gameStage == 2){
            moveMice();
            tryToAttackMice();
        }
        if(gameStage == 1){
            updateCatPreview(computerMousePosX, computerMousePosY);
        }
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

                if(cheese.isInside(posX, posY)){
                    mice.get(i).setState(Mouse.State.FINISHED);
                }
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
                        if (mouse.isDead()) {
                            diedMice.add(mouse);
                            coins.earn(2);

                        }
                        cat.setState(Cat.State.ATTACKING);
                        cat.updateTime();
                    }
                }
            }
            if(cat.getState() == Cat.State.ATTACKING){
                cat.increaseCircleRadius(8);
                if(cat.getCircleRadius() >= cat.getAttackRadius()){
                    cat.resetCircleRadius(10);
                    cat.setState(Cat.State.WAITING);
                    mice.removeAll(diedMice);
                    diedMice.clear();
                }
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
            cats.get(cats.size()-1).resetCircleRadius(10);
            cats.get(cats.size()-1).setState(Cat.State.READY);
        }
        else{
            System.out.println("Out of coins!");
        }

    }

    public void setCatPreviewVisibility(boolean visible){
        if(visible){
            catPreview.resetCircleRadius(catPreview.getAttackRadius());
        }
        else{
            catPreview.setVisible(false);
        }

    }

    public void updateCatPreview(double posX, double posY){
        catPreview.setVisible(true);
        catPreview.setPosXY(posX, posY);
    }
    public Sprite getCatPreviewSprite(){
        return catPreview.getSprite();
    }
    public Sprite[] getCatSprites(){
        Sprite[] sprites = new Sprite[cats.size()];
        for (int i = 0; i < cats.size(); i++) {
            sprites[i] = cats.get(i).getSprite();
        }
        return sprites;
    }

    public Sprite[] getMouseSprites(){
        Sprite[] sprites = new Sprite[mice.size()];
        for (int i = 0; i < mice.size(); i++){
            sprites[i] = mice.get(i).getSprite();
        }
        return sprites;
    }

    public int getCoinsCount(){
        return coins.getCount();
    }

    public Sprite getCheeseSprite(){
        return cheese.getSprite();
    }
}
