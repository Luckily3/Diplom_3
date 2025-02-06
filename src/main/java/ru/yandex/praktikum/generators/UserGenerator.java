package ru.yandex.praktikum.generators;

import com.github.javafaker.Faker;
import ru.yandex.praktikum.models.User;


public class UserGenerator {
  public static User validUser() {
    Faker faker = new Faker();

    return User.builder()
            .email(faker.internet().emailAddress())
            .password(faker.internet().password(6,8))
            .name(faker.name().name())
            .build();
  }

  public static User invalidUser() {
    Faker faker = new Faker();

    return User.builder()
            .email(faker.internet().emailAddress())
            .password(faker.internet().password(0,5))
            .name(faker.name().name())
            .build();
  }
}
