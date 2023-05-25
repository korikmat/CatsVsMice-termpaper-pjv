package game.fx.catsvsmice;

import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Arrays;
import java.util.Objects;

public class App extends Application {
    private static final int FPS = 30;

    private double computerMousePosX;
    private double computerMousePosY;
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());


    @Override
    public void start(Stage stage){
        GameModel gameModel;
        GameView gameView;
        LOGGER.log(Level.INFO, "App started!");

        GameData gameData = new GameData();

        gameModel = new GameModel(gameData);
        DataSaver dataSaver = new DataSaver(gameModel, gameData);

        gameView = new GameView(stage, gameModel, gameData);

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000.0 / FPS), upDateEvent -> {

            gameView.render();
            gameModel.advanceState(computerMousePosX, computerMousePosY);

        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();


        for (Scene scene: Arrays.asList(gameView.getScene(GameData.State.EDITOR), gameView.getScene(GameData.State.GAME))) {
            scene.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedEvent -> {
                switch (gameData.getStage()){
                    case 0:
                        gameModel.putPath(mouseClickedEvent.getSceneX(), mouseClickedEvent.getSceneY());
                        LOGGER.log(Level.INFO, "New point was added! Pos:{0}", mouseClickedEvent.getSceneX()+" "+ mouseClickedEvent.getSceneY());
                        break;
                    case 1:
                        LOGGER.log(Level.INFO, "Try to add new Cat Pos:{0}", mouseClickedEvent.getSceneX()+" "+ mouseClickedEvent.getSceneY());
                        gameModel.putCat(mouseClickedEvent.getSceneX(), mouseClickedEvent.getSceneY());
                        break;
                    default:
                        break;
                }
            });
        }

        for (Scene scene: Arrays.asList(gameView.getScene(GameData.State.EDITOR), gameView.getScene(GameData.State.GAME))) {
            scene.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedEvent -> {
                computerMousePosX = mouseMovedEvent.getSceneX();
                computerMousePosY = mouseMovedEvent.getSceneY();
            });
        }

        for (Button button: gameView.getGameButtons()) {
            button.setOnMouseClicked(event -> {
                if(Objects.equals(button.getId(), "startButton")){
                    LOGGER.log(Level.INFO, "Start Button was pressed");
                    gameData.setStage(0);
                    gameData.setState(GameData.State.EDITOR);
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "selectLvlButton")){
                    LOGGER.log(Level.INFO, "Select Lvl Button was pressed");
                    gameData.setState(GameData.State.LVL_SELECTION);
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "aboutMe")){
                    LOGGER.log(Level.INFO, "About Me Button was pressed");
                    gameData.setState(GameData.State.ABOUTME);
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "backStageButton")){
                    LOGGER.log(Level.INFO, "Back Stage Button was pressed");

                    gameData.setState(GameData.State.MENU);
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "levelButton")){
                    LOGGER.log(Level.INFO, "{0} Button was pressed", button.getText());
                    dataSaver.loadLvlFromJson(button.getText());
                    gameData.setState(GameData.State.GAME);
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "nextStageButton")){
                    LOGGER.log(Level.INFO, "Next Stage Button was pressed");
                    gameData.setStage((gameData.getStage()+1)%2);
                    gameData.setState(GameData.State.EDITOR);
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "saveLvlButton")){
                    LOGGER.log(Level.INFO, "Save Level Button was pressed");
                    dataSaver.saveLvlToJson();
                }
                if(Objects.equals(button.getId(), "startGameButton")){
                    LOGGER.log(Level.INFO, "Start Game Button was pressed");

                    gameData.setStage(1);
                    gameData.setState(GameData.State.GAME);
                    gameView.actualizeScene();
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}