package Pages;

import org.ini4j.Registry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by 1 on 2/28/2017.
 * Updated 8/01/2017
 * Updated 8/29/2017
 * Updated 9/19/2017
 * Updated 10/2/2017
 */

public class NDM_GroupsPage extends NDM_Page
{
    public NDM_GroupsPage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public void CreateNewFolder(String folderName) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'icon-add_folder')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[contains(@class,'icon-add_folder')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/input[@ng-model='addFolder.inputName']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[@ng-model='addFolder.inputName']")).get(0).sendKeys(folderName);

        Thread.sleep(450);

        driver.findElements(By.xpath("//button[@ng-click='addFolder.confirm()']")).get(0).click();

        Thread.sleep(200);
    }

    public void CreateFixedView(String folderName, String ipAdress) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[@title='Add group']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        try
        {
            driver.findElements(By.xpath("//div/span[@title='Add group']")).get(0).click();
        }
        catch (Exception ex)
        {
            Thread.sleep(3000);

            driver.findElements(By.xpath("//div/span[@title='Add group']")).get(0).click();
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//ul/li[contains(@ng-click,'addFixedView')]/a")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/input[@ng-model='fixed.data.name']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[@ng-model='fixed.data.name']")).get(0).sendKeys(folderName);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button/i/..")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//li/a[text()='" + "Advanced Search" + "']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//form/div/button[contains(@ng-click,'clear')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//form/div/button[contains(@ng-click,'clear')]")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/span")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/input[@type='search']")).get(0).sendKeys("IP address");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span/div")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@class,'modal-body-content')]/div/div/div/div/span")).get(1).click();

        Thread.sleep(200);

        //driver.findElements(By.xpath("//div/input[@type='search']")).get(1).sendKeys("Contains");
        driver.findElements(By.xpath("//div/input[@type='search']")).get(1).sendKeys("Single");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span/div")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/input")).get(4).sendKeys(ipAdress);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@class='modal-footer']/button[contains(@ng-click,'confirm()')]")).get(0).click();

        Thread.sleep(250);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div/input[@ng-checked='grid.selection.selectAll']/..")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(450);

        driver.findElements(By.xpath("//div/div/input[@ng-checked='grid.selection.selectAll']/..")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/button[@ng-click='nav.addFixedView()']")).get(0).click();

        Thread.sleep(200);
    }

    public void CreateDynamicView(String folderName, String realFolderName, String ipAdress) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div[contains(@ng-click,'home')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[contains(@ng-click,'home')]")).get(0).click();

        //((JavascriptExecutor) driver).executeScript("angular.element('.ndm-views-side-container .list-item').click();");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span[@title='Add group']")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//ul/li[contains(@ng-click,'addDynamicView')]/a")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@id='locationDropdown']/div")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@id='locationDropdown']/div")).get(0).click();

        Thread.sleep(450);

        driver.findElements(By.xpath("//span[text()='" + realFolderName + "' and @ng-bind='root.name']")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/input[contains(@ng-change,'setName()')]")).get(0).sendKeys(folderName);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[contains(@ng-repeat,'dynamic.data.constraint.filters')]/div/div/div/span")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div/div/input[@ng-model='$select.search']")).get(0).sendKeys("IP a");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/span/div")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[contains(@ng-repeat,'dynamic.data.constraint.filters')]/div/div/div/span")).get(1).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//span/div[text()='Single IP']")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/input[contains(@ng-model,'dynamic.values[fIndex]')]")).get(0).sendKeys(ipAdress);

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div[contains(@class,'dropdown') and contains(@class,'button')]/div[contains(@class,'toggle')]")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//li[contains(@ng-repeat,'dynamic.customizeCol')]/label[contains(@class,'checkbox-label')]/div/input/..")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//li[contains(@ng-repeat,'dynamic.customizeCol')]/label[contains(@class,'checkbox-label')]/div/input/..")).get(0).click();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/button[@ng-click='nav.addDynamicView()']")).get(0).click();

        Thread.sleep(200);
    }

    public int GetFoldersCount(String folderName) throws InterruptedException
    {
        Thread.sleep(900);

        /*try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'icon-views')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        try
        {
            driver.findElements(By.xpath("//div/span[contains(@class,'icon-views')]")).get(0).click();
        }
        catch (Exception ex)
        {
            Thread.sleep(3000);

            driver.findElements(By.xpath("//div/span[contains(@class,'icon-views')]")).get(0).click();
        }*/

        Thread.sleep(450);

        int result = driver.findElements(By.xpath("//span[text()='" + folderName + "']")).size();

        return result;
    }

    public void deleteFolders(String folderName, int number) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//span[text()='" + folderName + "']/../../div/span[contains(@ng-click,'deleteItem')]")).get(number)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//span[text()='" + folderName + "']/../../div/span[contains(@ng-click,'deleteItem')]")).get(number).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'delete') and contains(@ng-click, 'confirm()')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(@ng-click,'delete') and contains(@ng-click, 'confirm()')]")).get(0).click();

        Thread.sleep(200);
    }
}
