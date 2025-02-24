package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTests {
    private WebDriver driver; // Объявляем WebDriver как поле класса
    private LoginPage loginPage;
    private String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private String email = "km@yandex.ru";
    private String password = "123456";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        // Закрываем браузер после каждого теста
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void LoginFromMainPageAccountButton() {
        driver.get(baseUrl);
        // Нажимаем на кнопку "Войти в аккаунт"
        driver.findElement(By.xpath("//button[contains(text(),'Войти в аккаунт')]")).click();
        loginPage.login(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

        // Проверка, что мы перенаправлены на главную страницу
        assertTrue("Пользователь должен быть перенаправлен на главную страницу.", isHomePageDisplayed());
    }

    @Test
    public void LoginFromHeaderAccountButton() {
        driver.get(baseUrl);

        // Нажимаем на кнопку "Личный кабинет"
        driver.findElement(By.cssSelector("a[href='/account']")).click();
        loginPage.login(email, password);

        // Явное ожидание для проверки, что страница загрузилась
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

        // Проверка, что мы перенаправлены на главную страницу
        assertTrue("Пользователь должен быть перенаправлен на главную страницу.", isHomePageDisplayed());
    }

    @Test
    public void LoginFromRegistrationPageButton() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        // Нажимаем на кнопку "Войти"
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        loginPage.login(email, password);

        // Проверка, что мы перенаправлены на главную страницу
        assertTrue("Пользователь должен быть перенаправлен на главную страницу.", isHomePageDisplayed());
    }

    @Test
    public void LoginFromForgotPasswordButton() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        // Нажимаем на "Вспомнили пароль? Войти"
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        loginPage.login(email, password);

        // Проверка, что мы перенаправлены на главную страницу
        assertTrue("Пользователь должен быть перенаправлен на главную страницу.", isHomePageDisplayed());
    }

    private boolean isHomePageDisplayed() {
        // Проверка URL
        return driver.getCurrentUrl().equals(baseUrl);
    }
}