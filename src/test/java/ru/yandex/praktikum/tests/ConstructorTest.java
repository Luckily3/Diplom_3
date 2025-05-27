package ru.yandex.praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ru.yandex.praktikum.ConstructorPage;
import ru.yandex.praktikum.client.BrowserClient;


public class ConstructorTest {
  private WebDriver driver;
  private final String constructorURL = "https://stellarburgers.nomoreparties.site/";
  private ConstructorPage constructorPage;




  @Before
  public void setUp() {
    // Открытие браузера
    String browser = System.getProperty("browser", "google");
    driver = BrowserClient.getWebDriver(browser);
    driver.get(constructorURL);

    constructorPage = new ConstructorPage(driver);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  @DisplayName("Переходы по разделам: «Булки», «Соусы», «Начинки»")
  public void testSectionSelect() {
    // Открыть секцию «Соусы»
    constructorPage.clickSauceSection();
    constructorPage.isSectionActive(constructorPage.sauceSection);


    // Открыть секцию «Начинки»
    constructorPage.clickFillingSection();
    constructorPage.isSectionActive(constructorPage.fillingSection);

    // Открыть секцию «Соусы»
    constructorPage.clickBunSection();
    constructorPage.isSectionActive(constructorPage.bunSection);
  }
}
