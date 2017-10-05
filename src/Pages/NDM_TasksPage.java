package Pages;

//import org.ini4j.Registry;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by 1 on 4/20/2016.
 * Updated 7/28/2017
 * Updated 8/18/2017
 * Updated 9/1/2017
 */

public class NDM_TasksPage extends NDM_Page
{
    private By tasksPageSheduled = By.xpath("//div[@ui-sref='main.devices.tasks.scheduled']");
    private By tasksPageCompleted = By.xpath("//div[contains(@href,'#/main/tasks/completed')]");

    public NDM_TasksPage(WebDriver driver, String pageUrl)
    {
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public void GoToTasksPageSheduled() throws InterruptedException
    {
        Thread.sleep(100);

        driver.findElements(tasksPageSheduled).get(0).click();

        Thread.sleep(3000);
    }

    public void GoToTasksPageCompleted() throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(tasksPageCompleted).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(tasksPageCompleted).get(0).click();

        Thread.sleep(200);
    }

    public void FilterTasksByName(String splitedString) throws InterruptedException
    {
        Thread.sleep(200);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div/ul/input")).get(0)));
        }
        catch(Exception ex)
        {
            Thread.sleep(6000);
        }

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/ul/input")).get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/ul/input")).get(0).sendKeys(Keys.BACK_SPACE);

        Thread.sleep(200);

        String[] str = splitedString.split(" ");

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/ul/input")).get(0).sendKeys(str[str.length - 1]);

        Thread.sleep(200);
    }

    public Boolean RefreshResult(int minutesWait, int intervalSecond, String state) throws InterruptedException
    {
        Thread.sleep(400);
        long StartUnixTime = System.currentTimeMillis() / 1000L;
        String status = "";

        Boolean firstTimeStatus = true;

        while(StartUnixTime + minutesWait * 60L > System.currentTimeMillis() / 1000L)
        {
            if (firstTimeStatus)
            {
                Thread.sleep(400);

                firstTimeStatus = false;
            }
            else
            {
                Thread.sleep(10 /*second*/ * 1000);
            }

            driver.findElements(By.xpath("//div/ul/input")).get(0).sendKeys(Keys.ENTER);

            Thread.sleep(600);

            try
            {
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                WebDriverWait wait = new WebDriverWait(driver, 2);
                wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div/div/div[12]/div/div[2]")).get(0)));
            }
            catch(Exception ex)
            {
                Thread.sleep(200);

                driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                continue;
            }

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            status = driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div/div/div[12]/div/div[2]")).get(0).getAttribute("innerHTML");

            if (status != "")
            {
                if (status.equals(state))
                {
                    return true;
                }
                return false;
            }

            return false;
        }

        return false;
    }

    public void RemoveFilter() throws InterruptedException
    {
        Thread.sleep(200);

        driver.findElements(By.xpath("//div/ul/input")).get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));

        Thread.sleep(200);

        driver.findElements(By.xpath("//div/ul/input")).get(0).sendKeys(Keys.BACK_SPACE);

        Thread.sleep(200);

        try
        {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            driver.findElements(By.xpath("//div/div/div/span[contains(@ng-click,'delete')]")).get(0).click();
        }
        catch(Exception ex)
        {

        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        Thread.sleep(200);
    }
}
