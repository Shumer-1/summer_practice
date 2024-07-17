package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс считывателя необходимых данных из файла конфигурации
 */
public class PropertiesReader {
    public final static String PROP_PATH = "src/test/resources/config.properties";

    /**
     * Метод считывания данных из файла конфигурации
     *
     * @param key ключ по которому производится считывание
     * @return значение value по ключу key
     * @throws IOException ошибка ввода/вывода
     */
    public static String getPropertyByKey(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream(PROP_PATH);
        prop.load(ip);
        return prop.getProperty(key);
    }
}
