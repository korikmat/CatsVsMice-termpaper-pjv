package game.fx.catsvsmice.view.scenes;

import game.fx.catsvsmice.view.hud.GameButtons;
import game.fx.catsvsmice.view.hud.GameStageButton;
import game.fx.catsvsmice.view.GameView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

/**
 * The type About me scene.
 * A class that creates an About Me scene that contains a background,
 * text, and a button that returns to the main menu.
 */
public class AboutMeScene {
    private static final String BACKGROUND_IMG_NAME = "room-background.png";
    private static final double BACK_TO_MENU_BUTTON_POS_X = WINDOW_WIDTH*(7.0/320);
    private static final String MENU_IMG_PIC_NAME = "main-menu.png";
    private static  final double MENU_IMG_WIDTH = WINDOW_WIDTH * (136.0 / 320.0);
    private static  final double MENU_IMG_HEIGHT = WINDOW_HEIGHT * (88.0 / 180.0);
    private static  final double MENU_IMV_POS_X = WINDOW_WIDTH*(92.0/320.0);
    private static  final double MENU_IMV_POS_Y = WINDOW_HEIGHT*(45.0/180.0);
    private static  final double ABOUT_ME_TEXT_POS_X = WINDOW_WIDTH*(105.0/320.0);
    private static  final double ABOUT_ME_TEXT_POS_Y = WINDOW_HEIGHT*(57.0/180.0);
    private static final String ABOUT_ME_TEXT =
            "The rules are very simple.\n" +
            "By creating a level you can create\n" +
            " a path along which the mice will run.\n" +
            "During the game you can\n" +
            "buy cats and arrange them so that\n" +
            "they scare the mice.          ¨/\\_/\\\n" +
            "The goal of the game is       >^,^<\n" +
            "to protect the cheese!     ¨¨/ \\\n" +
            "Enjoy it. And good luck!      ¨(___)__\n";
    private static final String ABOUT_ME_TEXT_FONT = "/fonts/NineteenNinetyThree-L1Ay.ttf";
    private static final double ABOUT_ME_TEXT_SIZE = WINDOW_HEIGHT*(6/180.0);
    private static final String ABOUT_ME_TEXT_COLOR = "#cfb29b";

    private final Scene scene;

    /**
     * Instantiates a new About Me scene.
     *
     * @param gameButtons the game buttons
     */
    public AboutMeScene(GameButtons gameButtons){
        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        backToMenuButton.setLayoutX(BACK_TO_MENU_BUTTON_POS_X);
        gameButtons.add(backToMenuButton);

        Image background = new Image(BACKGROUND_IMG_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
        ImageView backgroundImv = new ImageView(background);
        backgroundImv.setX(0);
        backgroundImv.setY(0);

        Image menuImg = new Image(MENU_IMG_PIC_NAME, MENU_IMG_WIDTH, MENU_IMG_HEIGHT, false, false);
        ImageView menuImv = new ImageView(menuImg);
        menuImv.setX(MENU_IMV_POS_X);
        menuImv.setY(MENU_IMV_POS_Y);

        Text aboutMeText = new Text(ABOUT_ME_TEXT_POS_X, ABOUT_ME_TEXT_POS_Y, ABOUT_ME_TEXT);
        aboutMeText.setFont(Font.loadFont(GameView.class.getResourceAsStream(ABOUT_ME_TEXT_FONT), ABOUT_ME_TEXT_SIZE));
        aboutMeText.setFill(Color.valueOf(ABOUT_ME_TEXT_COLOR));
        Pane aboutMePane = new Pane(backgroundImv, menuImv, backToMenuButton, aboutMeText);

        scene = new Scene(aboutMePane);
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
