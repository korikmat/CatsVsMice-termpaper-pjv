package game.fx.catsvsmice.model;

import game.fx.catsvsmice.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;
import static java.lang.Math.sqrt;

public class GameModel {
    private final List<Mouse> mice = new ArrayList<>();
    private final List<Point> path = new ArrayList<>();
    private final List<Cat> cats = new ArrayList<>();
    private final Cat catPreview = new Cat();
    private final Coins coins;
    private final Cheese cheese;
    private int miceNum;
    private final GameData gameData;
    private final Random random = new Random();
    private static final int CATS_RATE = 20;
    private static final int MICE_NUM = 100;
    private static final int COINS_COUNT = 101;
    private static final int CHEESE_LIVES = 101;
    private static final int MICE_DAMAGE = 10;
    private static final int CATS_DAMAGE = 1;
    private static final int WAGES = 5;










    private static final Logger LOGGER = Logger.getLogger(GameModel.class.getName());


    public GameModel(GameData gameData){
        this.gameData = gameData;
        for(int i = 0; i < MICE_NUM; i++){
            mice.add(new Mouse());
            mice.get(mice.size()-1).setState(Mouse.State.GOING);
        }
        miceNum = mice.size();
        putPath(mice.get(0).getX(), mice.get(0).getY());

        coins = new Coins(COINS_COUNT);
        cheese = new Cheese();
    }

    public void advanceState(double computerMousePosX, double computerMousePosY){
        if(gameData.getState() == GameData.State.EDITOR){
            if(gameData.getStage() == 1){
                setCatPreviewVisibility(true);
                updateCatPreview(computerMousePosX, computerMousePosY);
            }
            else{
                setCatPreviewVisibility(false);
            }
        }
        if(gameData.getState() == GameData.State.GAME){
            moveMice();
            tryToAttackMice();
            setCatPreviewVisibility(true);
            updateCatPreview(computerMousePosX, computerMousePosY);
        }
    }
    public void moveMice(){
        if(miceNum > 0 && (random.nextInt(CATS_RATE)) == 1){
            miceNum--;
        }
        for(int i = mice.size()-1; i >= miceNum; i--){
            if(mice.get(i).getState() == Mouse.State.GOING){
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
                        mice.get(i).setState(Mouse.State.READY_TO_EAT);
                        cheese.reduceLivesCount(MICE_DAMAGE);
                        LOGGER.log(Level.INFO, "Cheese was Attacked");
                        mice.get(i).setState(Mouse.State.FINISHED);
                        if(cheese.isEaten()){

                            prepareGameScene();
                            gameData.setState(GameData.State.MENU);
                            LOGGER.log(Level.INFO, "All Cheese was eaten. You lost!");
                            return;
                        }
                    }
                }
            }
            if(mice.get(i).getState() == Mouse.State.SCARED){
                double posX = mice.get(i).getX()+Math.cos(mice.get(i).getDirection())*10;
                double posY = mice.get(i).getY()+Math.sin(mice.get(i).getDirection())*10;
                if(posX < 0 || posY > WINDOW_HEIGHT){
                    mice.get(i).setState(Mouse.State.OUT_OF_PLAYGROUND);
                }
                mice.get(i).setXY(posX, posY);
            }
        }

    }
    private void prepareGameScene(){
        coins.reset(COINS_COUNT);
        cheese.reset(CHEESE_LIVES);
        cats.clear();
        Point firstPoint = path.get(0);
        path.clear();
        path.add(firstPoint);
        mice.clear();
        for(int i = 0; i < MICE_NUM; i++){
            mice.add(new Mouse());
            mice.get(mice.size()-1).setState(Mouse.State.GOING);
        }

    }

    public void tryToAttackMice(){
        for(Cat cat: cats){
            if(cat.isReady()) {
                for (Mouse mouse : mice) {
                    if (cat.mouseInRange(mouse.getX(), mouse.getY())) {

                        mouse.reduceLivesCount(CATS_DAMAGE);
                        if (mouse.isScared()) {
                            mouse.setState(Mouse.State.SCARED);
                            LOGGER.log(Level.INFO, "Mouse was Scared!");

                            coins.earn(WAGES);
                            LOGGER.log(Level.INFO, "You earned {0} coins!", WAGES);
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
                }
            }
        }
    }

    public void putPath(double posX, double posY){
        path.add(new Point(posX, posY));
    }
    public void clearPath(){
        path.clear();
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
            LOGGER.log(Level.INFO, "Added!");

        }
        else{
            LOGGER.log(Level.WARNING, "Out of coins!");
        }

    }
    public void clearCats(){
        coins.reset(100);
        cats.clear();
    }

    public void setCatPreviewVisibility(boolean visible){
        if(visible){
            catPreview.setVisible(true);
            catPreview.resetCircleRadius(catPreview.getAttackRadius());
        }
        else{
            catPreview.setVisible(false);
        }

    }

    public void updateCatPreview(double posX, double posY){
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

    public int getHeartsCount(){
        return cheese.getLives();
    }

    public Sprite getCheeseSprite(){
        return cheese.getSprite();
    }


}
