package ru.netology.web.test;


import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static ru.netology.web.data.DataHelper.*;


class MoneyTransferTest {

    private String validSum = "1";
    private String invalidSum = "0";
    
    DashboardPage login() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromCardSecondInCardFirst() {
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        int balanceSecondCard = dashboardPage.getSecondCardBalance() - Integer.parseInt(validSum);
        int balanceFirstCard = dashboardPage.getFirstCardBalance() + Integer.parseInt(validSum);
        val moneyTransferPage = dashboardPage.replenishCard();
        moneyTransferPage.setSumBalance(validSum);
        moneyTransferPage.setWhenceTransfer(getCard2());
        moneyTransferPage.getReplenishButton();
        Assertions.assertEquals( dashboardPage.getFirstCardBalance(),balanceFirstCard);
        Assertions.assertEquals(balanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFormFirstToFirst() { //должна появиться ошибка, тк нелогично переводить деньги с одного и того же счета на этот же
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        int balanceFirstCard = dashboardPage.getFirstCardBalance() + 0;
        val moneyTransferPage = dashboardPage.replenishCard();
        moneyTransferPage.setSumBalance(validSum);
        moneyTransferPage.setWhenceTransfer(getCard2());
        moneyTransferPage.getReplenishButton();
        dashboardPage.isDashboardPage();
        Assertions.assertEquals(balanceFirstCard, dashboardPage.getFirstCardBalance());
    }

    @Test
    void shouldTransferMoneyFormSecondToSecond() { //должна появиться ошибка, тк нелогично переводить деньги с одного и того же счета на этот же
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        int balanceSecondCard = dashboardPage.getSecondCardBalance() + 0;
        val moneyTransferPage = dashboardPage.replenishCard();
        moneyTransferPage.setSumBalance(validSum);
        moneyTransferPage.setWhenceTransfer(getCard2());
        moneyTransferPage.getReplenishButton();
        dashboardPage.isDashboardPage();
        Assertions.assertEquals(balanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromFirstInSecondCard() {
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        int balanceSecondCard = dashboardPage.getSecondCardBalance() + Integer.parseInt(validSum);
        int balanceFirstCard = dashboardPage.getFirstCardBalance() - Integer.parseInt(validSum);
        val moneyTransferPage = dashboardPage.replenishCard2();
        moneyTransferPage.setSumBalance(validSum);
        moneyTransferPage.setWhenceTransfer(getCard1());
        moneyTransferPage.getReplenishButton();
        dashboardPage.isDashboardPage();
        Assertions.assertEquals( dashboardPage.getFirstCardBalance(),balanceFirstCard);
        Assertions.assertEquals(balanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferZeroMoney() { // тоже ошибка, сумма не должна быть = 0
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        int balanceSecondCard = dashboardPage.getSecondCardBalance() + Integer.parseInt(invalidSum);
        int balanceFirstCard = dashboardPage.getFirstCardBalance() - Integer.parseInt(invalidSum);
        val moneyTransferPage = dashboardPage.replenishCard();
        moneyTransferPage.setSumBalance(invalidSum);
        moneyTransferPage.setWhenceTransfer(getCard1());
        moneyTransferPage.getReplenishButton();
        dashboardPage.isDashboardPage();
        Assertions.assertEquals( dashboardPage.getFirstCardBalance(),balanceFirstCard);
        Assertions.assertEquals(balanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromInvalidCard() { // тоже ошибка, тк не сущ карты
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        val moneyTransferPage = dashboardPage.replenishCard();
        moneyTransferPage.setSumBalance(validSum);
        moneyTransferPage.setWhenceTransfer(getInvalidCard());
        moneyTransferPage.getError();
    }

    @Test
    void shouldTransferMoneyCancel() {
        val dashboardPage = login();
        dashboardPage.isDashboardPage();
        val moneyTransferPage = dashboardPage.replenishCard();
        moneyTransferPage.setSumBalance(validSum);
        moneyTransferPage.setWhenceTransfer(getInvalidCard());
        moneyTransferPage.getCancelButton();
    }


}

