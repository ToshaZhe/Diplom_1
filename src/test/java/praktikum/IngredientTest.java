package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void getPriceGraterZeroPriceReturnGraterZero() {
        Ingredient testIngredientPrice = new Ingredient(IngredientType.SAUCE, "testSauce", 100);
        assertEquals(100, testIngredientPrice.getPrice(), 0.001);
    }

    @Test
    public void getNameNotNullNameReturnName() {
        Ingredient testIngredientName = new Ingredient(IngredientType.FILLING, "testName", 111);
        assertEquals("testName", testIngredientName.getName());
    }

    @Test
    public void getTypeSauceReturnSauce() {
        Ingredient testIngredientSauce = new Ingredient(IngredientType.SAUCE, "sauce", 1);
        assertEquals(IngredientType.SAUCE, testIngredientSauce.getType());
    }

    @Test
    public void getTypeFillingReturnFilling() {
        Ingredient testIngredientFilling = new Ingredient(IngredientType.FILLING, "filling", 11);
        assertEquals(IngredientType.FILLING, testIngredientFilling.getType());
    }
}