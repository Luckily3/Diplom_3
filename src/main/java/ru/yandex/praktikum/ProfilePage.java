package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
  private WebDriver driver;

  private final By profileHeaderLogo = By.className("AppHeader_header__logo__2D0X2");
  private final By profileButton = By.xpath(".//*[text()='Профиль']");
  private final By exitButton = By.xpath(".//button[text()='Выход']");
  private final By constructorHeader = By.className("AppHeader_header__link__3D_hX");
  private final By profileFields = By.xpath(".//*[@class='text input__textfield text_type_main-default input__textfield-disabled']");

  public ProfilePage(WebDriver driver){
    this.driver = driver;
  }


  // метод ожидания прогрузки страницы профиля
  public ProfilePage waitForLoadProfilePage() {
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(profileButton).isEnabled()
    ));
    return this;
  }

  // метод нажатия на кнопку "Войти"
  public void clickHeaderLogo() {
    driver.findElement(profileHeaderLogo).click();
  }

  // метод нажатия на кнопку "Выход"
  public void clickExitButton() {
    driver.findElement(exitButton).click();
  }

  // метод нажатия на кнопку "Конструктор"
  public void clickConstructorButton() {
    driver.findElement(constructorHeader).click();
  }

  public String getName() {
    return driver.findElements(profileFields).get(0).getAttribute("value");
  }

  public String getLogin() {
    return driver.findElements(profileFields).get(1).getAttribute("value");
  }
}


