package game.fx.catsvsmice.model;

public class Cat {
    private final double attackRadius = 75;
    private long lastTime = 0;
    private long currTime;
    private final Sprite sprite;
    private State state;

    public enum State {
        WAITING, ATTACKING, READY
    }
    public Cat(){
        sprite = new Sprite();
        sprite.setVisibility(false);
        String picName = "cat-white.gif";
        switch ((int) (Math.random() * 2)){
            case 0:
                picName = "cat-white.gif";
                break;
            case 1:
                picName = "cat-grey.gif";
                break;
        }
        sprite.setImg(picName,100.0/1920.0, 100.0/1080.0);
    }

    public Cat(double posX, double posY) {
        sprite = new Sprite();
        sprite.setPosXY(posX, posY);
        String picName = "cat-white.gif";
        switch ((int) (Math.random() * 2)){
            case 0:
                picName = "cat-white.gif";
                break;
            case 1:
                picName = "cat-grey.gif";
                break;
        }
//        picName = "punch.gif";
//        sprite.setImg(picName,250.0/1920.0, 250.0/1080.0);
        sprite.setImg(picName,100.0/1920.0, 100.0/1080.0);
    }
    public void setPosXY(double posX, double posY){
        sprite.setPosXY(posX, posY);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public boolean isReady(){
        currTime = System.nanoTime();

        if(currTime - lastTime >= 2_000_000_000){
//            System.out.println("ready");

            return true;
        }
        return false;
    }
    public void updateTime(){
        lastTime = currTime;
    }
    public boolean mouseInRange(double MousePosX, double MousePosY){
                // (x - a)^2 + (y - b)^2 <= R^2
        return (MousePosX-getCatPosX())*(MousePosX-getCatPosX()) + (MousePosY-getCatPosY())*(MousePosY-getCatPosY()) <= attackRadius*attackRadius;
    }


    private double getCatPosX(){
        return sprite.getPosX();
    }
    private double getCatPosY(){
        return sprite.getPosY();
    }

    public double getAttackRadius(){
        return attackRadius;
    }
    public void increaseCircleRadius(double radius){
        sprite.setCircleRadius(sprite.getCircleRadius() + radius);
    }
    public double getCircleRadius(){
        return sprite.getCircleRadius();
    }
    public void resetCircleRadius(double radius){
        sprite.setCircleRadius(radius);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
    public void setVisible(boolean visible){
        sprite.setVisibility(visible);
    }
}
