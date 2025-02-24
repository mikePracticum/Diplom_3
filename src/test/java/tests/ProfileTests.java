package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import pages.LoginPage;
import pages.ProfilePage;
import java.time.Duration;


public class ProfileTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private String profileUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    private String email = "km@yandex.ru";
    private String password = "123456";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
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

        // Проверяем, что мы на главной странице
        assertEquals("Пользователь должен быть перенаправлен на главную страницу.", driver.getCurrentUrl());

        // Нажимаем на "Личный кабинет"
        profilePage.clickAccountButton();

        // Явное ожидание для проверки, что перенаправление прошло успешно
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(profileUrl));

        // Проверяем, что мы на странице профиля
        assertTrue("Пользователь должен быть на странице профиля.", profilePage.isProfilePageDisplayed());
    }

}