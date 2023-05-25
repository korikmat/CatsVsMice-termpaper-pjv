package game.fx.catsvsmice;


import java.util.List;

public class GameData {

    List<Double> pathCoords;
    List<Double> catsCoords;

    int coinsCount;
    public void setPathCoords(List<Double> pathCoords){
        this.pathCoords = pathCoords;
    }
    public List<Double> getPathCoords(){
        return pathCoords;
    }
    public void setCatsCoords(List<Double> catsCoords){
        this.catsCoords = catsCoords;
    }
    public List<Double> getCatsCoords(){
        return catsCoords;
    }
    public enum State{
        MENU, LVL_SELECTION, EDITOR, GAME, ABOUTME
    }
    private State state = State.MENU;
    private int stage = 0;
    public void setStage(int stage){
        this.stage = stage;
    }
    public int getStage(){
        return stage;
    }

    public void setState(State state){
        this.state = state;
    }
    public State getState(){
        return this.state;
    }
}
