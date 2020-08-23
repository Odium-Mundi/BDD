package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private SelenideElement replenishBut = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']>[data-test-id='action-deposit']");
  private SelenideElement replenishBut2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']>[data-test-id='action-deposit']");


  public void isDashboardPage() {
    heading.shouldBe(visible);
  }

  public MoneyTransferPage replenishCard() {
    replenishBut.click();
    return new MoneyTransferPage();
  }

  public MoneyTransferPage replenishCard2() {
    replenishBut2.click();
    return new MoneyTransferPage();
  }

}
