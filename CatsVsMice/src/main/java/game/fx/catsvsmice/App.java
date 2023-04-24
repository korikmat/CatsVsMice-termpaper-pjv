package game.fx.catsvsmice;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.Timeline;

import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.A;



public class App extends Application {

    double x = 0;
    double y = 540;
    Image img = new Image("mouse.png", 113*0.7, 114*0.7, false, false);
    Image back = new Image("background.png", 1920, 1080, false, false);
    Canvas canvas = new Canvas(back.getWidth(), back.getHeight());
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Scene scene;
    Pane pane = new Pane();
    Text fpsInGame = new Text();

    @Override
    public void start(Stage stage) {

        pane.getChildren().add(canvas);
        pane.getChildren().add(fpsInGame);

        fpsInGame.setFill(Color.WHITE);
        fpsInGame.setX(10);
        fpsInGame.setY(30);
        fpsInGame.setStyle("-fx-font: 40 arial;");


        scene = new Scene(pane);


        int FPS = 30;


        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000D / FPS), new EventHandler<ActionEvent>() {
            long lastFrameTime = System.nanoTime();

            @Override
            public void handle(ActionEvent event) {
                long currentFrameTime = System.nanoTime();
                double elapsedTime = (currentFrameTime - lastFrameTime) / 1_000_000.0;
                lastFrameTime = currentFrameTime;

                double fps = 1_000.0 / elapsedTime;
                fpsInGame.setText("FPS: " + String.format("%.2f", fps));

                gc.drawImage(back, 0, 0);
                gc.drawImage(img, x, y);

            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();


        scene.setOnKeyPressed((keyEvent -> {

            switch (keyEvent.getCode()) {
                case W: { // 27 = ESC key
                    y -= 10;
                    break;
                }
                case D: { // 10 = Return
                    x += 10;
                    break;
                }
                case S: {
                    y += 10;
                    break;
                }
                case A: {
                    x -= 10;
                    break;
                }
            }

        }));



//        var gameLoopTimer = new AnimationTimer() {
//            private long lastUpdate = 0;
//            private final int FPS = 100;
//            private final long INTERVAL = 1_000_000_000L/FPS;
//            long lastFrameTime = System.nanoTime();
//            @Override
//            public void handle(long now) { // in nanoseconds
//                long currentFrameTime = System.nanoTime();
//                double elapsedTime = (currentFrameTime - lastFrameTime) / 1_000_000.0;
//                lastFrameTime = currentFrameTime;
//
//                double fps = 1_000.0 / elapsedTime;
//                fpsInGame.setText("FPS: " + String.format("%.2f", fps));
//                if (now - lastUpdate >= INTERVAL) { // DEBUG: huge delay
//
//
//
////                    gc.drawImage(back, 0, 0);
//                    gc.drawImage(img, x, y);
//
//                    lastUpdate = now;
//                }
//            }
//        };
//        gameLoopTimer.start();



        stage.setScene(scene);
        stage.setTitle("CatsVsMice");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}