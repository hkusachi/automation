package Pages;


import static org.apache.commons.io.FileUtils.readFileToString;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by 1 on 2/22/2017.
 */

public class NDM_AddressBook extends NDM_Page
{
    public NDM_AddressBook(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }


    public Boolean AddContact(File tempFolder) throws InterruptedException, IOException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-add-contact')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(600);

        driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-add-contact')]")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@class='input-group']/input[contains(@ng-model,'.displayName') and not(contains(@name,'displayNameKana'))]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@class='input-group']/input[contains(@ng-model,'.displayName') and not(contains(@name,'displayNameKana'))]")).get(0).sendKeys("UserName1");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@class='input-group']/input[@ng-model='$formModel.mail']")).get(0).sendKeys("username1@adr.tpr");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[@class='input-group']/input[@ng-model='$formModel.ftpHostname']")).get(0).sendKeys("username1ftp");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@class,'modal-footer')]/button[@ng-click='save()']")).get(0).click();

        Thread.sleep(600);

        driver.findElements(By.xpath("//div[contains(@class,'open-submit-panel-button')]/button")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        String result = driver.findElements(By.xpath("//div/div/label[contains(text(),'Task name')]/../../span")).get(0).getAttribute("innerHTML").trim();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0).click();

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

        driver.findElements(By.xpath("//div/button[@ng-click='specButton.click()']")).get(0).click();

        Thread.sleep(200);


        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        return csvContains.contains("\"" + result + "\",\"Succeeded\"");
    }

    public boolean ExportAllContacts(File tempFolder, String[] resultArray) throws InterruptedException, IOException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div[@class='checkbox-container']/input/..")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-export-contact')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        Thread.sleep(50);

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        Thread.sleep(50);

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(50);

        boolean result = true;

        for (int k = 0; k < resultArray.length; ++k)
        {
            if (!csvContains.contains(resultArray[k]))
            {
                result = false;

                break;
            }
        }

        return result;
    }

    public void GoToGroups() throws InterruptedException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@class,'ndm-secondary-nav-container')]/div[contains(@ng-class,'groups')]")).get(0).click();

        Thread.sleep(1200);
    }

    public void GoToContacts() throws InterruptedException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@class,'ndm-secondary-nav-container')]/div[contains(@ng-class,'contacts')]")).get(0).click();

        Thread.sleep(1200);
    }

    public void GoToOneTouchKeys() throws InterruptedException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div[contains(@class,'ndm-secondary-nav-container')]/div[contains(@ng-class,'keys')]")).get(0).click();

        Thread.sleep(1200);
    }

    public void AddOneTouchKey(NDM_Core.GroupType.Type goupType, String gruopName) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div/span[contains(@class,'ts-icon-add-contact')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/span[contains(@class,'ts-icon-add-contact')]")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/input[@name='displayName']")).get(0).sendKeys(gruopName);

        Thread.sleep(200);

        if (goupType == NDM_Core.GroupType.Type.FTP)
        {
            driver.findElements(By.xpath("//div[contains(@ng-model,'Contacts.gridApi.grid.')]/div/span/i[@ng-click='$select.toggle($event)']")).get(0).click();

            Thread.sleep(400);

            driver.findElements(By.xpath("//span/div[contains(text(),'FTP')]")).get(0).click();

            Thread.sleep(400);
        }

        driver.findElements(By.xpath("//div[@role='rowgroup']/div[@class='ui-grid-canvas']/div[contains(@ng-repeat,'rowRenderIndex')]")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[@ng-click='save()']")).get(0).click();

        Thread.sleep(200);
    }

    public void AddGroup(NDM_Core.GroupType.Type goupType, String gruopName) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/div/span[contains(@class,'ts-icon-add-group')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/span[contains(@class,'ts-icon-add-group')]")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/input[@name='displayName']")).get(0).sendKeys(gruopName);

        Thread.sleep(200);

        if (goupType == NDM_Core.GroupType.Type.FTP)
        {
            driver.findElements(By.xpath("//div[contains(@ng-model,'Contacts.gridApi.grid.')]/div/span/i[@ng-click='$select.toggle($event)']")).get(0).click();

            Thread.sleep(400);

            driver.findElements(By.xpath("//span/div[contains(text(),'FTP')]")).get(0).click();

            Thread.sleep(400);
        }

        driver.findElements(By.xpath("//button[@ng-click='save()']")).get(0).click();

        Thread.sleep(200);
    }

    public boolean SubmitUser (File tempFolder) throws InterruptedException, IOException
    {
        Thread.sleep(400);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[contains(@class,'open-submit-panel-button')]/button")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        driver.findElements(By.xpath("//div[contains(@class,'open-submit-panel-button')]/button")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        String result = driver.findElements(By.xpath("//div/div/label[contains(text(),'Task name')]/../../span")).get(0).getAttribute("innerHTML").trim();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0).click();

        Thread.sleep(400);

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

        driver.findElements(By.xpath("//div/button[@ng-click='specButton.click()']")).get(0).click();

        Thread.sleep(200);

        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        return csvContains.contains("\"" + result + "\",\"Succeeded\"");
    }

    public boolean SubmitOneTouchKey (File tempFolder) throws InterruptedException, IOException
    {
        Thread.sleep(400);

        driver.findElements(By.xpath("//div[contains(@class,'open-submit-panel-button')]/button")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        String result = driver.findElements(By.xpath("//div/div/label[contains(text(),'Task name')]/../../span")).get(0).getAttribute("innerHTML").trim();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0).click();

        Thread.sleep(400);

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

        driver.findElements(By.xpath("//div/button[@ng-click='specButton.click()']")).get(0).click();

        Thread.sleep(200);

        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        return csvContains.contains("\"" + result + "\",\"Succeeded\"");
    }

    public boolean SubmitGroups(File tempFolder) throws InterruptedException, IOException
    {
        Thread.sleep(400);

        driver.findElements(By.xpath("//div[contains(@class,'open-submit-panel-button')]/button")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='save()']")).get(0).click();

        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        String result = driver.findElements(By.xpath("//div/div/label[contains(text(),'Task name')]/../../span")).get(0).getAttribute("innerHTML").trim();

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='sendWizardData()']")).get(0).click();

        Thread.sleep(400);

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

        driver.findElements(By.xpath("//div/button[@ng-click='specButton.click()']")).get(0).click();

        Thread.sleep(200);


        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(200);

        driver.findElements(By.xpath("//button[contains(text(),'Close')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        return csvContains.contains("\"" + result + "\",\"Succeeded\"");
    }

    public boolean ExportOneTouchKey(File tempFolder, String[] resultArray) throws InterruptedException, IOException
    {
        Thread.sleep(400);

        driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-export-key')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        Thread.sleep(50);

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        Thread.sleep(50);

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(50);

        boolean result = true;

        for (int k = 0; k < resultArray.length; ++k)
        {
            if (!csvContains.contains(resultArray[k]))
            {
                result = false;

                break;
            }
        }

        return result;
    }

    public boolean ExportAllGroups(File tempFolder, String[] resultArray) throws InterruptedException, IOException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div/div/div[@class='checkbox-container']/input/..")).get(0).click();

        Thread.sleep(400);

        driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-export-group')]")).get(0).click();

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ng-hide')]/i[contains(@class,'ndm-processing')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(2000);

        File[] fileArray = tempFolder.listFiles();
        long maxMod = 0;

        int i = 0;

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() > maxMod)
            {
                maxMod = fileArray[i].lastModified();
            }
        }

        for (i = 0; i < fileArray.length; ++i)
        {
            if (fileArray[i].lastModified() == maxMod)
            {
                break;
            }
        }

        Thread.sleep(50);

        String csvFileName = fileArray[i].getAbsoluteFile().toString();

        Thread.sleep(50);

        String csvContains = readFileToString(new File(csvFileName));

        Thread.sleep(50);

        //return csvContains.contains(",\"Email\",") && csvContains.contains("\"FTP\"") && csvContains.contains(",\"Internet fax IDs\"");

        boolean result = true;

        for (int k = 0; k < resultArray.length; ++k)
        {
            if (!csvContains.contains(resultArray[k]))
            {
                result = false;

                break;
            }
        }

        return result;
    }

    public void ImportOneTouchKey(NDM_Core.ImportType.Type importType, String fileName) throws InterruptedException, AWTException
    {
        Thread.sleep(400);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/span[contains(@class,'ts-icon-import-key')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        try
        {
            driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-import-key')]")).get(0).click();
        }
        catch(Exception ex)
        {
            Thread.sleep(4000);

            driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-import-key')]")).get(0).click();
        }

        Thread.sleep(600);

        try
        {
            driver.findElements(By.xpath("//div/button[@ng-click='closeDialog()' and contains(@class,'btn')]")).get(0).click();
        }
        catch(Exception ex)
        {
            Thread.sleep(1000);
        }

        Thread.sleep(900);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/button[@ng-click='model.customButtonHandler(save)']")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }



        if (importType == NDM_Core.ImportType.Type.Auto)
        {
            Thread.sleep(200);

            driver.findElements(By.xpath("//div[@class='radiobutton-container']/input[contains(@ng-value,'Auto')]/..")).get(0).click();
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='model.customButtonHandler(save)']")).get(0).click();

        // "//div/div/span/b"     - click for load file !!!

        //driver.findElements(By.xpath("//div/div/div/span[contains(@class,'dd-header-text')]/i/..")).get(0).click();

        Thread.sleep(600);

        StringSelection selection = new StringSelection(fileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//div/div/span/b")).get(0))
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

        driver.findElements(By.xpath("//button[contains(text(),'Upload File')]")).get(0).click();

        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'save()')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(@ng-click,'save()')]")).get(0).click();

        Thread.sleep(200);
    }

    public void ImportGroup(NDM_Core.ImportType.Type importType, String fileName) throws InterruptedException, AWTException
    {
        Thread.sleep(400);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/span[contains(@class,'ts-icon-import-group')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        try
        {
            driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-import-group')]")).get(0).click();
        }
        catch(Exception ex)
        {
            Thread.sleep(4000);

            driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-import-group')]")).get(0).click();
        }

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/button[@ng-click='model.customButtonHandler(save)']")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }



        if (importType == NDM_Core.ImportType.Type.Auto)
        {
            Thread.sleep(200);

            driver.findElements(By.xpath("//div[@class='radiobutton-container']/input[contains(@ng-value,'Auto')]/..")).get(0).click();
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='model.customButtonHandler(save)']")).get(0).click();

        // "//div/div/span/b"     - click for load file !!!

        //driver.findElements(By.xpath("//div/div/div/span[contains(@class,'dd-header-text')]/i/..")).get(0).click();

        Thread.sleep(600);

        StringSelection selection = new StringSelection(fileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//div/div/span/b")).get(0))
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

        driver.findElements(By.xpath("//button[contains(text(),'Upload File')]")).get(0).click();

        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'save()')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(@ng-click,'save()')]")).get(0).click();

        Thread.sleep(200);
    }

    public void ImportUser(NDM_Core.ImportType.Type importType, String fileName) throws InterruptedException, AWTException
    {
        Thread.sleep(400);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/span[contains(@class,'ts-icon-import-contact')]")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }

        Thread.sleep(200);

        try
        {
            driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-import-contact')]")).get(0).click();
        }
        catch(Exception ex)
        {
            Thread.sleep(4000);

            driver.findElements(By.xpath("//div/span[contains(@class,'ts-icon-import-contact')]")).get(0).click();
        }

        Thread.sleep(200);

        try
        {
            WebDriverWait wait1 = new WebDriverWait(driver, 150);
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/button[@ng-click='model.customButtonHandler(save)']")));
        }
        catch (Exception exx)
        {
            Thread.sleep(5000);
        }



        if (importType == NDM_Core.ImportType.Type.Auto)
        {
            Thread.sleep(200);

            driver.findElements(By.xpath("//div[@class='radiobutton-container']/input[contains(@ng-value,'Auto')]/..")).get(0).click();
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/button[@ng-click='model.customButtonHandler(save)']")).get(0).click();

        // "//div/div/span/b"     - click for load file !!!

        //driver.findElements(By.xpath("//div/div/div/span[contains(@class,'dd-header-text')]/i/..")).get(0).click();

        Thread.sleep(600);

        StringSelection selection = new StringSelection(fileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Actions builder = new Actions(driver);
        Action myAction = builder.click(driver.findElements(By.xpath("//div/div/span/b")).get(0))
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

        driver.findElements(By.xpath("//button[contains(text(),'Upload File')]")).get(0).click();

        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//button[contains(@ng-click,'save()')]")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(400);

        driver.findElements(By.xpath("//button[contains(@ng-click,'save()')]")).get(0).click();

        Thread.sleep(200);
    }
}
