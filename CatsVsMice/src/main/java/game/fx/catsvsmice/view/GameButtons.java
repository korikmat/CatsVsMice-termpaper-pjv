package game.fx.catsvsmice.view;

import game.fx.catsvsmice.GameData;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class GameButtons {

    private final List<Button> buttons = new ArrayList<>();

    public GameButtons(GameData gameData){
    }
    public void add(Button button){
        buttons.add(button);
    }
    public List<Button> getButtonsAsList(){
        return buttons;
    }

}
