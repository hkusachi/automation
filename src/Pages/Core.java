package Pages;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by 1 on 4/22/2016.
 */

class PrintScrean extends Thread
{
    public void run()
    {
        String devicesPath = "screenshots\\log";

        int i = 0;

        while (true)
        {
            try
            {
                Core.captureScreen(devicesPath +"\\" + "log_" + i++%logPic + ".png" , webDriver);
                Thread.sleep(waitTimeout);
            }
            catch (Exception e)
            {
                try
                {
                    Thread.sleep(waitTimeout);
                } catch (InterruptedException e1)
                {

                }
                e.printStackTrace();
            }
        }
    }

    public int waitTimeout = 500;
    public WebDriver webDriver;
    public int logPic = 10;
}

public class Core
{
    public void runScrinshots(int waitTimeout, int logPic)
    {
        printScrean = new PrintScrean();
        printScrean.waitTimeout = waitTimeout;
        printScrean.webDriver = realDriver;
        printScrean.logPic = logPic;
        printScrean.start();
    }

    public void stopScrinshots()
    {
        printScrean.stop();
    }

    private PrintScrean printScrean;
    private WebDriver realDriver;
    private File myTempDir;

    public File getTempDir()
    {
        return myTempDir;
    }

    public void quitWebDriver()
    {
        realDriver.quit();
    }

    public WebDriver getDriver()
    {
        return realDriver;
    }

    public void SetDriver(WebDriver driver) { realDriver = driver; }

    public void createWebDriver(String needSave)
    {
        FirefoxProfile fxProfile;

        if (!needSave.isEmpty())
        {
            myTempDir = Files.createTempDir();

            fxProfile = new FirefoxProfile();

            fxProfile.setPreference("browser.download.folderList",2);
            fxProfile.setPreference("browser.download.manager.showWhenStarting",false);
            fxProfile.setPreference("browser.download.dir",myTempDir.toString());
            fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/zip");

            realDriver = new FirefoxDriver(fxProfile);
        }
        else
        {
            realDriver = new FirefoxDriver();
        }

        realDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void getPage(String page)
    {
        realDriver.get(page);
    }

    public void deletDir()
    {
        deleteDirectory(myTempDir);
    }

    public enum KFS_VERSION
    {
        VER_1_1,
        VER_1_2
    }

    public enum SUB_VERSION
    {
        INTEG15,
        INTEG16
    }

    public static class Version
    {
        public Version(KFS_VERSION kfs_version, SUB_VERSION sub_version)
        {
            this.kfs_version = kfs_version;
            this.sub_versions = sub_version;
        }

        public KFS_VERSION kfs_version;
        public SUB_VERSION sub_versions;
    }

    public static String getCurrentTimeStamp(int min) {
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        Date now = new Date(System.currentTimeMillis()+min*60*1000);
        return sdfTime.format(now);
    }

    public static void extractFolder(String zipFile,String extractFolder)
    {
        try
        {
            int BUFFER = 2048;
            File file = new File(zipFile);

            ZipFile zip = new ZipFile(file);
            String newPath = extractFolder;

            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements())
            {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();

                File destFile = new File(newPath, currentEntry);
                //destFile = new File(newPath, destFile.getName());
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                if (!entry.isDirectory())
                {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }
            }
        }
        catch (Exception e)
        {
            //Log("ERROR: "+e.getMessage());
        }

    }

    public static boolean deleteDirectory(File directory)
    {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    public static void captureScreen(String fileName, WebDriver driver) throws Exception
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension driverScreen = driver.manage().window().getSize();
        org.openqa.selenium.Point point= driver.manage().window().getPosition();
        screenSize.width = driverScreen.width;
        screenSize.height = driverScreen.height;
        Rectangle screenRectangle = new Rectangle(screenSize);
        screenRectangle.x = point.x;
        screenRectangle.y =  point.y;
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write( image, "png", new File(fileName));
    }

    /*color lib*/
    public static String colorString(String msg)
    {
        return colorString(TEXT_COLOR.Red) + "[" + colorString(TEXT_COLOR.Green) + msg
                + colorString(TEXT_COLOR.Red) + "]" + colorString();
    }

    public static String colorString(TEXT_COLOR textColor)
    {
        return (char)27 + "[" + textColor.getValue() + "m";
    }

    public static String colorString(TEXT_COLOR textColor, BACKGROUND_COLOR backgroundColor)
    {
        return (char)27 + "[" + textColor.getValue() + ";" + backgroundColor.getValue() + "m";
    }

    public static String colorString()
    {
        return (char)27 + "[0m";
    }

    public enum TEXT_COLOR
    {
        Black(30),
        Red(31),
        Green(32),
        Yellow(33),
        Blue(34),
        Magenta(35),
        Cyan(36),
        White(37);

        private final int id;
        TEXT_COLOR(int id) { this.id = id; }
        public int getValue() { return id; }
    }

    public enum BACKGROUND_COLOR
    {
        Black(40),
        Red(41),
        Green(42),
        Yellow(43),
        Blue(44),
        Magenta(45),
        Cyan(46),
        White(47);

        private final int id;
        BACKGROUND_COLOR(int id) { this.id = id; }
        public int getValue() { return id; }
    }
    /*color lib*/
}
