package PageObject;

/**
 * Updated 1 on 2/10/2017.
 * Updated 4/11/2017
 * Updated 9/19/2017
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

import java.util.concurrent.TimeUnit;

public class NDM_TestCase2617DeviceRestart extends NDM_BaseSeleniumTest
{
    private WebDriver driver;
    private ExtentHtmlReporter htmlReporterCommon;
    private ExtentTest test;

    ExtentReports extentCommon;

    @Test
    public void testPage() throws InterruptedException
    {
        try
        {
            driver = getDriver();
            htmlReporterCommon = getHtmlReport();
            extentCommon = new ExtentReports();
            extentCommon.attachReporter(htmlReporterCommon);

            test = extentCommon.createTest("2617DeviceRestart", "NDM_TestCase2617DeviceRestart");

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

            test.info("AdvancedSearch");
            home.AdvancedSearch("IP address", getIpAddress());
            System.out.println("Filter [OK]");
            test.pass("AdvancedSearch");

            test.info("GoToDevicePropertiesPage");
            NDM_DevicePropertiesPage devicePropertiesPage = home.GoToDevicePropertiesPage(1);
            System.out.println("DevicePropertiesPage [OK]");
            test.pass("GoToDevicePropertiesPage");

            test.info("RestartDevice");
            String taskName = devicePropertiesPage.RestartDevice(NDM_Core.Restart.Type.TypeNetwork);
            System.out.println("Refresh status [OK]");
            test.pass("RestartDevice");

            test.info("GoToHomePage");
            NDM_HomePage ndm_homePage = devicePropertiesPage.GoToHomePage();
            System.out.println("Home page [OK]");
            test.pass("GoToHomePage");

            test.info("GoToTasksPage");
            NDM_TasksPage ndm_tasksPage = ndm_homePage.GoToTasksPage();
            System.out.println("Tasks page [OK]");
            test.pass("GoToTasksPage");

            test.info("GoToTasksPageCompleted");
            ndm_tasksPage.GoToTasksPageCompleted();
            System.out.println("Completed asks page [OK]");
            test.pass("GoToTasksPageCompleted");

            test.info("FilterTasksByName");
            ndm_tasksPage.FilterTasksByName(taskName);
            System.out.println("Filter tasks [OK]");
            test.pass("FilterTasksByName");

            test.info("RefreshResult");
            Boolean result = ndm_tasksPage.RefreshResult(15 /*minutes*/, 30 /*refresh sec*/, "Succeeded");
            System.out.println("Refresh status [OK]");
            test.pass("RefreshResult");

            if (!result)
            {
                Assert.assertTrue(false);
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
