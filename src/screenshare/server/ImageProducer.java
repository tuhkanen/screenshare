
/*
 * Producer
 * 
 * Adds images to shared container on defined interval.
 */

package screenshare.server;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.util.concurrent.SynchronousQueue;

import javax.swing.ImageIcon;

import screenshare.common.Parameters;

public class ImageProducer implements Runnable {

	private SynchronousQueue<ImageIcon> capturedImages;
	private Parameters param;
	
	public ImageProducer(SynchronousQueue<ImageIcon> capturedImages, Parameters param) {
		this.capturedImages = capturedImages;
		this.param = param;
	}
	
	public void run() {
		BufferedImage image;
		try {
			while( true ) {
				// Only produce more images if the current queue does not hava 20 images
				if( this.capturedImages.size() < 20) {
					UtilTimer.start( "CaptureImage" );
					image = ScreenCaptureHelper.capture(param.x, param.y, param.width, param.height);
					System.out.println( "Time to capture image: " + UtilTimer.stop( "CaptureImage") );
					ImageIcon icon = new ImageIcon(image);
					
					this.capturedImages.put( icon );
					Thread.sleep( param.delay );
				}
				Thread.sleep( param.delay );
			}
		} catch ( InterruptedException e) {
			System.out.println("Could not get the image");
			e.printStackTrace();
		} catch( AWTException e ){
			System.out.println("Could not get the image");
			e.printStackTrace();
		}
		
	}


}
