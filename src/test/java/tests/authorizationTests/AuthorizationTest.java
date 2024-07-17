package tests.authorizationTests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.authorizationPages.LoginPage;
import tests.BaseTest;
import utils.PropertiesReader;
import utils.RandomGenerator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Класс, содержащий все тесты авторизации пользователя
 */
public class AuthorizationTest extends BaseTest {
    private final static String EXPECTED_URL = "https://tramp-sport.ru/account/";
    private static String USER_EMAIL;
    private static String USER_PASSWORD;

    /**
     * Метод, считывающий данные пользователя из файла config.properties и заносящий их в поля класса
     *
     * @throws IOException Ошибка ввода/вывода
     */
    @BeforeAll
    public static void initializeUser() throws IOException {
        USER_EMAIL = PropertiesReader.getPropertyByKey("email");
        USER_PASSWORD = PropertiesReader.getPropertyByKey("password");
    }


    /**
     * Проверяет корректность авторизации на сайте при вводе правильных данных пользователя
     */
    @Tag("authorization")
    @Test public void correctAuthorizationTest() {
        try {
            var loginPage = new LoginPage();
            logger.info("Open login page " + loginPage.openPage());

            loginPage.authorize(USER_EMAIL, USER_PASSWORD);
            logger.info("Start authorization");
            logger.config("Email: " + USER_EMAIL);
            logger.config("Password: " + USER_PASSWORD);

            var currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            logger.info("Check for results");
            logger.config("Current url: " + currentUrl);

            assertEquals(currentUrl, EXPECTED_URL);
            logger.info("Current url should be " + EXPECTED_URL);
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Проверяет, что авторизация на сайте провалится при вводе неправильной почты
     */
    @Tag("authorization")
    @Test public void incorrectLoginAuthorizationTest() {
        try {
            var loginPage = new LoginPage();
            logger.info("Open login page " + loginPage.openPage());

            String FAKE_EMAIL = RandomGenerator.randomEmail();
            loginPage.authorize(FAKE_EMAIL, USER_PASSWORD);
            logger.info("Start authorization");
            logger.config("Email: " + FAKE_EMAIL);
            logger.config("Password: " + USER_PASSWORD);

            var warningMessageExists = loginPage.hasWarningMessage();
            logger.info("Check for results");
            logger.config("Warning window exists: " + warningMessageExists);

            assertTrue(warningMessageExists);
            logger.info("Warning window should exist");
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Проверяет, что авторизация на сайте провалится при вводе правильной почты и неправильного пароля
     */
    @Tag("authorization")
    @Test public void incorrectPasswordAuthorizationTest() {
        try {
            var loginPage = new LoginPage();
            logger.info("Open login page " + loginPage.openPage());

            String FAKE_PASSWORD = RandomGenerator.randomPassword();
            loginPage.authorize(USER_EMAIL, FAKE_PASSWORD);
            logger.info("Start authorization");
            logger.config("Email: " + USER_EMAIL);
            logger.config("Password: " + FAKE_PASSWORD);

            var warningMessageExists = loginPage.hasWarningMessage();
            logger.info("Check for results");
            logger.config("Warning window exists: " + warningMessageExists);

            assertTrue(warningMessageExists);
            logger.info("Warning window should exist");
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }

    /**
     * Проверяет, что авторизация на сайте не произойдет, если не будет введена почта
     * в соответствующее текстовое поле на странице
     */
    @Tag("authorization")
    @Test public void noLoginAuthorizationTest() {
        try {
            var loginPage = new LoginPage();
            logger.info("Open login page " + loginPage.openPage());

            loginPage.authorize("", USER_PASSWORD);
            logger.info("Start authorization");
            logger.config("Email: empty");
            logger.config("Password: " + USER_PASSWORD);

            var currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            logger.info("Check for results");
            logger.config("Current url: " + currentUrl);

            assertNotEquals(currentUrl, EXPECTED_URL);
            logger.info("Current url should not be " + EXPECTED_URL);
            logger.fine("Test passed");

        } catch (Exception e) {
            logger.severe(e.getMessage());
            fail();
        }
    }
}
