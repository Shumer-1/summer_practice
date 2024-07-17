package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Базовый класс элемента для работы со страницами, от которого наследуются все остальные
 */
public class BaseElement {
    /**
     * Поле для хранения элемента страницы типа SelenideElement
     */
    protected final SelenideElement element;

    /**
     * Конструктор базового элемента, создающий его по xpath-у
     *
     * @param xpath xpath, соответствующий элементу страницы
     */
    public BaseElement(String xpath) {
        element = $x(xpath);
    }

    /**
     * Конструктор базового элемента, создающий его по объекту SelenideElement
     *
     * @param element - сырой объект страницы типа SelenideElement
     */
    public BaseElement(SelenideElement element) {
        this.element = element;
    }

    /**
     * Метод возвращает описание элемента, включающее в себя блок, теги и содержимое элемента
     *
     * @return описание элемента типа String
     */
    public String describe() {
        return element.describe();
    }

    /**
     * Метод, проверяющий существование элемента на странице
     *
     * @return true если элемент существует, иначе - false
     */
    public boolean exists() {
        return element.exists();
    }

    /**
     * Метод, проверяющий доступен ли элемент для просмотра на странице
     *
     * @return true если элемент виден на странице, иначе - false
     */
    public boolean isDisplayed() {
        return element.isDisplayed();
    }
}
