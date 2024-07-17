package pages.authorizationPages;

import pages.BasePage;
import utils.PropertiesReader;

import java.io.IOException;

/**
 * Класс, соответствующий странице авторизации пользователя
 */
public class LoginPage extends BasePage {
    private final static String EMAIL_TEXTFIELD_XPATH = "//div[@class='f-row']//input[@type='text']";
    private final static String PASSWORD_TEXTFIELD_XPATH = "//div[@class='f-row']//input[@type='password']";
    private final static String SUBMIT_BUTTON_XPATH = "//button[@class='bttn bttn--action']";
    private final static String WARNING_WINDOW_XPATH = "//div[@class='warning']";

    /**
     * Конструктор класса, использующий URL-адрес страницы авторизации
     *
     * @throws IOException Ошибка ввода/вывода
     */
    public LoginPage() throws IOException {
        super(PropertiesReader.getPropertyByKey("url_authorization"));
    }

    /**
     * Метод, который авторизует пользователя по входным данным
     *
     * @param email    почта пользователя
     * @param password пароль пользователя
     */
    public void authorize(String email, String password) {
        var emailTextField = this.getTextFieldElement(EMAIL_TEXTFIELD_XPATH);
        emailTextField.setValue(email);
        var passwordTextField = this.getTextFieldElement(PASSWORD_TEXTFIELD_XPATH);
        passwordTextField.setValue(password);
        var submitButton = this.getButtonElement(SUBMIT_BUTTON_XPATH);
        submitButton.click();
    }

    /**
     * Метод класса, проверяющий существование элемента окна ошибки
     *
     * @return true при наличии окна ошибки на странице, иначе false
     */
    public boolean hasWarningMessage() {
        return this.getDescribableElement(WARNING_WINDOW_XPATH).exists();
    }
}
