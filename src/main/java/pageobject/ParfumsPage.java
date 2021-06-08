package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ParfumsPage extends BasePage {
    public ParfumsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='product_content']")
    private List<WebElement> parfums;


    public void clickOnFirstParfum() {
        parfums.get(0).click();
    }
}
