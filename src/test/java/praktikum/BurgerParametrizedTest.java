package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {

    private static final String expectedReceipt1 = String.format("(==== white bun ====)%n" +
            "(==== white bun ====)%n%n" +
            "Price: 400,000000%n");
    private static final String expectedReceipt2 = String.format("(==== red bun ====)%n" +
            "= filling dinosaur =%n" +
            "(==== red bun ====)%n%n" +
            "Price: 800,000000%n");
    private static final String expectedReceipt3 = String.format("(==== red bun ====)%n" +
            "= sauce chili sauce =%n" +
            "(==== red bun ====)%n%n" +
            "Price: 900,000000%n");
    private static final String expectedReceipt4 = String.format("(==== black bun ====)%n" +
            "= sauce sour cream =%n" +
            "= filling cutlet =%n" +
            "= sauce sour cream =%n" +
            "(==== black bun ====)%n%n" +
            "Price: 700,000000%n");
    private static final String expectedReceipt5 = String.format("(==== red bun ====)%n" +
            "= filling sausage =%n" +
            "= sauce hot sauce =%n" +
            "= filling sausage =%n" +
            "(==== red bun ====)%n%n" +
            "Price: 1300,000000%n");
    private final int bun;
    private final int[] ingredients;
    private final float expectedPrice;
    private final String expectedReceipt;

    public BurgerParametrizedTest(int bun, int[] ingredients, float expectedPrice, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[] createNewBurger() {
        return new Object[][]{
                {1, new int[]{}, 400.0f, expectedReceipt1},
                {2, new int[]{4}, 800.0f, expectedReceipt2},
                {2, new int[]{2}, 900.0f, expectedReceipt3},
                {0, new int[]{1, 3, 1}, 700.0f, expectedReceipt4},
                {2, new int[]{5, 0, 5}, 1300.0f, expectedReceipt5}
        };
    }

    @Test
    public void getPrice() {
        Burger burger = createBurger(bun, ingredients);
        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = createBurger(bun, ingredients);
        Assert.assertEquals(expectedReceipt, burger.getReceipt());
    }

    public Burger createBurger(int bun, int[] ingredients) {
        Database database = new Database();
        Burger burger = new Burger();
        burger.setBuns(database.availableBuns().get(bun));
        for (Integer ingredient : ingredients){
            burger.addIngredient(database.availableIngredients().get(ingredient));
        }
        return burger;
    }
}