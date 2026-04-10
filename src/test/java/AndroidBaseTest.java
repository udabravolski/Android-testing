import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class AndroidBaseTest extends TestUtils {
    public AppiumDriverLocalService service;
    public AndroidDriver driver;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public FormPage configureAppium() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "src/main/resources/data.properties");
        // -------------------------------------- ternary operator takes ip address from Maven command in iTerm(mvn test -PRegression -DipAddress=222.0.0.1). If not in Maven then from data.properties file(127.0.0.1)-----------
        String ipAddress = (System.getProperty("ipAddress") != null) ? System.getProperty("ipAddress") : properties.getProperty("ipAddress");
        //------------------------------------------------------------------------------------------------------------------------------------
        properties.load(fileInputStream);

        service = startAppiumService(ipAddress, Integer.parseInt((properties.getProperty("port"))));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("AndroidDeviceName"));
        options.setChromedriverExecutable("/Users/chromedriver-mac-x64/chromedriver");
        options.setApp("/Users/uladzimirdabravolski/Desktop/_1/src/main/resources/General-Store.apk");

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        formPage = new FormPage(driver);
        return formPage;
    }

    public List<HashMap<String, String>> getJsonData(String jsonFile) throws IOException {
        // ---------------------------from JSON to String (Apache Common IO framework)------------------------------------------/
        String jsonContent = FileUtils.readFileToString(new File(jsonFile));

        // -----------------------------from String to List of HashMaps (Jackson framework)-------------------------------------/
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
