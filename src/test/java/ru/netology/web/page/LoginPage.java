package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import ru.netology.web.data.DataHelper;

import java.lang.annotation.Annotation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

  private String url = "https://127.0.0.1:9999";
  private SelenideElement login = $("[data-test-id=login] input");
  private SelenideElement password = $("[data-test-id=password] input");
  private SelenideElement but = $("[data-test-id=action-login]");
  private SelenideElement error = $("[data-test-id='error-notification']");

  public LoginPage() {
    open(url);
  }

  public VerificationPage validLogin(DataHelper.AuthInfo info) {
    login.setValue(info.getLogin());
    password.setValue(info.getPassword());
    but.click();
    return new VerificationPage();
  }

  public void invalidLogin(DataHelper.AuthInfo info) {
    login.setValue(info.getLogin());
    password.setValue(info.getPassword());
    but.click();
    error.shouldBe(Condition.visible);
  }

}