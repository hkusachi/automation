package PageObject;

/**
 * Updated 1 on 2/3/2017.
 * Updated 4/11/2017.
 * Updated 9/12/2017
 * Updated 9/13/2017
 * Updated 9/15/2017
 */

import Pages.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.server.rest.ResultType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.FileUtils.readFileToString;

public class NDM_TestCase954Discovery extends NDM_BaseSeleniumTest
{
    private WebDriver driver;
    private ExtentHtmlReporter htmlReporterCommon;
    private ExtentTest test;

    ExtentReports extentCommon;

    @Test
    public void testPage() throws InterruptedException, AWTException, IOException
    {
        try
        {
            driver = getDriver();
            htmlReporterCommon = getHtmlReport();
            extentCommon = new ExtentReports();
            extentCommon.attachReporter(htmlReporterCommon);

            test = extentCommon.createTest("954Discovery", "NDM_TestCase954Discovery");

            String pathToTheCSVFile = getCpecificKeyWord("PathToCsvFile");

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
            home.AddDevicesByImportingList(pathToTheCSVFile);
            System.out.println("Discovered [OK]");
            test.pass("Discovered");

            String ipAdresses = readFileToString(new File(pathToTheCSVFile));
            String[] ipAdressesArray = ipAdresses.split("\r\n");

            System.out.println("Login [OK]");

            for (int i = 0; i < ipAdressesArray.length; ++i)
            {
                test.info("AdvancedSearch");
                home.AdvancedSearch("IP address", ipAdressesArray[i]);
                System.out.println("{ " + ipAdressesArray[i] + " } [OK]");
                test.pass("AdvancedSearch");

                test.info("CheckDevice");
                boolean result = home.CheckDeviceIn(ipAdressesArray[i], 1);
                System.out.println("Check device [OK]");
                test.pass("CheckDevice");

                test.info("RemoveFilter");
                home.RemoveFilter();
                System.out.println("RemoveFilter [OK]");
                test.pass("RemoveFilter");

                Assert.assertTrue(result);
            }
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