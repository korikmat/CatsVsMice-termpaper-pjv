package game.fx.catsvsmice.view;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.util.Duration;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;
import static game.fx.catsvsmice.view.GameView.BUTTON_PRESSED;
import static game.fx.catsvsmice.view.GameView.BUTTON_RELEASED;
import static game.fx.catsvsmice.view.GameView.ON_BUTTON;
import static game.fx.catsvsmice.view.GameView.OUT_OF_BUTTON;


public class GameStageButton {
    private final Button button;
    private final double sizeX;
    private final double sizeY;
    private Background buttonPressed;
    private Background buttonVisible;
    private Background buttonInvisible;
    private final TranslateTransition transitionLeft;

    public GameStageButton(){
        button = new Button();
        sizeX = WINDOW_WIDTH*(17.0/320.0);
        sizeY = WINDOW_HEIGHT*(28.0/180.0);
        button.setPrefSize(sizeX,sizeY);
        button.setLayoutX(WINDOW_WIDTH+sizeX);   //WINDOW_WIDTH*(299.0/320.0)
        button.setLayoutY(WINDOW_HEIGHT*(79.0/180.0));

        initButtonBackground();

        setStyle(OUT_OF_BUTTON);

        transitionLeft = new TranslateTransition(Duration.seconds(2), button);
        slide();
    }
    private void initButtonBackground(){
        Image buttonPressedImg = new Image("stage-button-pressed.png", sizeX, sizeY, false, false);
        Image buttonVisibleImg = new Image("stage-button-visible.png", sizeX, sizeY, false, false);
        Image buttonInvisibleImg = new Image("stage-button-invisible.png", sizeX, sizeY, false, false);

        BackgroundImage buttonPressedBackImg = new BackgroundImage(buttonPressedImg, null, null, null, null);
        BackgroundImage buttonVisibleBackImg = new BackgroundImage(buttonVisibleImg, null, null, null, null);
        BackgroundImage buttonInvisibleBackImg = new BackgroundImage(buttonInvisibleImg, null, null, null, null);

        buttonPressed = new Background(buttonPressedBackImg);
        buttonVisible = new Background(buttonVisibleBackImg);
        buttonInvisible = new Background(buttonInvisibleBackImg);
    }
    public void setStyle(int style){
        switch (style){
            case BUTTON_PRESSED:
                button.setBackground(buttonPressed);
                break;

            case BUTTON_RELEASED:
            case ON_BUTTON:
                button.setBackground(buttonVisible);
                break;

            case OUT_OF_BUTTON:
                button.setBackground(buttonInvisible);
                break;
        }
    }
    private void slide(){
        transitionLeft.setFromX(0);
        transitionLeft.setToX(-(WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0)+sizeX));
        transitionLeft.play();
    }
    public Button getButton(){
        return button;
    }
}
