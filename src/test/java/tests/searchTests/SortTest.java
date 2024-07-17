package tests.searchTests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.cataloguePages.BackpacksCataloguePage;
import tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Класс, содержащий тесты
 * меню сортировки каталога
 */
public class SortTest extends BaseTest {
    /**
     * Проверка корректности порядка товаров
     * в случае сортировки по цене
     */
    @Tag("search")
    @Test public void priceSortTest() {
        try {
            var cataloguePage = new BackpacksCataloguePage();
            logger.info("Open backpacks catalogue page " + cataloguePage.openPage());

            cataloguePage.sortProductsByPrice();
            logger.info("Sort products by price");

            var result = cataloguePage.checkSortedPrices();
            logger.config("Prices: " + result.second());

            assertTrue(result.first());
            logger.config("Prices are sorted: " + result.first());
            logger.info("Prices should be sorted");

            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }
}
