package tests;

import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ConstructorPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConstructorTests {
    private WebDriver driver;
    private ConstructorPage constructorPage;
    private String baseUrl = "https://stellarburgers.nomoreparties.site/";

    @Before
    @Step("Создание окна бразера и вход в приложение")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        constructorPage = new ConstructorPage(driver);
        driver.get(baseUrl);

        /*System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);*/
    }

    @After
    @Step("Выход из браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Переход на Конструктор")
    @Test
    public void redirectToConstructor() {
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.isConstructorPageDispalyed());
    }

    @Step("Переход на секцию Соусов и далее на Булки")
    @Test
    public void redirectToBunsSection() {
        constructorPage.clickSaucesButton();
        constructorPage.clickBunsButton();
        //заведомо испортил ожидание, тест упадет, так как ждем 'Мулки' а должно быть 'Булки'
        assertTrue("Пользователь должен увидеть заголовок 'Булки'.", driver.findElement(By.xpath("//h2[contains(text(), 'Мулки')]")).isDisplayed());
    }

    @Step("Переход на секцию Соусов")
    @Test
    public void redirectToSaucesSection() {
        constructorPage.clickSaucesButton();
        assertTrue("Пользователь должен увидеть заголовок 'Соусы'.", driver.findElement(By.xpath("//h2[contains(text(), 'Соусы')]")).isDisplayed());
    }

    @Step("Переход на секцию Начинок")
    @Test
    public void redirectToFillingsSection() {
        constructorPage.clickFillingsButton();
        assertTrue("Пользователь должен увидеть заголовок 'Начинки'.", driver.findElement(By.xpath("//h2[contains(text(), 'Начинки')]")).isDisplayed());
    }

}
