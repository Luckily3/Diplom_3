package ru.yandex.praktikum.tests;

import ru.yandex.praktikum.ConstructorPage;
import ru.yandex.praktikum.LoginPage;
import ru.yandex.praktikum.RegisterPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.client.BrowserClient;
import ru.yandex.praktikum.generators.UserGenerator;

import static org.junit.Assert.assertEquals;


public class RegisterTest {
  private WebDriver driver;
  private final String homeURL = "https://stellarburgers.nomoreparties.site/";
  private final String loginURL = "https://stellarburgers.nomoreparties.site/login";
  private ConstructorPage constructorPage;
  private LoginPage loginPage;
  private RegisterPage registerPage;
  private final UserGenerator userGenerator = new UserGenerator();


  @Before
  public void setUp() {
    String browser = System.getProperty("browser", "chrome");
    driver = BrowserClient.getWebDriver(browser);

    driver.get(homeURL);

    constructorPage = new ConstructorPage(driver);
    loginPage = new LoginPage(driver);
    registerPage = new RegisterPage(driver);
  }

  @After
  public void teardown() {
    driver.quit();
  }

  @Test
  @DisplayName("Успешная регистрация пользователя")
  public void testSuccessUserRegister() {
    openRegisterPage();
    registerPage.setName(userGenerator.validUser().getName())
            .setEmail(userGenerator.validUser().getEmail())
            .setPassword(userGenerator.validUser().getPassword())
            .clickRegisterButton();

    waitLoginPage();
    assertEquals(loginURL, driver.getCurrentUrl());
  }

  @Test
  @DisplayName("Проверка вывода ошибки для некорректного пароля")
  public void testInvalidUserRegister() {
    openRegisterPage();
    registerPage.setName(userGenerator.invalidUser().getName())
            .setEmail(userGenerator.invalidUser().getEmail())
            .setPassword(userGenerator.invalidUser().getPassword())
            .clickRegisterButton();

    assertEquals("Некорректный пароль",  registerPage.getPasswordError());
  }


  @Step("Открыть форму регистрации")
  private void openRegisterPage() {
    constructorPage.clickProfileButton();
    loginPage.clickRegisterLink();
  }

  @Step("Дождаться загрузки формы логина")
  private void waitLoginPage() {
    loginPage.waitForLoadLoginPage();
  }
}
