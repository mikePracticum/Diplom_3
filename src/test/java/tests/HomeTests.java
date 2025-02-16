package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.HomePage;

import static org.junit.Assert.assertTrue;

public class HomeTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private String email = "km@yandex.ru";
    private String password = "123456";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // Вход в систему перед каждым тестом
        driver.get(loginUrl);
        loginPage.login(email, password);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRedirectToConstructorFromProfile() {
        //driver.get(profileUrl);
        homePage.clickConstructorButton();
        assertTrue(homePage.isConstructorPageDispalyed());
    }

    @Test
    public void testRedirectToConstructorFromLogo() {
        //driver.get(profileUrl);
        homePage.clickLogo();
        assertTrue(homePage.isConstructorPageDispalyed());
    }
}