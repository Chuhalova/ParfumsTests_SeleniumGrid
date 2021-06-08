package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class BasePage {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='middle_block_wrapper']//a[@class='logo_link']")
    private WebElement homeLink;

    @FindBy(xpath = "//a[@href='/']")
    private WebElement homepageLink;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForPageLoadingComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
    }

    public void waitForVisibilityElement(long timeout, WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void implicitWait(long time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void clickHomeLink() {
        homeLink.click();
    }

    public Boolean isDisplayed(WebElement element) {
        Boolean passFail = false;
        try {
            if (element.isDisplayed())
                passFail = true;
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println("Unable to locate element");
        } catch (Exception e) {
            System.err.println("Unable to check display status of element");
            e.printStackTrace();
        }
        return passFail;
    }
}
