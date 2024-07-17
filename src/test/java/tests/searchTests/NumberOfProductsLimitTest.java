package tests.searchTests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.cataloguePages.BackpacksCataloguePage;
import tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Класс, содержащий тесты меню выбора
 * количества отображаемых на странице товаров
 */
public class NumberOfProductsLimitTest extends BaseTest {
    /**
     * Проверка корректности количества
     * при всех возможных значениях меню выбора
     * количества отображаемых на странице товаров
     */
    @Tag("search")
    @Test public void limitNumberOfProductsTest() {
        try {
            var cataloguePage = new BackpacksCataloguePage();
            logger.info("Open backpacks catalogue page " + cataloguePage.openPage());

            var pages = cataloguePage.getPagesWithNumberOfProductsLimits();
            logger.info("Get different settings for number of products");

            for (var page : pages) {
                logger.info("Set limit " + page.openPage());

                var limit = cataloguePage.getNumberOfProductsLimit();
                logger.config("Limit: " + limit);

                var numberOfProducts = cataloguePage.getNumberOfProducts();
                logger.config("Number of products: " + numberOfProducts);

                assertTrue(numberOfProducts <= limit);
                logger.info("Number of product should not exceed the limit");
            }
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }
}
