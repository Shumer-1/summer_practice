package elements;

/**
 * Класс, соответствующий элементу с текстом
 */
public class TextElement extends BaseElement {
    /**
     * Конструктор элемента текста, создающий его по xpath-у
     *
     * @param xpath xpath, соответствующий элементу страницы
     */
    public TextElement(String xpath) {
        super(xpath);
    }

    /**
     * Метод, возвращающий видимый текст элемента страницы.
     *
     * @return текст в формате String
     */
    public String text() {
        return element.text();
    }
}
