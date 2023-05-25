package game.fx.catsvsmice.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class AboutMeScene {
    private final Scene scene;

    public AboutMeScene(GameButtons gameButtons){
        GameStageButton backToMenuButton = new GameStageButton();
        backToMenuButton.setButtonAsBack();
        backToMenuButton.setId("backStageButton");
        backToMenuButton.setLayoutX(WINDOW_WIDTH*(7.0/320));
        gameButtons.add(backToMenuButton);

        Image background = new Image("room-background.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
        ImageView backgroundImv = new ImageView(background);
        backgroundImv.setX(0);
        backgroundImv.setY(0);

        Image menuImg = new Image("main-menu.png", WINDOW_WIDTH * (136.0 / 320.0), WINDOW_HEIGHT * (88.0 / 180.0), false, false);
        ImageView menuImv = new ImageView(menuImg);
        menuImv.setX(WINDOW_WIDTH*(92.0/320.0));
        menuImv.setY(WINDOW_HEIGHT*(45.0/180.0));

        Text aboutMeText = new Text(WINDOW_WIDTH*(98.0/320.0), WINDOW_HEIGHT*(60.0/180.0), "The rules are very simple.\n" +
                "By creating a level you can create\n" +
                " a path along which the mice will run.\n" +
                "During the game you can buy cats and arrange\n" +
                " them so that they scare the mice. \n" +
                "The goal of the game is to protect the cheese!\n" +
                "\n" +
                "Enjoy it. And good luck!\n");
        aboutMeText.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(5/180.0)));
        Pane aboutMePane = new Pane(backgroundImv, menuImv, backToMenuButton, aboutMeText);

        scene = new Scene(aboutMePane);
    }

    public Scene getScene() {
        return scene;
    }

}
