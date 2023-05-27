package game.fx.catsvsmice.view.hud;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

/**
 * The type Head up display.
 * A class that creates a game button and an info menu and combines them.
 * Responsible for almost the entire HUD in the game,
 * except the back button in the main menu.
 */
public class HeadUpDisplay {
    private GameStageButton gameButton;
    private GameStageInfoMenu infoMenuPane;
    private TranslateTransition transitionRight;
    private TranslateTransition transitionLeft;
    private TranslateTransition transitionDown;
    private TranslateTransition transitionUp;


    /**
     * Instantiates a new Head up display.
     */
    public HeadUpDisplay(){
        initGameStageButton();
        initGameStageInfoMenu();
    }
    private void initGameStageButton(){
        gameButton = new GameStageButton();
        gameButton.setId("nextStageButton");
        transitionLeft = new TranslateTransition(Duration.seconds(2), gameButton);
        transitionRight = new TranslateTransition(Duration.seconds(2), gameButton);
        slideLeftGameStageButton();
        slideRightGameStageButton();
    }

    /**
     * Prepare HUD.
     */
    public void prepareHud(){
        firstInfoMenuSlide();
    }
    private void slideLeftGameStageButton(){
        transitionLeft.setFromX(0);
        transitionLeft.setToX(-(WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0)));
    }
    private void slideRightGameStageButton(){
        transitionRight.setFromX(0);
        transitionRight.setToX((WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0)));
    }

    private void initGameStageInfoMenu(){
        infoMenuPane = new GameStageInfoMenu();
        transitionDown = new TranslateTransition(Duration.seconds(1), infoMenuPane);
        transitionUp = new TranslateTransition(Duration.seconds(1), infoMenuPane);
        firstInfoMenuSlide();
    }
    private void firstInfoMenuSlide(){
        transitionDown.setFromY(0);
        transitionDown.setToY(WINDOW_HEIGHT*(16.0/180.0)+WINDOW_HEIGHT*(6.0/180.0));
    }

    /**
     * Gets game button.
     *
     * @return the game button
     */
    public Button getGameButton() {
        return gameButton;
    }

    /**
     * Slide info menu.
     *
     * @param gameStage the game stage
     */
    public void slideInfoMenu(int gameStage){
        transitionUp.setFromY(WINDOW_HEIGHT*(16.0/180.0)+WINDOW_HEIGHT*(6.0/180.0));
        transitionUp.setToY(0);
        transitionUp.play();

        transitionUp.setOnFinished(actionEvent -> {
            updateGameStateText(gameStage);
            transitionDown.setFromY(0);
            transitionDown.setToY(WINDOW_HEIGHT*(16.0/180.0)+WINDOW_HEIGHT*(6.0/180.0));
            transitionDown.play();
        });
    }

    private void updateGameStateText(int gameStage){
        switch (gameStage%3){
            case 0:
                infoMenuPane.setGameStateText("Create path...");
                break;
            case 1:
                infoMenuPane.setGameStateText("Put Cats...");
                break;
            case 2:
                infoMenuPane.setGameStateText("Are attacking!");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameStage % 3);
        }
    }

    /**
     * Get game stage info menu pane.
     *
     * @return the pane
     */
    public Pane getGameStageInfoMenuPane(){
        return infoMenuPane;
    }

    /**
     * Update coins count text.
     *
     * @param coinsCount the coins count
     */
    public void updateCoinsCountText(int coinsCount){
        infoMenuPane.updateCoinsCountText(coinsCount);
    }

    /**
     * Update hearts count text.
     *
     * @param livesCount the lives count
     */
    public void updateHeartsCountText(int livesCount){
        infoMenuPane.updateHeartsCountText(livesCount);
    }

    /**
     * Starts the HUD animation.
     *
     */
    public void hudStart(){
        transitionLeft.play();
        transitionDown.play();
        transitionUp.play();

    }
}
