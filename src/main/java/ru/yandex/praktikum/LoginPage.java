package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
  private WebDriver driver;

  private final By loginHeader = By.xpath(".//h2[text() = 'Вход']");
  private final By emailField = By.name("name");
  private final By passwordField = By.name("Пароль");
  private final By loginButton = By.xpath(".//button[text()='Войти']");
  private final By registerLink = By.linkText("Зарегистрироваться");
  private final By recoverPasswordLink = By.linkText("Восстановить пароль");

  public LoginPage(WebDriver driver){
    this.driver = driver;
  }

  // метод ожидания прогрузки страницы входа
  public LoginPage waitForLoadLoginPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(loginHeader).isEnabled()
    ));
    return this;
  }

  // метод ввода в поле "Email"
  public LoginPage inputEmail(String newEmail) {
    driver.findElement(emailField).sendKeys(newEmail);
    return this;
  }

  // метод ввода в поле "Пароль"
  public LoginPage inputPassword(String newPassword) {
    driver.findElement(passwordField).sendKeys(newPassword);
    return this;
  }

  // метод нажатия на кнопку "Войти"
  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }

  // метод нажатия на кнопку "Зарегистрироваться"
  public void clickRegisterLink() {
    driver.findElement(registerLink).click();
  }

  // метод нажатия на кнопку "Восстановить пароль"
  public void recoverPasswordLink() {
    driver.findElement(recoverPasswordLink).click();
  }
}
