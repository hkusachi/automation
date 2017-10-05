package Pages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static Pages.NDM_Core.captureScreen;

/**
 * Created by 1 on 10/24/2016.
 * Updated 9/29/2017
 */
class NDM_PrintScrean extends Thread
{
    public void run()
    {
        String devicesPath = "screenshots\\log";

        int i = 0;

        while (true)
        {
            try
            {
                captureScreen(devicesPath +"\\" + "log_" + i++%logPic + ".png");
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
    public int logPic = 20;
}

public class NDM_Core
{
    private NDM_PrintScrean nDMPrintScrean;

    public void runScrinshots(int waitTimeout, int logPic)
    {
        nDMPrintScrean = new NDM_PrintScrean();
        nDMPrintScrean.waitTimeout = waitTimeout;
        nDMPrintScrean.logPic = logPic;
        nDMPrintScrean.start();
    }

    public void stopScrinshots()
    {
        nDMPrintScrean.stop();
    }

    public static class Restart
    {
        public enum Type
        {
            TypeDevice,
            TypeNetwork
        }
    }

    public static class GroupType
    {
        public enum Type
        {
            Email,
            FTP
        }
    }

    public static class ImportType
    {
        public enum Type
        {
            Manual,
            Auto
        }
    }

    public static void captureScreen(String fileName) throws Exception
    {
        Robot robot = new Robot();
        Rectangle screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write( image, "png", new File(fileName));
    }
}
