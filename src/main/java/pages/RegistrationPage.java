package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локаторы
    private By nameInput = By.name("name");
    private By emailInput = By.xpath("//fieldset[contains(@class,'Auth_fieldset__1QzWN')]//label[text()='Email']/following-sibling::input");
    private By passwordInput = By.name("Пароль");
    private By registerButton = By.cssSelector(".button_button__33qZ0");
    private By errorElement = By.cssSelector(".input__error.text_type_main-default"); // Исправлено: Изменён селектор на корректный
    private By loginPageLocator = By.xpath("//h2[text()='Вход']"); // Локатор для проверки страницы логина

    // Методы ввода данных
    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorElement)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageLocator)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}