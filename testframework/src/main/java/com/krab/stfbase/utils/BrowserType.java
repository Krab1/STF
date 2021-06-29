package com.krab.stfbase.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BrowserType {
    CHROME(new ChromeDriver()),
    EDGE(new EdgeDriver()),
    FIREFOX(new FirefoxDriver());

    private static final BrowserType browserType = valueOf(System.getProperty("browserType"));

    private final WebDriver driver;

    BrowserType(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
