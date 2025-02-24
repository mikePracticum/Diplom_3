package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By emailInput = By.xpath("//input[@name='name']");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[contains(text(),'Войти')]");

    // Конструктор класса
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для входа в аккаунт
    public void login(String email, String password) {
        wait.until(driver -> driver.findElement(emailInput));
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}