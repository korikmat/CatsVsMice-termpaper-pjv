package game.fx.catsvsmice;

import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;



public class App extends Application {
    private GameView gameView;
    private GameModel gameModel;
    private int gameStage = 0;
    @Override
    public void start(Stage stage) {
        gameModel = new GameModel();

        gameView = new GameView(stage, gameModel);
        gameView.initRender();

        int FPS = 30;
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000D / FPS), upDateEvent -> {
            gameView.render();
            if(gameStage%3 == 2){

//                firstT = 0;
//                firstT = System.nanoTime();
//                elapsed = firstT - lastT;
//                lastT = first;
                gameModel.moveMouse();
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

//        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyPressedEvent -> {
//            gameModel.moveMouseKeyboard(keyPressedEvent);
//        });

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedEvent -> {

            System.out.println(mouseClickedEvent.getSceneX() + " " + mouseClickedEvent.getSceneY());
            switch (gameStage%3){
                case 0:
                    gameModel.putPath(mouseClickedEvent.getSceneX(), mouseClickedEvent.getSceneY());
                    break;
                case 1:
                    gameModel.putCat(mouseClickedEvent.getSceneX(), mouseClickedEvent.getSceneY());
                    break;
            }

        });

        gameView.getGameStageButton().setOnMousePressed(event -> {
            gameView.setGameStageButtonStyle(GameView.BUTTON_PRESSED);
            gameStage+=1;
        });
        gameView.getGameStageButton().setOnMouseReleased(event -> {
            gameView.setGameStageButtonStyle(GameView.BUTTON_RELEASED);
        });
        gameView.getGameStageButton().setOnMouseEntered(event -> {
            gameView.setGameStageButtonStyle(GameView.ON_BUTTON);
        });
        gameView.getGameStageButton().setOnMouseExited(event -> {
            gameView.setGameStageButtonStyle(GameView.OUT_OF_BUTTON);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}