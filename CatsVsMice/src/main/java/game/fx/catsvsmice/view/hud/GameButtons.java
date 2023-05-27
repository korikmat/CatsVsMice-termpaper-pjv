 package game.fx.catsvsmice.view.hud;

  import javafx.scene.control.Button;
  import java.util.ArrayList;
  import java.util.List;

  /**
  * The type Game buttons.
   * The class contains an array of all game buttons.
  */
  public class GameButtons {
      private final List<Button> buttons = new ArrayList<>();

      /**
       * Add a new button to array.
       *
       * @param button the button
       */
      public void add(Button button){
          buttons.add(button);
      }

      /**
       * Get buttons as list.
       *
       * @return the list
       */
  public List<Button> getButtonsAsList(){
       return buttons;
       }

  }
