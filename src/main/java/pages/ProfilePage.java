package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String profileUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    // Локаторы
    private By AccountButton = By.xpath("//a[contains(@href, '/account')]");

    // Конструктор класса
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAccountButton() {
        waitForVisibility(AccountButton);
        wait.until(ExpectedConditions.elementToBeClickable(AccountButton)).click();
    }

    public boolean isProfilePageDisplayed() {
        // Проверка URL
        return driver.getCurrentUrl().equals(profileUrl);
    }

    // Общий метод ожидания видимости элемента
    private void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}