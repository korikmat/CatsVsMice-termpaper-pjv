package game.fx.catsvsmice;

import java.util.List;

/**
 * The type Game data.
 * The class contains an array with the coordinates of the cats
 * and an array with the coordinates of the path that the user has set.
 * It also contains the state of the scene,
 * and the width and height of the application window.
 *
 */
public class GameData {
    /**
     * The enum State.
     * Has MENU, LVL_SELECTION, EDITOR, GAME, ABOUTME
     */
    public enum State{
        /**
         * Menu state.
         */
        MENU,
        /**
         * Lvl selection state.
         */
        LVL_SELECTION,
        /**
         * Editor state.
         */
        EDITOR,
        /**
         * Game state.
         */
        GAME,
        /**
         * Aboutme state.
         */
        ABOUTME
    }
    private List<Double> pathCoords;
    private List<Double> catsCoords;
    private double windowWidth;
    private double windowHeight;
    private State state = State.MENU;
    private int stage = 0;

    /**
     * Set path coords as list.
     *
     * @param pathCoords the path coords
     */
    public void setPathCoords(List<Double> pathCoords){
        this.pathCoords = pathCoords;
    }

    /**
     * Get path coords as list.
     *
     * @return the list
     */
    public List<Double> getPathCoords(){
        return pathCoords;
    }

    /**
     * Set cats coords as list.
     *
     * @param catsCoords the cats coords
     */
    public void setCatsCoords(List<Double> catsCoords){
        this.catsCoords = catsCoords;
    }

    /**
     * Get cats coords list as list.
     *
     * @return the list
     */
    public List<Double> getCatsCoords(){
        return catsCoords;
    }

    /**
     * Gets window width.
     *
     * @return the window width
     */
    public double getWindowWidth() {
        return windowWidth;
    }

    /**
     * Gets window height.
     *
     * @return the window height
     */
    public double getWindowHeight() {
        return windowHeight;
    }

    /**
     * Sets window width.
     *
     * @param windowWidth the window width
     */
    public void setWindowWidth(double windowWidth) {
        this.windowWidth = windowWidth;
    }

    /**
     * Sets window height.
     *
     * @param windowHeight the window height
     */
    public void setWindowHeight(double windowHeight) {
        this.windowHeight = windowHeight;
    }

    /**
     * Set stage.
     *
     * @param stage the stage
     */
    public void setStage(int stage){
        this.stage = stage;
    }

    /**
     * Get stage int.
     *
     * @return the int
     */
    public int getStage(){
        return stage;
    }

    /**
     * Set state.
     *
     * @param state the state
     */
    public void setState(State state){
        this.state = state;
    }

    /**
     * Get state.
     *
     * @return the state
     */
    public State getState(){
        return this.state;
    }
}
