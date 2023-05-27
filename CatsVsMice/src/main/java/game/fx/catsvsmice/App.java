package game.fx.catsvsmice;

import game.fx.catsvsmice.model.GameModel;
import game.fx.catsvsmice.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Arrays;
import java.util.Objects;

/**
 * The type App.
 * Main application class. Launches the game. It is a controller
 * that controls actions in the model and view.
 * It also contains event listeners for buttons and screen clicks.
 */
public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final int FPS = 30;
    private GameData gameData;
    private GameView gameView;
    private GameModel gameModel;
    private DataSaver dataSaver;
    private double computerMousePosX;
    private double computerMousePosY;

    @Override
    public void start(Stage stage){
        LOGGER.log(Level.INFO, "App started!");

        gameData = new GameData();
        gameModel = new GameModel(gameData);
        dataSaver = new DataSaver(gameModel, gameData);
        gameView = new GameView(stage, gameModel, gameData);

        // game loop in which the gameView will be updated at max 30 frames per second.
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000.0 / FPS), upDateEvent -> {

            gameView.render();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        // A new thread in which there will be a cycle in which
        // the GameModel will be updated at !almost! 30 frames per second.
        Task<Void> gameLoopTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    gameModel.advanceState(computerMousePosX, computerMousePosY);
                    Thread.sleep(FPS); //almost 30 frames per second :)
                }
                return null;
            }
        };

        // starts a new thread
        Thread gameLoopThread = new Thread(gameLoopTask);
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();

        stage.setOnCloseRequest(event -> {
            gameLoopTask.cancel();
            gameLoop.stop();
        });


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

        listenGameButtons();
    }

    private void listenGameButtons(){
        for (Button button: gameView.getGameButtons()) {
            button.setOnMouseClicked(event -> {
                if(Objects.equals(button.getId(), "startButton")){
                    LOGGER.log(Level.INFO, "Start Button was pressed");
                    gameData.setStage(0);
                    gameData.setState(GameData.State.EDITOR);
                    gameModel.prepareGameScene();
                    gameView.actualizeScene();
                }
                if(Objects.equals(button.getId(), "selectLvlButton")){
                    LOGGER.log(Level.INFO, "Select Lvl Button was pressed");
                    gameData.setState(GameData.State.LVL_SELECTION);
                    gameView.actualizeScene();

                    listenGameButtons();
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
                    gameData.setStage(1);
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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}