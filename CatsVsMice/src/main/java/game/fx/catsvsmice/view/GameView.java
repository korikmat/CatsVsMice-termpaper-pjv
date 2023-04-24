package game.fx.catsvsmice.view;

import game.fx.catsvsmice.model.GameModel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView {
    private final Stage stage;
    private GameModel gameModel;
    private Canvas canvas;
    private Text fpsOnDisplay;
    private long lastFrameTime = System.nanoTime();
//    private final Image mouse = new Image("mouse.png", 113*0.7, 114*0.7, false, false);
    private final Image background = new Image("background.png", 1920, 1080, false, false);
    public GameView(Stage stage, GameModel gameModel){
        this.stage = stage;
        this.gameModel = gameModel;
    }
    public void initRender(){
        canvas = new Canvas(1920, 1080);
        fpsOnDisplay = new Text(1725, 90, "FPS: 0");
//        fpsOnDisplay.setFont(Font.loadFont("/fonts/NineteenNinetyThree-L1Ay.ttf", 120));
        fpsOnDisplay.setStyle("-fx-background-color: #000000; -fx-font-size:30px;");
        Pane pane = new Pane(canvas, fpsOnDisplay);
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
        gc.drawImage(background, 0, 0);
        gc.drawImage(gameModel.getMouseIMG(), gameModel.getMousePosX(), gameModel.getMousePosY());

    }
}
