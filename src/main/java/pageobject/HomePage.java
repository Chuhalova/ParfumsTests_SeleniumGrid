package pageobject;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class, 'profile')]")
    private WebElement registerLink;

    @FindBy(xpath = "//div[@id='login_modal']")
    private WebElement loginModalPopup;

    @FindBy(xpath = "//button[@class='checkout-button-system-l']")
    private WebElement registerButton;

    @FindBy(xpath = "//button[contains(@class, 'login') and not(contains(@class, 'facebook'))]")
    private WebElement loginBtn;

    @FindBy(xpath = "//form[contains(@class, 'login')]//input[@id='login']")
    private WebElement loginInput;

    @FindBy(xpath = "//form[contains(@class, 'login')]//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'profile')]//span[@class='name']")
    private WebElement nameLoginnedUserSpan;

    @FindBy(xpath = "//a[@data-category='63412']")
    private WebElement linkToParfums;

    @FindBy(xpath = "//div[@class='cart_block']//a")
    private WebElement cartLink;

    @FindBy(xpath = "//input[contains(@class, 'search_input')]")
    private WebElement searchInput;

    public void clickOnRegisterLink() {
        registerLink.click();
    }

    public WebElement getRegistartionPopup() {
        return loginModalPopup;
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickOnLoginButton() {
        loginBtn.click();
    }

    public WebElement getLoginInput() {
        return loginInput;
    }

    public void setLoginInput(String login) {
        loginInput.sendKeys(login);
    }

    public void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public String getNameLoginnedUser() {
        return nameLoginnedUserSpan.getText();
    }

    public void clickLinkToParfums() {
        linkToParfums.click();
    }

    public void clickCartLink() {
        cartLink.click();
    }

    public void setTextToSearchInput(String searchQuery) {
        searchInput.sendKeys(searchQuery, Keys.ENTER);
    }

    public void clickOnSearchInput() {
        searchInput.click();
    }
}
