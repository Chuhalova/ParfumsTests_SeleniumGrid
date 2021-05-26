package avic;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.*;

public class HomeAvicTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void checkSuccessMessageWhenSendingLetterToDirector() {
        driver.findElement(xpath("//a[contains(@class, 'js_addMessage_btn')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_addMessage")));
        driver.findElement(xpath("//form[@data-recipient-name='Директору магазина']//input[@name='user_name']")).sendKeys("TestName");
        driver.findElement(xpath("//form[@data-recipient-name='Директору магазина']//input[@name='user_email']")).sendKeys("testEmail@gmail.com");
        driver.findElement(xpath("//form[@data-recipient-name='Директору магазина']//textarea")).sendKeys("Test message.");
        driver.findElement(xpath("//form[@data-recipient-name='Директору магазина']//button")).click();
        WebDriverWait secondWait = new WebDriverWait(driver, 10);
        secondWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_addThanksMessage")));
        assertEquals(driver.findElement(xpath("//div[@id='js_addThanksMessage']//div[contains(@class, 'color-green')]")).getText(), "Сообщение успешно отправлено");
    }

    @Test(priority = 2)
    public void checkFacebookLink() {
        driver.findElement(xpath("//ul[contains(@class, 'header-top')]//a[text()='Контакты']")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        assertTrue(driver.findElements(xpath("//ul[@class='list-soc']//a")).get(0).getAttribute("href").equals("https://www.facebook.com/avicshop/"));
    }

    @Test(priority = 3)
    public void checkProdDescContainsSonyOnPageFilteredBySonyProds() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href,'game-zone')]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//div[@class='brand-box__title']//a[contains(@href, 'igrovie-pristavki')]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//div[contains(@class, 'filter-area')]//a[contains(@href, 'proizvoditel--sony')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));
        for (WebElement webElement : elementList) {
            assertTrue((webElement.getText().toLowerCase().contains("sony")));
        }
    }

    @Test(priority = 4)
    public void checkSuccessMessageWhenSendingRequestForCorpClients() {
        driver.findElement(xpath("//form[contains(@class, 'subscribe-form')]//input[@name='name']")).sendKeys("TestName");
        driver.findElement(xpath("//form[contains(@class, 'subscribe-form')]//input[@name='email']")).sendKeys("testEmail@gmail.com");
        driver.findElement(xpath("//button[contains(text(), 'Отправить заявку')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalAlert")));
        assertFalse(driver.findElement(xpath("//div[contains(@class, 'js_title')]")).getText().isEmpty());
//        assertEquals(driver.findElement(xpath("//div[contains(@class, 'js_title')]")).getText(), "Успешно!");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}

