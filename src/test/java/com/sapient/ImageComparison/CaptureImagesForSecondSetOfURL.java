package com.sapient.ImageComparison;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sapient.utility.*;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CaptureImagesForSecondSetOfURL {
	public static int Z = 1;
	public static int pos = 1;
	public static int rtw = 0;
	public static int ctr = 1;
	private static int DISPLAY_NUMBER = 99;
	private static String XVFB = "/usr/bin/Xvfb";
	private static String XVFB_COMMAND = XVFB + " :" + DISPLAY_NUMBER;
	//private static String URL = "http://www.google.com/";
	//private static String RESULT_FILENAME = "D:/Screenshot/screenshot.png";
	public static String NEWRESULT_FILENAME;
	static WebDriver driver;

	static String currentDir = System.getProperty("user.dir");

	@Test
	public void captureImageForSecondSet() throws IOException, InterruptedException {
		List<String> myStringExcelArray;
		myStringExcelArray = ReadExcel.readXLSFile(2);
		int len = myStringExcelArray.size();
		int lentemp = len;
		int i = 0;

		while (len > 0) {
			NEWRESULT_FILENAME = currentDir + "\\src\\test\\resources\\screenshots\\SecondSetOfURL" + "/"  + "screenshot" + Z + ".png";
			List<String> test = myStringExcelArray;
    		System.setProperty("webdriver.gecko.driver", currentDir +"/src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			try {
				String tempUrl = test.get(i);

				driver.get(tempUrl);
				Thread.sleep(10000);

				System.out.println("url" + " " + i + " " + "processed out of " + lentemp);
		            }
			catch (Exception e) {
				System.out.println("Unnhandled exception Error");
			}

			Thread.sleep(4000);
			Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(fpScreenshot.getImage(), "png", new File(NEWRESULT_FILENAME));
			driver.close();
			
			ReadExcel.WriteXLSFile(NEWRESULT_FILENAME, rtw, 3);

			pos++;
			rtw++;
			// p.destroy();
			len--;
			i++;
			Z++;
		}

	}
}
