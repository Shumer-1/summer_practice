package pages.profilePages;

import com.codeborne.selenide.Selenide;
import pages.productPages.ProductSetPage;
import pages.productPages.ProductPage;
import utils.DescriptionParser;
import utils.PropertiesReader;

import java.io.IOException;

/**
 * Класс, соответствующий странице корзины
 * пользователя.
 */
public class CartPage extends ProductSetPage {
    private static final String EMPTINESS_MESSAGE = "В корзине пусто";
    private static final String AMOUNT_XPATH = "//div[@class='p-cart__quantity']//input[@class='p-cart__quantity-input']";
    private static final String INCREASE_AMOUNT_BUTTON_XPATH = "//div[@class='p-cart__quantity']//a[@class='bttn bttn--action p-cart__quantity-handler i--plus']";
    private static final String DELETE_BUTTON_XPATH = "//div[@class='p-cart__actions']//a[@title='Удалить товар из корзины']";
    private static final String PRODUCT_XPATH = "//*[@class='p-cart__info-title']//a";

    /**
     * Метод-конструктор класса.
     *
     * @throws IOException Ошибка ввода/вывода
     */
    public CartPage() throws IOException {
        super(PropertiesReader.getPropertyByKey("url_cart"));
    }

    /**
     * Метод для получения количества экземпляров
     * первого товара.
     *
     * @return количество экземпляров
     * @throws Exception У элемента товара нет поля value, отвечающего за количество товара
     */
    public int getAmountOfFirstProduct() throws Exception {
        while (!this.getDescribableElement(AMOUNT_XPATH).isDisplayed()) {
            Selenide.refresh();
        }

        var element = this.getDescribableElement(AMOUNT_XPATH);

        var description = DescriptionParser.parse(element.describe());

        if (description.containsKey("value")) {
            return Integer.parseInt(description.get("value"));
        } else {
            throw new Exception("Product does not have amount");
        }
    }

    /**
     * Метод для увеличения количества экземпляров
     * первого товара.
     *
     * @param count параметр увеличения
     */
    public void increaseAmountOfFirstProduct(int count) {
        var increaseButton = this.getButtonElement(INCREASE_AMOUNT_BUTTON_XPATH);

        for (int i = 0; i < count; i++) {
            increaseButton.click();
        }
    }

    /**
     * Метод для удаления первого товара
     * из корзины.
     */
    public void removeFirstProduct() {
        while (!this.getDescribableElement(DELETE_BUTTON_XPATH).isDisplayed()) {
            Selenide.refresh();
        }
        this.getButtonElement(DELETE_BUTTON_XPATH).click();
    }

    /**
     * Метод для проверки отсутствия товаров
     * в корзине.
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
