package ru.netology.web.test;


import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.MoneyTransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTransferTest {




    @BeforeEach
    void login() {
      val loginPage = new LoginPage();
      val authInfo = DataHelper.getAuthInfo();
      val verificationPage = loginPage.validLogin(authInfo);
      val verificationCode = DataHelper.getVerificationCode();
      verificationPage.validVerify(verificationCode);
    }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV1() {
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("1");
    moneyTransferPage.setWhence("5559 0000 0000 0002");
    moneyTransferPage.getReplenish();
    dashboardPage.isDashboardPage();
  }

  @Test
  void shouldTransferMoneyFormFirstToFirst() { //должна появиться ошибка, тк нелогично переводить деньги с одного и того же счета на этот же
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("1");
    moneyTransferPage.setWhence("5559 0000 0000 0001");
    moneyTransferPage.getReplenish();
    dashboardPage.isDashboardPage();
  }

  @Test
  void shouldTransferMoneyFormSecondToSecond() { //должна появиться ошибка, тк нелогично переводить деньги с одного и того же счета на этот же
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard2();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("1");
    moneyTransferPage.setWhence("5559 0000 0000 0002");
    moneyTransferPage.getReplenish();
    dashboardPage.isDashboardPage();
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard2();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("1");
    moneyTransferPage.setWhence("5559 0000 0000 0001");
    moneyTransferPage.getReplenish();
    dashboardPage.isDashboardPage();
  }

  @Test
  void shouldTransferZeroMoney() { // тоже ошибка, сумма не должна быть = 0
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard2();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("0");
    moneyTransferPage.setWhence("5559 0000 0000 0001");
    moneyTransferPage.getReplenish();
    dashboardPage.isDashboardPage();
  }

  @Test
  void shouldTransferZeroMoney2() { // тоже ошибка, сумма не должна быть пустой
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard2();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("");
    moneyTransferPage.setWhence("5559 0000 0000 0001");
    moneyTransferPage.getReplenish();
    dashboardPage.isDashboardPage();
  }

  @Test
  void shouldTransferMoneyFromInvalidCard() { // тоже ошибка, тк не сущ карты
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard2();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("1");
    moneyTransferPage.setWhence("0");
    moneyTransferPage.getError();
  }

  @Test
  void shouldTransferMoneyCancel() { // тоже ошибка, тк не сущ карты
    val dashboardPage = new DashboardPage();
    dashboardPage.isDashboardPage();
    dashboardPage.replenishCard2();
    MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
    moneyTransferPage.setSum("1");
    moneyTransferPage.setWhence("0");
    moneyTransferPage.getCancel();
  }



}

