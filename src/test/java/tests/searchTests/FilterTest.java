package tests.searchTests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.cataloguePages.BackpacksCataloguePage;
import tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Класс, содержащий тесты меню фильтров каталога
 */
public class FilterTest extends BaseTest {
    private final static String COLOR = "Синий";
    private final static String TYPE = "аптечка";
    private final static int PRICE = 5000;

    /**
     * Проверка корректной работы фильтра по цвету
     */
    @Tag("search")
    @Test
    public void colorFilterTest() {
        try {
            var cataloguePage = new BackpacksCataloguePage();
            logger.info("Open backpacks catalogue page " + cataloguePage.openPage());

            cataloguePage.chooseFilter(COLOR);
            logger.info("Choose color filter");
            logger.config("Chosen color: " + COLOR);

            var productPages = cataloguePage.getProductPages();
            logger.info("Get all products from page");

            for (var product : productPages) {
                logger.info("Open product page " + product.openPage());

                var productColor = product.getColor();

                assertEquals(productColor, COLOR);
                logger.config("Product's color: " + productColor);
                logger.info("Product should be " + COLOR);
            }
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Проверка корректной работы фильтра по типу
     */
    @Tag("search")
    @Test
    public void typeFilterTest() {
        try {
            var cataloguePage = new BackpacksCataloguePage();
            logger.info("Open backpacks catalogue page " + cataloguePage.openPage());

            cataloguePage.chooseFilter(TYPE);
            logger.info("Choose type filter");
            logger.config("Chosen type: " + TYPE);

            var productPages = cataloguePage.getProductPages();
            logger.info("Check all products from page");

            for (var product : productPages) {
                logger.info("Open product page " + product.openPage());

                var isSpecificType = product.isSpecificType(TYPE);

                assertTrue(isSpecificType);
                logger.config("Product is " + TYPE + ": " + isSpecificType);
                logger.info("Product should be " + TYPE);
            }
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Проверка корректной работы фильтра по цене
     */
    @Tag("search")
    @Test
    public void priceRangeTest() {
        try {
            var cataloguePage = new BackpacksCataloguePage();
            logger.info("Open backpacks catalogue page " + cataloguePage.openPage());

            cataloguePage.setPriceLimit(PRICE);
            logger.info("Set prices limit");

            var result = cataloguePage.checkLimitedPrices(PRICE);
            logger.config("Prices: " + result.second());

            assertTrue(result.first());
            logger.config("Prices are limited by " + PRICE + ": " + result.first());
            logger.info("Prices should be limited by " + PRICE);

            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }
}
