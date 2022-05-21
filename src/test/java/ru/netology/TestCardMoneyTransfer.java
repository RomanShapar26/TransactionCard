package ru.netology;

import Login.DashboardPage;
import Login.LoginPage;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Login.DashboardPage.pushFirstCardButton;
import static Login.DashboardPage.pushSecondCardButton;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCardMoneyTransfer {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyFromFirstCardToSecondCard() {
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        int amount = 2_508;

        val transactionPage = pushSecondCardButton();
        transactionPage.transferMoney(amount, getFirstCardNumber());
        val firstCardBalanceResult = firstCardBalanceStart - amount;
        val secondCardBalanceResult = secondCardBalanceStart + amount;

        assertEquals(firstCardBalanceResult, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceResult, dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferMoneyFromSecondCardToFirstCard() {
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getSecondCardBalance();
        val secondCardBalanceStart = dashboardPage.getFirstCardBalance();
        int amount = 8_496;

        val transactionPage = pushFirstCardButton();
        transactionPage.transferMoney(amount, getSecondCardNumber());
        val secondCardBalanceResult = secondCardBalanceStart + amount;
        val firstCardBalanceResult = firstCardBalanceStart - amount;

        assertEquals(secondCardBalanceResult, dashboardPage.getSecondCardBalance());
        assertEquals(firstCardBalanceResult, dashboardPage.getFirstCardBalance());
    }


}
