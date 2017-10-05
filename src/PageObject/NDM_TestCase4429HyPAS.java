package PageObject;

/**
 * Updated 1 on 2/8/2017.
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

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class NDM_TestCase4429HyPAS extends NDM_BaseSeleniumTest
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

            test = extentCommon.createTest("4429HyPAS", "NDM_TestCase4429HyPAS");

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

            test.info("InstallHyPasApp");
            String installHP = devicePropertiesPage.InstallHyPasApp(getCpecificKeyWord("HyPassFile"));
            System.out.println("Install pkg [OK]");
            test.pass("InstallHyPasApp");

            test.info("WaitingRefreshing");
            devicePropertiesPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectPackege");
            devicePropertiesPage.SelectPackege("PanelPlus");
            System.out.println("Select pkg [OK]");
            test.pass("SelectPackege");

            test.info("ActivateHyPas");
            //String activateHP = devicePropertiesPage.ActivateHyPas("42367793603787407156");
            String activateHP = devicePropertiesPage.ActivateHyPas(null);
            System.out.println("Activate pkg [OK]");
            test.pass("ActivateHyPas");

            test.info("WaitingRefreshing");
            devicePropertiesPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectPackege");
            devicePropertiesPage.SelectPackege("PanelPlus");
            System.out.println("Select pkg [OK]");
            test.pass("SelectPackege");

            test.info("DeactivateHyPas");
            String deactivateHP = devicePropertiesPage.DeactivateHyPas();
            System.out.println("Deactivate pkg [OK]");
            test.pass("DeactivateHyPas");

            test.info("WaitingRefreshing");
            devicePropertiesPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectPackege");
            devicePropertiesPage.SelectPackege("PanelPlus");
            System.out.println("Select pkg [OK]");
            test.pass("SelectPackege");

            test.info("UninstallHyPas");
            String uninstallHP = devicePropertiesPage.UninstallHyPas();
            System.out.println("Uninstall pkg [OK]");
            test.pass("UninstallHyPas");

            test.info("WaitingRefreshing");
            devicePropertiesPage.WaitingRefreshing();
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
            ndm_tasksPage.FilterTasksByName(installHP);
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

            test.info("RemoveFilter");
            ndm_tasksPage.RemoveFilter();
            test.pass("RemoveFilter");

            test.info("FilterTasksByName");
            ndm_tasksPage.FilterTasksByName(activateHP);
            System.out.println("Filter tasks [OK]");
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
            ndm_tasksPage.FilterTasksByName(deactivateHP);
            System.out.println("Filter tasks [OK]");
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
            ndm_tasksPage.FilterTasksByName(uninstallHP);
            System.out.println("Filter tasks [OK]");
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