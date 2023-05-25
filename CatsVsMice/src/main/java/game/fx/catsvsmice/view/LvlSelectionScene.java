package game.fx.catsvsmice.view;

import game.fx.catsvsmice.DataSaver;
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

public class LvlSelectionScene {
    private final Scene scene;
    private final TranslateTransition transition;

    public LvlSelectionScene(GameButtons gameButtons){
        Image background = new Image("room-background.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
        ImageView backgroundImv = new ImageView(background);
        backgroundImv.setX(0);
        backgroundImv.setY(0);
        Image menuImg = new Image("main-menu.png", WINDOW_WIDTH * (136.0 / 320.0), WINDOW_HEIGHT * (88.0 / 180.0), false, false);
        ImageView menuImv = new ImageView(menuImg);
        menuImv.setX(WINDOW_WIDTH*(92.0/320.0));
        menuImv.setY(WINDOW_HEIGHT*(45.0/180.0));



        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        DataSaver dataSaver = new DataSaver();
        int buttonsCount = dataSaver.countSavedFiles();
        for (int i = 0; i < buttonsCount; i++) {
            Button lvlButton = new Button("Level " + i);
            lvlButton.setId("levelButton");
            lvlButton.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(7.6/180.0)));
            lvlButton.setPrefSize(WINDOW_WIDTH*(103.0/320.0),WINDOW_HEIGHT*(19.0/180.0));
            lvlButton.setLayoutX(0);
            lvlButton.setLayoutY(0);

            lvlButton.getStyleClass().add("my-menu-button");

            gameButtons.add(lvlButton);
            vbox.getChildren().add(lvlButton);
        }

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setPrefViewportWidth(WINDOW_WIDTH*(103.0/320.0));
        scrollPane.setPrefViewportHeight(WINDOW_HEIGHT*(69.0/180.0));

        scrollPane.setLayoutX(WINDOW_WIDTH*(107.0/320.0));
        scrollPane.setLayoutY(WINDOW_HEIGHT*(56.0/180));
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        backToMenuButton.setId("backStageButton");
        gameButtons.add(backToMenuButton);

        transition = new TranslateTransition(Duration.seconds(1), backToMenuButton);

        Pane lvlSelectionPane = new Pane(backgroundImv, menuImv, backToMenuButton, scrollPane);
        scene = new Scene(lvlSelectionPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

    }

    public Scene getScene(){
        return scene;
    }

    public void prepareToView(){
        transition.setFromX(0);
        transition.setToX((WINDOW_WIDTH-WINDOW_WIDTH*(299.0/320.0)));
        transition.play();
    }
}
