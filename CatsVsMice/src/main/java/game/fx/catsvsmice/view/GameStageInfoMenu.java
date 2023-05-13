package game.fx.catsvsmice.view;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class GameStageInfoMenu {
    private final Pane infoMenuPane;
    private final TranslateTransition transitionUp;
    private final TranslateTransition transitionDown;
    private ImageView backgroundImv;
    private ImageView coinImv;
    private Text coinsCountText;
    private Text gameStateText;
    public GameStageInfoMenu(){
        createImages();
        createText();

        infoMenuPane = new Pane(backgroundImv, coinImv, coinsCountText, gameStateText);
        setPaneSize();

        transitionDown = new TranslateTransition(Duration.seconds(1), infoMenuPane);
        transitionUp = new TranslateTransition(Duration.seconds(1), infoMenuPane);
        firstSlide();
    }
    private void createImages(){
        Image backgroundImg = new Image("menu.png", WINDOW_WIDTH*(136.0/320.0),  WINDOW_HEIGHT*(16.0/180.0), false, false);
        Image coinImg = new Image("coin.gif", WINDOW_WIDTH*(10.0/320.0), WINDOW_HEIGHT*(10.0/180.0), false, false);

        backgroundImv = new ImageView(backgroundImg);
        coinImv = new ImageView(coinImg);

        coinImv.setX(WINDOW_WIDTH*122.0/320.0);
        coinImv.setY(WINDOW_HEIGHT*3.0/180.0);
    }
    private void createText(){
        coinsCountText = new Text(WINDOW_WIDTH*(107.0/320.0), WINDOW_HEIGHT*(11.0/180.0), "");
        gameStateText = new Text(WINDOW_WIDTH*(8.0/320), WINDOW_HEIGHT*(11.0/180.0), "Create path...");

        gameStateText.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(8.0/180)));
        coinsCountText.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(8.0/180)));
        coinsCountText.setFill(Color.valueOf("#e8c3a1"));
        gameStateText.setFill(Color.valueOf("#e8c3a1"));
    }
    private void setPaneSize(){

        infoMenuPane.setLayoutX(WINDOW_WIDTH*(92.0/320.0));
        infoMenuPane.setLayoutY(-WINDOW_HEIGHT*(16.0/180.0));
    }
    private void firstSlide(){
        transitionDown.setFromY(0);
        transitionDown.setToY(WINDOW_HEIGHT*(16.0/180.0)+WINDOW_HEIGHT*(6.0/180.0));
        transitionDown.play();
    }

    public void slideUp(){
        transitionUp.setFromY(WINDOW_HEIGHT*(16.0/180.0)+WINDOW_HEIGHT*(6.0/180.0));
        transitionUp.setToY(0);
        transitionUp.play();
    }

    public void slideDown(){
        transitionUp.setOnFinished(actionEvent -> {
            transitionDown.setFromY(0);
            transitionDown.setToY(WINDOW_HEIGHT*(16.0/180.0)+WINDOW_HEIGHT*(6.0/180.0));
            transitionDown.play();
        });
    }
    public Pane getPane(){
        return infoMenuPane;
    }
    public void updateCoinsCountText(int coinsCount){
        coinsCountText.setText(String.valueOf(coinsCount));
    }
}
