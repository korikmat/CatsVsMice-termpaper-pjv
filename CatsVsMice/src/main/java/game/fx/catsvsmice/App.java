package game.fx.catsvsmice;

import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;



public class App extends Application {
    private GameView gameView;
    private GameModel gameModel;

    @Override
    public void start(Stage stage) {
        gameModel = new GameModel();

        gameView = new GameView(stage, gameModel);
        gameView.initRender();

        int FPS = 30;
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000D / FPS), upDateEvent -> {
            gameView.render();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyPressedEvent -> {
            gameModel.changeState(keyPressedEvent);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}