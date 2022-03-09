package com.xvision.start;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseView {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public BaseView(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 5);
        PageFactory.initElements(webDriver, this);
    }
}
