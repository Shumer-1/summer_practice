package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Класс - базовый тест,
 * от которого наследуются остальные
 */
public class BaseTest {
    protected static Logger logger;

    private static final String BROWSER = "chrome";
    private static final int PAGE_LOAD_TIMEOUT = 360_000;
    private static final int TIMEOUT = 10_000;
    private static final String PROP_PATH = "src/test/resources/logging.properties";

    /**
     * Метод для настройки веб-драйверов
     */
    void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = BROWSER;
        Configuration.pageLoadTimeout = PAGE_LOAD_TIMEOUT;
        Configuration.timeout = TIMEOUT;
    }

    /**
     * Метод для настройки логгера
     *
     * @param loggerName Имя логгера
     * @throws IOException Ошибка ввода/вывода
     */
    void setUpLogger(String loggerName) throws IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream(PROP_PATH));

        logger = Logger.getLogger(loggerName);
        logger.setLevel(Level.ALL);

        var fileHandler = new FileHandler("./logs/" + loggerName.replaceAll("\\(\\)", "") + "_log.txt");
        fileHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(fileHandler);
    }

    /**
     * Инициализация теста
     *
     * @throws IOException Ошибка ввода/вывода
     */
    @BeforeEach
    public void init(TestInfo testInfo) throws IOException {
        setUpWebDriver();
        setUpLogger(testInfo.getDisplayName());
    }

    /**
     * Завершение теста
     */
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
