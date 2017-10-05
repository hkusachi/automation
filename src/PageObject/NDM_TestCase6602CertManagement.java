package PageObject;

/**
 * Created 2/21/2017.
 * Updated 4/11/2017.
 * Updated 9/1/2017
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

public class NDM_TestCase6602CertManagement extends NDM_BaseSeleniumTest
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

            test = extentCommon.createTest("6602CertManagement", "NDM_TestCase6602CertManagement");

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
            //home.QuickSearch(getIpAddress());
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

            test.info("GoToCertManagement");
            NDM_CertPage ndm_certPage = devicePropertiesPage.GoToCertManagement();
            System.out.println("CertPage [OK]");
            test.pass("GoToCertManagement");

            Thread.sleep(2000);

            test.info("InstallCert");
            String installCert = ndm_certPage.InstallCert(getCpecificKeyWord("PathCertFile"));
            System.out.println("Install Cert [OK]");
            test.pass("InstallCert");

            test.info("WaitingRefreshing");
            ndm_certPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectCert");
            ndm_certPage.SelectCert("Device certificate 2");
            System.out.println("Select Cert [OK]");
            test.pass("SelectCert");

            Thread.sleep(2000);

            test.info("AssignCert");
            String assignCert = ndm_certPage.AssignCert(new String[]{"IEEE 802.1X", "Enhanced WSD over SSL"});
            System.out.println("Assign Cert [OK]");
            test.pass("AssignCert");

            test.info("WaitingRefreshing");
            ndm_certPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectCert");
            ndm_certPage.SelectCert("Root certificate 1");
            System.out.println("Select Cert [OK]");
            test.pass("SelectCert");

            Thread.sleep(2000);

            test.info("DeleteCert");
            String deleteCert = ndm_certPage.DeleteCert();
            System.out.println("Delete Cert [OK]");
            test.pass("DeleteCert");

            test.info("WaitingRefreshing");
            ndm_certPage.WaitingRefreshing();
            System.out.println("Waiting [OK]");
            test.pass("WaitingRefreshing");

            test.info("SelectCert");
            ndm_certPage.SelectCert("Device certificate 2");
            System.out.println("Select Cert [OK]");
            test.pass("SelectCert");

            Thread.sleep(2000);

            //test.info("DeleteAssignCert");
           // String deleteAssignCert = ndm_certPage.DeleteAssignCert(new String[]{"IEEE 802.1X", "Enhanced WSD over SSL"});
            //System.out.println("Delete Assign Cert [OK]");
           // test.pass("DeleteAssignCert");

            test.info("WaitingRefreshing");
            ndm_certPage.WaitingRefreshing();
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
            ndm_tasksPage.FilterTasksByName(installCert);
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
            ndm_tasksPage.FilterTasksByName(assignCert);
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
            ndm_tasksPage.FilterTasksByName(deleteCert);
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

            //test.info("FilterTasksByName");
           // ndm_tasksPage.FilterTasksByName(deleteAssignCert);
           // System.out.println("Filter tasks [OK]");
           // test.pass("FilterTasksByName");

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