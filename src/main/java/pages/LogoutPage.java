package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String logoutUrl = "https://stellarburgers.nomoreparties.site/login";
    //Локатор
    private By LogoutButton = By.xpath("//button[text()='Выход']");

    // Конструктор класса
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLogoutButton() {
        waitForVisibility(LogoutButton);
        wait.until(ExpectedConditions.elementToBeClickable(LogoutButton)).click();
    }
    // Общий метод ожидания видимости элемента
    private void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}