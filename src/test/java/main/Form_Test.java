package main;

import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static data.InputFieldData.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Form_Test {

    private static final Logger logger = LogManager.getLogger(Form_Test.class);
    private static final WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriver driver = null;
    private static final String webDriverName = System.getProperty("browser").toLowerCase();

    @BeforeAll
    public static void init() {
        webDriverFactory.webDriverManagerSetup(webDriverName);
    }

    @BeforeEach
    public void createDriver() throws MalformedURLException {

        String options = "-headless";
        driver = WebDriverFactory.create(webDriverName, Optional.of(options));
    }

    @Test
    public void isFormInputsCorrect() {
        String name = "Sergey";
        String email = "otus@mail.com";
        String password = System.getProperty("password");
        String confirmPassword = System.getProperty("password");
        //String birthday = "05081987";
        Date birthday = new Date(1987, Calendar.AUGUST,5);
        String language = "native";

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        mainPage.fillValueInField(NAME, name);
        mainPage.fillValueInField(EMAIL, email);
        mainPage.fillValueInField(PASSWORD, password);
        mainPage.fillValueInField(CONFIRM_PASSWORD, confirmPassword);
        mainPage.selectBirthday(birthday);
        mainPage.selectLanguage(language);
        mainPage.clickForSubmitForm();

        mainPage.assertAllConditions(name, email, birthday, language);

        logger.info("passwordConfirm: " + mainPage.chkForEqualPasswords());
        logger.info("outputContainsName: " + mainPage.outputContainsName(name));
        logger.info("outputContainsEmail: " + mainPage.outputContainsEmail(email));
        logger.info("outputContainsBirthday: " + mainPage.outputContainsBirthday(birthday));
        logger.info("outputContainsLanguage: " + mainPage.outputContainsLanguage(language));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}