package ru.yandex.praktikum.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.ConstructorPage;
import ru.yandex.praktikum.LoginPage;
import ru.yandex.praktikum.RecoverPasswordPage;
import ru.yandex.praktikum.RegisterPage;
import ru.yandex.praktikum.client.BrowserClient;
import ru.yandex.praktikum.client.UserClient;
import ru.yandex.praktikum.models.User;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.generators.UserGenerator.validUser;


public class LoginTest {
  private WebDriver driver;
  private final String constructorURL = "https://stellarburgers.nomoreparties.site/";
  private final String registerURL = "https://stellarburgers.nomoreparties.site/register";
  private final String recoverURL = "https://stellarburgers.nomoreparties.site/forgot-password";
  private User user;
  private UserClient userClient;
  private ConstructorPage constructorPage;
  private LoginPage loginPage;
  private RegisterPage registerPage;
  private RecoverPasswordPage recoverPasswordPage;
  private String accessToken;



  @Before
  public void setUp() {
    // Открытие браузера
    String browser = System.getProperty("browser", "chrome");
    driver = BrowserClient.getWebDriver(browser);

    constructorPage = new ConstructorPage(driver);
    loginPage = new LoginPage(driver);
    registerPage = new RegisterPage(driver);
    recoverPasswordPage = new RecoverPasswordPage(driver);

    // Создание пользователя через API
    user = validUser();
    userClient = new UserClient();
    userClient.requestCreateUser(user);
  }

  @After
  public void tearDown() {
    driver.quit();
    userClient.requestDeleteUser(user);
  }

  @Test
  @DisplayName("Вход по кнопке «Войти в аккаунт» на странице конструктора")
  public void testConstructorUserLogin() {
    driver.get(constructorURL);
    constructorPage.clickLoginButton();
    userLogin();

    assertEqualsConstructorPage();
  }

  @Test
  @DisplayName("Вход через кнопку «Личный кабинет» на странице конструктора")
  public void testProfileUserLogin() {
    driver.get(constructorURL);
    constructorPage.clickProfileButton();
    userLogin();

    assertEqualsConstructorPage();
  }

  @Test
  @DisplayName("Вход через кнопку «Войти» в форме регистрации")
  public void testRegisterUserLogin() {
    driver.get(registerURL);
    registerPage.clickLoginLink();
    userLogin();

    assertEqualsConstructorPage();
  }

  @Test
  @DisplayName("Вход через кнопку «Войти» в форме восстановления пароля")
  public void testRecoverUserLogin() {
    driver.get(recoverURL);
    recoverPasswordPage.loginLink();
    userLogin();

    assertEqualsConstructorPage();
  }

  @Step("Проверка соответствия url страницы")
  private void  assertEqualsConstructorPage() {
    constructorPage.waitForLoadConstructorPage();
    assertEquals(constructorURL, driver.getCurrentUrl());
  }

  @Step("Логин пользователя")
  private void userLogin() {
    loginPage.waitForLoadLoginPage()
            .inputEmail(user.getEmail())
            .inputPassword(user.getPassword())
            .clickLoginButton();
  }
}
