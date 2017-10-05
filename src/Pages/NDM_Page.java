package Pages;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.jetty9.http.HttpStatus.Code;

/**
 * Created by 1 on 4/22/2016.
 */
public abstract class NDM_Page
{
    protected WebDriver driver;
    protected String pageUrl;
    protected Core.Version version;
}
