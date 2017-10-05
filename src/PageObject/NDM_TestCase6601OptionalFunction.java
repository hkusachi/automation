package PageObject;

/**
 * Created 03/30/2017.
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class NDM_TestCase6601OptionalFunction extends NDM_BaseSeleniumTest
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

            test = extentCommon.createTest("6601OptionalFunction", "NDM_TestCase6601OptionalFunction");

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

            test.info("GoToManagement");
            devicePropertiesPage.GoToManagement();
            System.out.println("DeviceManagement [OK]");
            test.pass("GoToManagement");

            test.info("GoToOptionalFunction");
            NDM_OptionalFunctionPage ndm_optionalFunctionPage = devicePropertiesPage.GoToOptionalFunction();
            System.out.println("OptionalFunctionPage [OK]");
            test.pass("GoToOptionalFunction");

            test.info("SelectFunction");
            ndm_optionalFunctionPage.SelectFunction("Internet Fax Kit");
            System.out.println("Select Function [OK]");
            test.pass("SelectFunction");

            String randomKey20 = "";
            for (int k = 0; k < 20; ++k)
            {
                randomKey20 += (int) (Math.random() * 10);
            }

            String randomKey16 = "";
            for (int k = 0; k < 16; ++k)
            {
                randomKey16 += (int) (Math.random() * 10);
            }
            randomKey16 = "01QEAGNUG41GUK4A"; // for SN V786302634

            Thread.sleep(2000);

            test.info("ActivateFunctionWithKey");
            String activateWithKey = ndm_optionalFunctionPage.ActivateFunctionWithKey(randomKey20);
            System.out.println("Activate with 20 [OK]");
            test.pass("ActivateFunctionWithKey");

            test.info("WaitingRefreshing");
            ndm_optionalFunctionPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectFunction");
            ndm_optionalFunctionPage.SelectFunction("Internet Fax Kit");
            System.out.println("Select Function [OK]");
            test.pass("SelectFunction");

            Thread.sleep(2000);

            test.info("ActivateFunctionWithKey");
            String activateWithKey1 = ndm_optionalFunctionPage.ActivateFunctionWithKey(randomKey16);
            System.out.println("Activate with 16 [OK]");
            test.pass("ActivateFunctionWithKey");

            test.info("WaitingRefreshing");
            ndm_optionalFunctionPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectFunction");
            ndm_optionalFunctionPage.SelectFunction("Internet Fax Kit");
            System.out.println("Select Function [OK]");
            test.pass("SelectFunction");

            Thread.sleep(2000);

            test.info("ActivateFunctionWithKey");
            String activateWithKey2 = ndm_optionalFunctionPage.ActivateFunctionWithKey("");
            System.out.println("Activate without key [OK]");
            test.pass("ActivateFunctionWithKey");

            test.info("WaitingRefreshing");
            ndm_optionalFunctionPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

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
            ndm_tasksPage.FilterTasksByName(activateWithKey);
            System.out.println("Filter task [" + activateWithKey + "] [OK]");
            test.pass("FilterTasksByName");

            test.info("RefreshResult");
            Boolean result = ndm_tasksPage.RefreshResult(15 /*minutes*/, 30 /*refresh sec*/, "Succeeded");
            System.out.println("Refresh status [OK]");
            test.pass("RefreshResult");

            if (!result)
            {
                Assert.assertTrue(false);
            }

            test.info("RemoveFilter");
            ndm_tasksPage.RemoveFilter();
            test.pass("RemoveFilter");

            test.info("FilterTasksByName");
            ndm_tasksPage.FilterTasksByName(activateWithKey1);
            System.out.println("Filter task [" + activateWithKey1 + "] [OK]");
            test.pass("FilterTasksByName");

            test.info("RefreshResult");
            result = ndm_tasksPage.RefreshResult(15 /*minutes*/, 30 /*refresh sec*/, "Succeeded");
            System.out.println("Refresh status [OK]");
            test.pass("RefreshResult");

            if (!result)
            {
                Assert.assertTrue(false);
            }

            test.info("RemoveFilter");
            ndm_tasksPage.RemoveFilter();
            test.pass("RemoveFilter");

            test.info("FilterTasksByName");
            ndm_tasksPage.FilterTasksByName(activateWithKey2);
            System.out.println("Filter task [" + activateWithKey2 + "] [OK]");
            test.pass("FilterTasksByName");

            test.info("RefreshResult");
            result = ndm_tasksPage.RefreshResult(15 /*minutes*/, 30 /*refresh sec*/, "Succeeded");
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