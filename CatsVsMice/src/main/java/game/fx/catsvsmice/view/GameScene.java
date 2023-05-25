package game.fx.catsvsmice.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class GameScene {
    private final Scene scene;
    private final Canvas gameCanvas;
    private final HeadUpDisplay headUpDisplay;
    private Text fpsOnDisplay;
    public GameScene(GameButtons gameButtons){
        initFpsOnDisplayText();

        headUpDisplay = new HeadUpDisplay();
        gameButtons.add(headUpDisplay.getGameButton());

        gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        Pane gamePane = new Pane(gameCanvas, fpsOnDisplay, headUpDisplay.getGameStageInfoMenuPane());
        scene = new Scene(gamePane, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    private void initFpsOnDisplayText(){
        fpsOnDisplay = new Text(WINDOW_WIDTH*(1710.0/1920.0), WINDOW_HEIGHT*(95.0/1080.0), "FPS: 0");
        double textSize = (WINDOW_HEIGHT*(30.0/1080.0));
        fpsOnDisplay.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), textSize));

    }
    public void updateFps(double fps){
        fpsOnDisplay.setText("FPS: " + String.format("%.2f", fps));
    }
    public Scene getScene(){
        return scene;
    }
    public Canvas getCanvas(){
        return gameCanvas;
    }

    public void prepareToView(){
        headUpDisplay.prepareHud();
        headUpDisplay.hudStart();
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
