package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {
    @Test
    public void getNameNotNullNameReturnName() {
        Bun bun = new Bun("testBun", 10);
        assertEquals("testBun", bun.getName());
    }

    @Test
    public void getPriceZeroPriceReturnZero() {
        Bun bun = new Bun("testBun", 0);
        assertEquals(0, bun.getPrice(), 0.001);
    }
}