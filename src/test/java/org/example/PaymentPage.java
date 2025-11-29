package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "bepaid-app")
    private WebElement paymentContainer;

    @FindBy(className = "bepaid-iframe")
    private WebElement paymentIframe;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isPaymentContainerDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(paymentContainer)).isDisplayed();
    }

    private void switchToPaymentIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
    }

    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getPaymentTitle() {
        switchToPaymentIframe();
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Оплата: Услуги связи')]")));
            return element.getText();
        } finally {
            switchToDefaultContent();
        }
    }

    public String getPhoneNumberInfo() {
        switchToPaymentIframe();
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Номер:375297777777')]")));
            return element.getText();
        } finally {
            switchToDefaultContent();
        }
    }

    public String getAmountFromDescription() {
        switchToPaymentIframe();
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), '100.00 BYN')]")));
            return element.getText();
        } finally {
            switchToDefaultContent();
        }
    }

    public String getAmountFromButton() {
        switchToPaymentIframe();
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Оплатить')]")));
            return element.getText();
        } finally {
            switchToDefaultContent();
        }
    }

    public String getCardNumberPlaceholder() {
        switchToPaymentIframe();
        try {
            WebElement element = driver.findElement(By.id("cc-number"));
            return element.getAttribute("placeholder");
        } finally {
            switchToDefaultContent();
        }
    }

    public String getCardExpiryPlaceholder() {
        switchToPaymentIframe();
        try {
            WebElement element = driver.findElement(By.xpath("//input[@formcontrolname='expirationDate']"));
            return element.getAttribute("placeholder");
        } finally {
            switchToDefaultContent();
        }
    }

    public String getCardCvcPlaceholder() {
        switchToPaymentIframe();
        try {
            WebElement element = driver.findElement(By.xpath("//input[@formcontrolname='cvc']"));
            return element.getAttribute("placeholder");
        } finally {
            switchToDefaultContent();
        }
    }

    public String getCardHolderPlaceholder() {
        switchToPaymentIframe();
        try {
            WebElement element = driver.findElement(By.xpath("//input[@formcontrolname='holder']"));
            return element.getAttribute("placeholder");
        } finally {
            switchToDefaultContent();
        }

    }

    public boolean arePaymentSystemIconsDisplayed() {
        switchToPaymentIframe();
        try {
            WebElement visa = driver.findElement(By.xpath("//img[contains(@src, 'visa-system')]"));
            WebElement mastercard = driver.findElement(By.xpath("//img[contains(@src, 'mastercard-system')]"));
            WebElement belkart = driver.findElement(By.xpath("//img[contains(@src, 'belkart-system')]"));

            return visa.isDisplayed() && mastercard.isDisplayed() && belkart.isDisplayed();
        } finally {
            switchToDefaultContent();
        }
    }

    public boolean isVisaIconDisplayed() {
        switchToPaymentIframe();
        try {
            WebElement visa = driver.findElement(By.xpath("//img[contains(@src, 'visa-system')]"));
            return visa.isDisplayed();
        } finally {
            switchToDefaultContent();
        }
    }

    public boolean isMastercardIconDisplayed() {
        switchToPaymentIframe();
        try {
            WebElement mastercard = driver.findElement(By.xpath("//img[contains(@src, 'mastercard-system')]"));
            return mastercard.isDisplayed();
        } finally {
            switchToDefaultContent();
        }
    }

    public boolean isBelkartIconDisplayed() {
        switchToPaymentIframe();
        try {
            WebElement belkart = driver.findElement(By.xpath("//img[contains(@src, 'belkart-system')]"));
            return belkart.isDisplayed();
        } finally {
            switchToDefaultContent();
        }
    }

    public boolean isIframeLoaded() {
        try {
            switchToPaymentIframe();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            switchToDefaultContent();
        }
    }

    public void waitForPaymentFormToLoad() {
        wait.until(ExpectedConditions.visibilityOf(paymentContainer));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
        switchToDefaultContent();
    }
}