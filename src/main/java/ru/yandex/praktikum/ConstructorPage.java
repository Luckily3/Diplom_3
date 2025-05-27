package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
  private WebDriver driver;
  private final By constructorPageHeader =  By.className("AppHeader_header__logo__2D0X2");
  private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
  private final By profileButton = By.xpath(".//p[text() = 'Личный Кабинет']");
  public final By bunSection = By.xpath(".//*[@class='text text_type_main-default' and text()='Булки']");
  public final By sauceSection = By.xpath(".//*[@class='text text_type_main-default' and text()='Соусы']");
  public final By fillingSection = By.xpath(".//*[@class='text text_type_main-default' and text()='Начинки']");

  public ConstructorPage(WebDriver driver){
    this.driver = driver;
  }

  // метод ожидания прогрузки страницы конструктора
  public void waitForLoadConstructorPage() {
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(bunSection).isEnabled()
    ));
  }

  // метод нажатия на кнопку "Войти"
  public void  clickLoginButton() {
    driver.findElement(loginButton).click();
  }

  // метод нажатия на кнопку "Личный кабинет"
  public void clickProfileButton() {
    driver.findElement(profileButton).click();
  }

  // метод нажатия на секцию "Булки"
  public void clickBunSection() {
    driver.findElement(bunSection).click();
  }

  // метод нажатия на секцию "Соусы"
  public void clickSauceSection() {
    driver.findElement(sauceSection).click();
  }

  // метод нажатия на секцию "Начинки"
  public void clickFillingSection() {
    driver.findElement(fillingSection).click();
  }

  public boolean isSectionActive(By section) {
    return driver.findElement(section).getAttribute("class").contains("tab_tab_type_current__2BEPc");
  }
}




