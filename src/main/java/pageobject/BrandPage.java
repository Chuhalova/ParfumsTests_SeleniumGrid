package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrandPage extends BasePage {
    public BrandPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='products_wrapper clearfix']//a[@class='product_name']")
    private List<WebElement> productsDescs;


    public List<WebElement> getProductsDescriptions() {
        return productsDescs;
    }
}
