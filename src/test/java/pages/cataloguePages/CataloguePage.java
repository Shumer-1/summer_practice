package pages.cataloguePages;

import com.codeborne.selenide.Selenide;
import elements.DescribableElement;
import pages.productPages.ProductPage;
import pages.productPages.ProductSetPage;
import utils.DescriptionParser;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, соответствующий странице каталога товаров
 */
public class CataloguePage extends ProductSetPage {
    private static final String FILTER_BUTTON_XPATH = "//a[@title='%s']";
    private static final String PRICE_SORT_BUTTON_XPATH = "//*[text()='цене']";
    private static final String PRICE_TEXTFIELD_XPATH = "//*[@name='pa[p][price][max]']";
    private static final String PRODUCTS_LIMIT_XPATH = "//select[@name='limit']//*[@selected='selected']";
    private static final String PRODUCT_XPATH = "//*[@class='p-list__item']/*[@class='p-list__image-wrap']//a";
    private static final String PRODUCTS_LIMIT_PAGES_XPATH = "//select[@name='limit']//option";
    private static final String PRODUCT_PRICE_XPATH = "//*[@class='p-list__item']//*[@class='price']";
    private static final Double DOUBLE_NULL = 0.0;

    /**
     * Конструктор класса, принимающий на вход URL-адрес страницы каталога
     *
     * @param pageAddress URL-адрес
     */
    public CataloguePage(String pageAddress) {
        super(pageAddress);
    }

    /**
     * Метод для установки фильтра в меню фильтров каталога
     *
     * @param filterTitle Название фильтра
     */
    public void chooseFilter(String filterTitle) {
        var filterButton = this.getButtonElement(String.format(FILTER_BUTTON_XPATH, filterTitle));

        filterButton.click();
        Selenide.refresh();
    }

    /**
     * Метод для выбора опции сортировки товаров по цене
     */
    public void sortProductsByPrice() {
        var sortButton = this.getButtonElement(PRICE_SORT_BUTTON_XPATH);

        sortButton.click();
        Selenide.refresh();
    }

    /**
     * Метод для установки ограничения цены товаров в каталоге
     *
     * @param limit Верхняя граница цен товаров
     */
    public void setPriceLimit(int limit) {
        var priceTextField = this.getTextFieldElement(PRICE_TEXTFIELD_XPATH);

        priceTextField.setValue(Integer.toString(limit));
        priceTextField.pressEnter();
        Selenide.refresh();
    }

    /**
     * Метод, определяющий текущее ограничение на количество товаров на странице
     *
     * @return Текущее ограничение на количество товаров на странице
     */
    public int getNumberOfProductsLimit() {
        var limitValue = this.getTextElement(PRODUCTS_LIMIT_XPATH);

        return Integer.parseInt(limitValue.text());
    }

    /**
     * Метод, определяющий количество товаров на странице
     *
     * @return Количество товаров на странице
     */
    public int getNumberOfProducts() {
        return DescribableElement.getAllElements(PRODUCT_XPATH).size();
    }

    /**
     * Метод, формирующий массив страниц товаров каталога
     *
     * @return Массив страниц товаров каталога
     * @throws Exception У элемента товара нет href ссылки на страницу товара
     */
    public ArrayList<ProductPage> getProductPages() throws Exception {
        return super.getProductPages(PRODUCT_XPATH);
    }

    /**
     * Метод, возвращающий страницу первого товара
     *
     * @return Страница первого товара
     * @throws Exception У элемента товара нет href ссылки на страницу товара
     */
    public ProductPage getFirstProductPage() throws Exception {
        return super.getFirstProductPage(PRODUCT_XPATH);
    }

    /**
     * Метод, формирующий массив страниц со всеми возможными ограничениями на количество товаров
     *
     * @return Массив страниц со всеми возможными ограничениями на количество товаров
     * @throws Exception У элемента страницы нет значения value, отвечающего за ограничение
     */
    public ArrayList<CataloguePage> getPagesWithNumberOfProductsLimits() throws Exception {
        var pages = new ArrayList<CataloguePage>();


        var limitElements = DescribableElement.getAllElements(PRODUCTS_LIMIT_PAGES_XPATH);


        var description = new HashMap<String, String>();

        for (var elem : limitElements) {

            description = DescriptionParser.parse(elem.describe());

            if (description.containsKey("value")) {

                pages.add(new CataloguePage(description.get("value")));
            } else {
                throw new Exception("Unable to limit number of products on a page");
            }
        }

        return pages;
    }

    /**
     * Метод, проверяющий упорядоченность цен
     * товаров в порядке неубывания
     *
     * @return true, если цены товаров упорядочены, иначе - false; массив цен
     * @throws Exception У товара нет поля цены content
     */
    public Pair<Boolean, ArrayList<Double>> checkSortedPrices() throws Exception {
        var prevPrice = DOUBLE_NULL;
        var currPrice = DOUBLE_NULL;

        var description = new HashMap<String, String>();
        var prices = new ArrayList<Double>();

        var priceElements = DescribableElement.getAllElements(PRODUCT_PRICE_XPATH);

        for (var elem : priceElements) {
            description = DescriptionParser.parse(elem.describe());

            if (description.containsKey("content")) {
                currPrice = Double.parseDouble(description.get("content"));

                if (currPrice < prevPrice) {
                    return new Pair<>(false, null);
                }

                prices.add(currPrice);
                prevPrice = currPrice;
            } else {
                throw new Exception("Product's price is not stated in catalogue");
            }
        }

        return new Pair<>(true, prices);
    }

    /**
     * Метод, проверяющий ограниченность цен товаров
     *
     * @param limit Верхняя граница цен товаров
     * @return true, если цены товаров ограниченны, иначе - false; массив цен
     * @throws Exception У товара нет поля цены content
     */
    public Pair<Boolean, ArrayList<Double>> checkLimitedPrices(int limit) throws Exception {
        var description = new HashMap<String, String>();
        var prices = new ArrayList<Double>();

        var priceElements = DescribableElement.getAllElements(PRODUCT_PRICE_XPATH);
        for (var elem : priceElements) {
            description = DescriptionParser.parse(elem.describe());
            if (description.containsKey("content")) {
                var price = Double.parseDouble(description.get("content"));

                if (price > limit) {
                    return new Pair<>(false, null);
                }

                prices.add(price);
            } else {
                throw new Exception("Product's price is not stated in catalogue");
            }
        }

        return new Pair<>(true, prices);
    }
}
