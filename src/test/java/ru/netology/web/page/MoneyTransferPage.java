package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class MoneyTransferPage {
    private SelenideElement sum = $("[data-test-id='amount'] .input__control");
    private SelenideElement whence = $("[data-test-id='from'] .input_control");
    private SelenideElement replenish = $("[data-test-id='action-transfer']");
    private SelenideElement cancel = $("[data-test-id='action-cancel']");
    private SelenideElement error = $("[data-test-id='error-notification']");


    public void setSum(String amount) {
        sum.setValue(amount);
    }

    public void setWhence(String number) {
        whence.setValue(number);
    }

    public DashboardPage getReplenish() {
        replenish.click();
        return new DashboardPage();
    }

    public void getError() {
        replenish.click();
        error.shouldBe(visible);
    }

    public DashboardPage getCancel() {
        cancel.click();
        return new DashboardPage();
    }
}
