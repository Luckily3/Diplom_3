package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {

  private WebDriver driver;

  private final By emailField = By.name("name");
  private final By recoverButton = By.xpath(".//button[@text()='Восстановить']");
  private final By loginLink = By.linkText("Войти");

  public RecoverPasswordPage(WebDriver driver) {
    this.driver = driver;
  }

  // метод ожидания прогрузки страницы восстановления пароля
  public RecoverPasswordPage waitRecoverPasswordPage() {
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(loginLink).getText() != null
            && !driver.findElement(loginLink).getText().isEmpty()
    ));
    return this;
  }

  // метод нажатия на кнопку "Войти"
  public void loginLink() {
    driver.findElement(loginLink).click();
  }
}
