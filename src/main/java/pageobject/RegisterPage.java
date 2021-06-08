package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@id, 'firstname')]")
    private WebElement firstName;

    @FindBy(xpath = "//input[contains(@id, 'lastname')]")
    private WebElement lastName;

    @FindBy(xpath = "//select[contains(@id, 'day')]")
    private WebElement bdDay;

    @FindBy(xpath = "//select[contains(@id, 'month')]")
    private WebElement bdMonth;

    @FindBy(xpath = "//select[contains(@id, 'year')]")
    private WebElement bdYear;

    @FindBy(xpath = "//input[contains(@id, 'phone')]")
    private WebElement phone;

    @FindBy(xpath = "//input[contains(@id, 'email')]")
    private WebElement email;

    @FindBy(xpath = "//button[@id='registration_form_save']")
    private WebElement submitRegistration;

    public void setTextFirstName(String firstname) {
        firstName.sendKeys(firstname);
    }

    public void setTextLastName(String lastname) {
        lastName.sendKeys(lastname);
    }

    public void clickBdDay(Integer bdDaySelectValue) {
        Select bdDaySelect = new Select(bdDay);
        bdDaySelect.getOptions().get(bdDaySelectValue).click();
    }

    public void clickBdMonth(Integer bdMonthSelectValue) {
        Select bdMonthSelect = new Select(bdMonth);
        bdMonthSelect.getOptions().get(bdMonthSelectValue).click();
    }

    public void clickBdYear(Integer bdYearSelectValue) {
        Select bdYearSelect = new Select(bdYear);
        bdYearSelect.getOptions().get(bdYearSelectValue).click();
    }

    public void setTextPhone(String phoneText) {
        phone.sendKeys(phoneText);
    }

    public void setTextEmail(String emailText) {
        email.sendKeys(emailText);
    }

    public void clickRegistrationBtn() {
        submitRegistration.click();
    }
}
