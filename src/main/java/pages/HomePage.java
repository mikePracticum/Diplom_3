package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для входа на конструктор
    private By constructorButton = By.xpath("//a[contains(@class, 'AppHeader_header__link_active__1IkJo') and ./p[text()='Конструктор']]");
    private By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");

    // Конструктор класса
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для нажатия на кнопку "Конструктор"
    public void clickConstructorButton() {
        waitForVisibility(constructorButton);
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    // Метод для нажатия на логотип
    public void clickLogo() {
        waitForVisibility(logo);
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    // Метод для проверки, что URL соответствует URL конструктора
    public boolean isConstructorPageDispalyed() {
        return driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/");
    }

    // Общий метод ожидания видимости элемента
    private void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}