package pages.productPages;

import pages.BasePage;
import utils.DescriptionParser;

/**
 * Класс, соответствующий странице товара на сайте
 */
public class ProductPage extends BasePage {
    private final static String PRODUCT_COLOR_XPATH = "//*[@class='active']//img";
    private final static String PRODUCT_ARTICLE_XPATH = "//div[@class='sku']//span[@itemprop='sku']";
    private final static String ADD_TO_WISHLIST_BUTTON_XPATH = "//*[@class='js-wishlist']";
    private final static String ADD_TO_CART_BUTTON_XPATH = "//*[@id='button-cart']";
    private final static String PRODUCT_SIZE_BUTTON_XPATH = "//label[@for='opt-1732']//span[@class='radio__label']";
    private final static String CONFIRM_BUTTON_XPATH = "//div[@class='col-lg-6 col-sm-6 col-12']//button[@class='bttn bttn--action']";
    private final static String PRODUCT_TYPE_XPATH = "//*[@class='inline-dotted__value' and contains(text(), %s)]";

    /**
     * Конструктор класса страницы товара
     *
     * @param pageAddress адрес страницы товара
     */
    public ProductPage(String pageAddress) {
        super(pageAddress);
    }

    /**
     * Метод, возвращающий цвет товара
     *
     * @return цвет товара типа String
     * @throws Exception У элемента товара нет поля title, отвечающего за цвет товара
     */
    public String getColor() throws Exception {
        var colorElement = this.getDescribableElement(PRODUCT_COLOR_XPATH);
        var description = DescriptionParser.parse(colorElement.describe());
        if (description.containsKey("title")) {
            return description.get("title");
        } else {
            throw new Exception("Product does not have color");
        }
    }

    /**
     * Метод класса, проверяющий тип товара
     *
     * @param type тип, с которым нужно сравнить тип текущего товара
     * @return true если товар соответствующего типа, иначе вернет false.
     */
    public boolean isSpecificType(String type) {
        var typeElement = this.getDescribableElement(String.format(PRODUCT_TYPE_XPATH, type));
        return typeElement.exists();
    }

    /**
     * Метод класса для получения артикула товара
     *
     * @return артикул товара типа String
     */
    public String getArticle() {
        return this.getTextElement(PRODUCT_ARTICLE_XPATH).text();
    }

    /**
     * Метод, нажимающий на кнопку "Добавить в избранное" на странице товара
     */
    public void clickFavouritesButton() {
        this.getButtonElement(ADD_TO_WISHLIST_BUTTON_XPATH).click();
    }

    /**
     * Метод, нажимающий на кнопку "Добавить в корзину" на странице товара
     */
    public void addToShoppingCart() {
        this.getButtonElement(ADD_TO_CART_BUTTON_XPATH).click();
        this.getButtonElement(PRODUCT_SIZE_BUTTON_XPATH).click();
        this.getButtonElement(CONFIRM_BUTTON_XPATH).click();
    }
}
