package game.fx.catsvsmice.model;

public class Cat {
    private final Sprite sprite;

    public Cat(double posX, double posY) {
        sprite = new Sprite();
        sprite.setXY(posX, posY);
        String picName = "cat-white.gif";
        switch ((int) (Math.random() * 2)){
            case 0:
                picName = "cat-white.gif";
                break;
            case 1:
                picName = "cat-grey.gif";
                break;
        }
        sprite.setImg(picName,140.0/1920.0, 140.0/1080.0);
    }
    public Sprite getSprite(){
        return sprite;
    }

}
