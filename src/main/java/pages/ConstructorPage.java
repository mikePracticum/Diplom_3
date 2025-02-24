package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ConstructorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    //Локаторы
    private By constructorButton = By.xpath("//a[contains(@class, 'AppHeader_header__link_active__1IkJo') and ./p[text()='Конструктор']]");
    private By bunsButton = By.xpath("//span[text()='Булки']");
    private By saucesButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and not(contains(@class, 'tab_tab_type_current__2BEPc'))][1]//span[text()='Соусы']");
    private By fillingsButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and not(contains(@class, 'tab_tab_type_current__2BEPc'))][2]//span[text()='Начинки']");

    // Конструктор класса
    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для нажатия на кнопку "Конструктор"
    public void clickConstructorButton() {
        waitForVisibility(constructorButton);
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    // Метод для проверки, что URL соответствует URL конструктора
    public boolean isConstructorPageDispalyed() {
        String baseUrl = "https://stellarburgers.nomoreparties.site/";
        return driver.getCurrentUrl().equals(baseUrl);
    }

    // Метод для нажатия на кнопку "Булки"
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    // Метод для нажатия на кнопку "Соусы"
    public void clickSaucesButton() {
        waitForVisibility(saucesButton);
        wait.until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    // Метод для нажатия на кнопку "Начинки"
    public void clickFillingsButton() {
        waitForVisibility(fillingsButton);
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    // Общий метод ожидания видимости элемента
    private void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



}
