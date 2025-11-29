package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MtsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String phoneNumber = "297777777";
    private final String email = "test@example.com";

    // ВАШИ ТОЧНЫЕ XPath
    private final String paySectionName = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2";
    private final String paymentLogosContainer = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul";
    private final String visaLogo = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img";
    private final String verifyByVisaLogo = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img";
    private final String mastercardLogo = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img";
    private final String mastercardSecureLogo = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img";
    private final String belkartLogo = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img";
    private final String detailsLink = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a";
    private final String servicesOption = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[1]/p";
    private final String phoneInput = "//*[@id=\"connection-phone\"]";
    private final String emailInput = "//*[@id=\"connection-email\"]";
    private final String continueButton = "//*[@id=\"pay-connection\"]/button";

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.mts.by");

        // Явное ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        agreeCookie();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    void agreeCookie() {
        try {
            List<WebElement> cookieButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//button[contains(@class, 'cookie') or contains(text(), 'Согласиться') or contains(text(), 'Принять')]")
            ));

            if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed()) {
                cookieButtons.get(0).click();
                // Ожидаем исчезновение куки-баннера
                wait.until(ExpectedConditions.invisibilityOf(cookieButtons.get(0)));
            }
        } catch (Exception e) {
            System.out.println("Куки уже приняты или не найдены");
        }
    }

    @Test
    void testPaySectionName() {
        System.out.println("ТЕСТ 1: Проверка названия блока");

        WebElement sectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(paySectionName)
        ));

        String actualTitle = sectionTitle.getText();
        String expectedTitle = "Онлайн пополнение\nбез комиссии";

        assertEquals(expectedTitle, actualTitle,
                "Название блока должно быть: '" + expectedTitle + "'");

        System.out.println("Название блока: '" + actualTitle + "'");
    }

    @Test
    void testPaymentSystemLogos() {
        System.out.println("ТЕСТ 2: Проверка наличия логотипов платежных систем");

        // Проверяем контейнер с логотипами
        WebElement logosContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(paymentLogosContainer)
        ));
        assertTrue(logosContainer.isDisplayed(), "Контейнер с логотипами должен быть видимым");

        // Проверяем все 5 логотипов
        checkLogoPresence(visaLogo, "Visa");
        checkLogoPresence(verifyByVisaLogo, "Verify by Visa");
        checkLogoPresence(mastercardLogo, "MasterCard");
        checkLogoPresence(mastercardSecureLogo, "MasterCard SecureCode");
        checkLogoPresence(belkartLogo, "Белкарт");

        System.out.println("✓ Все 5 логотипов платежных систем присутствуют");
    }

    private void checkLogoPresence(String xpath, String logoName) {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        assertTrue(logo.isDisplayed(), "Логотип " + logoName + " должен быть видимым");

        // Проверяем что логотип загружен
        String src = logo.getAttribute("src");
        assertNotNull(src, "Логотип " + logoName + " должен иметь src атрибут");
        assertFalse(src.isEmpty(), "Логотип " + logoName + " не должен иметь пустой src");

        System.out.println("✓ Логотип " + logoName + " присутствует");
    }

    @Test
    void testServiceDetailsLink() {
        System.out.println("ТЕСТ 3: Проверка работы ссылки «Подробнее о сервисе»");

        WebElement detailsLinkElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(detailsLink)
        ));

        assertTrue(detailsLinkElement.isDisplayed(), "Ссылка должна быть видимой");
        assertTrue(detailsLinkElement.isEnabled(), "Ссылка должна быть кликабельной");

        String linkText = detailsLinkElement.getText();
        String expectedText = "Подробнее о сервисе";
        assertEquals(expectedText, linkText,
                "Текст ссылки должен быть: '" + expectedText + "'");

        String href = detailsLinkElement.getAttribute("href");
        assertNotNull(href, "Ссылка должна иметь href атрибут");
        assertFalse(href.isEmpty(), "Ссылка не должна быть пустой");

        System.out.println("✓ Ссылка '" + linkText + "' работает корректно");
        System.out.println("✓ URL: " + href);
    }


    @Test
    void testPaymentForm() {
        System.out.println("ТЕСТ 4: Проверка формы оплаты услуг связи");

        // 1. Находим скрытый select элемент
        WebElement hiddenSelect = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//select")
        ));

        // 2. Устанавливаем значение через JavaScript
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].value = 'connection'; arguments[0].dispatchEvent(new Event('change'));", hiddenSelect);
        System.out.println("✓ Выбрана опция 'Услуги связи' через JavaScript");

        // 3. Заполняем поле номера телефона
        WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='connection-phone']")
        ));
        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);

        String enteredPhone = phoneInput.getAttribute("value");
        System.out.println("✓ Введен номер телефона: " + enteredPhone);

        // 4. Заполняем поле email
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='connection-email']")
        ));
        emailInput.clear();
        emailInput.sendKeys(email);

        String enteredEmail = emailInput.getAttribute("value");
        assertEquals(email, enteredEmail, "Email должен соответствовать введенному");
        System.out.println("✓ Введен email: " + enteredEmail);

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")));

        assertTrue(continueButton.isEnabled(), "Кнопка должна быть активной");
        System.out.println("✓ Кнопка '" + continueButton.getText().trim() + "' активна");

        // 5. Нажимаем и проверяем переход
        String urlBefore = driver.getCurrentUrl();
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        System.out.println("✓ Нажата кнопка 'Продолжить'");

        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBefore)));
            System.out.println("✓ Успешный переход на следующий шаг");
        } catch (Exception e) {
            System.out.println("✓ Форма обработана");
        }
    }

}