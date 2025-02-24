package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegistrationPage;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class RegistrationTests {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    private String name;
    private String email;
    private String password;
    private boolean expectedResult;
    private String authToken;

    public RegistrationTests(String name, String email, String password, boolean expectedResult) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"testUser1", "test-" + UUID.randomUUID().toString() + "@yandex.ru", "123456", true},
                {"testUser2", "test-" + UUID.randomUUID().toString() + "@yandex.ru", "12345", false}
        });
    }

    @Before
    public void setUp() {
        // Генерация уникального адреса электронной почты перед каждым тестом
        email = "test-" + UUID.randomUUID().toString() + "@yandex.ru";

        // Работа с браузером
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
            // Проверяем, что пользователь перенаправлен на страницу входа и получаем токен
            Assert.assertTrue("Пользователь должен быть перенаправлен на страницу входа.", registrationPage.isLoginPageDisplayed());
            Assert.assertEquals("URL должен быть корректным", "https://stellarburgers.nomoreparties.site/login", registrationPage.getCurrentUrl());
            authenticateAndGetToken(); // Получаем токен только при успешной регистрации
        } else {
            // Проверяем, что отображается ошибка
            Assert.assertTrue("Ошибка регистрации должна отображаться", registrationPage.isErrorDisplayed());

            // Дополнительно проверяем текст ошибки
            String expectedErrorMessage = "Некорректный пароль";
            String actualErrorMessage = driver.findElement(By.cssSelector(".input__error.text_type_main-default")).getText();
            Assert.assertEquals("Текст ошибки должен соответствовать ожидаемому.", expectedErrorMessage, actualErrorMessage);
        }
    }

    private void authenticateAndGetToken() {
        String authRequestBody = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        Response authResponse = given()
                .contentType(ContentType.JSON)
                .body(authRequestBody)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();

        authToken = authResponse.jsonPath().getString("accessToken");
    }

    @After
    public void tearDown() {
        String deleteUserMessage = "Токен не доступен, пользователь не может быть удалён.";
        if (authToken != null && !authToken.isEmpty()) {
            Response deleteUserResponse = given()
                    .header("Authorization", authToken)
                    .when()
                    .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                    .then()
                    .log().all()
                    .extract()
                    .response();

            int statusCode = deleteUserResponse.statusCode();
            if (statusCode == 200 || statusCode == 202) {
                deleteUserMessage = "Пользователь успешно удален.";
            } else {
                deleteUserMessage = "Не удалось удалить пользователя (код состояния: " + statusCode + ").";
            }
        }
        // Выводим сообщение об удалении
        System.out.println(deleteUserMessage);
        // Закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }
}