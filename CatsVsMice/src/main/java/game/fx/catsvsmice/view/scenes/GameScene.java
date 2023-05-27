package game.fx.catsvsmice.view.scenes;

import game.fx.catsvsmice.view.hud.GameButtons;
import game.fx.catsvsmice.view.hud.GameStageButton;
import game.fx.catsvsmice.view.GameView;
import game.fx.catsvsmice.view.hud.HeadUpDisplay;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

/**
 * The type Game scene.
 * The class creates the main game scene,
 * which contains the hud, and on which the game objects change.
 */
public class GameScene {
    private static final double BACK_TO_MENU_BUTTON_POS_X = WINDOW_WIDTH*(7.0/320);
    private static final double FPS_POS_X = WINDOW_WIDTH*(1710.0/1920.0);
    private static final double FPS_POS_Y = WINDOW_HEIGHT*(95.0/1080.0);
    private static final double FPS_TEXT_SIZE = WINDOW_HEIGHT*(30.0/1080.0);
    private static final String FONT_PATH = "/fonts/NineteenNinetyThree-L1Ay.ttf";

    private final Scene scene;
    private final Canvas gameCanvas;
    private final HeadUpDisplay headUpDisplay;
    private Text fpsOnDisplay;

    /**
     * Instantiates a new Game scene.
     *
     * @param gameButtons the game buttons
     */
    public GameScene(GameButtons gameButtons){
        initFpsOnDisplayText();

        headUpDisplay = new HeadUpDisplay();
        gameButtons.add(headUpDisplay.getGameButton());

        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        backToMenuButton.setLayoutX(BACK_TO_MENU_BUTTON_POS_X);
        gameButtons.add(backToMenuButton);

        gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        Pane gamePane = new Pane(gameCanvas, fpsOnDisplay, backToMenuButton, headUpDisplay.getGameStageInfoMenuPane());
        scene = new Scene(gamePane, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    private void initFpsOnDisplayText(){
        fpsOnDisplay = new Text(FPS_POS_X, FPS_POS_Y, "");
        fpsOnDisplay.setFont(Font.loadFont(GameView.class.getResourceAsStream(FONT_PATH), FPS_TEXT_SIZE));

    }

    /**
     * Update fps.
     *
     * @param fps the fps
     */
    public void updateFps(double fps){
        fpsOnDisplay.setText("FPS: " + String.format("%.2f", fps));
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
        return gameCanvas;
    }

    /**
     * Prepare to view.
     */
    public void prepareToView(){
        headUpDisplay.prepareHud();
        headUpDisplay.hudStart();
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
