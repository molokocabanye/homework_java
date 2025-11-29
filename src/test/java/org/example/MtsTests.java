package org.example;

import static org.junit.jupiter.api.Assertions.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

class MtsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String BASE_URL = "https://www.mts.by";
    private final String PHONE_NUMBER = "297777777";
    private final String EMAIL = "test.mail@gmail.com";
    private final String CHECK_SUM = "100";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);

        WebElement cookieAgreeElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
        cookieAgreeElement.click();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPaySectionTitle() {
        String supposedResult = "Онлайн пополнение\nбез комиссии";
        WebElement sectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")));

        assertNotNull(sectionTitle);
        assertTrue(sectionTitle.isDisplayed(), "Заголовок блока не отображается");
        assertEquals(supposedResult, sectionTitle.getText());
    }

    @Test
    public void testPaySectionValidData()  {
        WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        WebElement sumElement = driver.findElement(By.id("connection-sum"));
        WebElement emailElement = driver.findElement(By.id("connection-email"));

        assertNotNull(phoneElement);
        phoneElement.sendKeys(PHONE_NUMBER);
        sumElement.sendKeys(CHECK_SUM);
        emailElement.sendKeys(EMAIL);

        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        continueButton.click();

        WebElement payElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("bepaid-app")));
        assertTrue(payElement.isEnabled());
    }

    @Test
    public void testButtonMoreAboutService() {
        String originalUrl = driver.getCurrentUrl();

        WebElement button = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a"
        ));

        button.click();

        String newUrl = driver.getCurrentUrl();
        assertNotEquals(originalUrl, newUrl, "URL не изменился после клика");
    }

    @Test
    public void testPresenceOfPaySystemLogos() {
        WebElement visaLogoElement = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img"
        ));

        WebElement verifiedByVisaLogoElement = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img"
        ));

        WebElement masterCardLogoElement = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img"
        ));

        WebElement masterCardSecureCodeElement = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img"
        ));

        WebElement belCardElement = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img"
        ));

        checkLogoPresence(visaLogoElement, "Visa");

        checkLogoPresence(verifiedByVisaLogoElement, "Verified By Visa");
        checkLogoPresence(masterCardLogoElement, "MasterCard");
        checkLogoPresence(masterCardSecureCodeElement, "MasterCard Secure Code");
        checkLogoPresence(belCardElement, "Белкарт");
    }

    private void checkLogoPresence(WebElement element, String name) {
        String src = element.getAttribute("src");
        String alt = element.getAttribute("alt");

        assertTrue(element.isDisplayed());
        assertNotNull(src);
        assertEquals(name, alt);
    }
}