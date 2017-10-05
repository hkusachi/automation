package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.InterruptedIOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 1 on 4/20/2016.
 * Updated 7/28/2017
 * Updated 8/18/2017
 * Updated 8/29/2017
 * Updated 9/1/2017
 * Updated 9/12/2017
 * Updated 9/13/2017
 * Updated 9/15/2017
 * Updated 9/29/2017
 */

public class NDM_HomePage extends NDM_Page
{
    private By tasksPage = By.xpath("//div[@ui-sref='main.nav.tasks']");
    private By waitElement = By.xpath("//div[@ui-sref='main.devices.tasks.active']");
    private By filterButton = By.xpath("//div[@id='filterlist']");
    private By filterInput = By.xpath("//div/input[@id='searchDeviceText']");

    public NDM_HomePage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public NDM_TasksPage GoToTasksPage() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(tasksPage).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(tasksPage).get(0).click();

        return new NDM_TasksPage(driver, pageUrl);
    }

    public void FilterDevicesBy(String filter, String name) throws InterruptedException
    {
        Thread.sleep(1500);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(filterButton).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        driver.findElements(filterButton).get(0).click();

        Thread.sleep(500);

        driver.findElements(By.xpath("//li/a[text()='" + filter + "']")).get(0).click();

        Thread.sleep(500);

        driver.findElements(filterInput).get(0).sendKeys(name);

        Thread.sleep(1500);

        driver.findElements(By.xpath("//div[@class='input-group-btn']/button")).get(0).click();

        Thread.sleep(3000);
    }

    public void SelectDevice(int deviceItemNumber) throws InterruptedException
    {
        Thread.sleep(650);

        driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div[" + deviceItemNumber + "]/div/div[1]/div/div/div")).get(0).click();

        Thread.sleep(200);
    }

    public NDM_DevicePropertiesPage GoToDevicePropertiesPage(int deviceItemNumber) throws InterruptedException
    {
        Thread.sleep(600);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div[" + deviceItemNumber + "]/div/div/div/div[@uib-tooltip='Device Properties']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        //driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div[" + deviceItemNumber + "]/div/div[3]/div/div")).get(0).click();
        //driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div[" + deviceItemNumber + "]/div/div[3]/div/div")).get(0).click();
        driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div[" + deviceItemNumber + "]/div/div/div/div[@uib-tooltip='Device Properties']")).get(0).click();
        //    "//div[@class='ui-grid-canvas']/div[" + deviceItemNumber + "]/div/div/div/div[@uib-tooltip='Device Properties']"

        Thread.sleep(200);

        return new NDM_DevicePropertiesPage(driver, pageUrl);
    }

    public String RestartDeviceImmidietly(NDM_Core.Restart.Type restartType) throws InterruptedException
    {
        Thread.sleep(5000);

        driver.findElements(By.xpath("//span[@class='icon-create-task']")).get(0).click();

        Thread.sleep(1000);

        driver.findElements(By.xpath("//div[contains(@ng-click,'restartDevice')]")).get(0).click();

        Thread.sleep(5000);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(1500);

        if (restartType == NDM_Core.Restart.Type.TypeNetwork)
        {
            driver.findElements(By.xpath("//input[@value='RestartNetwork']/..")).get(0).click();

            Thread.sleep(1500);
        }

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(1500);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(1500);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(1500);

        String taskName = driver.findElements(By.xpath("//div/div/div/div/label[contains(text(),'Name')]/../../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(1500);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.confirm()')]")).get(0).click();

        Thread.sleep(1500);

        return taskName.trim();
    }

    public String RestartDevice() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//span[@class='icon-create-task']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//span[@class='icon-create-task']")).get(0).click();

        Thread.sleep(250);

        driver.findElements(By.xpath("//div[contains(@ng-click,'restartDevice')]")).get(0).click();

        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(600);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(600);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(250);

        driver.findElements(By.xpath("//input[@value='Scheduled']/..")).get(0).click();

        Thread.sleep(200);

        /* +3 min */
        driver.findElements(By.xpath("//a[contains(@ng-click,'incrementMinutes()')]/span")).get(0).click();
        Thread.sleep(200);
        driver.findElements(By.xpath("//a[contains(@ng-click,'incrementMinutes()')]/span")).get(0).click();
        Thread.sleep(200);
        driver.findElements(By.xpath("//a[contains(@ng-click,'incrementMinutes()')]/span")).get(0).click();
        Thread.sleep(250);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(200);

        long unixTime = System.currentTimeMillis() / 1000L;
        String uniqueNameFromUnixTime = "_" + unixTime;

        driver.findElements(By.xpath("//input[@name='name']")).get(0).sendKeys(uniqueNameFromUnixTime);

        Thread.sleep(250);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(200);

        String taskName = driver.findElements(By.xpath("//div/div/div/div/label[contains(text(),'Name')]/../../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.confirm()')]")).get(0).click();

        Thread.sleep(250);

        return taskName.trim();
    }

    public String FirmwareUpgrade(String firmWarePath) throws InterruptedException, AWTException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//span[@class='icon-create-task']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//span[@class='icon-create-task']")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//span[@class='icon-firmware_upgrade']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(900);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(900);

        StringSelection selection = new StringSelection(firmWarePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//span[text()='Click here to select a file to upload']")).get(0))
                .release()
                .build();

        myAction.perform();

        Thread.sleep(800);

        Robot robot = new Robot();

        robot.delay(600);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(600);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(900);

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[text()='Upload']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[text()='Next' and not(@disabled='disabled')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(700);

        try
        {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElements(By.xpath("//button[@ng-click='confirm()']")).get(0).click();
            Thread.sleep(400);
        }
        catch (Exception exxx)
        {}

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        Thread.sleep(1500);

        driver.findElements(By.xpath("//input[@value='Scheduled']/..")).get(0).click();

        Thread.sleep(200);

        /* +3 min */
        driver.findElements(By.xpath("//a[contains(@ng-click,'incrementMinutes()')]/span")).get(0).click();
        Thread.sleep(200);
        driver.findElements(By.xpath("//a[contains(@ng-click,'incrementMinutes()')]/span")).get(0).click();
        Thread.sleep(200);
        driver.findElements(By.xpath("//a[contains(@ng-click,'incrementMinutes()')]/span")).get(0).click();
        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(200);

        long unixTime = System.currentTimeMillis() / 1000L;
        String uniqueNameFromUnixTime = "_" + unixTime;

        driver.findElements(By.xpath("//input[@name='name']")).get(0).sendKeys(uniqueNameFromUnixTime);

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(400);

        String taskName = driver.findElements(By.xpath("//div/div/div/div/label[contains(text(),'Name')]/../../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//label/input[@type='checkbox' and @ng-model='wizard.form.disclaimer']/..")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.confirm()')]")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public void AddDevicesBy(String searchItem, String searchValue) throws InterruptedException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div[@class='dropdown']/div/span[contains(@class,'discover')]")).get(0)));
        }
        catch(Exception ex1)
        {
            Thread.sleep(6000);
        }

        driver.findElements(By.xpath("//div/div[@class='dropdown']/div/span[contains(@class,'discover')]")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/ul/li[contains(@ng-click,'addDevices')]/a")).get(0).click();

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[@ng-click='dialog.customBtn_1()']")).get(0)));
        }
        catch(Exception ex2)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[@ng-click='dialog.customBtn_1()']")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//div/div/span/span/span/span[contains(@class,'close')]")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//input[@ng-click='$select.activate()']")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//div/span/div[contains(text(),'" + searchItem + "')]")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[contains(@ng-model,'dialog.data.ipAndHostnames')]")).get(0).sendKeys(searchValue);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div/span/i")).get(0).click();

        Thread.sleep(600);

        driver.findElements(By.xpath("//button[contains(@ng-click,'dialog.confirm()')]")).get(0).click();

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 220);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div/div[@id='progress-discovery']/span[@class='ng-binding' and contains(text(),'100%')]")).get(0)));
        }
        catch(Exception ex3)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(100);

        String discoverResult = driver.findElements(By.xpath("//button[@type='button']/../strong")).get(0).getAttribute("innerHTML");

        Thread.sleep(20);

        driver.findElements(By.xpath("//button[@ng-click='dialog.close()']")).get(0).click();

        Thread.sleep(20);
    }

    public void AddDevicesByImportingList(String csvFilePath) throws InterruptedException, AWTException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div/div[@class='dropdown']/div/span[contains(@class,'discover')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/div[@class='dropdown']/div/span[contains(@class,'discover')]")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/ul/li[contains(@ng-click,'addDevices')]/a")).get(0).click();

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[@ng-click='dialog.customBtn_1()']")).get(0)));
        }
        catch(Exception ex2)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[@ng-click='dialog.customBtn_1()']")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//div/div/span/span/span/span[contains(@class,'close')]")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//input[@ng-click='$select.activate()']")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//span/div[contains(text(),'" + "By importing a list" + "')]")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div/span[contains(@class,'dd-header-text')]/i/..")).get(0).click();

        Thread.sleep(600);

        StringSelection selection = new StringSelection(csvFilePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//div/ng-include/div/div/div/div/span")).get(0))
                .release()
                .build();

        myAction.perform();

        Thread.sleep(800);

        Robot robot = new Robot();

        robot.delay(600);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(600);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(900);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div/button[contains(text(),'Upload file')]")).get(0).click();

        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'dialog.confirm()')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(@ng-click,'dialog.confirm()')]")).get(0).click();

        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 220);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div/div[@id='progress-discovery']/span[@class='ng-binding' and contains(text(),'100%')]")).get(0)));
        }
        catch(Exception ex3)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(100);

        String discoverResult = driver.findElements(By.xpath("//button[@type='button']/../strong")).get(0).getAttribute("innerHTML");

        Thread.sleep(20);

        driver.findElements(By.xpath("//button[@ng-click='dialog.close()']")).get(0).click();

        Thread.sleep(20);
    }

    public void RemoveFilter() throws InterruptedException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div/span[contains(@ng-click,'deleteAdvancedSearch')]")).get(0).click();

        Thread.sleep(200);
    }

    public void FilterDevicesBySymbol(String filter, String name) throws InterruptedException
    {
        Thread.sleep(100);

        driver.findElements(filterButton).get(0).click();

        Thread.sleep(500);

        driver.findElements(By.xpath("//li/a[text()='" + filter + "']")).get(0).click();

        Thread.sleep(500);

        for(int i = 0; i < name.length(); ++i)
        {
            Thread.sleep(500);

            driver.findElements(filterInput).get(0).sendKeys("" + name.charAt(i));

            Thread.sleep(500);
        }

        Thread.sleep(1500);

        driver.findElements(filterInput).get(0).sendKeys(" ");

        Thread.sleep(3000);
    }

    public boolean CheckDeviceIn(String ipAdress, int deviceItemNumber) throws InterruptedException
    {
        Thread.sleep(200);

        String status = "OK_end";

        try
        {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement notNullElement = driver.findElements(By.xpath("//div[contains(text(),'"+ ipAdress +"')]")).get(0);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        }
        catch (Exception ex)
        {
            status = "ERROR_end";
        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        if (status.equals("OK_end"))
        {
            return true;
        }

        return false;
    }

    public void AdvancedSearch(String filter, String name) throws InterruptedException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//div/button/i/..")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button/i/..")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//li/a[text()='" + "Advanced Search" + "']")).get(0).click();

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//form/div/button[contains(@ng-click,'clear')]")).get(0)));
        }
        catch(Exception ex1)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(100);

        driver.findElements(By.xpath("//form/div/button[contains(@ng-click,'clear')]")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//div/div/span")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[@type='search']")).get(0).sendKeys("IP address");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span/div")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@class,'modal-body-content')]/div/div/div/div/span")).get(1).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[@type='search']")).get(1).sendKeys("Single");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span/div")).get(0).click();

        Thread.sleep(100);

        driver.findElements(By.xpath("//div/div/input")).get(4).sendKeys(name);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@class='modal-footer']/button[contains(@ng-click,'confirm()')]")).get(0).click();

        Thread.sleep(200);
    }

    public NDM_TasksPage goToTasksFromProcessing() throws InterruptedException
    {
        Thread.sleep(2000);

        driver.findElements(By.xpath("//button[contains(text(),'Go to tasks')]")).get(0).click();

        Thread.sleep(2000);

        return new NDM_TasksPage(driver, pageUrl);
    }

    public void GoToSystem() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//span[contains(@class,'icon-default_preference')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//span[contains(@class,'icon-default_preference')]")).get(0).click();

        Thread.sleep(4000);

        for(int i = 0; i< 10000; ++i)
        {
            Thread.sleep(200);

            int aaa = 0 + (int)(Math.random() * ((3 - 0) + 1)); /* 0 - 3 */
            int bbb = 0 + (int)(Math.random() * ((5 - 0) + 1)); /* 0 - 5 */

            if (aaa == 3)
            {
                bbb = 0 + (int)(Math.random() * ((7 - 0) + 1));
            }

            System.out.println("aaa=" + aaa + " bbb=" + bbb + "");

            WebElement draggable = driver.findElements(By.xpath("//span[@class='irs']/span[@class='irs-single']")).get(aaa);
            Thread.sleep(50);
            if (bbb > 5)
            {
                aaa = 0;
            }
            WebElement target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-" + bbb + "')]")).get(aaa);
            Thread.sleep(50);
            new Actions(driver).dragAndDrop(draggable, target).perform();
        }




        /*WebElement draggable = driver.findElements(By.xpath("//span[@class='irs']/span[@class='irs-single']")).get(0);
        Thread.sleep(200);
        WebElement target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-2')]")).get(0);
        Thread.sleep(200);
        new Actions(driver).dragAndDrop(draggable, target).perform();

        Thread.sleep(7000);

        target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-2')]")).get(0);
        Thread.sleep(200);
        new Actions(driver).dragAndDrop(draggable, target).perform();
        Thread.sleep(200);

        Thread.sleep(7000);

        target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-4')]")).get(0);
        Thread.sleep(200);
        new Actions(driver).dragAndDrop(draggable, target).perform();
        Thread.sleep(200);

        Thread.sleep(7000);

        target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-2')]")).get(0);
        Thread.sleep(200);
        new Actions(driver).dragAndDrop(draggable, target).perform();
        Thread.sleep(200);

        Thread.sleep(7000);

        draggable = driver.findElements(By.xpath("//span[@class='irs']/span[@class='irs-single']")).get(2);
        Thread.sleep(200);
        target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-2')]")).get(2);
        Thread.sleep(200);
        new Actions(driver).dragAndDrop(draggable, target).perform();
        Thread.sleep(200);

        Thread.sleep(7000);

        draggable = driver.findElements(By.xpath("//span[@class='irs']/span[@class='irs-single']")).get(2);
        Thread.sleep(200);
        target = driver.findElements(By.xpath("//span[@class='irs-grid']/span[contains(@class,'js-grid-text-4')]")).get(2);
        Thread.sleep(200);
        new Actions(driver).dragAndDrop(draggable, target).perform();
        Thread.sleep(200);*/
    }

    public NDM_GroupsPage ShowGroups() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'icon-views')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[contains(@class,'icon-views')]")).get(0).click();

        Thread.sleep(200);

        return new NDM_GroupsPage(driver, pageUrl);
    }

    public void QuickSearch(String ipAddress) throws InterruptedException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//ul/input[@type='search']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//ul/input[@type='search']")).get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));

        Thread.sleep(200);

        driver.findElements(By.xpath("//ul/input[@type='search']")).get(0).sendKeys(Keys.BACK_SPACE);

        Thread.sleep(200);

        driver.findElements(By.xpath("//ul/input[@type='search']")).get(0).sendKeys(ipAddress);

        Thread.sleep(200);

        driver.findElements(By.xpath("//ul/input[@type='search']")).get(0).sendKeys(Keys.ENTER);

        Thread.sleep(200);
    }
}
