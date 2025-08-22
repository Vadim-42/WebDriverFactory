package pages;

import data.InputFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class MainPage extends AbsBasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By textName = By.id("username");
    private By textEmail = By.id("email");
    private By textPassword = By.id("password");
    private By textConfirmPassword = By.id("confirm_password");
    private By selectBirthday = By.id("birthdate");
    private By selectLanguage = By.id("language_level");
    private By submitButton = By.cssSelector("input[type='submit']");
    private By output = By.id("output");

    public void fillValueInField(InputFieldData inputFieldData, String value) {
        switch (inputFieldData) {
            case NAME: {
                driver.findElement(textName).sendKeys(value);
                return;
            }
            case EMAIL: {
                driver.findElement(textEmail).sendKeys(value);
                return;
            }
            case PASSWORD: {
                driver.findElement(textPassword).sendKeys(value);
                return;
            }
            case CONFIRM_PASSWORD: {
                driver.findElement(textConfirmPassword).sendKeys(value);
            }
        }
    }

    public void selectBirthday(Date birthday) {
        driver.findElement(selectBirthday).click();
        new Actions(driver)
                .sendKeys(new SimpleDateFormat("ddMMyyyy").format(birthday))
                .perform();
    }

    public void selectLanguage(String language) {
        Select select = new Select(driver.findElement(selectLanguage));
        select.selectByValue(language);
    }

    public void clickForSubmitForm() {
        driver.findElement(submitButton).click();
    }

    public String getPassword() {
        return driver.findElement(textPassword).getAttribute("value");
    }

    public String getConfirmPassword() {
        return driver.findElement(textConfirmPassword).getAttribute("value");
    }

    public boolean chkForEqualPasswords() {
        return Objects.equals(getPassword(), getConfirmPassword());
    }

    public String outputText() {
        return driver.findElement(output).getText();
    }

    public boolean outputContainsName(String name){
        return outputText().contains("Имя пользователя: " + name);
    }
    public boolean outputContainsEmail(String email){
        return outputText().contains("Электронная почта: " + email);
    }
    public boolean outputContainsBirthday(Date birthday){
        return outputText().contains("Дата рождения: "
                + new SimpleDateFormat("yyyy-MM-dd").format(birthday));
    }
    public boolean outputContainsLanguage(String language){
        return outputText().contains("Уровень языка: " + language);
    }

    public void assertAllConditions(String name, String email, Date birthday, String language) {
        assertAll("output",
                () -> Assertions.assertTrue(chkForEqualPasswords()),
                () -> Assertions.assertTrue(outputContainsName(name)),
                () -> Assertions.assertTrue(outputContainsEmail(email)),
                () -> Assertions.assertTrue(outputContainsBirthday(birthday)),
                () -> Assertions.assertTrue(outputContainsLanguage(language))
        );
    }

}