package org.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement cartTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private String displayedSum;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsText;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkbox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement submitButton;

    public WebElement getCartTitle() {
        return cartTitle;
    }

    public double getTotalSumOfProducts() {
        int count = productPrices.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String priceWithDollarSign = productPrices.get(i).getText();
            double priceWithoutDollarSign = getFormattedAmount(priceWithDollarSign);
            totalSum += priceWithoutDollarSign;
        }
        return totalSum;
    }

    public double getDisplayedSumOfProducts() {
        return getFormattedAmount(displayedSum);
    }

    public void acceptTerms() {
        longPressAction(termsText);
        closeButton.click();
    }

    public void submitPurchase() {
        checkbox.click();
        submitButton.click();
    }
}
