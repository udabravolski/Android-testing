import org.pageObjects.android.CartPage;
import org.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BuyProductsTestAndroid extends AndroidBaseTest {
    @BeforeMethod(alwaysRun = true)
    public void preSetup() {
        formPage.setActivity();
    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void buyProducts(HashMap<String, String> data) {
        formPage.setNameField(data.get("name"));
        formPage.setGender(data.get("gender"));
        formPage.setCountrySelection(data.get("country"));

        ProductCatalog productCatalog = formPage.clickShopButton();
        productCatalog.addItemToCartByIndex(0);
        productCatalog.addItemToCartByIndex(0);

        CartPage cartPage = productCatalog.goToCartPage();
        cartPage.explicitWaitForCartPage(cartPage.getCartTitle(), driver);

        double totalSum = cartPage.getTotalSumOfProducts();
        double displayedSum = cartPage.getDisplayedSumOfProducts();
        Assert.assertEquals(totalSum, displayedSum);

        cartPage.acceptTerms();
        cartPage.submitPurchase();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "/src/test/java/testData/ECommerce.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
