package game.fx.catsvsmice.view;

import game.fx.catsvsmice.model.GameModel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class GameView {
    private final Stage stage;
    private GameModel gameModel;
    private Canvas canvas;
    private Text fpsOnDisplay;
    private long lastFrameTime = System.nanoTime();
    private List<Double> path;
    private List<Double> cats;
    private Button gameStageButton;

    public static final int BUTTON_PRESSED = 0;
    public static final int BUTTON_RELEASED = 1;

    public static final int ON_BUTTON = 2;
    public static final int OUT_OF_BUTTON = 3;

    private final Image background = new Image("background.png", 1920, 1080, false, false);
    public GameView(Stage stage, GameModel gameModel){
        this.stage = stage;
        this.gameModel = gameModel;
    }
    public void initRender(){
        canvas = new Canvas(1920, 1080);
        fpsOnDisplay = new Text(1725, 95, "FPS: 0");
//        fpsOnDisplay.setFont(Font.loadFont("/fonts/minecraft.ttf", 50));
        fpsOnDisplay.setStyle("-fx-font-family: 'Nineteen Ninety Three'; -fx-font-size:30px");
        fpsOnDisplay.setFont(Font.loadFont("/fonts/NineteenNinetyThree-L1Ay.ttf", 50));

        gameStageButton = new Button();
        gameStageButton.setStyle("-fx-background-radius: 20;-fx-background-color: transparent; -fx-border-color: transparent; -fx-background-image: url(button-invis.png)");
        gameStageButton.setPrefSize(100,130 );
        gameStageButton.setLayoutX(1785);
        gameStageButton.setLayoutY(475); //921 right-bottom


        Pane pane = new Pane(canvas, fpsOnDisplay, gameStageButton);
        Scene scene = new Scene(pane);

        stage.setTitle("CatsVsMice");
        stage.setScene(scene);
        stage.show();

    }

    public void render(){
        long currentFrameTime = System.nanoTime();
        double elapsedTime = (currentFrameTime - lastFrameTime) / 1_000_000.0;
        lastFrameTime = currentFrameTime;

        double fps = 1_000.0 / elapsedTime;
        fpsOnDisplay.setText("FPS: " + String.format("%.2f", fps));

        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.drawImage(background, 0, 0);
        gc.setFill(Color.ORANGE);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        path = gameModel.getPath();
        for(int i = 0; i < path.size(); i+=2){
            if(i > 0){
                gc.strokeLine(path.get(i-2), path.get(i-1), path.get(i), path.get(i+1));
                gc.fillOval(path.get(i-2)-15, path.get(i-1)-15, 25, 25);
            }
            gc.fillOval(path.get(i)-15, path.get(i+1)-15, 25, 25);
        }
        cats = gameModel.getCats();
        for(int i = 0; i < cats.size(); i+=2){
            gc.drawImage(gameModel.getCatImg(), cats.get(i)-gameModel.getCatImg().getWidth()/2, cats.get(i+1)-gameModel.getCatImg().getHeight()/2);
        }


        gc.drawImage(gameModel.getMouseIMG(), gameModel.getMousePosX()-gameModel.getMouseWidth()/2, gameModel.getMousePosY()-gameModel.getMouseHeight()/2);

    }

    public Button getGameStageButton(){
        return gameStageButton;
    }

    public void setGameStageButtonStyle(int style){
        switch (style){
            case BUTTON_PRESSED:
                gameStageButton.setStyle("-fx-background-radius: 20;-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; -fx-background-image: url(button-press-brown.png)");
                break;
            case BUTTON_RELEASED:
            case ON_BUTTON:
                gameStageButton.setStyle("-fx-background-radius: 20;-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; -fx-background-image: url(button-vis-brown.png)");
                break;
            case OUT_OF_BUTTON:
                gameStageButton.setStyle("-fx-background-radius: 20;-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; -fx-background-image: url(button-invis.png)");
                break;
        }
    }

}
