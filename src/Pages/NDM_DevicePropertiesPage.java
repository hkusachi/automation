package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by 1 on 4/20/2016.
 * Updated 7/28/2017
 * Updated 9/1/2017
 * Updated 9/5/2017
 * Updated 9/8/2017
 * Updated 9/29/2017
 */

public class NDM_DevicePropertiesPage extends NDM_Page
{
    public NDM_DevicePropertiesPage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public String RestartDevice(NDM_Core.Restart.Type restartType) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[contains(@ng-click,'restartOneDevice')]/div")).get(0)));
        }
        catch(Exception ex1)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(600);

        driver.findElements(By.xpath("//div[contains(@ng-click,'restartOneDevice')]/div")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0)));
        }
        catch(Exception ex1)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(300);

        if (restartType == NDM_Core.Restart.Type.TypeNetwork)
        {
            try
            {
                driver.findElements(By.xpath("//input[@value='RestartNetwork']/..")).get(0).click();
            }
            catch(Exception eex)
            {
                Thread.sleep(10000);

                driver.findElements(By.xpath("//input[@value='RestartNetwork']/..")).get(0).click();
            }

            Thread.sleep(250);
        }

        Thread.sleep(200);

        try
        {
            driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();
        }
        catch(Exception ex)
        {
            Thread.sleep(10000);

            driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();
        }

        Thread.sleep(400);

        String taskName = driver.findElements(By.xpath("//div/div/label[contains(text(),'Name')]/../../div")).get(1).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(@ng-click,'wizard.confirm()')]")).get(0).click();

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public Boolean waitingAndRefreshState(int minutesWait, int intervalSecond, String state) throws InterruptedException
    {
        Thread.sleep(6000);
        long StartUnixTime = System.currentTimeMillis() / 1000L;
        String status = "";

        while(StartUnixTime + minutesWait * 60L > System.currentTimeMillis() / 1000L)
        {
            Thread.sleep(intervalSecond * 1000);

            System.out.println("waiting... " +  (((StartUnixTime + minutesWait * 60L) - (System.currentTimeMillis() / 1000L)) / 60) + " min.");

            Thread.sleep(6000);

            status = driver.findElements(By.xpath("//tbody/tr/td[2]")).get(0).getAttribute("innerHTML");

            if (status.equals(state))
            {
                driver.findElements(By.xpath("//button[contains(@ng-click,'dialog.cancel()')]")).get(0).click();

                Thread.sleep(500);

                return true;
            }
        }

        return false;
    }

    public NDM_HomePage GoToHomePage() throws InterruptedException
    {
        Thread.sleep(400);

        driver.findElements(By.xpath("//div/span[contains(@class,'icon-back')]")).get(0).click();

        Thread.sleep(200);

        return new NDM_HomePage(driver, pageUrl);
    }

    public void GoToManagement() throws InterruptedException
    {
        Thread.sleep(400);

        driver.findElements(By.xpath("//span[text()='Management']")).get(0).click();

        Thread.sleep(200);
    }

    public String InstallHyPasApp(String filePath) throws InterruptedException, AWTException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[@class='icon-install toolbar-icons']/../div")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(600);

        try
        {
            driver.findElements(By.xpath("//div/span[@class='icon-install toolbar-icons']/../div")).get(0).click();
        }
        catch (Exception exx1)
        {
            Thread.sleep(3500);

            driver.findElements(By.xpath("//div/span[@class='icon-install toolbar-icons']/../div")).get(0).click();
        }

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@class='dz-default dz-message']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        StringSelection selection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//div[@class='dz-default dz-message']")).get(0))
                .release()
                .build();

        myAction.perform();

        Thread.sleep(200);

        Robot robot = new Robot();

        robot.delay(400);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(200);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(400);

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[text()='Upload File']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();

        Thread.sleep(400);

        String taskName = driver.findElements(By.xpath("//div/label[text()='Task name']/../../span")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Install')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public void WaitingRefreshing() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div/div/div/div[@aria-hidden='true']/../div/div/div/span[contains(@class,'icon-restart')]")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);
    }

    public void SelectPackege(String hyPasPackageName) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='" + hyPasPackageName + "']/../../..")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[text()='" + hyPasPackageName + "']/../../..")).get(0).click();

        Thread.sleep(200);
    }

    public String ActivateHyPas(String aKey) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'enable_task')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[contains(@class,'enable_task')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//label/div/input[contains(@ng-value,'LICENSE_TYPE.official')]/..")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//label/div/input[contains(@ng-value,'LICENSE_TYPE.official')]/..")).get(0).click();

        Thread.sleep(200);

        if (aKey != null)
        {
            driver.findElements(By.xpath("//div[@ng-model='device.licenseKey']/div/span/span[1]")).get(0).click();
            Thread.sleep(1500);

            driver.findElements(By.xpath("//div[@ng-model='device.licenseKey']/input")).get(0).sendKeys(aKey);
            Thread.sleep(1500);
        }

        driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();

        Thread.sleep(450);

        try
        {
            driver.findElements(By.xpath("//button[@ng-click='confirm()']")).get(0).click();
        }
        catch (Exception exx2)
        {}

        Thread.sleep(200);

        String taskName = driver.findElements(By.xpath("//div/label[text()='Task name']/../../span")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Activate')]")).get(0).click();

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public String DeactivateHyPas() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'disable_task')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(550);

        driver.findElements(By.xpath("//div/span[contains(@class,'disable_task')]")).get(0).click();

        Thread.sleep(850);

        try
        {
            driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();
        }
        catch (Exception ex)
        {
            Thread.sleep(1500);

            driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();
        }

        Thread.sleep(450);

        String taskName = driver.findElements(By.xpath("//div/label[text()='Task name']/../../span")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Deactivate')]")).get(0).click();

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public String UninstallHyPas() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'disable_task')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[contains(@class,'disable_task')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//label/div/input/..")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//label/div/input/..")).get(0).click();

        Thread.sleep(450);

        driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();

        Thread.sleep(450);

        String taskName = driver.findElements(By.xpath("//div/label[text()='Task name']/../../span")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Deactivate')]")).get(0).click();

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public NDM_CertPage GoToCertManagement() throws InterruptedException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[@ui-sref='main.nav.management.certificate']/span")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div[contains(@class,'ndm-icon-enabled')]/span[contains(@class,'icon-install')]")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        return new NDM_CertPage(driver, pageUrl);
    }

    public NDM_OptionalFunctionPage GoToOptionalFunction() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div[@ui-sref='main.nav.management.optional']/span")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[@ui-sref='main.nav.management.optional']/span")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        return new NDM_OptionalFunctionPage(driver, pageUrl);
    }

    public NDM_AddressBook GoToAddressBook() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[contains(@ui-sref,'nav.address-book')]/span")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@ui-sref,'nav.address-book')]/span")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        return new NDM_AddressBook(driver, pageUrl);
    }
}
