package game.fx.catsvsmice.view.scenes;

import game.fx.catsvsmice.DataSaver;
import game.fx.catsvsmice.view.hud.GameButtons;
import game.fx.catsvsmice.view.hud.GameStageButton;
import game.fx.catsvsmice.view.GameView;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

/**
 * The type Lvl selection scene.
 * The class creates a scene to select a saved level loaded into a json file.
 */
public class LvlSelectionScene {
    private static final String BACKGROUND_IMG_NAME = "room-background.png";
    private static final String MENU_IMG_PIC_NAME = "main-menu.png";
    private static  final double MENU_IMG_WIDTH = WINDOW_WIDTH * (136.0 / 320.0);
    private static  final double MENU_IMG_HEIGHT = WINDOW_HEIGHT * (88.0 / 180.0);
    private static  final double MENU_IMV_POS_X = WINDOW_WIDTH*(92.0/320.0);
    private static  final double MENU_IMV_POS_Y = WINDOW_HEIGHT*(45.0/180.0);
    private static final String FONT_PATH = "/fonts/NineteenNinetyThree-L1Ay.ttf";
    private static final double LVL_BUTTONS_TEXT_SIZE = WINDOW_HEIGHT*(7.6/180.0);
    private static final double LVL_BUTTONS_WIDTH = WINDOW_WIDTH*(103.0/320.0);
    private static final double LVL_BUTTONS_HEIGHT = WINDOW_HEIGHT*(19.0/180.0);
    private static final double SCROLL_PANE_WIDTH = WINDOW_WIDTH*(103.0/320.0);
    private static final double SCROLL_PANE_HEIGHT = WINDOW_HEIGHT*(69.0/180.0);
    private static final double SCROLL_PANE_POS_X = WINDOW_WIDTH*(107.0/320.0);
    private static final double SCROLL_PANE_POS_Y = WINDOW_HEIGHT*(56.0/180);
    private static final double BACK_STAGE_BUTTON_END_POS_X = WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0);

    private final Scene scene;
    private final TranslateTransition transition;
    private final GameButtons gameButtons;
    private final VBox vboxButtons = new VBox();
    private final DataSaver dataSaver = new DataSaver();
    private int currLvlsCount;

    /**
     * Instantiates a new Lvl selection scene.
     *
     * @param gameButtons the game buttons
     */
    public LvlSelectionScene(GameButtons gameButtons){
        this.gameButtons = gameButtons;

        Image background = new Image(BACKGROUND_IMG_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
        ImageView backgroundImv = new ImageView(background);
        backgroundImv.setX(0);
        backgroundImv.setY(0);
        Image menuImg = new Image(MENU_IMG_PIC_NAME, MENU_IMG_WIDTH, MENU_IMG_HEIGHT, false, false);
        ImageView menuImv = new ImageView(menuImg);
        menuImv.setX(MENU_IMV_POS_X);
        menuImv.setY(MENU_IMV_POS_Y);


        vboxButtons.setAlignment(Pos.CENTER);
        vboxButtons.setSpacing(10);

        currLvlsCount = dataSaver.countSavedFiles();
        for (int i = 0; i < currLvlsCount; i++) {
            Button lvlButton = new Button("Level " + i);
            lvlButton.setId("levelButton");
            lvlButton.setFont(Font.loadFont(GameView.class.getResourceAsStream(FONT_PATH), LVL_BUTTONS_TEXT_SIZE));
            lvlButton.setPrefSize(LVL_BUTTONS_WIDTH,LVL_BUTTONS_HEIGHT);
            lvlButton.setLayoutX(0);
            lvlButton.setLayoutY(0);

            lvlButton.getStyleClass().add("my-menu-button");

            gameButtons.add(lvlButton);
            vboxButtons.getChildren().add(lvlButton);
        }

        ScrollPane scrollPane = new ScrollPane(vboxButtons);
        scrollPane.setPrefViewportWidth(SCROLL_PANE_WIDTH);
        scrollPane.setPrefViewportHeight(SCROLL_PANE_HEIGHT);

        scrollPane.setLayoutX(SCROLL_PANE_POS_X);
        scrollPane.setLayoutY(SCROLL_PANE_POS_Y);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        gameButtons.add(backToMenuButton);

        transition = new TranslateTransition(Duration.seconds(1), backToMenuButton);

        Pane lvlSelectionPane = new Pane(backgroundImv, menuImv, backToMenuButton, scrollPane);
        scene = new Scene(lvlSelectionPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
    }

    /**
     * Check for new levels.
     * Checks the folder with levels for a new one,
     * and if there are new ones,
     * adds a new button with a level
     */
    public void checkForNewLevels(){
        for(int i = currLvlsCount; i < dataSaver.countSavedFiles(); i++){
            Button lvlButton = new Button("Level " + i);
            lvlButton.setId("levelButton");
            lvlButton.setFont(Font.loadFont(GameView.class.getResourceAsStream(FONT_PATH), LVL_BUTTONS_TEXT_SIZE));
            lvlButton.setPrefSize(LVL_BUTTONS_WIDTH,LVL_BUTTONS_HEIGHT);
            lvlButton.setLayoutX(0);
            lvlButton.setLayoutY(0);

            lvlButton.getStyleClass().add("my-menu-button");

            gameButtons.add(lvlButton);
            vboxButtons.getChildren().add(lvlButton);
        }
        currLvlsCount = dataSaver.countSavedFiles();
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
     * Prepare to view.
     */
    public void prepareToView(){
        transition.setFromX(0);
        transition.setToX(BACK_STAGE_BUTTON_END_POS_X);
        transition.play();
    }

}
