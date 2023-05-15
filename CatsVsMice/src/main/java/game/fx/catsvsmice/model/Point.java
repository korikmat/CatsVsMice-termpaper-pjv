package game.fx.catsvsmice.model;

public class Point {
    private final Sprite sprite;
    public Point(double posX, double posY) {
        sprite = new Sprite();
        sprite.setPosXY(posX, posY);
    }
    public double getX(){
        return sprite.getPosX();
    }
    public double getY(){
        return sprite.getPosY();
    }
    public Sprite getSprite() {
        return sprite;
    }
}
