package game.fx.catsvsmice.model.objects;

import game.fx.catsvsmice.model.Sprite;

/**
 * The type Point.
 * The class object stores a sprite with the
 * coordinates where the user put the point.
 */
public class Point {
    private final Sprite sprite;

    /**
     * Instantiates a new Point.
     * The constructor creates a sprite with the coordinates where the user clicked.
     *
     * @param posX the pos x
     * @param posY the pos y
     */
    public Point(double posX, double posY) {
        sprite = new Sprite();
        sprite.setPosXY(posX, posY);
    }

    /**
     * Get position X double.
     *
     * @return the double
     */
    public double getX(){
        return sprite.getPosX();
    }

    /**
     * Get position Y double.
     *
     * @return the double
     */
    public double getY(){
        return sprite.getPosY();
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }
}
