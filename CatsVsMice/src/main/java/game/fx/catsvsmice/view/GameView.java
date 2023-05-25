package game.fx.catsvsmice.view;

import game.fx.catsvsmice.GameData;
import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.model.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameView {
    public static final double WINDOW_WIDTH = (int)(Screen.getPrimary().getBounds().getWidth()/1.4);
    public static final double WINDOW_HEIGHT = (int)(WINDOW_WIDTH*(9.0/16.0));
//    public static final double WINDOW_WIDTH = 1920;
//    public static final double WINDOW_HEIGHT = 1080;
//    public static final double WINDOW_WIDTH = 2500;
//    public static final double WINDOW_HEIGHT = 700;
    private final Stage stage;
    private final GameModel gameModel;
    private final GameButtons gameButtons;
    private final EditorScene editorScene;
    private final GameScene gameScene;
    private final LvlSelectionScene lvlSelectionScene;
    private final MainMenuScene mainMenuScene;
    private final AboutMeScene aboutMeScene;
    private Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
    private long lastFrameTime = 0;
    private final GameData gameData;

    public static final int BUTTON_PRESSED = 0;
    public static final int BUTTON_RELEASED = 1;
    public static final int ON_BUTTON = 2;
    public static final int OUT_OF_BUTTON = 3;
    private static final Logger LOGGER = Logger.getLogger(GameView.class.getName());


    private final Image background = new Image("room-background.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
    public GameView(Stage stage, GameModel gameModel, GameData gameData){
        LOGGER.log(Level.INFO, "Window was opened at size {0}", WINDOW_WIDTH+" "+WINDOW_HEIGHT);

        this.stage = stage;
        this.gameModel = gameModel;
        this.gameData = gameData;

        gameButtons = new GameButtons(gameData);

        editorScene = new EditorScene(gameButtons);
        lvlSelectionScene = new LvlSelectionScene(gameButtons);
        mainMenuScene = new MainMenuScene(gameButtons);
        gameScene = new GameScene(gameButtons);
        aboutMeScene = new AboutMeScene(gameButtons);

        actualizeScene();
        stage.show();
    }
    public List<Button> getGameButtons(){
        return gameButtons.getButtonsAsList();
    }

    public void actualizeScene(){
        switch (gameData.getState()){
            case MENU:
                if(stage.getScene() != mainMenuScene.getScene()){
                    stage.setScene(mainMenuScene.getScene());
                    stage.setTitle("CatsVsMice<Menu>");
                }
                break;
            case LVL_SELECTION:
                if(stage.getScene() != lvlSelectionScene.getScene()){
                    stage.setScene(lvlSelectionScene.getScene());
                    lvlSelectionScene.prepareToView();
                    stage.setTitle("CatsVsMice<LvlSelection>");
                }
                break;
            case EDITOR:
                if(stage.getScene() != editorScene.getScene()){
                    editorScene.prepareToView();
                    canvas = editorScene.getCanvas();
                    stage.setScene(editorScene.getScene());
                    stage.setTitle("CatsVsMice<LvlCreation>");
                }
                else {
                    editorScene.animateHud(gameData.getStage());
                }
                break;
            case GAME:
                if(stage.getScene() != gameScene.getScene()){
                    gameScene.animateHud(gameData.getStage());
                    stage.setScene(gameScene.getScene());
                    canvas = gameScene.getCanvas();
                    stage.setTitle("CatsVsMice<Menu>");
                }
                break;
            case ABOUTME:
                if(stage.getScene() != aboutMeScene.getScene()){
                    stage.setScene(aboutMeScene.getScene());
                    stage.setTitle("CatsVsMice<AboutMe>");
                }
                break;
        }
    }

    public Scene getScene(GameData.State scene){
        switch (scene){
            case MENU:
                return mainMenuScene.getScene();
            case GAME:
                return gameScene.getScene();
            case LVL_SELECTION:
                return lvlSelectionScene.getScene();
            case EDITOR:
                return editorScene.getScene();
        }
        return null;
    }

    public void render(){
        if(gameData.getState() == GameData.State.MENU){
            actualizeScene();
        }
        GraphicsContext gameGc = canvas.getGraphicsContext2D();
        drawFpsOnDisplay();
        drawBackground(gameGc);
        drawPath(gameGc);
        drawCheese(gameGc);
        drawMouse(gameGc);
        drawCats(gameGc);
        drawCatPreview(gameGc);
        editorScene.updateCoinsCountText(gameModel.getCoinsCount());
        editorScene.updateHeartsCountText((gameModel.getHeartsCount()));
        gameScene.updateCoinsCountText(gameModel.getCoinsCount());
        gameScene.updateHeartsCountText((gameModel.getHeartsCount()));

    }
    private void drawFpsOnDisplay(){
        long currentFrameTime = System.nanoTime();
        double fps = 1_000_000_000.0 / (currentFrameTime - lastFrameTime);
        lastFrameTime = currentFrameTime;
        gameScene.updateFps(fps);
    }
    private void drawBackground(GraphicsContext gc){
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.drawImage(background, 0, 0);
    }
    private void drawPath(GraphicsContext gc){
        double widthRadius = WINDOW_WIDTH*(25.0/1920.0);
        double heightRadius = WINDOW_HEIGHT*(25.0/1080.0);

        gc.setFill(Color.ORANGE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        Sprite[] sprites = gameModel.getPathSprites();

        int pathLen = sprites.length;
        double firstPointX = sprites[0].getPosX()-widthRadius/2;
        double firstPointY = sprites[0].getPosY()-widthRadius/2;
        double lastPointX = sprites[pathLen-1].getPosX()-widthRadius/2;
        double lastPointY = sprites[pathLen-1].getPosY()-widthRadius/2;

        for (int i = 0; i < pathLen; i++) {
            if(i > 0){
                gc.strokeLine(sprites[i-1].getPosX(), sprites[i-1].getPosY(), sprites[i].getPosX(), sprites[i].getPosY());
                gc.fillOval(sprites[i-1].getPosX()-widthRadius/2, sprites[i-1].getPosY()-heightRadius/2, widthRadius, heightRadius);
            }
        }
        gc.fillOval(firstPointX, firstPointY, widthRadius, heightRadius);
        gc.fillOval(lastPointX, lastPointY, widthRadius, heightRadius);
//
    }
    private void drawCats(GraphicsContext gc){
        gc.setStroke(Color.color(1,1,1, 0.5));
        gc.setLineWidth(5);
        for (Sprite sprite: gameModel.getCatSprites()) {
            gc.strokeOval(sprite.getPosX()- sprite.getCircleRadius(), sprite.getPosY()- sprite.getCircleRadius(), sprite.getCircleRadius()*2, sprite.getCircleRadius()*2);
            gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);
        }

    }
    private void drawCatPreview(GraphicsContext gc){
        Sprite sprite = gameModel.getCatPreviewSprite();
        if(sprite.isVisible()){
            gc.setStroke(Color.color(1,1,1, 0.5));
            gc.setLineWidth(5);
            gc.strokeOval(sprite.getPosX()- sprite.getCircleRadius(), sprite.getPosY()- sprite.getCircleRadius(), sprite.getCircleRadius()*2, sprite.getCircleRadius()*2);
            gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);
        }
    }
    private void drawMouse(GraphicsContext gc){
        for (Sprite sprite:gameModel.getMouseSprites()) {
            if(sprite.isVisible()){
                gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);
            }

        }
    }
    private void drawCheese(GraphicsContext gc){
        Sprite sprite = gameModel.getCheeseSprite();
        gc.drawImage(sprite.getImg(), sprite.getPosX(), sprite.getPosY());
    }


}
