package game.fx.catsvsmice.view;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class HeadUpDisplay {
    private GameStageButton gameButton;
    private GameStageInfoMenu infoMenuPane;
    private TranslateTransition transitionRight;
    private TranslateTransition transitionLeft;
    private TranslateTransition transitionDown;
    private TranslateTransition transitionUp;


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

    public Button getGameButton() {
        return gameButton;
    }
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
        }
    }
    public Pane getGameStageInfoMenuPane(){
        return infoMenuPane;
    }

    public void updateCoinsCountText(int coinsCount){
        infoMenuPane.updateCoinsCountText(coinsCount);
    }

    public void updateHeartsCountText(int livesCount){
        infoMenuPane.updateHeartsCountText(livesCount);
    }

    public void hudStart(){
        transitionLeft.play();
        transitionDown.play();
        transitionUp.play();

    }
}
