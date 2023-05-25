package game.fx.catsvsmice.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;
import static game.fx.catsvsmice.view.GameView.BUTTON_PRESSED;
import static game.fx.catsvsmice.view.GameView.BUTTON_RELEASED;
import static game.fx.catsvsmice.view.GameView.ON_BUTTON;
import static game.fx.catsvsmice.view.GameView.OUT_OF_BUTTON;


public class GameStageButton extends Button {
    private Background buttonPressed;
    private Background buttonVisible;
    private Background buttonInvisible;

    public GameStageButton(){
        setWidth(WINDOW_WIDTH*(17.0/320.0));
        setHeight(WINDOW_HEIGHT*(28.0/180.0));
        setPrefSize(getWidth(), getHeight());
        setLayoutX(WINDOW_WIDTH);   //WINDOW_WIDTH*(299.0/320.0)
        setLayoutY(WINDOW_HEIGHT*(79.0/180.0));

        initButtonBackground();

        setStyle(OUT_OF_BUTTON);

        setOnMousePressed(event -> {
            setStyle(BUTTON_PRESSED);
        });
        setOnMouseEntered(event -> {
            setStyle(ON_BUTTON);
        });
        setOnMouseExited(event -> {
            setStyle(OUT_OF_BUTTON);
        });
    }
    private void initButtonBackground(){
        Image buttonPressedImg = new Image("stage-button-pressed.png", getWidth(), getHeight(), false, false);
        Image buttonVisibleImg = new Image("stage-button-visible.png", getWidth(), getHeight(), false, false);
        Image buttonInvisibleImg = new Image("stage-button-invisible.png", getWidth(), getHeight(), false, false);

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
                setBackground(buttonPressed);
                break;

            case BUTTON_RELEASED:
            case ON_BUTTON:
                setBackground(buttonVisible);
                break;

            case OUT_OF_BUTTON:
                setBackground(buttonInvisible);
                break;
            default:
                setBackground(buttonInvisible);
        }
    }

    public void setButtonAsBack(){
        setStyle("-fx-scale-x: -1");
        setLayoutX(-getWidth());
        setLayoutY(WINDOW_HEIGHT*(79.0/180.0));
    }
}
