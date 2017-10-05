package PageObject;

/**
 * Created 2/28/2017.
 * Updated 4/3/2017
 * Updated 4/14/2017
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
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NDM_TestCase6728Views extends NDM_BaseSeleniumTest
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

            test = extentCommon.createTest("6728Views", "NDM_TestCase6728Views");

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

            test.info("ShowGroups");
            NDM_GroupsPage ndmGroupsPage = home.ShowGroups();
            System.out.println("Group Page opened [OK]");
            test.pass("ShowGroups");

            String folderName = DateTimeFormatter.ofPattern("MMddyyyy").format(LocalDate.now());

            test.info("ShowGroups");
            ndmGroupsPage.CreateNewFolder(folderName);
            System.out.println("Folder created [OK]");
            test.pass("ShowGroups");

            test.info("CreateFixedView");
            ndmGroupsPage.CreateFixedView(folderName, getIpAddress());
            System.out.println("Fixed view created [OK]");
            test.pass("CreateFixedView");

            test.info("CreateDynamicView");
            ndmGroupsPage.CreateDynamicView(folderName + "_1", folderName, getIpAddress());
            System.out.println("Dynamic view created [OK]");
            test.pass("CreateDynamicView");

            test.info("GetFoldersCount");
            int foldersCount = ndmGroupsPage.GetFoldersCount(folderName);
            System.out.println("Check result [OK]");
            test.pass("GetFoldersCount");

            Assert.assertTrue(foldersCount >= 2);

            test.info("deleteFolders");
            ndmGroupsPage.deleteFolders(folderName + "_1", 0);
            System.out.println("Folder deleted [OK]");
            test.pass("deleteFolders");

            test.info("deleteFolders");
            ndmGroupsPage.deleteFolders(folderName, 1);
            System.out.println("Folder deleted [OK]");
            test.pass("deleteFolders");

            test.info("deleteFolders");
            ndmGroupsPage.deleteFolders(folderName, 0);
            System.out.println("Folder deleted [OK]");
            test.pass("deleteFolders");
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