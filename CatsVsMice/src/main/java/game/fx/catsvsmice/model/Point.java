package game.fx.catsvsmice.model;

public class Point {
    private final Sprite sprite;
    public Point(double posX, double posY) {
        sprite = new Sprite();
        sprite.setXY(posX, posY);
    }
    public Sprite getSprite() {
        return sprite;
    }
}
