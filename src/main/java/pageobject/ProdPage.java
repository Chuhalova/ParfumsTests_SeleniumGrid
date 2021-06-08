package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProdPage extends BasePage {
    public ProdPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'skus_variable')]//button[contains(text(), 'Купить')]")
    private WebElement buyBtn;

    public void clickBuyBtn() {
        buyBtn.click();
    }
}
