package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "cookie-agree")
    private WebElement cookieAgreeButton;

    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")
    private WebElement sectionTitle;

    @FindBy(xpath = "//div[contains(@class, 'pay-variant')]")
    private List<WebElement> paymentVariants;

    @FindBy(id = "connection-phone")
    private WebElement phoneInput;

    @FindBy(id = "connection-sum")
    private WebElement sumInput;

    @FindBy(id = "connection-email")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    private WebElement continueButton;

    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")
    private WebElement moreAboutServiceLink;

    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li/img")
    private List<WebElement> paymentSystemLogos;

    public MtsHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.mts.by");
        acceptCookies();
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieAgreeButton)).click();
    }

    public String getSectionTitle() {
        return wait.until(ExpectedConditions.visibilityOf(sectionTitle)).getText();
    }

    public void fillConnectionServiceForm(String phone, String sum, String email) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
        sumInput.clear();
        sumInput.sendKeys(sum);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public PaymentPage clickContinueButton() {
        continueButton.click();
        return new PaymentPage(driver);
    }

    public void clickMoreAboutService() {
        moreAboutServiceLink.click();
    }

    public boolean arePaymentSystemLogosDisplayed() {
        return paymentSystemLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public int getPaymentSystemLogosCount() {
        return paymentSystemLogos.size();
    }

    public String getLogoAltText(int index) {
        return paymentSystemLogos.get(index).getAttribute("alt");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPhonePlaceholder() {
        return phoneInput.getAttribute("placeholder");
    }

    public String getSumPlaceholder() {
        return sumInput.getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return emailInput.getAttribute("placeholder");
    }
}