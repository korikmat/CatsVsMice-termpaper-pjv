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
    private double computerMousePosX;
    private double computerMousePosY;
    @Override
    public void start(Stage stage){
        gameModel = new GameModel();

        gameView = new GameView(stage, gameModel);
        gameView.initRender();

        int FPS = 30;
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000.0 / FPS), upDateEvent -> {

            gameView.render();
            gameModel.advanceState(gameStage, computerMousePosX, computerMousePosY);
        }));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedEvent -> {

            System.out.println(mouseClickedEvent.getSceneX() + " " + mouseClickedEvent.getSceneY());
            switch (gameStage){
                case 0:
                    gameModel.putPath(mouseClickedEvent.getSceneX(), mouseClickedEvent.getSceneY());
                    break;
                case 1:
                    gameModel.putCat(mouseClickedEvent.getSceneX(), mouseClickedEvent.getSceneY());
                    break;
            }
        });

        stage.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedEvent -> {
            computerMousePosX = mouseMovedEvent.getSceneX();
            computerMousePosY = mouseMovedEvent.getSceneY();
//            System.out.println("X: " + computerMousePosX + ", Y: " + computerMousePosY);
        });

        gameView.getGameStageButton().setOnMousePressed(event -> {
            gameStage=(gameStage+1)%3;
            gameView.setGameStageButtonStyle(GameView.BUTTON_PRESSED);
            gameView.slideUpInfoMenu();
            gameView.slideDownInfoMenu(gameStage);

            if(gameStage == 1){
                gameModel.setCatPreviewVisibility(true);
            }
            if(gameStage == 2){
                gameModel.setCatPreviewVisibility(false);
            }
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