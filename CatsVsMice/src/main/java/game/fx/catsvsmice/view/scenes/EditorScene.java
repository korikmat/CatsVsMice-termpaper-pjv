package game.fx.catsvsmice.view.scenes;

import game.fx.catsvsmice.view.hud.GameButtons;
import game.fx.catsvsmice.view.hud.GameStageButton;
import game.fx.catsvsmice.view.GameView;
import game.fx.catsvsmice.view.hud.HeadUpDisplay;
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

/**
 * The type Editor scene.
 * A class that creates an editor scene that contains an info menu,
 * a button to switch stages, return to the main menu,
 * save the level and start the game.
 */
public class EditorScene {
    private static final double BACK_TO_MENU_BUTTON_END_POS = WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0);
    private static final String BUTTON_TEXT_FONT = "/fonts/NineteenNinetyThree-L1Ay.ttf";
    private static final double BUTTON_TEXT_SIZE = WINDOW_HEIGHT*(7/180.0);
    private static final double BUTTON_WIDTH = WINDOW_WIDTH*(69.0/320.0);
    private static final double BUTTON_HEIGHT = WINDOW_HEIGHT*(10.0/180.0);
    private static final double BUTTONS_HBOX_SPACING = WINDOW_HEIGHT*(6.0/180);
    private static final double BUTTONS_HBOX_POS_X = WINDOW_WIDTH*(89.0/320.0);
    private static final double BUTTONS_HBOX_POS_Y = WINDOW_HEIGHT*(161.0/180);

    private final Scene scene;
    private final Canvas editorCanvas;
    private final HeadUpDisplay headUpDisplay;
    private final TranslateTransition transition;

    /**
     * Instantiates a new Editor scene.
     *
     * @param gameButtons the game buttons
     */
    public EditorScene(GameButtons gameButtons){
        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        transition = new TranslateTransition(Duration.seconds(1), backToMenuButton);
        transition.setFromX(0);
        transition.setToX(BACK_TO_MENU_BUTTON_END_POS);
        gameButtons.add(backToMenuButton);


        Button saveLvlButton = new Button("Save level");
        saveLvlButton.setId("saveLvlButton");
        gameButtons.add(saveLvlButton);
        Button startGameButton = new Button("Start game");
        startGameButton.setId("startGameButton");
        gameButtons.add(startGameButton);

        for (Button button: Arrays.asList(startGameButton, saveLvlButton)) {
            button.setFont(Font.loadFont(GameView.class.getResourceAsStream(BUTTON_TEXT_FONT), BUTTON_TEXT_SIZE));
            button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            button.getStyleClass().add("my-menu-button");

        }

        HBox hbox = new HBox(saveLvlButton, startGameButton);
        hbox.setSpacing(BUTTONS_HBOX_SPACING);
        hbox.setLayoutX(BUTTONS_HBOX_POS_X);
        hbox.setLayoutY(BUTTONS_HBOX_POS_Y);

        headUpDisplay = new HeadUpDisplay();
        gameButtons.add(headUpDisplay.getGameButton());


        editorCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

        Pane editorPane = new Pane(editorCanvas, hbox, backToMenuButton, headUpDisplay.getGameButton(), headUpDisplay.getGameStageInfoMenuPane());
        scene = new Scene(editorPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        editorPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

    }

    /**
     * Get scene.
     *
     * @return the scene
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * Get canvas.
     *
     * @return the canvas
     */
    public Canvas getCanvas(){
        return editorCanvas;
    }

    /**
     * Prepare to view.
     */
    public void prepareToView(){
        headUpDisplay.prepareHud();
        headUpDisplay.hudStart();
        transition.play();
    }

    /**
     * Animate hud.
     *
     * @param stage the stage
     */
    public void animateHud(int stage){
        headUpDisplay.slideInfoMenu(stage);
    }

    /**
     * Update coins count text.
     *
     * @param coinsCount the coins count
     */
    public void updateCoinsCountText(int coinsCount){
        headUpDisplay.updateCoinsCountText(coinsCount);
    }

    /**
     * Update hearts count text.
     *
     * @param heartsCount the hearts count
     */
    public void updateHeartsCountText(int heartsCount){
        headUpDisplay.updateHeartsCountText(heartsCount);
    }

}
