package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
  private WebDriver driver;
  private final By registerHeader = By.xpath(".//h2[text() = 'Регистрация']");
  private final By registrationFormFields = By.xpath(".//*[@class='text input__textfield text_type_main-default']");
  private final By errorPasswordField = By.xpath(".//*[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
  private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");

  private final By loginLink = By.linkText("Войти");

  public RegisterPage(WebDriver driver) {
    this.driver = driver;
  }

  // метод ожидания прогрузки страницы профиля
  public RegisterPage waitForLoadRegisterPage() {
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(registerHeader).getText() != null
            && !driver.findElement(registerHeader).getText().isEmpty()
    ));
    return this;
  }

  // метод ввода в поле "Имя"
  public RegisterPage setName(String newName) {
    driver.findElements(registrationFormFields).get(0).sendKeys(newName);
    return this;
  }

  // метод ввода в поле "Email"
  public RegisterPage setEmail(String newEmail) {
    driver.findElements(registrationFormFields).get(1).sendKeys(newEmail);
    return this;
  }

  // метод ввода в поле "Пароль"
  public RegisterPage setPassword(String password) {
    driver.findElements(registrationFormFields).get(2).sendKeys(password);
    return this;
  }

  // метод нажатия на кнопку "Зарегистрироваться"
  public void clickRegisterButton() {
    driver.findElement(registerButton).click();
  }

  // метод нажатия на кнопку "Войти"
  public void clickLoginLink() {
    driver.findElement(loginLink).click();
  }

  // метод поиска ошибки "Некорректный пароль"
  public String getPasswordError() {
    return driver.findElement(errorPasswordField).getText();
  }
}
