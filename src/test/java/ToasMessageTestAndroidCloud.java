import org.testng.Assert;
import org.testng.annotations.Test;

public class ToasMessageTestAndroidCloud extends AndroidBaseTest {
    @Test
    public void verifyValidationToastMessage() {
        formPage.setGender("Female");
        formPage.setCountrySelection("Argentina");
        formPage.clickShopButton();

        String toastMessage = formPage.getValidationToastMessage();
        Assert.assertEquals(toastMessage, "Please enter your name");
    }
}