package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class MtsPageObjectTest {
    private WebDriver driver;
    private MtsHomePage homePage;

    private final String PHONE_NUMBER = "297777777";
    private final String EMAIL = "test.mail@gmail.com";
    private final String CHECK_SUM = "100";
    private final String EXPECTED_AMOUNT = "100.00 BYN";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new MtsHomePage(driver);
        homePage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testPaySectionTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = homePage.getSectionTitle();

        assertEquals(expectedTitle, actualTitle, "Заголовок блока не соответствует ожидаемому");
    }

    @Test
    void testPaymentPlaceholdersForDifferentServices() {
        String phonePlaceholder = homePage.getPhonePlaceholder();
        String sumPlaceholder = homePage.getSumPlaceholder();
        String emailPlaceholder = homePage.getEmailPlaceholder();

        assertNotNull(phonePlaceholder, "Плейсхолдер для номера телефона не найден");
        assertNotNull(sumPlaceholder, "Плейсхолдер для суммы не найден");
        assertNotNull(emailPlaceholder, "Плейсхолдер для email не найден");
    }

    @Test
    void testConnectionServicePaymentFlow() {
        homePage.fillConnectionServiceForm(PHONE_NUMBER, CHECK_SUM, EMAIL);

        PaymentPage paymentPage = homePage.clickContinueButton();

        paymentPage.waitForPaymentFormToLoad();

        assertTrue(paymentPage.isPaymentContainerDisplayed(),
                "Контейнер оплаты не отображается");

        assertTrue(paymentPage.isIframeLoaded(),
                "Iframe с платежной формой не загрузился");

        String paymentTitle = paymentPage.getPaymentTitle();
        String phoneInfo = paymentPage.getPhoneNumberInfo();

        assertTrue(paymentTitle.contains("Услуги связи"),
                "Заголовок не содержит информацию об услугах связи");
        assertTrue(phoneInfo.contains(PHONE_NUMBER),
                "Информация о номере не соответствует введенному");

        String amountFromDescription = paymentPage.getAmountFromDescription();
        String amountFromButton = paymentPage.getAmountFromButton();

        assertTrue(amountFromDescription.contains(EXPECTED_AMOUNT),
                "Сумма в описании не соответствует ожидаемой");
        assertTrue(amountFromButton.contains(EXPECTED_AMOUNT),
                "Сумма на кнопке не соответствует ожидаемой");

        String cardNumberPlaceholder = paymentPage.getCardNumberPlaceholder();
        String cardExpiryPlaceholder = paymentPage.getCardExpiryPlaceholder();
        String cardCvcPlaceholder = paymentPage.getCardCvcPlaceholder();
        String cardHolderPlaceholder = paymentPage.getCardHolderPlaceholder();

        assertNotNull(cardNumberPlaceholder, "Плейсхолдер номера карты не найден");
        assertNotNull(cardExpiryPlaceholder, "Плейсхолдер срока действия не найден");
        assertNotNull(cardCvcPlaceholder, "Плейсхолдер CVC не найден");
        assertNotNull(cardHolderPlaceholder, "Плейсхолдер владельца карты не найден");

        assertTrue(paymentPage.arePaymentSystemIconsDisplayed(),
                "Не все иконки платежных систем отображаются");
        assertTrue(paymentPage.isVisaIconDisplayed(), "Иконка Visa не отображается");
        assertTrue(paymentPage.isMastercardIconDisplayed(), "Иконка Mastercard не отображается");
        assertTrue(paymentPage.isBelkartIconDisplayed(), "Иконка Белкарт не отображается");
    }

    @Test
    void testPaymentSystemLogosPresence() {
        assertTrue(homePage.arePaymentSystemLogosDisplayed(),
                "Не все логотипы платежных систем отображаются");

        assertEquals(5, homePage.getPaymentSystemLogosCount(),
                "Количество логотипов не соответствует ожидаемому");

        String[] expectedAltTexts = {"Visa", "Verified By Visa", "MasterCard",
                "MasterCard Secure Code", "Белкарт"};

        for (int i = 0; i < expectedAltTexts.length; i++) {
            String actualAlt = homePage.getLogoAltText(i);
            assertEquals(expectedAltTexts[i], actualAlt,
                    "Alt текст логотипа не соответствует ожидаемому");
        }
    }
}