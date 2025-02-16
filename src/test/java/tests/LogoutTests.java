package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ProfilePage;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class LogoutTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private LogoutPage logoutPage;
    private String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private String profileUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    private String logoutUrl = "https://stellarburgers.nomoreparties.site/login";
    private String email = "km@yandex.ru";
    private String password = "123456";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        logoutPage = new LogoutPage(driver); // Добавлено
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNavigateToProfileFromMainPage() {
        // Переходим на страницу логина
        driver.get(loginUrl);
        // Входим в аккаунт
        loginPage.login(email, password);

        // Нажимаем на "Личный кабинет"
        profilePage.clickAccountButton();

        // Явное ожидание для проверки, что перенаправление прошло успешно
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(profileUrl));

        // Нажимаем выход
        logoutPage.clickLogoutButton();

        // Явное ожидание для проверки, что перенаправление прошло успешно
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(logoutUrl));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl); // Лог текущего URL для отладки
        assertTrue("Пользователь должен быть перенаправлен на страницу логина/логаута.", currentUrl.equals(logoutUrl));
    }

}