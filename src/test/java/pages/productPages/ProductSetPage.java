package pages.productPages;

import com.codeborne.selenide.Selenide;
import elements.DescribableElement;
import pages.BasePage;
import utils.DescriptionParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, соответствующий странице,
 * содержащей набор товаров.
 */
public class ProductSetPage extends BasePage {
    private static final String EMPTINESS_MESSAGE_XPATH = "//*[contains(text(), '%s')]";

    /**
     * Метод-конструктор класса.
     *
     * @param pageAddress URL-адрес страницы
     */
    public ProductSetPage(String pageAddress) {
        super(pageAddress);
    }

    /**
     * Метод, проверяющий пуста
     * ли страница.
     *
     * @param message Сообщение о пустой странице
     * @return true, если страница пустая, иначе - false
     */
    protected boolean isEmpty(String message) {
        String xpath = String.format(EMPTINESS_MESSAGE_XPATH, message);

        do {
            Selenide.refresh();
        } while (!this.getDescribableElement(xpath).isDisplayed());

        return this.getDescribableElement(xpath).exists();
    }

    /**
     * Метод, возвращающий страницу первого
     * товара.
     *
     * @param productXpath xpath элемента товара
     * @return Массив страниц товаров каталога
     * @throws Exception У элемента товара нет href ссылки на страницу товара
     */
    protected ProductPage getFirstProductPage(String productXpath) throws Exception {
        var productElement = this.getDescribableElement(productXpath);

        var description = DescriptionParser.parse(productElement.describe());

        if (description.containsKey("href")) {
            return new ProductPage(description.get("href"));
        } else {
            throw new Exception("Product's element does not have href");
        }
    }

    /**
     * Метод, формирующий массив страниц товаров каталога.
     *
     * @param ProductXpath xpath элемента товара
     * @return Массив страниц товаров каталога
     * @throws Exception У элемента товара нет href ссылки на страницу товара
     */
    protected ArrayList<ProductPage> getProductPages(String ProductXpath) throws Exception {
        var productPages = new ArrayList<ProductPage>();

        var productElements = DescribableElement.getAllElements(ProductXpath);

        var description = new HashMap<String, String>();

        for (var elem : productElements) {

            description = DescriptionParser.parse(elem.describe());

            if (description.containsKey("href")) {

                productPages.add(new ProductPage(description.get("href")));
            } else {
                throw new Exception("Product's element does not have href");
            }
        }

        return productPages;
    }
}