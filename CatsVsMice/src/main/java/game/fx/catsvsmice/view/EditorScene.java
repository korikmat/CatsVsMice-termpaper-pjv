package game.fx.catsvsmice.view;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Objects;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class EditorScene {

    private final Scene scene;
    private final Canvas editorCanvas;
    private final HeadUpDisplay headUpDisplay;
    private final TranslateTransition transition;
    public EditorScene(GameButtons gameButtons){
        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        backToMenuButton.setId("backStageButton");
        transition = new TranslateTransition(Duration.seconds(1), backToMenuButton);
        transition.setFromX(0);
        transition.setToX((WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0)));
        gameButtons.add(backToMenuButton);


        Button saveLvlButton = new Button("Save level");
        saveLvlButton.setId("saveLvlButton");
        gameButtons.add(saveLvlButton);
        Button startGameButton = new Button("Start game");
        startGameButton.setId("startGameButton");
        gameButtons.add(startGameButton);

        for (Button button: Arrays.asList(startGameButton, saveLvlButton)) {
            button.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(7/180.0)));
            button.setPrefSize(WINDOW_WIDTH*(69.0/320.0), WINDOW_HEIGHT*(10.0/180.0));
            button.getStyleClass().add("my-menu-button");

        }

        HBox hbox = new HBox(saveLvlButton, startGameButton);
        hbox.setSpacing(WINDOW_HEIGHT*(6.0/180));
        hbox.setLayoutX(WINDOW_WIDTH*(89.0/320.0));
        hbox.setLayoutY(WINDOW_HEIGHT*(161.0/180));

        headUpDisplay = new HeadUpDisplay();
        gameButtons.add(headUpDisplay.getGameButton());


        editorCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

        Pane editorPane = new Pane(editorCanvas, hbox, backToMenuButton, headUpDisplay.getGameButton(), headUpDisplay.getGameStageInfoMenuPane());
        scene = new Scene(editorPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        editorPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

    }

    public Scene getScene(){
        return scene;
    }
    public Canvas getCanvas(){
        return editorCanvas;
    }
    public void prepareToView(){
        headUpDisplay.prepareHud();
        headUpDisplay.hudStart();
        transition.play();
    }
    public void animateHud(int stage){
        headUpDisplay.slideInfoMenu(stage);
    }

    public void updateCoinsCountText(int coinsCount){
        headUpDisplay.updateCoinsCountText(coinsCount);
    }

    public void updateHeartsCountText(int heartsCount){
        headUpDisplay.updateHeartsCountText(heartsCount);
    }

}
