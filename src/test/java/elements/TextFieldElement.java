package elements;

/**
 * Класс, соответствующий текстовому полю на странице
 */
public class TextFieldElement extends BaseElement {
    /**
     * Конструктор элемента текстового поля, создающий его по xpath-у
     *
     * @param xpath xpath, соответствующий элементу страницы
     */
    public TextFieldElement(String xpath) {
        super(xpath);
    }

    /**
     * Метод, вводящий значение value в текстовое поле
     *
     * @param value Текст, вставляемый в поле
     */
    public void setValue(String value) {
        element.setValue(value);
    }

    /**
     * Метод, выполняющий нажатие клавиши Enter
     */
    public void pressEnter() {
        element.pressEnter();
    }
}
