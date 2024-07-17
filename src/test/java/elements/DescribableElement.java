package elements;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Класс, соответствующий объекту на странице, имеющему описание
 *
 */
public class DescribableElement extends BaseElement {
    /**
     * Конструктор элемента, имеющего описание, создающий его по xpath-у
     *
     * @param xpath xpath, соответствующий элементу страницы
     */
    public DescribableElement(String xpath) {
        super(xpath);
    }

    /**
     * Конструктор элемента, имеющего описание, создающий его по объекту SelenideElement
     *
     * @param element сырой объект страницы типа SelenideElement
     */
    public DescribableElement(SelenideElement element) {
        super(element);
    }

    /**
     * Метод, создающий массив элементов по xpath-у, то есть берет все элементы,
     * удовлетворяющие данному xpath-у, после чего каждый из элементов типа
     * SelenideElement делает элементом класса DescribableElement
     *
     * @param xpath xpath, соответствующий элементу страницы
     * @return массив элементов страницы типа ArrayList<DescribableElement>
     */
    public static ArrayList<DescribableElement> getAllElements(String xpath) {
        var elements = new ArrayList<DescribableElement>();
        var rawElements = $$x(xpath);
        for (var elem : rawElements) {
            elements.add(new DescribableElement(elem));
        }
        return elements;
    }
}
