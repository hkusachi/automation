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

/**
 * Created by 1 on 2/22/2017.
 * Updated 9/8/2017
 */

public class NDM_OptionalFunctionPage extends NDM_Page
{
    public NDM_OptionalFunctionPage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public void SelectFunction(String functionName) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[text()='" + functionName + "']/../../..")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[text()='" + functionName + "']/../../..")).get(0).click();

        Thread.sleep(200);
    }

    public void WaitingRefreshing() throws InterruptedException
    {
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
    }

    public String ActivateFunctionWithKey(String key) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'icon-enable_task')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[contains(@class,'icon-enable_task')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[@ng-click='next()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        if (key != "")
        {
            Thread.sleep(200);

            driver.findElements(By.xpath("//label/div/input[@ng-value='$currentModel.LICENSE_TYPE.official']/..")).get(0).click();

            Thread.sleep(450);

            driver.findElements(By.xpath("//div[contains(@placeholder,'license key')]/span/span[1]")).get(0).click();

            Thread.sleep(450);

            driver.findElements(By.xpath("//div[contains(@placeholder,'license key')]/span/span[1]/../../../input[1]")).get(0).sendKeys(key);
        }

        Thread.sleep(250);

        driver.findElements(By.xpath("//button[@ng-click='next()']")).get(0).click();

        Thread.sleep(200);

        String result = driver.findElements(By.xpath("//div/div/label[text()='Task name']/../../span")).get(0).getAttribute("innerHTML");

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Activate')]")).get(0).click();

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

        driver.findElements(By.xpath("//button[@ng-click='cancel()']")).get(0).click();

        Thread.sleep(200);

        return result.trim();
    }
}
