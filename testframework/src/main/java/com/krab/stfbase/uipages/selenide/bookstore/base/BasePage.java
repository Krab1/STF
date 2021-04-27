package com.krab.stfbase.uipages.selenide.bookstore.base;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public abstract class BasePage {
    @Attachment(value = "screenshot", type = "image/png")
    protected byte[] takeScreenShot(){
        return Selenide.screenshot(OutputType.BYTES);
    }
}
