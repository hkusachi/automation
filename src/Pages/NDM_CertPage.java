package Pages;

//import org.ini4j.Registry;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by 1 on 2/20/2017.
 * Updated 7/28/2017
 * Updated 9/1/2017
 * Updated 9/15/2017
 */

public class NDM_CertPage extends NDM_Page
{
    public NDM_CertPage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public void SelectCert(String certName) throws InterruptedException
    {
        Thread.sleep(450);

        driver.findElements(By.xpath("//div[text()='" + certName + "']/../../..")).get(0).click();

        Thread.sleep(200);
    }

    public void WaitingRefreshing() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            //wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[contains(@class,'ng-hide')]/div/span")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);
    }

    public String InstallCert(String filePath) throws InterruptedException, AWTException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div[contains(@class,'ndm-icon-enabled')]/span[contains(@class,'icon-install')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[contains(@class,'ndm-icon-enabled')]/span[contains(@class,'icon-install')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@class='dz-default dz-message']")).get(0)));
        }
        catch(Exception ex1)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(600);

        StringSelection selection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//div[@class='dz-default dz-message']")).get(0))
                .release()
                .build();

        myAction.perform();

        Thread.sleep(8000);

        Robot robot = new Robot();

        robot.delay(6000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(6000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(9000);

        Thread.sleep(2000);

        driver.findElements(By.xpath("//button[text()='Upload file']")).get(0).click();

        Thread.sleep(2000);

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

        Thread.sleep(200);

        String taskName = driver.findElements(By.xpath("//div[@class='modal-body']/div/div/div/div[contains(text(),'Name')]/../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Import')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 350);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(@ng-click,'dialog.cancel')]/span")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public String AssignCert(String[] certProtocols) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@ng-click='vm.assignProtocols()']/span")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        driver.findElements(By.xpath("//div[@ng-click='vm.assignProtocols()']/span")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/input[@type='search' and contains(@ng-click,'select.activate()')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[@type='search' and contains(@ng-click,'select.activate()')]")).get(0).click();

        Thread.sleep(450);

        for (int i = 0; i < certProtocols.length; ++i)
        {
            Thread.sleep(200);

            driver.findElements(By.xpath("//ul/li/div/span/div[text()='" + certProtocols[i] + "']")).get(0).click();

            Thread.sleep(200);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();

        Thread.sleep(450);

        String taskName = driver.findElements(By.xpath("//div[@class='modal-body']/div/div/div/div[contains(text(),'Name')]/../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Assign')]")).get(0).click();

        Thread.sleep(1500);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 350);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(@ng-click,'dialog.cancel')]/span")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public String DeleteCert() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'delete_views')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[contains(@class,'delete_views')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(@ng-click,'wizard.currentStep + 1')]")).get(0).click();

        Thread.sleep(450);

        String taskName = driver.findElements(By.xpath("//div[@class='modal-body']/div/div/div/div[contains(text(),'Name')]/../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Delete')]")).get(0).click();

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 350);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(@ng-click,'dialog.cancel')]/span")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }

    public String DeleteAssignCert(String[] certProtocols) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@ng-click='vm.assignProtocols()']/span")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@ng-click='vm.assignProtocols()']/span")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        for (int i = 0; i < certProtocols.length; ++i)
        {
            Thread.sleep(450);

            driver.findElements(By.xpath("//span/span/span[text()='" + certProtocols[i] + "']/../../span[1]")).get(0).click();

            Thread.sleep(100);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Next')]")).get(0).click();

        Thread.sleep(200);

        String taskName = driver.findElements(By.xpath("//div[@class='modal-body']/div/div/div/div[contains(text(),'Name')]/../div[2]")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(text(),'Assign')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 350);
            wait1.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='Succeeded']")).get(0)));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[contains(@ng-click,'dialog.cancel')]/span")).get(0).click();

        Thread.sleep(200);

        return taskName.trim();
    }
}
