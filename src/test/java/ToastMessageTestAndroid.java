import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToastMessageTestAndroid extends AndroidBaseTest {
    @BeforeMethod
    public void preSetup() {
        formPage.setActivity();
    }

    @Test
    public void verifyValidationToastMessage() {
        formPage.setGender("Female");
        formPage.setCountrySelection("Argentina");
        formPage.clickShopButton();

        String toastMessage = formPage.getValidationToastMessage();
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test
    public void verifyNoValidationToastMessagesDisplayed() {
        formPage.setNameField("Ulad");
        formPage.setGender("Female");
        formPage.setCountrySelection("Argentina");
        formPage.clickShopButton();

        Assert.assertTrue((driver.findElements(By.xpath("(//android.widget.Toast)[1]"))).size() < 1);
    }
}