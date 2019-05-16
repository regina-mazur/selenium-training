package selenium;

import org.junit.Test;

public class AddProductsToCartTest extends BaseTest {
    private static final Integer PRODUCT_AMOUNT = 3;

    @Test
    public void addProductsToCartTest(){
        app.homePage.open();
        app.productPage.addProductsToCart(PRODUCT_AMOUNT);
        app.cartPage.deleteProductsFromCart(PRODUCT_AMOUNT);
    }
}
