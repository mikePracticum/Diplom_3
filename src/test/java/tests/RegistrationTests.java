package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegistrationPage;
import io.qameta.allure.Step;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegistrationTests {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    private String name;
    private String email;
    private String password;
    private boolean expectedResult;

    public RegistrationTests(String name, String email, String password, boolean expectedResult) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"test11", "kmn14@ya.ru", "123456", true}, // Валидный кейс
                {"test12", "kmn14@ya.ru", "12345", false}  // Не валидный кейс
        });
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registrationPage = new RegistrationPage(driver);
    }

    @Step("Проверка регистрации с данными: Имя: {0}, Email: {1}, Пароль: {2}")
    @Test
    public void testRegistration() {
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.clickRegister();

        if (expectedResult) {
            // Проверяем, что пользователь перенаправлен на страницу входа
            Assert.assertTrue("Пользователь должен быть перенаправлен на страницу входа.", registrationPage.isLoginPageDisplayed());
            Assert.assertEquals("URL должен быть корректным", "https://stellarburgers.nomoreparties.site/login", registrationPage.getCurrentUrl());
        } else {
            // Проверяем, что отображается ошибка
            Assert.assertTrue("Ошибка регистрации должна отображаться", registrationPage.isErrorDisplayed());

            // Дополнительно проверяем текст ошибки
            String expectedErrorMessage = "Некорректный пароль";
            String actualErrorMessage = driver.findElement(By.cssSelector(".input__error.text_type_main-default")).getText();
            Assert.assertEquals("Текст ошибки должен соответствовать ожидаемому.", expectedErrorMessage, actualErrorMessage);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}