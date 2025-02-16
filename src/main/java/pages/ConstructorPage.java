package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ConstructorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private String baseUrl = "https://stellarburgers.nomoreparties.site/";
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
        return driver.getCurrentUrl().equals(baseUrl);
    }

    // Метод для нажатия на кнопку "Булки"
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    // Метод для проверки перехода на раздел "Булки"
    public void isBunsSectionDisplayed() {
        try {
            // Находим элемент заголовка для секции "Булки"
            WebElement bunsHeader = driver.findElement(By.xpath("//h2[text()='Булки']"));
            // Проверка, что элемент заголовка виден
            bunsHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            // Если элемент не найден, значит мы не на секции "Булки"
        }
    }

    // Метод для нажатия на кнопку "Соусы"
    public void clickSaucesButton() {
        waitForVisibility(saucesButton);
        wait.until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    // Метод для проверки перехода на раздел "Соусы"
    public boolean isSaucesSectionDisplayed() {
        try {
            // Находим элемент заголовка для секции "Соусы"
            WebElement saucesHeader = driver.findElement(By.xpath("//h2[text()='Соусы']"));
            // Проверка, что элемент заголовка виден
            return saucesHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Метод для нажатия на кнопку "Начинки"
    public void clickFillingsButton() {
        waitForVisibility(fillingsButton);
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    // Метод для проверки перехода на раздел "Начинки"
    public boolean isFillingsSectionDisplayed() {
        try {
            // Находим элемент заголовка для секции "Начинки"
            WebElement fillingsHeader = driver.findElement(By.xpath("//h2[text()='Начинки']"));
            // Проверка, что элемент заголовка виден
            return fillingsHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Общий метод ожидания видимости элемента
    private void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



}
