package elements;

/**
 * Класс, соответствующий кнопке на странице
 */
public class ButtonElement extends BaseElement {
    /**
     * Конструктор элемента кнопки, создающий его по xpath-у
     *
     * @param xpath xpath, соответствующий элементу страницы
     */
    public ButtonElement(String xpath) {
        super(xpath);
    }

    /**
     * Метод, производящий нажатие по кнопке
     */
    public void click() {
        element.click();
    }
}
