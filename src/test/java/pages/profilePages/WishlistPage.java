package pages.profilePages;

import pages.productPages.ProductSetPage;
import pages.productPages.ProductPage;
import utils.PropertiesReader;

import java.io.IOException;

/**
 * Класс, соответствующий странице
 * избранного.
 */
public class WishlistPage extends ProductSetPage {
    private static final String EMPTINESS_MESSAGE = "Ваши закладки пусты";
    private static final String PRODUCT_XPATH = "//*[@class='p-list__item']/*[@class='p-list__image-wrap']//a";

    /**
     * Метод-конструктор класса.
     *
     * @throws IOException Ошибка ввода/вывода
     */
    public WishlistPage() throws IOException {
        super(PropertiesReader.getPropertyByKey("url_wishlist"));
    }

    /**
     * Метод для проверки отсутствия товаров
     * в избранном.
     *
     * @return true, если товаров нет, иначе - false
     */
    public boolean isEmpty() {
        return super.isEmpty(EMPTINESS_MESSAGE);
    }

    /**
     * Метод, возвращающий страницу первого
     * товара.
     *
     * @return ProductPage
     * @throws Exception У элемента товара нет href ссылки на страницу товара
     */
    public ProductPage getFirstProductPage() throws Exception {
        return super.getFirstProductPage(PRODUCT_XPATH);
    }
}
