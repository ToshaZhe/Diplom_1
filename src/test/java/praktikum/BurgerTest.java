package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.matchers.JUnitMatchers.hasItems;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    Burger burgerTest = new Burger();
    String bunName = "black&white";
    String fillingName = "NULL";
    float bunPrice = 5;
    float fillingPrice = -10;

    @Test
    public void addIngredient() {
        Assert.assertEquals(0, burgerTest.ingredients.size());
        burgerTest.addIngredient(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        Assert.assertEquals(1, burgerTest.ingredients.size());
    }

    @Test
    public void removeIngredient() {
        burgerTest.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        burgerTest.addIngredient(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        burgerTest.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        burgerTest.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        Assert.assertEquals(4, burgerTest.ingredients.size());
        Ingredient removedIngredient = burgerTest.ingredients.get(2);
        burgerTest.removeIngredient(2);
        Assert.assertEquals(3, burgerTest.ingredients.size());
        Assert.assertThat(burgerTest.ingredients, not(hasItems(removedIngredient)));
    }

    @Test
    public void moveIngredient() {
        burgerTest.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        burgerTest.addIngredient(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        burgerTest.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        burgerTest.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        Ingredient movedIngredient = burgerTest.ingredients.get(3);
        burgerTest.moveIngredient(3, 1);
        Assert.assertEquals(movedIngredient, burgerTest.ingredients.get(1));
    }

    @Test
    public void getPrice() {
        burgerTest.setBuns(bun);
        burgerTest.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(fillingPrice);
        float expectedPrice = bunPrice * 2 + fillingPrice;
        Assert.assertEquals(expectedPrice, burgerTest.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest() {
        burgerTest.setBuns(bun);
        burgerTest.addIngredient(ingredient);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getName()).thenReturn(fillingName);
        Mockito.when(ingredient.getPrice()).thenReturn(fillingPrice);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n%n" +
                        "Price: %f%n",
                bunName, IngredientType.FILLING.toString().toLowerCase(),
                fillingName, bunName, bunPrice * 2 + fillingPrice);

        System.out.println(burgerTest.getReceipt());
        System.out.println("Expected:");
        System.out.println(expectedReceipt);
        Assert.assertEquals(expectedReceipt, burgerTest.getReceipt());
    }
}