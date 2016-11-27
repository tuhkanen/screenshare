package screenshare.server;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ScreenCaptureHelper {
	public static BufferedImage capture(int x, int y, int width, int height) throws AWTException {
		Rectangle areaToCapture = new Rectangle();
		areaToCapture.x = x;
		areaToCapture.y = y;
		areaToCapture.width = width;
		areaToCapture.height = height;
		//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		BufferedImage image = new Robot().createScreenCapture(areaToCapture);
		return image;
	}
}
