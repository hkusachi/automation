package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by 1 on 4/20/2016.
 * Updated 8/18/2017
 */
public class NDM_UserLoginPage extends NDM_Page
{
    @FindBy(xpath = "//div[@id='user_details']/input[2]")
    private WebElement passwordElement;

    @FindBy(xpath = "//div[@id='user_details']/input[1]")
    private WebElement userNameElement;

    @FindBy(xpath = "//div[@ui-sref='main.devices']")
    private WebElement waitElement;

    @FindBy(xpath = "//div[@id='login-btn-container']/div")
    private WebElement loginElement;

    public NDM_UserLoginPage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;

        PageFactory.initElements(driver, this);
    }

    private void setUserName(String strUserName) throws InterruptedException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(userNameElement));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        userNameElement.sendKeys(strUserName);

        Thread.sleep(50);
    }

    private void setPassword(String strPassword) throws InterruptedException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(passwordElement));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        passwordElement.sendKeys(strPassword);

        Thread.sleep(50);
    }

    private void clickLogin() throws InterruptedException
    {
        Thread.sleep(100);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(loginElement));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        loginElement.click();

        Thread.sleep(50);
    }

    public NDM_HomePage login(String strUserName,String strPasword) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);

            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@id='user_details']/input[@ng-model='ctrl.log_username']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        this.setUserName(strUserName);
        this.setPassword(strPasword);
        this.clickLogin();

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);

            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@ng-controller='DeviceViewController as deviceview']")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(50);

        return new NDM_HomePage(driver, pageUrl);
    }
}