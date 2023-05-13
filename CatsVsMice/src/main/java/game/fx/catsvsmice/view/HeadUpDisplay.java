package game.fx.catsvsmice.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HeadUpDisplay {
    private GameStageButton gameButton;
    private GameStageInfoMenu infoMenu;
    public void initHUD(){
        initGameStageButton();
        initGameStageInfoMenu();
    }
    private void initGameStageButton(){
        gameButton = new GameStageButton();
    }

    private void initGameStageInfoMenu(){
        infoMenu = new GameStageInfoMenu();
    }

    public Button getGameButton() {
        return gameButton.getButton();
    }
    public void setGameButtonStyle(int style){
        gameButton.setStyle(style);
    }
    public void slideUpInfoMenu(){
        infoMenu.slideUp();
    }
    public void slideDownInfoMenu(){
        infoMenu.slideDown();
    }
    public Pane getGameStageInfoMenuPane(){
        return infoMenu.getPane();
    }

    public void updateCoinsCountText(int coinsCount){
        infoMenu.updateCoinsCountText(coinsCount);
    }
}
