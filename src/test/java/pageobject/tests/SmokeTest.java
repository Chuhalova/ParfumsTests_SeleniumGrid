package pageobject.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SmokeTest extends BaseTests {
    private static final String FIRSTNAME = "ТестовеІмя";
    private static final String LASTNAME = "ТестовеПрізвище";
    private static final String EMAIL = "pamelawardtx40441@gmail.com";
    private static final String EMAIL_FOR_LOGIN = "p.amelawardtx@gmail.com";
    private static final String PHONE = "+38 (099) 944 44 19";
    private static final String PASS = "383809992939";
    private static final Integer BD_DAY_SELECT_VALUE = 1;
    private static final Integer BD_MONTH_SELECT_VALUE = 1;
    private static final Integer BD_YEAR_SELECT_VALUE = 110;
    private static final String SUCCESS_TEXT = "Регистрация прошла успешно!";
    private static final String SEARCH_QUERY = "maybelline";

    private void waitForPopupWithAuthLinks() {
        getHomePage().clickOnRegisterLink();
        getHomePage().waitForVisibilityElement(10, getHomePage().getRegistartionPopup());
    }

    private void loginWithCredentials() {
        waitForPopupWithAuthLinks();
        getHomePage().clickOnLoginButton();
        getHomePage().waitForVisibilityElement(30, getHomePage().getLoginInput());
        getHomePage().setLoginInput(EMAIL_FOR_LOGIN);
        getHomePage().setPasswordInput(PASS);
        getHomePage().clickOnLoginButton();
    }

    @Test
    public void registrationTest() {
        waitForPopupWithAuthLinks();
        getHomePage().clickRegisterButton();
        getRegisterPage().waitForPageLoadingComplete(30);
        getRegisterPage().setTextFirstName(FIRSTNAME);
        getRegisterPage().setTextLastName(LASTNAME);
        getRegisterPage().clickBdDay(BD_DAY_SELECT_VALUE);
        getRegisterPage().clickBdMonth(BD_MONTH_SELECT_VALUE);
        getRegisterPage().clickBdYear(BD_YEAR_SELECT_VALUE);
        getRegisterPage().setTextPhone(PHONE);
        getRegisterPage().setTextEmail(EMAIL);
        getRegisterPage().implicitWait(30);
        getRegisterPage().clickRegistrationBtn();
        getProfilePage().waitForPageLoadingComplete(30);
        Assert.assertTrue(getProfilePage().getTextModalMessage().contains(SUCCESS_TEXT));
    }

    @Test
    public void loginTest() {
        loginWithCredentials();
        getHomePage().implicitWait(60);
        try {
            Assert.assertTrue(getHomePage().getNameLoginnedUser().contains(FIRSTNAME));
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            Assert.assertTrue(getHomePage().getNameLoginnedUser().contains(FIRSTNAME));
        }
    }

    @Test
    public void addProdToCartTest() {
        loginWithCredentials();
        getHomePage().clickLinkToParfums();
        getParfumsPage().waitForPageLoadingComplete(30);
        getParfumsPage().clickOnFirstParfum();
        getProdPage().waitForPageLoadingComplete(30);
        getProdPage().clickBuyBtn();
        getProdPage().clickHomeLink();
        getHomePage().waitForPageLoadingComplete(30);
        getHomePage().clickCartLink();
        getCartPage().waitForPageLoadingComplete(30);
        Assert.assertTrue(getCartPage().getCartTitle().contains("1 товар"));
    }

    @Test
    public void searchTest() {
        getHomePage().clickOnSearchInput();
        try {
            getHomePage().setTextToSearchInput(SEARCH_QUERY);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            getHomePage().setTextToSearchInput(SEARCH_QUERY);
        }
        getBrandPage().implicitWait(60);
        List<WebElement> productsDescriptions = getBrandPage().getProductsDescriptions();
        for (WebElement productsDescription : productsDescriptions) {
            Assert.assertTrue(productsDescription.getText().toLowerCase().contains(SEARCH_QUERY.toLowerCase()));
        }
    }
}
