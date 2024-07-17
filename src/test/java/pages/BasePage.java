package pages;

import elements.ButtonElement;
import elements.DescribableElement;
import elements.TextElement;
import elements.TextFieldElement;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс - базовая страница,
 * от которой наследуются
 * остальные.
 */
public class BasePage {
    private final String pageAddress;

    /**
     * Метод-конструктор класса.
     *
     * @param pageAddress URL-адрес страницы
     */
    public BasePage(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    /**
     * Метод открытия страницы по URL-адресу.
     */
    public String openPage() {
        open(pageAddress);
        return pageAddress;
    }

    /**
     * Метод получения элемента по xpath.
     *
     * @param xpath xpath, соответствующий элементу
     * @return DescribableElement
     */
    protected DescribableElement getDescribableElement(String xpath) {
        return new DescribableElement(xpath);
    }

    /**
     * Метод получения элемента кнопки по xpath.
     *
     * @param xpath xpath, соответствующий элементу
     * @return ButtonElement
     */
    protected ButtonElement getButtonElement(String xpath) {
        return new ButtonElement(xpath);
    }

    /**
     * Метод получения элемента текста по xpath.
     *
     * @param xpath xpath, соответствующий элементу
     * @return TextElement
     */
    protected TextElement getTextElement(String xpath) {
        return new TextElement(xpath);
    }

    /**
     * Метод получения элемента текстового поля по xpath.
     *
     * @param xpath xpath, соответствующий элементу
     * @return TextFieldElement
     */
    protected TextFieldElement getTextFieldElement(String xpath) {
        return new TextFieldElement(xpath);
    }
}
