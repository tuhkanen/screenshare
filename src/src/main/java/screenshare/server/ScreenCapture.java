package screenshare.server;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ScreenCapture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long captureInterval = 2000;
		int x = 500, y=500, width = 500, height = 500;
		String outputFilename = "screenshot";
		if (args.length > 0) {
			captureInterval = Long.parseLong(args[0]);
			if (captureInterval < 1000) {
				captureInterval = 1000;
			}
		} else {
			captureInterval = 2000;
		}
		if (args.length == 6) {
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
			width = Integer.parseInt(args[3]);
			height = Integer.parseInt(args[4]);
			outputFilename = args[5];
		}
		
		System.out.println("Capture interval: " + captureInterval);
		while( true ) {
			try{
				Rectangle areaToCapture = new Rectangle();
				areaToCapture.x = x;
				areaToCapture.y = y;
				areaToCapture.width = width;
				areaToCapture.height = height;
				//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				BufferedImage image = new Robot().createScreenCapture(areaToCapture);
				ImageIO.write(image, "png", new File(outputFilename + ".png"));
				System.out.print(".");
				Thread.sleep(captureInterval);
			}
			catch (Exception e) {
				System.out.println("Could not create filed, skipped.");
			}
		}
	}
}
