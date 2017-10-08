package PageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.File;
import com.google.common.io.Files;

/**
 * Created by 1 on 4/11/2017.
 * Updated 7/28/2017
 * Updated 8/4/2017
 * Updated 8/29/2017
 * Updated 9/1/2017
 * Updated 9/5/2017
 * Updated 9/8/2017
 * Updated 9/12/2017
 * Updated 9/13/2017
 * Updated 9/15/2017
 * Updated 9/19/2017
 */


@RunWith(Suite.class)
@Suite.SuiteClasses(
{
   // NDM_TestCase947Discovery.class,
   //NDM_TestCase6601OptionalFunction.class,
  //  NDM_TestCase3384FirmwareUpgrade.class,
    //NDM_TestCase2617DeviceRestart.class,
    //NDM_TestCaseAddressBookAddContact.class,
   // NDM_TestCase954Discovery.class,
   // NDM_TestCase2616DeviceRestart.class,
   // NDM_TestCase6602CertManagement.class,
   // NDM_TestCase2588DeviceRestart.class,
   // NDM_TestCase4429HyPAS.class,
  //  NDM_TestCase6728Views.class,
})

public class NDM_TestSuite
{
    private static String curDateTime = DateTimeFormatter.ofPattern("MMddyyyy_HHmmss").format(LocalDateTime.now());
    private static WebDriver driver;
    private static int driverCounter = 0;
    private static int reportCounter = 0;
    private static String userName = "admin";
    //private static String password = "Kyocera1!";
    private static String password = "admin";
    //private static String pageUrl = "https://ndm-qa-rec1:9292";
    //private static String pageUrl = "https://10.10.16.216:9292";
    private static String pageUrl = "http://ndm-dev-rec:9191";
    //private static String pageUrl = "http://10.191.12.30:9191";
    private static String ipAddress = "10.10.30.203";
    private static File tempFolder = Files.createTempDir();

    private static Map<String, Object> objectKeyMap = new HashMap<String, Object>()
    {{
        put("tst1", (Object)"sdfsdf");
        put("tst2", (Object)new String[] {"IEEE 802.1X", "Enhanced WSD over SSL"});
    }};

    public static Object getObjectWord(String key)
    {
        return objectKeyMap.get(key);
    }

    private static Map<String, String> cpecificKeyWords = new HashMap<String, String>()
    {{
        //put("FirmwarePath", "C:\\Users\\1\\Downloads\\CustumFirmware4.zip");
        put("FirmwarePath", "C:\\eclipse2\\E-GeminiHy005.011.zip");
        put("PathToCsvFile", "C:\\eclipse2\\test_csv_ip.txt");
        put("PathCertFile", "C:\\eclipse2\\root7.cer");
        put("HyPassFile", "C:\\eclipse2\\PanelPlus-006012.pkg");
        put("AddressBookExportUserManual", "C:\\eclipse2\\AddressBookExportUserManual.csv");
        put("AddressBookExportUserAuto", "C:\\eclipse2\\AddressBookExportUserAuto.csv");
        put("AddressBookExportGroupManual", "C:\\eclipse2\\AddressBookExportGroupManual.csv");
        put("AddressBookExportGroupAuto", "C:\\eclipse2\\AddressBookExportGroupAuto.csv");
        put("AddressBookExportOneTouchKeyManual", "C:\\eclipse2\\AddressBookExportOneTouchKeyManual.csv");
        put("AddressBookExportOneTouchKeyAuto", "C:\\eclipse2\\AddressBookExportOneTouchKeyAuto.csv");
    }};

    public static String getCpecificKeyWord(String key)
    {
        String result = cpecificKeyWords.get(key);

        return result == null ? "" : result;
    }

    @BeforeMethod
	public void setUp()
    {
        driver = getRealWebDriver();
    }

    public static ExtentHtmlReporter getRealHtmlReport()
    {
        ExtentHtmlReporter result = new ExtentHtmlReporter("./NDM_TestCase_" + curDateTime + ".html");

        result.config().setDocumentTitle("Kyocera NDM reports");
        result.config().setReportName("Test Suite reports");

        if (++reportCounter >=1 )
        {
            result.setAppendExisting(true);
        }

        return result;
    }

    public static WebDriver getRealWebDriver()
    {
    	System.setProperty("webdriver.chrome.driver", "C:\\eclipse2\\Workspace\\NDM3\\lib\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        prefs.put("download.default_directory", tempFolder.toString());
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        options.setExperimentalOptions("prefs", prefs);

        WebDriver currentDriver = new ChromeDriver(options);

        currentDriver.manage().window().maximize();

        currentDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        return currentDriver;
    }

    public static WebDriver getDriver()
    {
        ++driverCounter;

        return driver;
    }

    public static void setDriver(WebDriver currentDriver)
    {
        driver = currentDriver;
    }

    public static int getDriverCounter()
    {
        return driverCounter;
    }

    public static String getUserName()
    {
        return userName;
    }

    public static String getPassword()
    {
        return password;
    }

    public static String getPageUrl()
    {
        return pageUrl;
    }

    public static String getIpAddress()
    {
        return ipAddress;
    }

    public static File getTempFolder()
    {
        return tempFolder;
    }
}
