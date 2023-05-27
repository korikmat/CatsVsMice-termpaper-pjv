package game.fx.catsvsmice.view.scenes;

import game.fx.catsvsmice.view.hud.GameButtons;
import game.fx.catsvsmice.view.GameView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.Objects;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

/**
 * The type Main menu scene.
 * The class creates a scene with the main menu.
 * It contains three buttons: create level, select level, about me.
 */
public class MainMenuScene {
    private static final String BACKGROUND_IMG_NAME = "room-background.png";
    private static final String MENU_IMG_PIC_NAME = "main-menu.png";
    private static  final double MENU_IMG_WIDTH = WINDOW_WIDTH * (136.0 / 320.0);
    private static  final double MENU_IMG_HEIGHT = WINDOW_HEIGHT * (88.0 / 180.0);
    private static  final double MENU_IMV_POS_X = WINDOW_WIDTH*(92.0/320.0);
    private static  final double MENU_IMV_POS_Y = WINDOW_HEIGHT*(45.0/180.0);
    private static final String FONT_PATH = "/fonts/NineteenNinetyThree-L1Ay.ttf";
    private static  final double MENU_BUTTON_TEXT_SIZE = WINDOW_HEIGHT*(7.6/180.0);
    private static  final double MENU_BUTTON_WIDTH = WINDOW_WIDTH*(103.0/320.0);
    private static  final double MENU_BUTTON_HEIGHT = WINDOW_HEIGHT*(19.0/180.0);
    private static  final double MENU_BUTTON_VBOX_SPACING = WINDOW_HEIGHT*(6.0/180);
    private static  final double MENU_BUTTON_VBOX_POS_X = WINDOW_WIDTH*(107.0/320.0);
    private static  final double MENU_BUTTON_VBOX_POS_Y = WINDOW_HEIGHT*(56.0/180);

    private final Scene scene;

    /**
     * Instantiates a new Main menu scene.
     *
     * @param gameButtons the game buttons
     */
    public MainMenuScene(GameButtons gameButtons){

        Image background = new Image(BACKGROUND_IMG_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
        ImageView backgroundImv = new ImageView(background);
        backgroundImv.setX(0);
        backgroundImv.setY(0);

        Image menuImg = new Image(MENU_IMG_PIC_NAME, MENU_IMG_WIDTH, MENU_IMG_HEIGHT, false, false);
        ImageView menuImv = new ImageView(menuImg);
        menuImv.setX(MENU_IMV_POS_X);
        menuImv.setY(MENU_IMV_POS_Y);


        Button startButton = new Button("Create LVL");
        startButton.setId("startButton");
        Button selectLvlButton = new Button("Select LVL");
        selectLvlButton.setId("selectLvlButton");
        Button createLvlButton = new Button("About Me");
        createLvlButton.setId("aboutMe");

        for (Button menuButton : Arrays.asList(startButton, selectLvlButton, createLvlButton)) {
            menuButton.setFont(Font.loadFont(GameView.class.getResourceAsStream(FONT_PATH), MENU_BUTTON_TEXT_SIZE));
            menuButton.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
            menuButton.getStyleClass().add("my-menu-button");
            gameButtons.add(menuButton);
        }

        VBox menuButtonsVbox = new VBox(startButton, selectLvlButton, createLvlButton);
        menuButtonsVbox.setSpacing(MENU_BUTTON_VBOX_SPACING);
        menuButtonsVbox.setLayoutX(MENU_BUTTON_VBOX_POS_X);
        menuButtonsVbox.setLayoutY(MENU_BUTTON_VBOX_POS_Y);

        Pane menuPane = new Pane(backgroundImv, menuImv, menuButtonsVbox);
        scene = new Scene(menuPane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }
}
