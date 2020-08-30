package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement sumBalance = $("[data-test-id='amount'] .input__control");
    private SelenideElement whenceTransfer = $("[data-test-id='from'] .input__control");
    private SelenideElement replenishButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");
    private SelenideElement errorTransfer = $("[data-test-id='error-notification']");


    public void setSumBalance(String amount) {
        sumBalance.setValue(amount);
    }

    public void setWhenceTransfer(String number) {
        whenceTransfer.setValue(number);
    }

    public void getReplenishButton() {
        replenishButton.click();
    }

    public void getError() {
        replenishButton.click();
        errorTransfer.shouldBe(visible);
    }

    public DashboardPage getCancelButton() {
        cancelButton.click();
        return new DashboardPage();
    }
}
