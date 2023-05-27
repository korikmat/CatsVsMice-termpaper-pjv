import game.fx.catsvsmice.model.objects.Cat;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The type Cats test.
 */
public class CatsTest {
    /**
     * Test is mouse inside.
     */
// NEED TO UNCOMMENT sprite.setImg in Cat constructor!
    @Test
    public void testIsMouseInside(){
        Cat cat = new Cat(500, 500);
        boolean result = cat.mouseInRange(500+ cat.getAttackRadius(), 500);
        assertTrue("mouse should be inside, but was not", result);

        result = cat.mouseInRange(500, 500+ cat.getAttackRadius());
        assertTrue("mouse should be inside, but was not", result);

        result = cat.mouseInRange(500, 501+ cat.getAttackRadius());
        assertFalse("mouse should be inside, but was not", result);

        result = cat.mouseInRange(501+ cat.getAttackRadius(), 501);
        assertFalse("mouse should be inside, but was not", result);

        result = cat.mouseInRange(500+ cat.getAttackRadius(), 500+ cat.getAttackRadius());
        assertFalse("mouse should be inside, but was not", result);

        result = cat.mouseInRange(500, 500);
        assertTrue("mouse should be inside, but was not", result);

        result = cat.mouseInRange(500-cat.getAttackRadius(), 500);
        assertTrue("mouse should be inside, but was not", result);

        result = cat.mouseInRange(500, 500-cat.getAttackRadius());
        assertTrue("mouse should be inside, but was not", result);
    }
}
