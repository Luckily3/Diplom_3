package ru.yandex.praktikum.tests;


import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.*;
import ru.yandex.praktikum.client.BrowserClient;
import ru.yandex.praktikum.client.UserClient;
import ru.yandex.praktikum.models.User;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.generators.UserGenerator.validUser;

public class ProfilePageTest {
    private WebDriver driver;
    private final String constructorURL = "https://stellarburgers.nomoreparties.site/";
    private final String profileURL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final String loginURL = "https://stellarburgers.nomoreparties.site/login";
    private User user;
    private UserClient userClient;
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private String accessToken;



    @Before
    public void setUp() {
      // Открытие браузера
      String browser = System.getProperty("browser", "yandex");
      driver = BrowserClient.getWebDriver(browser);
      driver.get(constructorURL);

      constructorPage = new ConstructorPage(driver);
      loginPage = new LoginPage(driver);
      profilePage = new ProfilePage(driver);

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
  @DisplayName("Переход из конструктора в личный кабинет по клику на «Личный кабинет»")
  public void testProfilePageClick() {
    userLogin();

    constructorPage.clickProfileButton();
    profilePage.waitForLoadProfilePage();
    assertEquals(profileURL, driver.getCurrentUrl());
    assertEquals(user.getName(), profilePage.getName());
    assertEquals(user.getEmail(), profilePage.getLogin());
  }

  @Test
  @DisplayName("Переход из личного кабинета по клику на «Конструктор» и на логотип Stellar Burgers")
  public void testConstructorPageClick() {
    userLogin();

    // Проверка перехода по клику на «Конструктор»
    constructorPage.clickProfileButton();
    profilePage.clickConstructorButton();
    assertEquals(constructorURL, driver.getCurrentUrl());

    // Проверка перехода по клику на логотип Stellar Burgers
    constructorPage.clickProfileButton();
    profilePage.clickHeaderLogo();
    assertEquals(constructorURL, driver.getCurrentUrl());
  }

  @Test
  @DisplayName("Проверка выход по кнопке «Выйти» в личном кабинете")
  public void testExitProfile() {
    userLogin();

    constructorPage.clickProfileButton();
    profilePage.waitForLoadProfilePage();
    profilePage.clickExitButton();
    loginPage.waitForLoadLoginPage();
    assertEquals(loginURL, driver.getCurrentUrl());
  }

  @Step("Логин пользователя")
  private void userLogin() {
    constructorPage.waitForLoadConstructorPage();
    constructorPage.clickLoginButton();
    loginPage.waitForLoadLoginPage()
            .inputEmail(user.getEmail())
            .inputPassword(user.getPassword())
            .clickLoginButton();
  }
}

