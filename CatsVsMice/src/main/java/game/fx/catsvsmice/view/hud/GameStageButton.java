package game.fx.catsvsmice.view.hud;

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


/**
 * The type Game stage button.
 * A class that creates a certain kind of button for the HUD,
 * as well as a method to designate this button as returning to the main menu.
 */
public class GameStageButton extends Button {
    private static final double STAGE_BUTTON_WIDTH = WINDOW_WIDTH*(17.0/320.0);
    private static final double STAGE_BUTTON_HEIGHT = WINDOW_HEIGHT*(28.0/180.0);
    private static final double STAGE_BUTTON_POS_X = WINDOW_HEIGHT*(79.0/180.0);
    private static final String BUTTON_PRESSED_IMG_NAME = "stage-button-pressed.png";
    private static final String BUTTON_VISIBLE_IMG_NAME = "stage-button-visible.png";
    private static final String BUTTON_INVISIBLE_IMG_NAME = "stage-button-invisible.png";

    private Background buttonPressed;
    private Background buttonVisible;
    private Background buttonInvisible;

    /**
     * Instantiates a new Game stage button.
     */
    public GameStageButton(){
        setWidth(STAGE_BUTTON_WIDTH);
        setHeight(STAGE_BUTTON_HEIGHT);
        setPrefSize(getWidth(), getHeight());
        setLayoutX(WINDOW_WIDTH);   //WINDOW_WIDTH*(299.0/320.0)
        setLayoutY(STAGE_BUTTON_POS_X);

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
        Image buttonPressedImg = new Image(BUTTON_PRESSED_IMG_NAME, getWidth(), getHeight(), false, false);
        Image buttonVisibleImg = new Image(BUTTON_VISIBLE_IMG_NAME, getWidth(), getHeight(), false, false);
        Image buttonInvisibleImg = new Image(BUTTON_INVISIBLE_IMG_NAME, getWidth(), getHeight(), false, false);

        BackgroundImage buttonPressedBackImg = new BackgroundImage(buttonPressedImg, null, null, null, null);
        BackgroundImage buttonVisibleBackImg = new BackgroundImage(buttonVisibleImg, null, null, null, null);
        BackgroundImage buttonInvisibleBackImg = new BackgroundImage(buttonInvisibleImg, null, null, null, null);


        buttonPressed = new Background(buttonPressedBackImg);
        buttonVisible = new Background(buttonVisibleBackImg);
        buttonInvisible = new Background(buttonInvisibleBackImg);
    }

    /**
     * Set style.
     *
     * @param style the style
     */
    public void setStyle(int style){
        switch (style){
            case BUTTON_PRESSED:
                setBackground(buttonPressed);
                break;

            case BUTTON_RELEASED:
            case ON_BUTTON:
                setBackground(buttonVisible);
                break;
            default:
                setBackground(buttonInvisible);
        }
    }

    /**
     * Set button as back to main menu.
     */
    public void setButtonAsBack(){
        setId("backStageButton");
        setStyle("-fx-scale-x: -1");
        setLayoutX(-getWidth());
        setLayoutY(WINDOW_HEIGHT*(79.0/180.0));
    }
}
