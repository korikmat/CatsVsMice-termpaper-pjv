package game.fx.catsvsmice.view;

import game.fx.catsvsmice.model.GameModel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
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
    private final Image background = new Image("background.png", 1920, 1080, false, false);
    public GameView(Stage stage, GameModel gameModel){
        this.stage = stage;
        this.gameModel = gameModel;
    }
    public void initRender(){
        canvas = new Canvas(1920, 1080);
        fpsOnDisplay = new Text(1725, 95, "FPS: 0");
//        fpsOnDisplay.setFont(Font.loadFont("/fonts/NineteenNinetyThree-L1Ay.ttf", 120));
        fpsOnDisplay.setStyle("-fx-font-family: 'Nineteen Ninety Three'; -fx-font-size:30px");

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


        gc.drawImage(gameModel.getMouseIMG(), gameModel.getMousePosX()-gameModel.getMouseWidth()/2, gameModel.getMousePosY()-gameModel.getMouseHeight()/2);

    }
}
