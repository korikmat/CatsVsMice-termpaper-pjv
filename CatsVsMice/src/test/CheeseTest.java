import game.fx.catsvsmice.model.Cat;
import game.fx.catsvsmice.model.Cheese;
import org.junit.*;
import static org.junit.Assert.*;

public class CheeseTest {
    // NEED TO UNCOMMENT sprite.setImg in Cheese constructor!
    @Test
    public void testIsMouseInside(){
        double sizeX = 20;
        double sizeY = 20;
        Cheese cheese = new Cheese(sizeX, sizeY);
        double cheesePosX = cheese.getSprite().getPosX();
        double cheesePosY = cheese.getSprite().getPosY();


        boolean result = cheese.isInside(cheesePosX + sizeX, cheesePosY);
        assertTrue("mouse should be inside, but was not", result);

        result = cheese.isInside(cheesePosX, cheesePosY + sizeY);
        assertTrue("mouse should be inside, but was not", result);

        result = cheese.isInside(cheesePosX - sizeX, cheesePosY);
        assertTrue("mouse should be inside, but was not", result);

        result = cheese.isInside(cheesePosX, cheesePosY - sizeY);
        assertTrue("mouse should be inside, but was not", result);

        result = cheese.isInside(cheesePosX + sizeX, cheesePosY + sizeY);
        assertFalse("mouse should be inside, but was not", result);

        result = cheese.isInside(cheesePosX - sizeX, cheesePosY - sizeY);
        assertFalse("mouse should be inside, but was not", result);


    }
}
