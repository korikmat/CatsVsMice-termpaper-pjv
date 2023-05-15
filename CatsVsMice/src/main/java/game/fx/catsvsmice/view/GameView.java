package game.fx.catsvsmice.view;

import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.model.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class GameView {
    public static final double WINDOW_WIDTH = (int)(Screen.getPrimary().getBounds().getWidth()/1.4);
    public static final double WINDOW_HEIGHT = (int)(WINDOW_WIDTH*(9.0/16.0));
//    public static final double WINDOW_WIDTH = 1920;
//    public static final double WINDOW_HEIGHT = 1080;
    private final Stage stage;
    private final GameModel gameModel;
    private Canvas canvas;
    private Text fpsOnDisplay;
    private long lastFrameTime = 0;
    private HeadUpDisplay headUpDisplay;

    public static final int BUTTON_PRESSED = 0;
    public static final int BUTTON_RELEASED = 1;
    public static final int ON_BUTTON = 2;
    public static final int OUT_OF_BUTTON = 3;

    private final Image background = new Image("room-background.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
    public GameView(Stage stage, GameModel gameModel){
        this.stage = stage;
        this.gameModel = gameModel;
    }
    public void initRender(){
        System.out.println(WINDOW_WIDTH+" "+WINDOW_HEIGHT);

        initFpsOnDisplayText();
        initHeadUpDisplay();

        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        Pane pane = new Pane(canvas, fpsOnDisplay, headUpDisplay.getGameButton(), headUpDisplay.getGameStageInfoMenuPane());
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("CatsVsMice");
        stage.setScene(scene);
        stage.show();

    }
    private void initFpsOnDisplayText(){
        fpsOnDisplay = new Text(WINDOW_WIDTH*(1710.0/1920.0), WINDOW_HEIGHT*(95.0/1080.0), "FPS: 0");
        double textSize = (WINDOW_HEIGHT*(30.0/1080.0));
        fpsOnDisplay.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), textSize));

    }
    private void initHeadUpDisplay(){
        headUpDisplay = new HeadUpDisplay();
        headUpDisplay.initHUD();
    }
    public void render(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawFpsOnDisplay();
        drawBackground(gc);
        drawPath(gc);
        drawCheese(gc);
        drawMouse(gc);
        drawCats(gc);
        drawCatPreview(gc);
        headUpDisplay.updateCoinsCountText(gameModel.getCoinsCount());
    }
    private void drawFpsOnDisplay(){
        long currentFrameTime = System.nanoTime();
        double fps = 1_000_000_000.0 / (currentFrameTime - lastFrameTime);
        lastFrameTime = currentFrameTime;
        fpsOnDisplay.setText("FPS: " + String.format("%.2f", fps));

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
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(5);
        for (Sprite sprite: gameModel.getCatSprites()) {
            gc.strokeOval(sprite.getPosX()- sprite.getCircleRadius(), sprite.getPosY()- sprite.getCircleRadius(), sprite.getCircleRadius()*2, sprite.getCircleRadius()*2);
            gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);
        }

    }
    private void drawCatPreview(GraphicsContext gc){
        Sprite sprite = gameModel.getCatPreviewSprite();
        if(sprite.isVisible()){
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);
            gc.strokeOval(sprite.getPosX()- sprite.getCircleRadius(), sprite.getPosY()- sprite.getCircleRadius(), sprite.getCircleRadius()*2, sprite.getCircleRadius()*2);
            gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);
        }
    }
    private void drawMouse(GraphicsContext gc){
        for (Sprite sprite:gameModel.getMouseSprites()) {
            gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);

        }
    }

    private void drawCheese(GraphicsContext gc){
        Sprite sprite = gameModel.getCheeseSprite();
        gc.drawImage(sprite.getImg(), sprite.getPosX(), sprite.getPosY());
    }

    public Button getGameStageButton(){
        return headUpDisplay.getGameButton();
    }
    public void setGameStageButtonStyle(int style){
        headUpDisplay.setGameButtonStyle(style);
    }
    public void slideUpInfoMenu(){
        headUpDisplay.slideUpInfoMenu();
    }
    public void slideDownInfoMenu(int gameState){
        headUpDisplay.slideDownInfoMenu(gameState);
    }

}
