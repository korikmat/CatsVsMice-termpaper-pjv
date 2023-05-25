package game.fx.catsvsmice.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.fx.catsvsmice.view.GameView.WINDOW_HEIGHT;
import static game.fx.catsvsmice.view.GameView.WINDOW_WIDTH;

public class GameStageInfoMenu extends Pane {
    private ImageView backgroundImv;
    private ImageView coinImv;
    private ImageView heartImv;
    private Text coinsCountText;
    private Text gameStateText;
    private Text heartsCountText;
    public GameStageInfoMenu(){
        createImages();
        createText();

        getChildren().addAll(backgroundImv, coinImv, heartImv, coinsCountText, heartsCountText, gameStateText);
        setPanePos(WINDOW_WIDTH*(92.0/320.0), -WINDOW_HEIGHT*(16.0/180.0));


    }
    private void createImages(){
        Image backgroundImg = new Image("info-menu-back.png", WINDOW_WIDTH*(136.0/320.0),  WINDOW_HEIGHT*(16.0/180.0), false, false);
        Image coinImg = new Image("coin.gif", WINDOW_WIDTH*(10.0/320.0), WINDOW_HEIGHT*(10.0/180.0), false, false);
        Image heartImg = new Image("heart.gif", WINDOW_WIDTH*(11.0/320.0), WINDOW_HEIGHT*(11.0/180.0), false, false);

        backgroundImv = new ImageView(backgroundImg);
        coinImv = new ImageView(coinImg);
        heartImv = new ImageView(heartImg);

        coinImv.setX(WINDOW_WIDTH*122.0/320.0);
        coinImv.setY(WINDOW_HEIGHT*3.0/180.0);

        heartImv.setX(WINDOW_WIDTH*93.0/320.0);
        heartImv.setY(WINDOW_HEIGHT*3.0/180.0);
    }
    private void createText(){
        coinsCountText = new Text(WINDOW_WIDTH*(107.0/320.0), WINDOW_HEIGHT*(11.0/180.0), "");
        heartsCountText = new Text(WINDOW_WIDTH*(80.0/320.0), WINDOW_HEIGHT*(11.0/180.0), "");
        gameStateText = new Text(WINDOW_WIDTH*(5.0/320), WINDOW_HEIGHT*(11.0/180.0), "Create path...");

        coinsCountText.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(7.6/180.0)));
        heartsCountText.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(7.6/180.0)));
        gameStateText.setFont(Font.loadFont(GameView.class.getResourceAsStream("/fonts/NineteenNinetyThree-L1Ay.ttf"), WINDOW_HEIGHT*(7.6/180.0)));

        coinsCountText.setFill(Color.valueOf("#e8c3a1"));
        heartsCountText.setFill(Color.valueOf("#e8c3a1"));
        gameStateText.setFill(Color.valueOf("#e8c3a1"));
    }
    private void setPanePos(double posX, double posY){

        setLayoutX(posX);
        setLayoutY(posY);
    }
    public void updateCoinsCountText(int coinsCount){
        coinsCountText.setText(String.format("%4d", coinsCount));
    }
    public void updateHeartsCountText(int heartsCount) {
        heartsCountText.setText(String.format("%3d", heartsCount));
    }
    public void setGameStateText(String text){
        gameStateText.setText(text);

    }
}
