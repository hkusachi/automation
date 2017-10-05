package PageObject;

import Pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class NDM_Login
{
    private WebDriver driver;
    private String pageUrl = "localhost:9191";
    //private String pageUrl = "http://192.168.50.123:9191";

    @Before
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(pageUrl);
    }

    @Test
    public void testPage() throws InterruptedException
    {
        NDM_UserLoginPage login = new NDM_UserLoginPage(driver, pageUrl);
        NDM_HomePage home = login.login("admin", "admin");
        System.out.println("Login [OK]");

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}