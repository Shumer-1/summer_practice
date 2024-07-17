package pages.cataloguePages;

import utils.PropertiesReader;
import java.io.IOException;

/**
 * Класс, соответствующий странице каталога рюкзаков
 */
public class BackpacksCataloguePage extends CataloguePage {
    /**
     * Конструктор класса, использующий URL-адрес страницы каталога рюкзаков
     *
     * @throws IOException Ошибка ввода/вывода
     */
    public BackpacksCataloguePage() throws IOException {
        super(PropertiesReader.getPropertyByKey("url_backpacks_catalogue"));
    }
}
