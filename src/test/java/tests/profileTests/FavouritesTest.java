package tests.profileTests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.cataloguePages.ClothesCataloguePage;
import pages.profilePages.WishlistPage;
import tests.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс, содержащий тесты функций
 * добавления и удаления из избранного.
 */
public class FavouritesTest extends BaseTest {
    /**
     * Метод, тестирующий добавление
     * в избранное.
     */
    @Tag("profile")
    @Test public void addFavouritesTest() {
        try {
            var cataloguePage = new ClothesCataloguePage();
            logger.info("Open clothes catalogue page " + cataloguePage.openPage());

            var productPage = cataloguePage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            productPage.clickFavouritesButton();
            logger.info("Add product to favourites");

            var expectedArticle = productPage.getArticle();
            logger.config("Product article: " + expectedArticle);

            var wishlistPage = new WishlistPage();
            logger.info("Open wishlist page " + wishlistPage.openPage());

            productPage = wishlistPage.getFirstProductPage();
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
     * Метод, тестирующий удаление
     * из избранного.
     */
    @Tag("profile")
    @Test public void removeFavouritesTest() {
        try {
            var cataloguePage = new ClothesCataloguePage();
            logger.info("Open clothes catalogue page " + cataloguePage.openPage());

            var productPage = cataloguePage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            productPage.clickFavouritesButton();
            logger.info("Add product to favourites");

            var wishlistPage = new WishlistPage();
            logger.info("Open wishlist page " + wishlistPage.openPage());

            productPage = wishlistPage.getFirstProductPage();
            logger.info("Open first product page " + productPage.openPage());

            productPage.clickFavouritesButton();
            logger.info("Remove product from favourites");

            logger.info("Open wishlist page " + wishlistPage.openPage());

            assertTrue(wishlistPage.isEmpty());
            logger.config("Page empty: " + wishlistPage.isEmpty());
            logger.info("Page should be empty");

            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }
}
