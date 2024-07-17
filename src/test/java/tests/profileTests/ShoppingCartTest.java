package tests.profileTests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.cataloguePages.ClothesCataloguePage;
import pages.profilePages.CartPage;
import tests.BaseTest;
import utils.RandomGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Класс, содержащий тесты функций
 * добавления в корзину, удаления из нее
 * и увеличения количества экземпляров товара.
 */
public class ShoppingCartTest extends BaseTest {
    /**
     * Метод, тестирующий добавление
     * в корзину.
     */
    @Test
    @Tag("profile")
    public void addToShoppingCartTest() {
        try {
            var cataloguePage = new ClothesCataloguePage();
            logger.info("Open clothes catalogue page " + cataloguePage.openPage());

            var productPage = cataloguePage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            productPage.addToShoppingCart();
            logger.info("Add product to cart");

            var expectedArticle = productPage.getArticle();
            logger.config("Product article: " + expectedArticle);

            var cartPage = new CartPage();
            logger.info("Open cart page " + cartPage.openPage());

            productPage = cartPage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            var actualArticle = productPage.getArticle();
            logger.config("Product article: " + expectedArticle);

            assertEquals(expectedArticle, actualArticle);
            logger.info("Articles should be the same");

            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Метод, тестирующий увеличение
     * количества экземпляров товара
     * в корзине.
     */
    @Tag("profile")
    @Test public void increaseAmountOfProduct() {
        try {
            var cataloguePage = new ClothesCataloguePage();
            logger.info("Open clothes catalogue page " + cataloguePage.openPage());

            var productPage = cataloguePage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            logger.info("Add product to cart");
            productPage.addToShoppingCart();

            var cartPage = new CartPage();
            logger.info("Open cart page " + cartPage.openPage());

            var amount = cartPage.getAmountOfFirstProduct();
            logger.config("Product amount: " + amount);

            var increaseValue = RandomGenerator.randInt(1, 4);
            cartPage.increaseAmountOfFirstProduct(increaseValue);
            logger.config("Increase amount by: " + increaseValue);

            int newAmount = cartPage.getAmountOfFirstProduct();
            logger.config("Product amount: " + newAmount);

            assertEquals(amount + increaseValue, newAmount);
            logger.info("Product amount should be increased by proper value");

            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Метод, тестирующий удаление
     * из корзины.
     */
    @Tag("profile")
    @Test public void removeFromShoppingCart() {
        try {
            var cataloguePage = new ClothesCataloguePage();
            logger.info("Open clothes catalogue page " + cataloguePage.openPage());

            var productPage = cataloguePage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            productPage.addToShoppingCart();
            logger.info("Add product to cart");

            var cartPage = new CartPage();
            logger.info("Open cart page " + cartPage.openPage());

            cartPage.removeFirstProduct();
            logger.info("Remove product from cart");

            assertTrue(cartPage.isEmpty());
            logger.config("Page empty: " + cartPage.isEmpty());
            logger.info("Page should be empty");

            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }
}
