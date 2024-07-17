package pages.cataloguePages;

import utils.PropertiesReader;
import java.io.IOException;

/**
 * Класс, соответствующий странице каталога одежды
 */
public class ClothesCataloguePage extends CataloguePage {
    /**
     * Конструктор класса, использующий URL-адрес страницы каталога одежды
     *
     * @throws IOException Ошибка ввода/вывода
     */
    public ClothesCataloguePage() throws IOException {
        super(PropertiesReader.getPropertyByKey("url_clothes_catalogue"));
    }
}
