package game.fx.catsvsmice.view;

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

public class MainMenuScene {
    private final Scene scene;

    public MainMenuScene(GameButtons gameButtons){

        Image background = new Image("room-background.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
        ImageView backgroundImv = new ImageView(background);
        backgroundImv.setX(0);
        backgroundImv.setY(0);
        Image menuImg = new Image("main-menu.png", WINDOW_WIDTH * (136.0 / 320.0), WINDOW_HEIGHT * (88.0 / 180.0), false, false);
        ImageView menuImv = new ImageView(menuImg);
        menuImv.setX(WINDOW_WIDTH*(92.0/320.0));
        menuImv.setY(WINDOW_HEIGHT*(45.0/180.0));


        Button startButton = new Button("Create LVL");
        startButton.setId("startButton");
        Button selectLvlButton = new Button("Select LVL");
        selectLvlButton.setId("selectLvlButton");
        Button createLvlButton = new Button("About Me");
        createLvlButton.setId("aboutMe");

        for (Button menuButton : Arrays.asList(startButton, selectLvlButton, createLvlButton)) {
            menuButton.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(7.6/180.0)));
            menuButton.setPrefSize(WINDOW_WIDTH*(103.0/320.0),WINDOW_HEIGHT*(19.0/180.0));
            menuButton.getStyleClass().add("my-menu-button");
            gameButtons.add(menuButton);
        }

        VBox menuButtonsVbox = new VBox(startButton, selectLvlButton, createLvlButton);
        menuButtonsVbox.setSpacing(WINDOW_HEIGHT*(6.0/180));
        menuButtonsVbox.setLayoutX(WINDOW_WIDTH*(107.0/320.0));
        menuButtonsVbox.setLayoutY(WINDOW_HEIGHT*(56.0/180));

        Pane menuPane = new Pane(backgroundImv, menuImv, menuButtonsVbox);
        scene = new Scene(menuPane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

    }

    public Scene getScene() {
        return scene;
    }
}
