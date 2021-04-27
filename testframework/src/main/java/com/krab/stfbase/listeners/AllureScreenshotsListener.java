package com.krab.stfbase.listeners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureScreenshotsListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false).includeSelenideSteps(false));
        Configuration.driverManagerEnabled=true;
        ScreenShooter.captureSuccessfulTests = true;
    }
}
