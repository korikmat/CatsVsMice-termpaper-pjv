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

import java.util.List;

public class GameView {
    public static final double WINDOW_WIDTH = (int)(Screen.getPrimary().getBounds().getWidth()/1.5);
    public static final double WINDOW_HEIGHT = (int)(Screen.getPrimary().getBounds().getHeight()/1.5);

    private final Stage stage;
    private final GameModel gameModel;
    private Canvas canvas;
    private Text fpsOnDisplay;
    private long lastFrameTime = 0;
    private Button gameStageButton;

    public static final int BUTTON_PRESSED = 0;
    public static final int BUTTON_RELEASED = 1;

    public static final int ON_BUTTON = 2;
    public static final int OUT_OF_BUTTON = 3;

    private final Image background = new Image("background.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
    public GameView(Stage stage, GameModel gameModel){
        this.stage = stage;
        this.gameModel = gameModel;
    }
    public void initRender(){
        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        initFpsOnDisplayText();
        initGameStageButton();

        Pane pane = new Pane(canvas, fpsOnDisplay, gameStageButton);
        Scene scene = new Scene(pane);
        stage.setTitle("CatsVsMice");
        stage.setScene(scene);
        stage.show();

    }
    private void initFpsOnDisplayText(){
        fpsOnDisplay = new Text(WINDOW_WIDTH*(1710.0/1920.0), WINDOW_HEIGHT*(95.0/1080.0), "FPS: 0");
        double textSize = (WINDOW_HEIGHT*(30.0/1080.0));
        fpsOnDisplay.setStyle(
                "-fx-font-family: 'Nineteen Ninety Three'; " +
                "-fx-font-size: " +
                String.format("%.0f", textSize) + "px"
        );
        fpsOnDisplay.setFont(Font.loadFont("/fonts/NineteenNinetyThree-L1Ay.ttf", 50));
//        fpsOnDisplay.setFont(Font.loadFont("/fonts/minecraft.ttf", 50));


    }
    private void initGameStageButton(){
        gameStageButton = new Button();
        double buttonSizeX = WINDOW_WIDTH*(100.0/1920);
        double buttonSizeY = WINDOW_HEIGHT*(130.0/1080);
        gameStageButton.setStyle(
                "-fx-background-radius: 20;" +
                "-fx-background-color: transparent; " +
                "-fx-border-color: transparent;" +
                " -fx-background-image: url(button-invis.png); " +
                "-fx-background-size: " +
                String.format("%.0fpx %.0fpx", buttonSizeX, buttonSizeY)
        );
        gameStageButton.setPrefSize(buttonSizeX,buttonSizeY);
        gameStageButton.setLayoutX(WINDOW_WIDTH*(1785.0/1920.0));
        gameStageButton.setLayoutY(WINDOW_HEIGHT*(475.0/1080.0)); //921.0 right-bottom
    }
    public void render(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawFpsOnDisplay();
        drawBackground(gc);
        drawPath(gc);
        drawMouse(gc);
        drawCats(gc);
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
        for (Sprite sprite: gameModel.getCatSprites()) {
            gc.drawImage(sprite.getImg(), sprite.getPosX()-sprite.getImg().getWidth()/2, sprite.getPosY()-sprite.getImg().getHeight()/2);
        }
//        cats = gameModel.getCats();
//        for(int i = 0; i < cats.size(); i+=2){
//            gc.drawImage(gameModel.getCatImg(), cats.get(i)-gameModel.getCatImg().getWidth()/2, cats.get(i+1)-gameModel.getCatImg().getHeight()/2);
//        }
    }
    private void drawMouse(GraphicsContext gc){
        gc.drawImage(gameModel.getMouseIMG(), gameModel.getMousePosX()-gameModel.getMouseWidth()/2, gameModel.getMousePosY()-gameModel.getMouseHeight()/2);
    }

    public Button getGameStageButton(){
        return gameStageButton;
    }
    public void setGameStageButtonStyle(int style){
        double buttonSizeX = WINDOW_WIDTH*(100.0/1920);
        double buttonSizeY = WINDOW_HEIGHT*(130.0/1080);
        switch (style){
            case BUTTON_PRESSED:
                gameStageButton.setStyle(
                        "-fx-background-radius: 20;" +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-background-image: url(button-press-brown.png); " +
                        "-fx-background-size: " +
                        String.format("%.0fpx %.0fpx", buttonSizeX, buttonSizeY)
                );
                break;
            case BUTTON_RELEASED:
            case ON_BUTTON:
                gameStageButton.setStyle(
                        "-fx-background-radius: 20;" +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-background-image: url(button-vis-brown.png); " +
                        "-fx-background-size: " +
                        String.format("%.0fpx %.0fpx", buttonSizeX, buttonSizeY)
                );
                break;
            case OUT_OF_BUTTON:
                gameStageButton.setStyle(
                        "-fx-background-radius: 20;" +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-background-image: url(button-invis.png); " +
                        "-fx-background-size: " +
                        String.format("%.0fpx %.0fpx", buttonSizeX, buttonSizeY)
                );
                break;
        }
    }

}
