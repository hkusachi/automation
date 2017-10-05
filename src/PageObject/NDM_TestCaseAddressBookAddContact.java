package PageObject;

/**
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
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NDM_TestCaseAddressBookAddContact extends NDM_BaseSeleniumTest
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

            test = extentCommon.createTest("AddressBookAddContact", "NDM_TestCaseAddressBookAddContact");

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

            test.info("GoToAddressBook");
            NDM_AddressBook addressBookPage = devicePropertiesPage.GoToAddressBook();
            System.out.println("AddressBook [OK]");
            test.pass("GoToAddressBook");

            test.info("AddContact");
            boolean result = addressBookPage.AddContact(getTempFolder());
            System.out.println("Contact Added [OK]");
            test.pass("AddContact");

            test.info("ExportAllContacts");
            boolean result1 = addressBookPage.ExportAllContacts(getTempFolder(), new String[] {"\"SMB password\"", ",\"UserName1\","});
            System.out.println("Contacts Exported [OK]");
            test.pass("ExportAllContacts");

            if (!result || !result1)
            {
                Assert.assertTrue(false);
            }

            Thread.sleep(5000);

            test.info("GoToGroups");
            addressBookPage.GoToGroups();
            System.out.println("Go to Groups page [OK]");
            test.pass("GoToGroups");

            test.info("AddGroup");
            addressBookPage.AddGroup(NDM_Core.GroupType.Type.Email, "Email");
            System.out.println("Added group [OK]");
            test.pass("AddGroup");

            test.info("AddGroup");
            addressBookPage.AddGroup(NDM_Core.GroupType.Type.FTP, "FTP");
            System.out.println("Added group [OK]");
            test.pass("AddGroup");

            test.info("SubmitGroups");
            boolean result2 = addressBookPage.SubmitUser(getTempFolder());
            System.out.println("Submit group [OK]");
            test.pass("SubmitGroups");

            test.info("ExportAllGroups");
            boolean result3 = addressBookPage.ExportAllGroups(getTempFolder(), new String[] {",\"Email\",", "\"FTP\"", ",\"Internet fax IDs\""});
            System.out.println("Export group [OK]");
            test.pass("ExportAllGroups");

            if (!result2 || !result3)
            {
                Assert.assertTrue(false);
            }

            Thread.sleep(5000);

            test.info("GoToOneTouchKeys");
            addressBookPage.GoToOneTouchKeys();
            System.out.println("Go to OneTouchKeys [OK]");
            test.pass("GoToOneTouchKeys");

            test.info("AddOneTouchKey");
            addressBookPage.AddOneTouchKey(NDM_Core.GroupType.Type.Email, "Email");
            System.out.println("Added OneTouchKey [OK]");
            test.pass("AddOneTouchKey");

            test.info("AddOneTouchKey");
            addressBookPage.AddOneTouchKey(NDM_Core.GroupType.Type.Email, "FTP");
            System.out.println("Added OneTouchKey [OK]");
            test.pass("AddOneTouchKey");

            test.info("SubmitOneTouchKey");
            boolean result4 = addressBookPage.SubmitOneTouchKey(getTempFolder());
            System.out.println("Submit OneTouchKey [OK]");
            test.pass("SubmitOneTouchKey");

            test.info("ExportOneTouchKey");
            boolean result5 = addressBookPage.ExportOneTouchKey(getTempFolder(), new String[] {",\"Email\",", "\"FTP\"", "\"One touch key\","});
            System.out.println("Export OneTouchKey [OK]");
            test.pass("ExportOneTouchKey");

            if (!result4 || !result5)
            {
                Assert.assertTrue(false);
            }

            Thread.sleep(5000);

            test.info("GoToContacts");
            addressBookPage.GoToContacts();
            System.out.println("Go To Contact [OK]");
            test.pass("GoToContacts");

            test.info("ImportUser");
            addressBookPage.ImportUser(NDM_Core.ImportType.Type.Manual, getCpecificKeyWord("AddressBookExportUserManual"));
            System.out.println("Import User [OK]");
            test.pass("ImportUser");

            test.info("ImportUser");
            addressBookPage.ImportUser(NDM_Core.ImportType.Type.Auto, getCpecificKeyWord("AddressBookExportUserAuto"));
            System.out.println("Import User [OK]");
            test.pass("ImportUser");

            test.info("SubmitUser");
            boolean result6 = addressBookPage.SubmitUser(getTempFolder());
            System.out.println("Submit User [OK]");
            test.pass("SubmitUser");

            test.info("ExportAllContacts");
            boolean result7 = addressBookPage.ExportAllContacts(getTempFolder(), new String[] {"\"SMB password\"", ",\"UserName2\",", ",\"UserName3\","});
            System.out.println("Export All Contacts [OK]");
            test.pass("ExportAllContacts");

            if (!result6 || !result7)
            {
                Assert.assertTrue(false);
            }

            Thread.sleep(5000);

            test.info("GoToGroups");
            addressBookPage.GoToGroups();
            System.out.println("Go To Groups [OK]");
            test.pass("GoToGroups");

            test.info("ImportGroup");
            addressBookPage.ImportGroup(NDM_Core.ImportType.Type.Manual, getCpecificKeyWord("AddressBookExportGroupManual"));
            System.out.println("Import Groups [OK]");
            test.pass("ImportGroup");

            test.info("ImportGroup");
            addressBookPage.ImportGroup(NDM_Core.ImportType.Type.Auto, getCpecificKeyWord("AddressBookExportGroupAuto"));
            System.out.println("Import Groups [OK]");
            test.pass("ImportGroup");

            test.info("SubmitUser");
            boolean result8 = addressBookPage.SubmitUser(getTempFolder());
            System.out.println("Submit User [OK]");
            test.pass("SubmitUser");

            test.info("ExportAllGroups");
            boolean result9 = addressBookPage.ExportAllGroups(getTempFolder(), new String[] {",\"Internet fax IDs\"", "\"FTP1\"", "\"Email1\""});
            System.out.println("Export All Groups [OK]");
            test.pass("ExportAllGroups");

            if (!result8 || !result9)
            {
                Assert.assertTrue(false);
            }

            Thread.sleep(5000);

            test.info("GoToOneTouchKeys");
            addressBookPage.GoToOneTouchKeys();
            System.out.println("Go To OneTouchKeys [OK]");
            test.pass("GoToOneTouchKeys");

            test.info("ImportOneTouchKey");
            addressBookPage.ImportOneTouchKey(NDM_Core.ImportType.Type.Manual, getCpecificKeyWord("AddressBookExportOneTouchKeyManual"));
            System.out.println("Import OneTouchKeys [OK]");
            test.pass("ImportOneTouchKey");

            test.info("ImportOneTouchKey");
            addressBookPage.ImportOneTouchKey(NDM_Core.ImportType.Type.Auto, getCpecificKeyWord("AddressBookExportOneTouchKeyAuto"));
            System.out.println("Import OneTouchKeys [OK]");
            test.pass("ImportOneTouchKey");

            test.info("SubmitUser");
            boolean result10 = addressBookPage.SubmitUser(getTempFolder());
            System.out.println("Submit OneTouchKeys [OK]");
            test.pass("SubmitUser");

            test.info("ExportOneTouchKey");
            boolean result11 = addressBookPage.ExportOneTouchKey(getTempFolder(), new String[] {"\"One touch key\",", "\"FTP2\"", "\"Email2\""});
            System.out.println("Export OneTouchKeys [OK]");
            test.pass("ExportOneTouchKey");

            if (!result10 || !result11)
            {
                Assert.assertTrue(false);
            }

            Thread.sleep(5000);
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