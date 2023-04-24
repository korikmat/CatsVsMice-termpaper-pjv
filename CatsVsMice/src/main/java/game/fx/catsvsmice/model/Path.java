package game.fx.catsvsmice.model;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Double> points = new ArrayList<>();

    public Path(double mousePosX, double mousePosY) {
        points.add(mousePosX);
        points.add(mousePosY);
    }

    public void setPoint(double posX, double posY){
        points.add(posX);
        points.add(posY);
    }
    public List<Double> getListOfPoints() {
        return points;
    }

}
