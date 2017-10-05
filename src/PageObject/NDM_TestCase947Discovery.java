package PageObject;

/**
 * Updated 1 on 2/3/2017.
 * Updated 4/11/2017.
 * Updated 7/28/2017
 * Updated 9/1/2017
 * Updated 9/5/2017
 * Updated 9/12/2017
 * Updated 9/13/2017
 * Updated 9/15/2017
 */

import Pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class NDM_TestCase947Discovery extends NDM_BaseSeleniumTest
{
    private WebDriver driver;
    private ExtentHtmlReporter htmlReporterCommon;
    private ExtentTest test;

    ExtentReports extentCommon;

    @Test
    public void testPage() throws InterruptedException, AWTException
    {
        try
        {
            driver = getDriver();

            htmlReporterCommon = getHtmlReport();
            extentCommon = new ExtentReports();
            extentCommon.attachReporter(htmlReporterCommon);

            test = extentCommon.createTest("947Discovery", "NDM_TestCase947Discovery");

            //Object res1 = getObjectWord("tst1");
            //Object res2 = getObjectWord("tst2");
            //Object res3 = getObjectWord("tst3");
            //String rrr = getCpecificKeyWord("sdfsdf");
            //String rr1 = getCpecificKeyWord("FirmwarePath");

            int driverCounter = getDriverCounter();

            NDM_HomePage home = new NDM_HomePage(driver, getPageUrl());

            if (driverCounter == 1)
            {
                driver.get(getPageUrl());

                NDM_UserLoginPage login = new NDM_UserLoginPage(driver, getPageUrl());

                if (!getPageUrl().contains("localhost"))
                {
                    test.info("Login");
                    home = login.login(getUserName(), getPassword());
                    System.out.println("Login [OK]");
                    test.pass("Login");
                }
            } else
            {
                driver.get(getPageUrl());
            }

            Thread.sleep(5000);

            test.info("Discovered");
            home.AddDevicesBy("By IP address or host name", getIpAddress());
            System.out.println("Discovered device [OK]");
            test.pass("Discovered");

            test.info("AdvancedSearch");
            home.AdvancedSearch("IP address", getIpAddress());
            System.out.println("Filter device [OK]");
            test.pass("AdvancedSearch");

            test.info("CheckDevice");
            boolean result = home.CheckDeviceIn(getIpAddress(), 1);
            System.out.println("Check device [OK]");
            test.pass("CheckDevice");

            Assert.assertTrue(result);
        }
        catch(Exception ex)
        {
            test.fail(ex.getMessage());

            extentCommon.flush();

            throw ex;
        }
    }

    @After
    public void tearDown()
    {
        extentCommon.flush();
    }
}