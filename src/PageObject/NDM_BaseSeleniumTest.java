package PageObject;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by 1 on 4/6/2017.
 * Updated 7/28/2017
 * Updated 9/12/2017
 * Updated 9/29/2017
 */
//add comments
public abstract class NDM_BaseSeleniumTest
{
    public String getCpecificKeyWord(String key)
    {
        return NDM_TestSuite.getCpecificKeyWord(key);
    }

    public String getUserName()
    {
        return NDM_TestSuite.getUserName();
    }

    public String getPassword()
    {
        return NDM_TestSuite.getPassword();
    }

    public Object getObjectWord(String Key)
    {
        return NDM_TestSuite.getObjectWord(Key);
    }

    public String getIpAddress()
    {
        return NDM_TestSuite.getIpAddress();
    }

    public File getTempFolder()
    {
        return NDM_TestSuite.getTempFolder();
    }

    public int getDriverCounter()
    {
        return NDM_TestSuite.getDriverCounter();
    }

    public String getPageUrl()
    {
        return NDM_TestSuite.getPageUrl();
    }

    public WebDriver getDriver()
    {
        WebDriver driver = NDM_TestSuite.getDriver();
        if (driver != null)
            return driver;

        driver = NDM_TestSuite.getRealWebDriver();

        NDM_TestSuite.setDriver(driver);

        return driver;
    }

    public ExtentHtmlReporter getHtmlReport()
    {
        return NDM_TestSuite.getRealHtmlReport();
    }
}
