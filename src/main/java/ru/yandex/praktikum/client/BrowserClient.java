package ru.yandex.praktikum.client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserClient {
  public static WebDriver getWebDriver(String browserName) {
    switch (browserName) {
      case "chrome":
        return new ChromeDriver();
      case "yandex":
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe");
        return new ChromeDriver(options);
      default:
        throw new IllegalArgumentException("Браузер не поддерживается: " + browserName);
    }
  }
}

