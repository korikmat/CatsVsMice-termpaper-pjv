package game.fx.catsvsmice.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView {
    private final Stage stage;
    privat Scene scene;
    private Canvas canvas;
    private Text fpsOnDisplay;
    public GameView(Stage stage){
        this.stage = stage;
    }
    public void initRender(){
        canvas = new Canvas(1920, 1080);
        fpsOnDisplay = new Text(10, 30, "FPS: 0");
        fpsOnDisplay.setFont(Font.loadFont("/fonts/NineteenNinetyThree-L1Ay.ttf", 30));
//        fpsOnDisplay.setStyle("-fx-background-color: #000000; -fx-font-size:30px;");
        Pane pane = new Pane(canvas, fpsOnDisplay);
        Scene scene = new Scene(pane);

        stage.setTitle("CatsVsMice");
        stage.setScene(scene);
        stage.show();

    }
    public void render(){

    }
}
