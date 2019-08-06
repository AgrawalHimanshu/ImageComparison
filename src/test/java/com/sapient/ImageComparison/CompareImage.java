package com.sapient.ImageComparison;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.testng.annotations.Test;

import com.sapient.utility.*;

public class CompareImage {
	static int firstvar = 0;

	static String currentDir = System.getProperty("user.dir");
	public static String NEWRESULT_FILENAME;

	@Test
	public void compareImage() throws IOException {
		List<String> myStringExcelArray1;
		List<String> myStringExcelArray2;
		
		// ReadExcel rx= new ReadExcel();
		myStringExcelArray1 = ReadExcel.readXLSFileSpeCol(1);
		myStringExcelArray2 = ReadExcel.readXLSFileSpeCol(3);
		int newlen = myStringExcelArray1.size();
		System.out.println(newlen);
		while (newlen > 0) {
			String file1 = myStringExcelArray1.get(firstvar);
			String file2 = myStringExcelArray2.get(firstvar);

			Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(file2);
			
			

			try {
				PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
				PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);

				int[] data1 = null;

				if (grab1.grabPixels()) {
					int width = grab1.getWidth();
					int height = grab1.getHeight();
					data1 = new int[width * height];
					data1 = (int[]) grab1.getPixels();
				}

				int[] data2 = null;

				if (grab2.grabPixels()) {
					int width = grab2.getWidth();
					int height = grab2.getHeight();
					data2 = new int[width * height];
					data2 = (int[]) grab2.getPixels();
				}

				System.out.println("Pixels equal: " + java.util.Arrays.equals(data1, data2));
				NEWRESULT_FILENAME = currentDir + "\\src\\test\\resources\\screenshots\\Difference" + "/"  + "screenshot" + firstvar+1 + ".png";

				//if(!java.util.Arrays.equals(data1, data2))
					ImageIO.write(
					        getDifferenceImage(
					                ImageIO.read(new File(file1)),
					                ImageIO.read(new File(file2))),
					        "png", new File(NEWRESULT_FILENAME));
					

			} catch (InterruptedException e1) {
				e1.printStackTrace();	
			}
			newlen--;
			firstvar++;
		}
	}
	
	public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
	    // convert images to pixel arrays...
	    final int w = img1.getWidth(),
	            h = img1.getHeight(), 
	            highlight = Color.MAGENTA.getRGB();
	    final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
	    final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
	    // compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
	    for (int i = 0; i < p1.length; i++) {
	        if (p1[i] != p2[i]) {
	            p1[i] = highlight;
	        }
	    }
	    // save img1's pixels to a new BufferedImage, and return it...
	    // (May require TYPE_INT_ARGB)
	    final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    out.setRGB(0, 0, w, h, p1, 0, w);
	    return out;
	}
	
	 


}