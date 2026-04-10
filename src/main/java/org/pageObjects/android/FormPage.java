package org.pageObjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utils.AndroidActions;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropdown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleGenderRadioButton;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleGenderRadioButton;

    @AndroidFindBy(id ="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement validationToastMessage;

    public void setActivity() {
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
        ));
    }

    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.contains("Female"))
            femaleGenderRadioButton.click();
        else
            maleGenderRadioButton.click();
    }

    public void setCountrySelection(String country) {
        countryDropdown.click();
        scrollToText(country);
        driver.findElement(By.xpath("//<android.widget.TextView[@text='" + country + "']")).click();
    }

    public ProductCatalog clickShopButton() {
        shopButton.click();
        return new ProductCatalog(driver);
    }

    public String getValidationToastMessage() {
        return validationToastMessage.getAttribute("name");
    }
}
