package ru.netology.testmode.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.DataGenerator;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;

public class AuthTest {
    SelenideElement formCard = $x("//form");

    @BeforeEach
    void setup() {

        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {

        var registeredUser = getRegisteredUser("active");

        formCard.$x(".//span[@data-test-id='login']//input").val(registeredUser.getLogin());
        formCard.$x(".//span[@data-test-id='password']//input").val(registeredUser.getPassword());
        formCard.$x(".//button").click();
        $x(".//h2").should(Condition.text("Личный кабинет"));

        // TODO: добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
        //  данными зарегистрированного активного пользователя, для заполнения полей формы используйте
        //  пользователя registeredUser
    }

     @Test
     @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
    var notRegisteredUser = getUser("active");
         formCard.$x(".//span[@data-test-id='login']//input").val(notRegisteredUser.getLogin());
         formCard.$x(".//span[@data-test-id='password']//input").val(notRegisteredUser.getPassword());
         formCard.$x(".//button").click();
         $x(".//div[@class='notification__title']").should(Condition.text("Ошибка"));
         $x(".//div[@class='notification__content']").should(Condition.text("Неверно указан логин или пароль"));

    // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет
    //  незарегистрированного пользователя, для заполнения полей формы используйте пользователя notRegisteredUser
         }

      @Test
     @DisplayName("Should get error message if login with blocked registered user")
     void shouldGetErrorIfBlockedUser() {
       var blockedUser = getRegisteredUser("blocked");
          formCard.$x(".//span[@data-test-id='login']//input").val(blockedUser.getLogin());
          formCard.$x(".//span[@data-test-id='password']//input").val(blockedUser.getPassword());
          formCard.$x(".//button").click();
          $x(".//div[@class='notification__title']").should(Condition.text("Ошибка"));
          $x(".//div[@class='notification__content']").should(Condition.text("Пользователь заблокирован"));

          // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
    //  заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
    }

     @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
      var registeredUser = getRegisteredUser("active");
     var wrongLogin = getRandomLogin();
         formCard.$x(".//span[@data-test-id='login']//input").val(wrongLogin);
         formCard.$x(".//span[@data-test-id='password']//input").val(registeredUser.getPassword());
         formCard.$x(".//button").click();
         $x(".//div[@class='notification__title']").should(Condition.text("Ошибка"));
         $x(".//div[@class='notification__content']").should(Condition.text("Неверно указан логин или пароль"));

         // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
    //  логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
    //  "Пароль" - пользователя registeredUser
     }

      @Test
     @DisplayName("Should get error message if login with wrong password")
     void shouldGetErrorIfWrongPassword() {
       var registeredUser = getRegisteredUser("active");
      var wrongPassword = getRandomPassword();
          formCard.$x(".//span[@data-test-id='login']//input").val(registeredUser.getLogin());
          formCard.$x(".//span[@data-test-id='password']//input").val(wrongPassword);
          formCard.$x(".//button").click();
          $x(".//div[@class='notification__title']").should(Condition.text("Ошибка"));
          $x(".//div[@class='notification__content']").should(Condition.text("Неверно указан логин или пароль"));

          // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
    //  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
    //  "Пароль" - переменную wrongPassword
 }

}
