
/*
 * Producer
 * 
 * Adds images to shared container on defined interval.
 */

package screenshare.server;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

import javax.swing.ImageIcon;

import screenshare.common.ImageWrapper;
import screenshare.common.Parameters;

public class ImageProducer implements Runnable {

	private volatile boolean keepProducing = false;
	
	private ConcurrentLinkedQueue<ImageWrapper> capturedImages;
	private Parameters param;
	
	public ImageProducer(ConcurrentLinkedQueue<ImageWrapper> capturedImages, Parameters param) {
		this.capturedImages = capturedImages;
		this.param = param;
	}
	
	public void run() {
		this.keepProducing = true;
		BufferedImage image;
		try {
			while( this.keepProducing ) {
				// Only produce more images if the current queue does not hava 20 images
				if( this.capturedImages.size() < 20) {
					UtilTimer.start( "CaptureImage" );
					image = ScreenCaptureHelper.capture(param.x, param.y, param.width, param.height);
					//System.out.println( "Time to capture image: " + UtilTimer.stop( "CaptureImage") );
					//ImageIcon icon = new ImageIcon(image);
					//System.out.println("capture, keep producing: " + this.keepProducing);
					this.capturedImages.add( new ImageWrapper( image ) );
					//System.out.println("size: " + this.capturedImages.size() + " sleeping");
					Thread.sleep( param.delay );
				}
				Thread.sleep( param.delay );
				//keepProducing = false;
			}
		} catch ( InterruptedException e) {
			System.out.println("Interrupted...");
			e.printStackTrace();
		} catch( AWTException e ){
			System.out.println("Could not get the image");
			e.printStackTrace();
		}
		//System.out.println("Producer Stopped");
	}

	public void stop() {
		//System.out.println("Stop called");
		this.keepProducing = false;
	}
}
